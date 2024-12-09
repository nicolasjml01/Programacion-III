package view;
import java.util.List;

import controller.Controller;
import model.Task;
import model.exceptions.ExporterException;
import model.exceptions.RepositoryException;

public abstract class BaseView {
    
    // Atributo para el controlador
    protected Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public abstract void init() throws RepositoryException, ExporterException; 
    public abstract void showMessage(String msg); 
    public abstract void showErrorMessage(String msg);
    public abstract void showTasks(List<Task> tasks);
    public abstract boolean end();

}