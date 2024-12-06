package controller;

import model.Model;
import view.BaseView;

public class Controller {
    Model model;
    BaseView view;

    public Controller(Model model, BaseView view) {
        this.model = model;
        this.view = view;
        
        // Inyectamos el controlador a la vista
        view.setController(this);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    

    

}
