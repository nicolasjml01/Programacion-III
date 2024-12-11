package controller;

import java.util.List;
import model.Model;
import model.Task;
import model.exceptions.ExporterException;
import model.exceptions.RepositoryException;
import model.interfacesClases.CSVExporter;
import model.interfacesClases.JSONExporter;
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
    public void initAplication() throws RepositoryException, ExporterException {
        view.init();
    }

    public void loadData() throws RepositoryException {
        try {
            model.loadData();
            view.showMessage("Exito accediendo/cargando la base de datos.");
        } catch (RepositoryException ex) {
            throw new RepositoryException("Error al cargar los datos: " + ex.getMessage());
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

    public Task existsTask(int id) throws RepositoryException {
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
            List<Task> tasks = model.getAllTask();
            view.showTasks(tasks);
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al cargar las tareas: " + e.getMessage());
        }
    }

    public void ShowUncompletedTasks() {
        try {
            List<Task> tasks = model.getUncompletedTasks();
            view.showTasks(tasks);
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al cargar las tareas: " + e.getMessage());
        }
    }

    // Exportar a archvio bin
    public void saveData() {
        try {
            view.showMessage("Finalizacion del programa realizada correctamente");
            model.saveData();
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al finalizar el programa: " + e.getMessage());
        }
    }

    // Exportar o importar a CSV o JSON
    public void importFromCSV() throws ExporterException {
        try {
            // Configuramos el exporter sin crear un nuevo Model
            model.setExporter(new CSVExporter());
            model.importFromCSV();
            view.showMessage("Fichero CSV importado correctamente.");
        } catch (ExporterException e) {
            view.showErrorMessage("Error al importar el fichero CSV: " + e.getMessage());
        }
    }

    public void exportToCSV() {
        try {
            // Configuramos el exporter sin crear un nuevo Model
            model.setExporter(new CSVExporter());
            model.exportToCSV();
            view.showMessage("Fichero CSV exportado correctamente.");
        } catch (ExporterException e) {
            view.showErrorMessage("Error al exportar el fichero CSV: " + e.getMessage());
        } catch (RepositoryException e) {
            view.showErrorMessage("Error obteniendo el listado de tareas: " + e.getMessage());
        }
    }

    public void importFromJSON() {
        try {
            // Configuramos el exporter sin crear un nuevo Model
            model.setExporter(new JSONExporter());
            model.importFromJSON();
            view.showMessage("Fichero JSON importado correctamente.");
        } catch (ExporterException e) {
            view.showErrorMessage("Error al importar el fichero JSON: " + e.getMessage());
        }
    }

    public void exportToJSON() {
        try {
            // Configuramos el exporter sin crear un nuevo Model
            model.setExporter(new JSONExporter());
            model.exportToJSON();
            view.showMessage("Fichero JSON importado correctamente.");
        } catch (ExporterException e) {
            view.showErrorMessage("Error al exportar el fichero JSON: " + e.getMessage());
        } catch (RepositoryException e) {
            view.showErrorMessage("Error obteniendo el listado de tareas: " + e.getMessage());
        }
    }

}
