package paquete1;

public class Clase1 {
    // Atributos con diferentes modificadores de visibilidad
    public String atributoPublico = "Atributo público";
    String atributoPorDefecto = "Atributo por defecto";
    protected String atributoProtegido = "Atributo protegido";
    private String atributoPrivado = "Atributo privado";

    // Métodos con diferentes modificadores de visibilidad
    public void metodoPublico() {
        System.out.println("Método público");
    }

    void metodoPorDefecto() {
        System.out.println("Método por defecto");
    }

    protected void metodoProtegido() {
        System.out.println("Método protegido");
    }

    private void metodoPrivado() {
        System.out.println("Método privado");
    }
}
