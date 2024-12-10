import controller.Controller;
import model.Model;
import model.exceptions.ExporterException;
import model.exceptions.RepositoryException;
import model.interfacesClases.BinaryRepository;
import model.interfacesClases.IRepository;
import model.interfacesClases.NotionRepository;
import view.BaseView;
import view.InteractiveView;


public class App {
    public static void main(String[] args) throws RepositoryException, ExporterException {
        IRepository repository;
        BaseView view;

        if(args.length == 2)
        {
            // Opcion bin
            view = new InteractiveView();
            repository = new BinaryRepository();
        }else if (args.length == 4)
        {
            // Opcion Notion
            String apiToken = args[2];
            String databaseId = args[3];
            repository = new NotionRepository(apiToken, databaseId);
        }else 
        {
            // Bin por defecto
            repository = new BinaryRepository();
        }

        view = new InteractiveView();
        Model model = new Model(repository);
        Controller controller = new Controller(model, view);
        controller.initAplication();
    }
}

