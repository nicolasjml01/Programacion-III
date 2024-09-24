import java.io.Console;

public class App {
    public static void main(String[] args) throws Exception {

        Console sc = System.console();

        // Verificamos si hay una consola disponible
        if (sc == null) {
            System.out.println("No hay una consola disponible.");
            return;
        }

        // Solicitamos el año de nacimiento
        String anioNacimientoStr = sc.readLine("Introduce tu año de nacimiento: ");
        // Convertimos la cadena a un entero
        int anioNacimiento = Integer.parseInt(anioNacimientoStr);

        // Solicitamos el año actual
        String anioActualStr = sc.readLine("Introduce el año actual: ");
        // Convertimos la cadena a un entero
        int anioActual = Integer.parseInt(anioActualStr);

        // Suponiendo que la persona ya cumplió años este año, calculamos la edad
        int edad = anioActual - anioNacimiento;

        // Mostramos la edad
        sc.printf("Tienes %d años.\n", edad);
    }
}
