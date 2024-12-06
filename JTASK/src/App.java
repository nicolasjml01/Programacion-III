
import controller.Controller;
import model.IRepository;
import model.Model;
import view.BaseView;
import view.CLIView;
import view.InteractiveView;

public class App {
    public static void main(String[] args) throws Exception {
        IRepository repository;
        BaseView view;

        // Verificar los argumentos proporcionados en la línea de comandos
        if (args.length >= 1) {
            repository = getRepositoryForOption(args);
            view = getViewForOption(args);
        } else {
            // Opciones por defecto si no se pasan argumentos
            repository = new BinaryRepository();
            view = new InteractiveView();
        }

        // Crear el modelo con el repositorio seleccionado
        Model model = new Model(repository);

        // Crear el controlador
        Controller controller = new Controller(model, view);

        // Iniciar la aplicación
        controller.start();

    }

     /**
     * Método auxiliar para obtener el repositorio correcto según los argumentos.
     */
    private static IRepository getRepositoryForOption(String[] args) {
        String repositoryType = args[0];

        switch (repositoryType) {
            case "bin":
                return new BinaryRepository();
            case "notion":
            //Usar excepciones de la vista para esto
                if (args.length < 3) {
                    throw new IllegalArgumentException("Para 'notion' se requieren API_KEY y DATABASE_ID.");
                }
                String apiKey = args[1];
                String databaseId = args[2];
                return new NotionRepository(apiKey, databaseId);
            default:
                //Si usamos excepcion usar la por defecto
                System.err.println("Tipo de repositorio desconocido, usando 'bin' como valor por defecto.");
                return new BinaryRepository();
        }
    }

    // Metodopara cargar la vista segun la opción
    private static BaseView getViewForOption(String[] args) {
        if (args.length > 3) {
            //Esto es notion ya veremos
            String viewType = args[3];
            switch (viewType) {
                case "interactive":
                    //return new InteractiveView();
                case "cli":
                    //return new CLIView();
                default:
                    System.err.println("Tipo de vista desconocido, usando 'interactive' como valor por defecto.");
                   // return new InteractiveView();
            }
        } else if (args.length == 2){
            // Opcion binaria
            
        }else
        {
            // Vista por defecto si no se especifica (Notion)
            //return new InteractiveView();
        }
    }
}
