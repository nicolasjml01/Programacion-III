package Ejercicios_1234;
import com.coti.tools.Esdia;
import model.Person;

public class Ejercicio1 {
    public static void main(String[] args) throws Exception {

        Person persona1 = new Person();
        System.out.println();
        persona1.setName(Esdia.readString("Introduce tu nombre: "));
        persona1.setHeightMeters(Esdia.readFloat("Introduce tu altura en metros: "));
        persona1.setWeightKg(Esdia.readFloat("Introduce tu peso en kg: "));

        Person persona2 = new Person();
        System.out.println();
        persona2.setName(Esdia.readString("Introduce tu nombre: "));
        persona2.setHeightMeters(Esdia.readFloat("Introduce tu altura en metros: "));
        persona2.setWeightKg(Esdia.readFloat("Introduce tu peso en kg: "));

        Person persona3 = new Person();
        System.out.println();
        persona3.setName(Esdia.readString("Introduce tu nombre: "));
        persona3.setHeightMeters(Esdia.readFloat("Introduce tu altura en metros: "));
        persona3.setWeightKg(Esdia.readFloat("Introduce tu peso en kg: "));


        comprobarMayorPesoyAltura(persona1, persona2, persona3);
        
        if (mostrarPorPantalla(persona1) == -1){
            System.out.printf("\nError el peso o la altura es menor o igual que 0 en: %s\n", persona1.getName());
        }
        if (mostrarPorPantalla(persona2) == -1){
            System.out.printf("\nError el peso o la altura es menor o igual que 0 en: %s\n", persona2.getName());
        }
        if (mostrarPorPantalla(persona3) == -1){
            System.out.printf("\nError el peso o la altura es menor o igual que 0 en: %s\n", persona2.getName());
        }
    }

    public static void comprobarMayorPesoyAltura(Person persona1, Person persona2, Person persona3){
        // Vemos el de mayor altura
        if (persona1.getHeightMeters() >= persona2.getHeightMeters()){
            if (persona1.getHeightMeters() >= persona3.getHeightMeters()){
                System.out.printf("\n%s es el más alto con una altura -> %.2f m\n", persona1.getName(), persona1.getHeightMeters());
            } else {
                System.out.printf("\n%s es el más alto con una altura -> %.2f m\n", persona3.getName(), persona3.getHeightMeters()); 
            }
        }else if (persona2.getHeightMeters() >= persona3.getHeightMeters()){
            System.out.printf("\n%s es el más alto con una altura -> %.2f m\n", persona2.getName(), persona2.getHeightMeters()); 
        }else{
            System.out.printf("\n%s es el más alto con una altura -> %.2f m\n", persona3.getName(), persona3.getHeightMeters()); 
        }

        // Vemos el de mayor peso
        if (persona1.getWeightKg() >= persona2.getWeightKg()){
            if (persona1.getWeightKg() >= persona3.getWeightKg()){
                System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg\n", persona1.getName(), persona1.getWeightKg());
            } else {
                System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg\n", persona3.getName(), persona3.getWeightKg()); 
            }
        }else if (persona2.getWeightKg() >= persona3.getWeightKg()){
            System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg\n", persona2.getName(), persona2.getWeightKg()); 
        }else{
            System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg\n", persona3.getName(), persona3.getWeightKg()); 
        }

    }
    // Método para calcular el IMC. *EJERCICIO 3*
    public static int mostrarPorPantalla(Person persona) {
        if (persona.getWeightKg() <= 0 || persona.getHeightMeters() <= 0) {
            System.out.println("Error: Altura o peso no válidos para el cálculo del IMC.");
            return -1;
        }

        float imc = persona.getWeightKg() / (persona.getHeightMeters() * persona.getHeightMeters()); // Fórmula del IMC
        

        System.out.println("Nombre: " + persona.getName());
        System.out.println("Altura: "+ persona.getHeightMeters() + "m");
        System.out.println("Peso: " + persona.getWeightKg() + "kg");
        System.out.printf("IMC: %.2f\n\n", imc);
        return 0;
    }
}
