package model;

public class Person {
    // Estrtuctura de clases

    // Atributos
    private float weightKg, heightMeters;
    private String name;

    // Constructores
    
    // Java tiene un constructor por defecto ( Ya existe por si solo). NO DEVUELVE NADAç
    public Person(){
        // Establecemos valores por defecto
        this.name = "Unknown";
        this.heightMeters = 0.0f;
        this.weightKg = 0.0f;
    }
    // Metodos (logica) -> Comprobaciones de datos

    // Comprobaciones que esten bien dados los datos....
    private boolean isWeightValid(float weightKg){
        if (weightKg >= 0){
        return true;
        } else {
            return false;
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
