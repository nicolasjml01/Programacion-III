import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Solicitamos el año de nacimiento
        System.out.print("Introduce tu año de nacimiento: ");
        int anioNacimiento = sc.nextInt();

        // Solicitamos el año actual
        System.out.print("Introduce el año actual: ");
        int anioActual = sc.nextInt();

        // Suponiendo que la persona ya cumplió años este año, calculamos la edad
        int edad = anioActual - anioNacimiento;

        // Mostramos la edad usando printf para formatear la salida
        System.out.printf("Tienes %d años.\n", edad);

        // Cerramos el Scanner
        sc.close();

    }
}
