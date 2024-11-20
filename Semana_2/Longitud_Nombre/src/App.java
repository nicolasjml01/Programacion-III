import java.util.Scanner;
import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre y apellidos al usuario
        String nombre = Esdia.readString("Introduce tu nombre: ");
        String apellidos = Esdia.readString("Introduce tus apellidos: ");

        // Calcular las longitudes del nombre y apellidos
        int longitudNombre = nombre.length();
        int longitudApellidos = apellidos.length();

        // Calcular el ancho máximo entre "Nombre" y el nombre del usuario
        int anchoNombre = Math.max("Nombre".length(), longitudNombre);
        
        // Calcular el ancho máximo entre "Apellidos" y los apellidos del usuario
        int anchoApellidos = Math.max("Apellidos".length(), longitudApellidos);

        // Calcular el ancho total del cuadro
        int anchoTotal = anchoNombre + anchoApellidos + 6; // 7 incluye el espacio extra para los asteriscos y el separador

        // Imprimir el cuadro
        imprimirLineaSuperior(anchoTotal);
        imprimirFila("Nombre", "Apellidos", anchoNombre, anchoApellidos);
        imprimirLineaSuperior(anchoTotal);
        imprimirFila(nombre, apellidos, anchoNombre, anchoApellidos);
        imprimirLineaSuperior(anchoTotal);

        // Cerrar el scanner
        scanner.close();
    }

    // Método para imprimir la línea superior/inferior del cuadro
    public static void imprimirLineaSuperior(int anchoTotal) {
        for (int i = 0; i < anchoTotal; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    // Método para imprimir una fila con el nombre y apellidos ajustados
    public static void imprimirFila(String nombre, String apellidos, int anchoNombre, int anchoApellidos) {
        // Formateamos la salida ajustando los anchos
        System.out.printf("* %-"+ anchoNombre + "s * %-"+ anchoApellidos + "s *\n", nombre, apellidos);
    }

}
