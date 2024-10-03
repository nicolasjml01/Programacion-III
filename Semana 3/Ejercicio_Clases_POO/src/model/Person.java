package model;

public class Person {
    // Estrtuctura de clases

    // Atributos
    private float weightKg, heightMeters;
    private String name;

    // Constructores
    
    // Java tiene un constructor por defecto ( Ya existe por si solo). NO DEVUELVE NADA
    // Constructor con parámetros (personalizado)
    public Person(String name, float heightMeters, float weightKg) {
        this.name = name;
        this.heightMeters = heightMeters;
        this.weightKg = weightKg;
    }
    //Constructor sin Parámetros
    public Person(){
        // Establecemos valores por defecto
        this.name = "Unknown";
        this.heightMeters = 0.0f;
        this.weightKg = 0.0f;
    }
    // Metodos (logica) -> Comprobaciones de datos
    // Cálculo del IMC
    // Método para calcular el IMC. *EJERCICIO 3*
    public double calcularIMC() {
        if (this.weightKg <= 0 || this.heightMeters <= 0) {
            System.out.println("Error: Altura o peso no válidos para el cálculo del IMC.");
            return -1;
        }

        double imc = this.weightKg / (this.heightMeters * this.heightMeters); // Fórmula del IMC
        return imc;
    }
    // Método para mostrar la información de la persona
    public void mostrarInformacion() {
        System.out.println("Nombre: " + this.name);
        System.out.println("Altura: " + this.heightMeters + " metros");
        System.out.println("Peso: " + this.weightKg + " kg");
        
        double imc = calcularIMC();
        if (imc != -1) {
            System.out.println("IMC: " + imc);
        }
    }
    // Getters y setters
    public float getWeightKg() {
        return weightKg;
    }
    public void setWeightKg(float weightKg) {
        this.weightKg = weightKg;
        // Aqui podriamos llamar a isWeightValid
    }
    public float getHeightMeters() {
        return heightMeters;
    }
    public void setHeightMeters(float heightMeters) {
        this.heightMeters = heightMeters;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
