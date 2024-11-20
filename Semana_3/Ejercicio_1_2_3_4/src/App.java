import com.coti.tools.Esdia;
import model.Person;

public class App {
    public static void main(String[] args) throws Exception {
        //Ejemplo Primero
        Person persona1 = new Person();
        persona1.setName("Nico");
        float height = 1.81f;
        persona1.setHeightMeters(height);
        float weightKg = 85f;
        persona1.setWeightKg(weightKg);
        System.out.printf("La primera persona tiene el nombre: %s, altura: %.2f y peso: %.2f", 
        persona1.getName(), persona1.getHeightMeters(), persona1.getWeightKg());

        //Ejemplo Segundo (evolucion)
        Person persona2 = new Person();
        System.out.println();
        persona2.setName(Esdia.readString("Introduce tu nombre: "));
        persona2.setHeightMeters(Esdia.readFloat("Introduce tu altura en metros: "));
        persona2.setWeightKg(Esdia.readFloat("Introduce tu peso en kg: "));
        
        System.out.printf("La segunda persona tiene el nombre: %s, altura: %.2f m y peso: %.2f kg", 
        persona2.getName(), persona2.getHeightMeters(), persona2.getWeightKg());
    }
}
