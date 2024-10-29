package almacenLibros.controller;

import almacenLibros.model.Model;
import almacenLibros.view.View;

public class Controller {
    Model m;
    View v;
    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
    }

}
