import almacenLibros.controller.Controller;
import almacenLibros.model.Model;
import almacenLibros.view.View;

public class App {
    public static void main(String[] args) throws Exception {
        // Armar MVC
        Model m = new Model();
        View v = new View();
        Controller c = new Controller(m, v);

        

    }
}
