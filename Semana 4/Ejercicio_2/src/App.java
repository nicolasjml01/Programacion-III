import model.Person;

public class App {
    public static void main(String[] args) throws Exception {
        Person p = Person.createPersonFromStrings(args);

        int[] columns = {20, 20, 20, 20};
        if (p != null){
            System.out.printf("\nNombre: %s \t Peso: %.2f \t Altura: %.2f \t IMC: %.2f\n", p.getName(), p.getWeightKg(), 
            p.getHeightMeters(), p.calculateIMC());

            // Otra manera de representar
            System.out.println(p.asTableRow(columns));
        }else{
            System.out.println("Los datos introducidos no son validos");
        }
    }

    
}
