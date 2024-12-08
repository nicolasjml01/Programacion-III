package controller;

import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Task;
import model.exceptions.RepositoryException;
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
    public void initAplication() throws RepositoryException {
        view.init();
    }

    public void loadData() throws RepositoryException {
        try {
            model.loadData();
            view.showMessage("Datos cargados correctamente.");
        } catch (RepositoryException ex) {
            view.showErrorMessage("Error al cargar los datos: " + ex.getMessage());
        }
    }

    public void addTask(Task task) throws RepositoryException {
        try {
            model.addTask(task);
            view.showMessage("Tarea Añadida correctamente");
        } catch (RepositoryException ex) {
            view.showErrorMessage("Error al agregar la tarea: " + ex.getMessage());
        }
    }

    public void removeTask(int id) throws RepositoryException {
        try {
            model.removeTask(id);
            view.showMessage("Tarea Eliminada correctamente");
        } catch (RepositoryException ex) {
            view.showErrorMessage("Error al eliminar la tarea: " + ex.getMessage());
        }
    }

    public boolean existsTask(int id) throws RepositoryException {
        return model.existsTask(id);
    }

    public void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        try {
            model.modifyTask(id, title, date, content, priority, estimatedDuration, completed);
            view.showMessage("Tarea modificada correctamente");
        } catch (RepositoryException ex) {
            view.showErrorMessage("Error al modificar la tarea: " + ex.getMessage());
        }
    }

    public void getAllTask() {
        try {
            List<Task> tasks = model.getAllTask();  // Obtener una copia de las tareas
            view.showTasks(tasks);  // Pasar las tareas a la vista para que las muestre
        } catch (RepositoryException e) {
            view.showMessage("Error al cargar las tareas: " + e.getMessage());
        }
    }


    // Exportar a archvio bin
    public void saveData() {
        try {
            model.saveData();
            view.showMessage("Fichero binario creado correctamente");
        } catch (RepositoryException e) {
            view.showMessage("Error al crear el fichero binario: " + e.getMessage());
        }
    }
    

}
