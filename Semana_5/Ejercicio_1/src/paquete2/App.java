package paquete2;

import paquete1.Clase1;

public class App {
    public static void main(String[] args) throws Exception {
        // Crear un objeto de Clase1
        Clase1 objetoClase1 = new Clase1();

        // Acceder a los atributos y métodos de Clase1
        System.out.println("Acceso a atributos de Clase1:");
        System.out.println("Public: " + objetoClase1.atributoPublico); // Acceso permitido
        // System.out.println("Por defecto: " + objetoClase1.atributoPorDefecto); // Error
        // System.out.println("Protegido: " + objetoClase1.atributoProtegido); // Error
        // System.out.println("Privado: " + objetoClase1.atributoPrivado); // Error

        System.out.println("\nAcceso a métodos de Clase1:");
        objetoClase1.metodoPublico(); // Acceso permitido
        // objetoClase1.metodoPorDefecto(); // Error
        // objetoClase1.metodoProtegido(); // Error
        // objetoClase1.metodoPrivado(); // Error

        // Crear un objeto de Clase2
        // Clase2 objetoClase2 = new Clase2(); // No se puede porque Clase2 es package-private
    }
}

