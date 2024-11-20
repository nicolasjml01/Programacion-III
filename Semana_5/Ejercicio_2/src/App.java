import java.util.Arrays;
import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {
        String[] cadenas = new String[5];

        // Solicitar 5 cadenas al usuario
        System.out.println("Ingrese 5 cadenas de texto:");
        for (int i = 0; i < cadenas.length; i++) {
            System.out.println();
            System.out.print("Cadena " + (i + 1) + ": ");
            cadenas[i] = Esdia.readString("Introduzca la cadena elegida: ");
        }

        // Ordenar las cadenas usando Arrays.sort()
        Arrays.sort(cadenas);

        // Mostrar las cadenas ordenadas
        System.out.println("\nCadenas ordenadas:");
        for (String cadena : cadenas) {
            System.out.println(cadena);
        }
    }
}
