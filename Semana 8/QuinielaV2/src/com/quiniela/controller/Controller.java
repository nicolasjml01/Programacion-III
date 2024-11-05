package com.quiniela.controller;

import java.util.ArrayList;

import com.quiniela.model.Partido;
import com.quiniela.model.QuinielaModel;
import com.quiniela.view.View;

public class Controller {
    QuinielaModel m;
    View v;

    public Controller(QuinielaModel m, View v) {
        this.m = m;
        this.v = v;
        // Inyectamos el controlador a la vista
        v.setController(this);
    }

    public void init() {
        // Iniciar la aplicacion
        // Leer informacion del fichero y establecer valores en el modelo
        boolean ficheroExito = m.cargarDatosPartidos();
        if (ficheroExito){
            v.mostrarMnsg("Fichero leido con exito");
        }else{
            v.mostrarMnsgError("No se ha podido leer el fichero");
        }
        // Mostrar Menu al usuario
        v.showMainMenu();
        // Terminar ordenadamente
    }


    public boolean cargarEquiposDeFichero() {
        return m.cargarDatosPartidos();
    }

    public ArrayList<Partido> getPartidos() {
        // Devolvemos una copia de los partidos del modelo. Para evitar modificaciones de la vista
        return m.setPartidos();
    }

    public ArrayList<Partido> setPartidos() {
        return m.setPartidos();
    }

}
