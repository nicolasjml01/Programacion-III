public class Alumno {
    String nombre;
    double parcial1;
    double parcial2;
    double finalExamen;
    double notaFinal;
    
    public Alumno(String nombre, double parcial1, double parcial2, double finalExamen) {
        this.nombre = nombre;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.finalExamen = finalExamen;
        this.notaFinal = calcularNotaFinal();
    }

    private double calcularNotaFinal() {
        return parcial1 * 0.1 + parcial2 * 0.1 + finalExamen * 0.8;
    }    
}
