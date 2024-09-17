import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Solicitar los tres números al usuario
        System.out.print("Introduce el primer número: ");
        int num1 = sc.nextInt();

        System.out.print("Introduce el segundo número: ");
        int num2 = sc.nextInt();

        System.out.print("Introduce el tercer número: ");
        int num3 = sc.nextInt();

        // Comparar si los tres números son iguales
        if (num1 == num2 && num2 == num3) {
            System.out.println("Los tres números son iguales.");
        } 
        // Comparar si dos números son iguales y mayores que el tercero
        else if (num1 == num2 && num1 > num3) {
            System.out.println("El primer y segundo número (" + num1 + ") son iguales y son los mayores.");
        } else if (num1 == num3 && num1 > num2) {
            System.out.println("El primer y tercer número (" + num1 + ") son iguales y son los mayores.");
        } else if (num2 == num3 && num2 > num1) {
            System.out.println("El segundo y tercer número (" + num2 + ") son iguales y son los mayores.");
        } 
        // Comparar si uno de los números es mayor que los otros dos
        else if (num1 > num2 && num1 > num3) {
            System.out.println("El primer número (" + num1 + ") es el mayor.");
        } else if (num2 > num1 && num2 > num3) {
            System.out.println("El segundo número (" + num2 + ") es el mayor.");
        } else if (num3 > num1 && num3 > num2) {
            System.out.println("El tercer número (" + num3 + ") es el mayor.");
        }

        // Cerrar el objeto Scanner
        sc.close();
    }
}
