package Ejercicio2;
import com.coti.tools.Esdia;
import model.Person;

public class Ejercicio2 {
    // Creación de un objeto utilizando el constructor sin parámetros
    Person persona1 = new Person();
    persona1.mostrarInformacion();

    System.out.println();

    // Creación de un objeto utilizando el constructor con parámetros
    Person personax = new Person("Juan", 175, 68);
    persona2.mostrarInformacion();

    System.out.println();

    // Prueba con valores inválidos
    Person persona3 = new Person("Ana", 0, 55); // Altura inválida
    persona3.mostrarInformacion();
}
}
