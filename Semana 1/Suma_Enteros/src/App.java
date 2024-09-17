import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Suma de dos enteros
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el primer entero: ");
        int num1 = sc.nextInt();
        System.out.println("Introduce el segundo entero: ");
        int num2 = sc.nextInt();
        System.out.printf("La suma de ambos enteros es: %d\n", num1 + num2);

        sc.close();
    }
}
