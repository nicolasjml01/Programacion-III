package model.interfacesClases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Task;
import model.exceptions.RepositoryException;
import notion.api.v1.NotionClient;
import notion.api.v1.http.OkHttp5Client;
import notion.api.v1.logging.Slf4jLogger;
import notion.api.v1.model.databases.QueryResults;
import notion.api.v1.model.pages.Page;
import notion.api.v1.model.pages.PageParent;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.pages.PageProperty.RichText;
import notion.api.v1.model.pages.PageProperty.RichText.Text;
import notion.api.v1.request.databases.QueryDatabaseRequest;
import notion.api.v1.request.pages.CreatePageRequest;
import notion.api.v1.request.pages.UpdatePageRequest;

public class NotionRepository implements IRepository{
    private final NotionClient client;
    private final String databaseId;

    // Constructor para recibir apiToken y databaseId
    public NotionRepository(String apiToken, String databaseId) {
        // Crear cliente de Notion
        this.client = new NotionClient(apiToken);

        // Configurar cliente HTTP adecuado y tiempos de espera
        client.setHttpClient(new OkHttp5Client(60000,60000,60000));

        // Configurar loggers
        client.setLogger(new Slf4jLogger());
        
        // Silenciar/Activar los registros de log de Notion API
        // Ver en nivel debug los mensajes de depuración
        System.setProperty("notion.api.v1.logging.StdoutLogger", "debug");

        // Nivel más alto de log para NO ver mensajes de depuración
        //System.setProperty("notion.api.v1.logging.StdoutLogger", "off");

        this.databaseId = databaseId;
    }

    @Override
    public void loadData() throws RepositoryException {
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            client.queryDatabase(queryRequest);
        } catch (Exception e) {
            throw new RepositoryException("Error al intentar conectar con Notion: " + e.getMessage());
        }
    }
    
    @Override
    public Task existsTask(int id) throws RepositoryException {
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);
    
            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                String identifierValue = properties.get("Identifier").getTitle().get(0).getText().getContent();
                
                if (Integer.parseInt(identifierValue) == id) {
                    Task existingTask = mapPageToTask(page.getId(), properties);
                    return existingTask;
                }
            }
        } catch (Exception e) {
            throw new RepositoryException("No existe la tarea, se puede proceder a añadir una nueva: " + e.getMessage(), e);
        }
        return null;
    }

    // Obtenemos la informacion de la tarea a obtener
    private Task mapPageToTask(String pageId, Map<String, PageProperty> properties) throws RepositoryException {    
        // Obtenemos los datos
        try {
            String identifierValue = properties.get("Identifier").getTitle().get(0).getText().getContent();
            int identifier = Integer.parseInt(identifierValue);
            String title = properties.get("TaskTitle").getRichText().get(0).getText().getContent();
            String content = properties.get("Content").getRichText().get(0).getText().getContent();
            String dateString = properties.get("Date").getDate().getStart();
            int priority = properties.get("Priority").getNumber().intValue();
            int estimatedDuration = properties.get("Estimated Duration").getNumber().intValue();
            boolean completed = properties.get("Completed").getCheckbox();
            // Crear y devolver el objeto Task
            return new Task(identifier, title, dateString, content, priority, estimatedDuration, completed);
        } catch (Exception e) {
            throw new RepositoryException("Error al obtener los datos de la tarea: " + e.getMessage(), e);
        }
    }
    
    
    @Override
    public void addTask(Task task) throws RepositoryException {

        if (existsTask(task.getIdentifier()) != null) {
            throw new RepositoryException("Ya existe una tarea con el Identifier: " + task.getIdentifier());
        }    
        // Configurar las propiedades de la pagina si no existe
        Map<String, PageProperty> properties = Map.of(
                "Identifier", createTitleProperty(String.valueOf(task.getIdentifier())),
                "TaskTitle", createRichTextProperty(task.getTitle()),  
                "Date", createDateProperty(task.getDate()), 
                "Content", createRichTextProperty(task.getContent()),
                "Priority", createNumberProperty(task.getPriority()), 
                "Estimated Duration", createNumberProperty(task.getEstimatedDuration()),
                "Completed", createCheckboxProperty(task.isCompleted())
        );
    
        // Cargamos la base de datos y creamos la solicitud para la API
        PageParent parent = PageParent.database(databaseId);
        CreatePageRequest request = new CreatePageRequest(parent, properties);
    
        try {
            // Ejecutar la solicitud para crear la página
            client.createPage(request);
        } catch (Exception e) {
            throw new RepositoryException("Error al añadir la tarea en Notion: " + e.getMessage(), e);
        }
    }
    
    // Metodos para crear las propiedades de la pagina
    private PageProperty createTitleProperty(String title) {
        RichText titleText = new RichText();
        titleText.setText(new Text(title));
        PageProperty titleProperty = new PageProperty();
        titleProperty.setTitle(Collections.singletonList(titleText));
        return titleProperty;
    }
    

    private PageProperty createRichTextProperty(String text) {
        RichText richText = new RichText();
        richText.setText(new Text(text));
        PageProperty property = new PageProperty();
        property.setRichText(Collections.singletonList(richText));
        return property;
    }
    
    private PageProperty createDateProperty(LocalDate date) {
        PageProperty property = new PageProperty();
        PageProperty.Date dateProperty = new PageProperty.Date();
        String formattedDate = date.toString();
        dateProperty.setStart(formattedDate);
        property.setDate(dateProperty);
        return property;
    }    

    private PageProperty createNumberProperty(int number) {
        PageProperty property = new PageProperty();
        property.setNumber(number);
        return property;
    }
    
    private PageProperty createCheckboxProperty(boolean completed) {
        PageProperty property = new PageProperty();
        property.setCheckbox(completed);
        return property;
    }
    
    @Override
    public void removeTask(int id) throws RepositoryException {
        try 
        {
            // Encontrar el ID real de la página en Notion
            String pageId = findPageIdByIdentifier(id);
            if (pageId == null) {
                throw new RepositoryException("No se encontró ninguna tarea con el identificador: " + id);
            }

            // Creamos la solicitud para archivar la página
            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, Collections.emptyMap(), true);
    
            // Ejecutamos la solicitud
            client.updatePage(updateRequest);    
        } catch (Exception e) {
            throw new RepositoryException("Error al eliminar la tarea con ID: " + id);
        }
    }
    
    // Buscamos en Notion el pageID correspondiente a mi identificador
    private String findPageIdByIdentifier(int identifier) throws RepositoryException {
        try
        {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);

            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                String identifierValue = properties.get("Identifier").getTitle().get(0).getText().getContent();
    
                // Comparamos el identificador lógico
                if (Integer.parseInt(identifierValue) == identifier) {
                    return page.getId(); // Retornamos el ID
                }
            }
        } catch (Exception e) {
            throw new RepositoryException("Error al buscar la página por identificador: " + identifier, e);
        }
        return null;
    }
    
    
    @Override
    public void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        try {
            // Encontrar el Id real de Notion usando el identificador
            String pageId = findPageIdByIdentifier(id);
            if (pageId == null) {
                throw new RepositoryException("No se encontró ninguna tarea con el identificador: " + id);
            }

            // Mapa para las propiedades actualizadas, solo incluimos las propiedades que se nos pasan
            Map<String, PageProperty> updatedProperties = new HashMap<>();

            // Comprobar si se quiere actualizar todo o solo el estado
            if (title != null) updatedProperties.put("TaskTitle", createRichTextProperty(title));
            if (date != null) {
                LocalDate localDate = LocalDate.parse(date);
                updatedProperties.put("Date", createDateProperty(localDate));
            }
            if (content != null && !content.isEmpty()) updatedProperties.put("Content", createRichTextProperty(content));
            if (priority != -1) updatedProperties.put("Priority", createNumberProperty(priority));
            if (estimatedDuration != -1) updatedProperties.put("Estimated Duration", createNumberProperty(estimatedDuration));
            updatedProperties.put("Completed", createCheckboxProperty(completed));

            if (updatedProperties.isEmpty()) {
                throw new RepositoryException("No se proporcionaron valores válidos para actualizar.");
            }

            // Crear la solicitud de actualización
            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, updatedProperties);
            client.updatePage(updateRequest);    
        } catch (Exception e) {
            throw new RepositoryException("Error al actualizar la tarea con ID: " + id, e);
        }
    }

    @Override
    public ArrayList<Task> getAllTask() throws RepositoryException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);
    
            // Procesamos lso resultados
            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                Task task = mapPageToTask(page.getId(), properties);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            throw new RepositoryException("Error al obtener todas las tareas: " + e.getMessage(), e);
        }
        return tasks;
    }    

    @Override
    public ArrayList<Task> getUncompletedTasks() throws RepositoryException {
        ArrayList<Task> uncompletedTasks = new ArrayList<>();
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);
    
            // Procesamos los resultados
            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                Task task = mapPageToTask(page.getId(), properties);
    
                // Agregamos la tarea solo si no está completada
                if (task != null && !task.isCompleted()) {
                    uncompletedTasks.add(task);
                }
            }

            // Ordenamos la lista por prioridad
            Collections.sort(uncompletedTasks, (task1, task2) -> Integer.compare(task2.getPriority(), task1.getPriority()));

        } catch (Exception e) {
            throw new RepositoryException("No hay tareas sin completar: " + e.getMessage(), e);
        }
        return uncompletedTasks;
    }
    
    // Nos desconectamos del cliente
    @Override
    public void saveData() throws RepositoryException {
        try {
            client.close();
        } catch (Exception e) {
            throw new RepositoryException("Error al cerrar la conexión: " + e.getMessage(), e);
        }
    }
    
}
