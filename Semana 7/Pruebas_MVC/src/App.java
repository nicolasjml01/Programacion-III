import com.calculadora.controller.Controller;
import com.calculadora.model.CalculadoraModel;
import com.calculadora.view.View;

public class App {
    public static void main(String[] args) throws Exception {

        // Armar MVC
        CalculadoraModel m = new CalculadoraModel();
        View v = new View();
        Controller c = new Controller(v, m);

        c.init();
    }
}
