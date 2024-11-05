package com.quiniela.model;

public class Partido {
    Equipo equipoLocal;
    Equipo equipoVisitante;
    int golesEquipoLocal, golesEquipoVisitante;
    String resultado; // 1 x 2 (Local, Empate, Visitante)

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int golesEquipoLocal, int golesEquipoVisitante,
            String resultado) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
        this.resultado = resultado;
    }

    public Partido(String equipoLocal, String equipoVisitante) {
        this.equipoLocal = new Equipo(equipoLocal);
        this.equipoVisitante = new Equipo(equipoVisitante);
        this.golesEquipoLocal = 0;
        this.golesEquipoVisitante = 0;
        this.resultado = "";
    }

    // Factory Method (metodo estatico de creacion)
    public static Partido createEquipoFromString(String delimitedString, String delimitador)
    {
        // Utilizamos String.split para obtener los trozos
        String [] trozos = delimitedString.split(delimitador);
        if (trozos.length == 2)
        {
            return new Partido(trozos[0], trozos[1]);
        }else{
            return null;
        }

    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public void setGolesEquipoLocal(int golesEquipoLocal) {
        this.golesEquipoLocal = golesEquipoLocal;
    }

    public int getGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public void setGolesEquipoVisitante(int golesEquipoVisitante) {
        this.golesEquipoVisitante = golesEquipoVisitante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
