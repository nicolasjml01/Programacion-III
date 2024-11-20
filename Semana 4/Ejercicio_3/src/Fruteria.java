
public class Fruteria {
    private final double IVA = 0.21;
    private String nombre;
    private double precioPorkg;

    public Fruteria(String nombre, double precioPorkg) {
        this.nombre = nombre;
        this.precioPorkg = precioPorkg;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPorkg() {
        return precioPorkg;
    }

    public double getPrecioConIVA() {
        return precioPorkg * (1 + IVA);
    }
}
