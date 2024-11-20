package model;

public class Person {
    String name;
    float weightKg;
    float heightMeters;
    
   
    // Metodos
    public float calculateIMC(){
        return this.weightKg/(this.heightMeters*this.heightMeters);
    }

    //Factory Method (Simple, sin herencia)
    // Devuelve una instancia de la clase target (o null en caso contrario)
    // Es estatico
    // Recibe como paramtros la informacion necesaria para crear una instancia
    // Factory Method Vs Constructor con parametors (Pensar) -> El constructor no tiene metodo de retorno. Solo podriamos hacer una excepcion
    public static Person createPersonFromStrings(String[] parametros){
        // Nombre, peso y altura
        try {
            String nombre = parametros[0];
            float peso = Float.parseFloat(parametros[1]); // hay que comprobar que sean validos
            float altura = Float.parseFloat(parametros[2]);
            // Todo ha ido bien
            Person p = new Person(nombre, peso, altura);
            return p;
        } catch (Exception e) {
            // Algo ha ido mal
            return null;
        }
    }

    public String asTableRow(int[] widthColumns){
        //String.format (igual que printf f pero devuelve un string)
        // Estamos accediendo al estado actual del objeto
        return String.format("| %-"+ widthColumns[0] + "s | %-" + widthColumns[1] + ".2f | %-" + widthColumns[2] +".2f | %-" + widthColumns[3] +".2f |", 
        this.name, this.heightMeters, this.weightKg, this.calculateIMC());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getWeightKg() {
        return weightKg;
    }
    public void setWeightKg(float weightKg) {
        this.weightKg = weightKg;
    }
    public float getHeightMeters() {
        return heightMeters;
    }
    public void setHeightMeters(float heightMeters) {
        this.heightMeters = heightMeters;
    }

    public Person(String name, float weightKg, float heightMeters) {
        this.name = name;
        this.weightKg = weightKg;
        this.heightMeters = heightMeters;
    }
    
}
