package model;

public class Person {
    // Estrtuctura de clases

    // Atributos
    private float weightKg, heightMeters;
    private String name;

    // Constructores
    
    // Java tiene un constructor por defecto ( Ya existe por si solo). NO DEVUELVE NADA
    // Constructor con parámetros (personalizado). No lo usaremos
    /*public Person(String name, float heightMeters, float weightKg) {
        this.name = name;
        this.dweightKg = heightMeters;
        this.dheightMeters = weightKg;
    }*/
    //Constructor sin Parámetros
    public Person(){
        // Establecemos valores por defecto
        this.name = "Unknown";
        this.heightMeters = 0.0f;
        this.weightKg = 0.0f;
    }
    // Metodos (logica) -> Comprobaciones de datos
    // Cálculo del IMC
    
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
