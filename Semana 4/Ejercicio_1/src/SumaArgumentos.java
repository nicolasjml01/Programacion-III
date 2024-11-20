public class SumaArgumentos {
    public static void main(String[] args) throws Exception {
        // Validamos que se reciban 2 argumentos
        if (args.length != 2) {
            System.out.println("Error: Debes proporcionar exactamente dos números como argumentos.");
            System.out.println("Uso: java SumaArgumentos <num1> <num2>");
            return;
        }

        try {
            // Tranformamos los argumentos en numeros
            double num1 = Double.parseDouble(args[0]);
            double num2 = Double.parseDouble(args[1]);

            double suma = num1 + num2;
            System.out.println("La suma de " + num1 + " y " + num2 + " es: " + suma);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ambos argumentos deben ser números válidos.");
        }
    }
}
