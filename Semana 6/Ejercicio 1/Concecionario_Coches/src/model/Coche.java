package model;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;

    public Coche(String marca, String modelo, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstadoComoString() {
        return "Coche [marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + "]";
    }
    
}
