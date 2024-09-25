import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // Solicita la edad con nextInt()
        System.out.print("Introduce tu edad: ");
        int edad = scanner.nextInt();

        // Consumimos el salto de línea que quedó después de nextInt()
        scanner.nextLine(); // Limpiamos el buffer

        // Solicita el nombre con nextLine()
        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine(); // No se espera la entrada del nombre correctamente

        // Muestra los valores ingresados
        System.out.println("Edad: " + edad);
        System.out.println("Nombre: " + nombre);

        scanner.close();
    }
}
