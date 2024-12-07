import controller.Controller;
import model.Model;
import model.interfacesClases.BinaryRepository;
import model.interfacesClases.IRepository;
import model.interfacesClases.NotionRepository;
import view.BaseView;
import view.InteractiveView;


public class App {
    public static void main(String[] args) {
        IRepository repository;
        BaseView view = new InteractiveView();
        if(args.length == 4)
        {
            // Opcion bin
            repository = new NotionRepository();
        }else
        {
            // Opcion por defecto
            repository = new BinaryRepository();
        }

        Model model = new Model(repository);
        Controller controller = new Controller(model, view);
        controller.initAplication();
    }
}

