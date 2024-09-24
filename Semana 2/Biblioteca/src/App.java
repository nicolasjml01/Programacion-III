import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        float miNumero = Esdia.readFloat("Escriba un float: ");

        System.out.printf("Numero introducido: %.2f", miNumero);

    }
}
