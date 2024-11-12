package com.mvc.controller;

import com.mvc.model.Model;
import com.mvc.view.View;

public class Controller {
    Model m;
    View v;

    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        // Inyectamos el controlador a la vista
        v.setController(this);
    }

    public void init() {
        // Iniciar la aplicacion
        
        // Mostrar Menu al usuario
        v.showMainMenu();
        // Terminar ordenadamente
    }

}
