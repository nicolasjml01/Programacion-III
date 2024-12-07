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

    //Inicio de la aplicación
    public void initAplication() {
        if (model.importTaskBin())
        {
            view.showMessage("Fichero Binario Cargado Correctamente");
        }else
        {
            view.showErrorMessage("No se ha encontrado fichero binario");
        }

        // Menu principal
        view.init();
    }
    
    public boolean createTask(int identifier, String title, String date, String content, int priority,
            int estimatedDuration, boolean completed) {
        return model.createTask(identifier, title, date, content, priority, estimatedDuration, completed);
    }
    

}
