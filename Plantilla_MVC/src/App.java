import controller.Controller;
import model.Model;
import view.View;

public class App {
    public static void main(String[] args) throws Exception {
        // Modelo
        Model m = new Model();

        // Vista
        View v = new View();

        // Cntrolador
        Controller c = new Controller(m, v);

        // Iniciar la aplicacion
        c.init();
    }
}
