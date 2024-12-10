package model.interfacesClases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
    private final String titleColumnName = "Identifier"; // Este es el nombre de la columna que se utilizará 
                                                        // como clave primaria única de type Title en Notion

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
            throw new RepositoryException("Error al intentar verificar la existencia de la tarea: " + e.getMessage(), e);
        }
        return null;
    }

    private Task mapPageToTask(String pageId, Map<String, PageProperty> properties) throws RepositoryException {
        // Extraemos las propiedades de la página y las mapeamos a un objeto Task
        int identifier = 0;
        String title = null;
        String content = null;
        String dateString = null;
        int priority = 0;
        int estimatedDuration = 0;
        boolean completed = false;
    
        // Obtenemos los datos
        try {
            String identifierValue = properties.get("Identifier").getTitle().get(0).getText().getContent();
            identifier = Integer.parseInt(identifierValue);
            title = properties.get("TaskTitle").getRichText().get(0).getText().getContent();
            content = properties.get("Content").getRichText().get(0).getText().getContent();
            dateString = properties.get("Date").getDate().getStart();
            priority = properties.get("Priority").getNumber().intValue();
            estimatedDuration = properties.get("Estimated Duration").getNumber().intValue();
            completed = properties.get("Completed").getCheckbox();
        } catch (Exception e) {
            throw new RepositoryException("Error al mapear los datos de la tarea: " + e.getMessage(), e);
        }
        // Crear y devolver el objeto Task
        return new Task(identifier, title, dateString, content, priority, estimatedDuration, completed);
    }
    
    

    @Override
    public void addTask(Task task) throws RepositoryException {

        if (existsTask(task.getIdentifier()) != null) {
            throw new RepositoryException("Ya existe una tarea con el Identifier: " + task.getIdentifier());
        }
    
        // Si no existe, creamos una nueva página en Notion
        System.out.println("Creando una nueva página en Notion...");
    
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
            Page response = client.createPage(request);
            System.out.println("Página creada con ID (interno Notion): " + response.getId());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTask'");
    }

    @Override
    public void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyTask'");
    }

    @Override
    public ArrayList<Task> getAllTask() throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTask'");
    }

    @Override
    public void saveData() throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }

    @Override
    public ArrayList<Task> getUncompletedTasks() throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUncompletedTasks'");
    }
    
}
