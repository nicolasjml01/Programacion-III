package com.quiniela;

import com.quiniela.controller.Controller;
import com.quiniela.model.QuinielaModel;
import com.quiniela.view.View;

public class App {
    public static void main(String[] args) throws Exception {
        // Modelo
        QuinielaModel m = new QuinielaModel();

        // Vista
        View v = new View();

        // Cntrolador
        Controller c = new Controller(m, v);

        // Iniciar la aplicacion
        c.init();
    }
}
