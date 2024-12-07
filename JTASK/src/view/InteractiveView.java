package view;

import com.coti.tools.Esdia;

import model.Task;

public class InteractiveView extends BaseView{

    @Override
    public void init() 
    {
        System.out.println("Bienvenido a la aplicación de gestión de tareas");
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
    
    @Override
    public void end() 
    {
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }

    public void showMainMenu()
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
                    end();
                    exit = true;
                    return;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }
    private void showMenuOptions()
    {
        System.out.println("Menu");
        System.out.println("1. Menu Crud");
        System.out.println("2. Menu Exportar/Importar");
        System.out.println("3. Finalizar Aplicacion");
    }

    // CRUD Options
    private void showCRUDMenu()
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
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    showMainMenu();
                    return;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }

    private void showCRUDOptions()
    {
        System.out.println("\n--- Menu CRUD ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas pendientes por prioridad");
        System.out.println("3. Listar historial completo de tareas");
        System.out.println("4. Ver detalle de una tarea");
        System.out.println("5. Volver al menú principal");
    }

    private void createTask()
    {
        System.out.println("\n--- Crear Tarea ---");
        int identifier = Esdia.readInt("Ingrese el ID de la tarea: ");
        String title = Esdia.readString("Ingrese el titulo de la tarea: ");
        String date = Esdia.readString("Introduce la fecha de la tarea (dd/mm/yyyy): ");
        String content = Esdia.readString("Ingrese la descripcion de la tarea: ");
        int priority = Esdia.readInt("Introduzca la prioridad de la tarea (Entre 1 y 5)", 1, 5);
        int estimatedDuration = Esdia.readInt("Introduzca la duracion de la tarea (en minutos): ");
        boolean completed = Esdia.yesOrNo("¿Esta la tarea completada? (y/n): ");

        if(controller.createTask(identifier, title, date, content, priority, estimatedDuration, completed))
        {
            showMessage("Tarea Añadida correctamente");
        }
    }

















    // Export Import Options
    private void showExportImportMenu()
    {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            showExportImportOptions();
            int opcion = Esdia.readInt("Introduzca una opcion: ");
            switch(opcion)
            {
                case 1:  
                                         
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    showMainMenu();
                    return;
                default:
                    System.out.println("Opcion Incorrecta, seleccione una opcion valida.");
            }
        }
    }
    private void showExportImportOptions()
    {
        System.out.println("\n--- Menú Exportar/Importar ---");
        System.out.println("1. Exportar tareas a JSON");
        System.out.println("2. Exportar tareas a CSV");
        System.out.println("3. Importar tareas desde JSON");
        System.out.println("4. Importar tareas desde CSV");
        System.out.println("5. Volver al menú principal");
    }

    
}
