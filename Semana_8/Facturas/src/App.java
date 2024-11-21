import controller.Controller;
import model.Model;
import view.View;

public class App {
    public static void main(String[] args) throws Exception {
        // Declaramos el modelo
        Model m = new Model();

        // Declaramos la vista
        View v = new View();

        // Declaramos el controlador
        Controller c = new Controller(m, v);

        c.init();
    }
}
