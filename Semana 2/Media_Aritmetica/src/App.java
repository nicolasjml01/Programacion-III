import java.util.Scanner;

import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n;

        while (true) {
            
            n = Esdia.readInt("Introduce un numero entero: ");

            if (n > 0)
            {
                break;
            }else{
                System.out.println("Debes introducir un numero mayor que 0");
            }
        }

        float suma = 0;

        // Solicitar N números flotantes
        for (int i = 1; i <= n; i++) {
            suma += Esdia.readFloat("Introduce un numero float: ");
        }

        float media;
        media = suma/n;

        System.out.printf("La media de los números es: %.2f\n", media);

        sc.close();
    }
}
