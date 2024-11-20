public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        final int ARRAY_LENGTH = 5;
        int[] numeros = new int[ARRAY_LENGTH];
        numeros[1] = 9;
        numeros[2] = 8;
        numeros[3] = 7;
        int total = 0;
        for (int i = 0; i < numeros.length; i++) {
            total += numeros[i];
        }
            System.out.printf("Total: %d%n", total);
        }
}
