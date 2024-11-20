package com.quiniela.controller;

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
        v.showMainMenu();
        // Mostrar Menu al usuario

        // Terminar ordenadamente
    }

}
