package Ejercicio1;
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

        if (persona1.getHeightMeters() >= persona2.getHeightMeters()){
            if (persona1.getHeightMeters() >= persona3.getHeightMeters()){
                System.out.printf("\n%s es el más alto con una altura -> %.2f m", persona1.getName(), persona1.getHeightMeters());
            } else {
                System.out.printf("\n%s es el más alto con una altura -> %.2f m", persona3.getName(), persona3.getHeightMeters()); 
            }
        }else if (persona2.getHeightMeters() >= persona3.getHeightMeters()){
            System.out.printf("\n%s es el más alto con una altura -> %.2f m", persona2.getName(), persona2.getHeightMeters()); 
        }else{
            System.out.printf("\n%s es el más alto con una altura -> %.2f m", persona3.getName(), persona3.getHeightMeters()); 
        }

        if (persona1.getWeightKg() >= persona2.getWeightKg()){
            if (persona1.getWeightKg() >= persona3.getWeightKg()){
                System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg", persona1.getName(), persona1.getWeightKg());
            } else {
                System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg", persona3.getName(), persona3.getWeightKg()); 
            }
        }else if (persona2.getWeightKg() >= persona3.getWeightKg()){
            System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg", persona2.getName(), persona2.getWeightKg()); 
        }else{
            System.out.printf("\n\n%s es el que mas pesa con un peso de -> %.2f kg", persona3.getName(), persona3.getWeightKg()); 
        }

        
    }
}
