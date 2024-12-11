package view;

import java.util.List;
import com.coti.tools.Esdia;
import model.Task;
import model.exceptions.ExporterException;
import model.exceptions.RepositoryException;

public class InteractiveView extends BaseView{

    @Override
    public void init() throws RepositoryException, ExporterException 
    {
        System.out.println("Bienvenido a la aplicación de gestión de tareas");
        controller.loadData();
        showMainMenu();
    }

    @Override
    public void showMessage(String msg) 
    {
        System.out.println(msg);
    }

    @Override
    public void showErrorMessage(String msg) 
    {
        System.out.println(msg);
    }

    public void showMainMenu() throws RepositoryException, ExporterException
    {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            showMenuOptions();
            int opcion = Esdia.readInt("Introduzca una opcion: ");
            switch(opcion)
            {
                case 1:  
                    showCRUDMenu();                       
                    break;
                case 2:
                    showExportImportMenu();
                    break;
                case 3:
                    exit = end();
                    break;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }
    private void showMenuOptions()
    {
        System.out.println("Menu");
        System.out.println("1. Menu CRUD");
        System.out.println("2. Menu Exportar/Importar");
        System.out.println("3. Finalizar Aplicacion");
    }

    // CRUD Options
    private void showCRUDMenu() throws RepositoryException
    {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            showCRUDOptions();
            int opcion = Esdia.readInt("Introduzca una opcion: ");
            switch(opcion)
            {
                case 1:  
                    createTask();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    modifyTaskStatus();
                    break;
                case 4:
                    modifyTask();
                    break;
                case 5:
                    getAllTask();
                    break;
                case 6:
                    ShowUncompletedTasks();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }

    private void showCRUDOptions()
    {
        System.out.println("\n--- Menu CRUD ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Eliminar tarea");
        System.out.println("3. Modificar estado de la tarea");
        System.out.println("4. Modificar Tarea completa");
        System.out.println("5. Ver listado de tareas");
        System.out.println("6. Ver listado de tareas sin completar ordenadas por prioridad");
        System.out.println("7. Volver al menú principal");
    }

    private void createTask() throws RepositoryException
    {
        System.out.println("\n--- Crear Tarea ---");
        int identifier = Esdia.readInt("Ingrese el ID de la tarea: ");
        String title = Esdia.readString("Ingrese el titulo de la tarea: ");
        String date = Esdia.readString("Introduce la fecha de la tarea (yyyy-mm-dd): ");
        String content = Esdia.readString("Ingrese la descripcion de la tarea: ");
        int priority = Esdia.readInt("Introduzca la prioridad de la tarea (Entre 1 y 5)", 1, 5);
        int estimatedDuration = Esdia.readInt("Introduzca la duracion de la tarea (en minutos): ");
        boolean completed = Esdia.yesOrNo("¿Esta la tarea completada? (y/n): ");

        controller.addTask(new Task(identifier, title, date, content, priority, estimatedDuration, completed));
    }

    private void deleteTask() throws RepositoryException
    {
        int id = Esdia.readInt("Introduzca el identificador de la tarea a eliminar: ");
        // Obtener la tarea si existe
        Task task = controller.existsTask(id);

        // Si la tarea existe, mostrar los detalles y permitir la modificación
        if (task != null) {
            showTask(task);
            boolean result = Esdia.yesOrNo("¿Seguro que desea eliminar esta tarea? (y/n): ");
            if (result)
            {
                controller.removeTask(id);                
            }else System.out.println("Volviendo al menu CRUD....");
        } else {
            showErrorMessage("No existe la tarea con el identificador: " + id);
        }
    }

    private void modifyTaskStatus() throws RepositoryException {
        int id = Esdia.readInt("Introduzca el identificador de la tarea a la cual modificar su estado: ");
        
        // Obtener la tarea si existe
        Task task = controller.existsTask(id);
        
        // Si la tarea existe, mostrar los detalles y permitir la modificación
        if (task != null) {
            showTask(task);   
            boolean completed = Esdia.yesOrNo("¿Está la tarea completada? (y/n): ");
            controller.modifyTask(id, null, null, null, -1, -1, completed);
        } else showErrorMessage("No existe la tarea con el identificador: " + id);
    }
             
    private void modifyTask() throws RepositoryException
    {
        int id = Esdia.readInt("Introduzca el identificador de la tarea a modificar: ");

        // Obtener la tarea si existe
        Task task = controller.existsTask(id);
        
        // Si la tarea existe, mostrar los detalles y permitir la modificación
        if (task != null) {
            showTask(task);
            
            String title = Esdia.readString("Ingrese el titulo de la tarea: ");
            String date = Esdia.readString("Introduce la fecha de la tarea (yyyy-mm-dd): ");
            String content = Esdia.readString("Ingrese la descripcion de la tarea: ");
            int priority = Esdia.readInt("Introduzca la prioridad de la tarea (Entre 1 y 5)", 1, 5);
            int estimatedDuration = Esdia.readInt("Introduzca la duracion de la tarea (en minutos): ");
            boolean completed = Esdia.yesOrNo("¿Esta la tarea completada? (y/n): ");
            controller.modifyTask(id,title, date, content, priority, estimatedDuration, completed);
            
        } else showErrorMessage("No existe la tarea con el identificador: " + id);
        
    }

    private void showTask(Task task) {
        // Mostrar detalles de la tarea
        System.out.println("Tarea Seleccionada:");
        System.out.println(String.format("%-10s %-30s %-12s %-40s %-10s %-15s %-12s", 
                                    "ID", "Title", "Date", "Content", "Priority", "Duration", "Completed"));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10d %-30s %-12s %-40s %-10d %-15d %-12b\n",
        task.getIdentifier(),
        task.getTitle(),
        task.getDate().toString(),
        task.getContent(),
        task.getPriority(),
        task.getEstimatedDuration(),
        task.isCompleted());
    }

    private void getAllTask() throws RepositoryException
    {
        controller.getAllTask();
    }

    private void ShowUncompletedTasks() throws RepositoryException
    {
        controller.ShowUncompletedTasks();
    }
    
    @Override
    public void showTasks(List<Task> tasks) {
        // Imprimir los encabezados de la tabla
        System.out.println(String.format("%-10s %-30s %-12s %-40s %-10s %-15s %-12s", 
                                        "ID", "Title", "Date", "Content", "Priority", "Duration", "Completed"));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        // Iterar sobre la lista de tareas y mostrar los datos en formato tabla
        for (Task task : tasks) {
        System.out.printf("%-10d %-30s %-12s %-40s %-10d %-15d %-12b\n",
        task.getIdentifier(),
        task.getTitle(),
        task.getDate().toString(),
        task.getContent(),
        task.getPriority(),
        task.getEstimatedDuration(),
        task.isCompleted());
        }
    }


    // Export/Import Options
    private void showExportImportMenu() throws RepositoryException, ExporterException
    {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            showExportImportOptions();
            int opcion = Esdia.readInt("Introduzca una opcion: ");
            switch(opcion)
            {
                case 1:  
                    importFromCSV();          
                    break;
                case 2:
                    importFromJSON();
                    break;
                case 3:
                    exportToCSV();
                    break;
                case 4:
                    exportToJSON();
                    break;
                case 5:
                    exit = true;
                    return;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }
    private void showExportImportOptions()
    {
        System.out.println("\n--- Menú Exportar/Importar ---");
        System.out.println("1. Importar tareas desde CSV");
        System.out.println("2. Importar tareas desde JSON");
        System.out.println("3. Exportar tareas a CSV");
        System.out.println("4. Exportar tareas a JSON");
        System.out.println("5. Volver al menú principal");
    }  
    
    private void importFromCSV() throws ExporterException
    {
        controller.importFromCSV();
    }

    private void importFromJSON()
    {    
        controller.importFromJSON();
    }

    private void exportToCSV()
    {    
        controller.exportToCSV();
    }

    private void exportToJSON()
    {    
        controller.exportToJSON();
    }

    // Finalizacion de la aplicacion
    @Override
    public boolean end() 
    {
        controller.saveData();
        return Esdia.yesOrNo("¿Esta seguro de que desea salir? (y/n): ");
    }
}
