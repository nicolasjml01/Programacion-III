package controller;

import model.Model;
import view.View;

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
        // Aqui haremos toda la logica necesaria para el programa 
            
        // Mostrar Menu al usuario
        //v.showMainMenu();
    }
}
