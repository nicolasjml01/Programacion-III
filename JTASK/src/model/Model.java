package model;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.ExporterException;
import model.exceptions.RepositoryException;
import model.interfacesClases.IExporter;
import model.interfacesClases.IRepository;

public class Model {
    private IRepository repository;
    private IExporter exporter;
    File ficheroEstadoSerializado;
    
    // Constructor para inyectar la implementación específica
    public Model(IRepository repository, IExporter exporter) {
        this.repository = repository;
        this.exporter = exporter;
    }

    // Constructor si solo se conoce el repositorio inicialmente
    public Model(IRepository repository) {
        this.repository = repository;
    }

    // Setter para configurar el exporter después de instanciar el Model
    public void setExporter(IExporter exporter) {
        this.exporter = exporter;
    }

    public void loadData() throws RepositoryException {
        repository.loadData();
    }


    public void addTask(Task task) throws RepositoryException {
        repository.addTask(task);
    }


    public void removeTask(int id) throws RepositoryException {
        repository.removeTask(id);
    }

    public Task existsTask(int id) throws RepositoryException {
        return repository.existsTask(id);
    }

    public void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        repository.modifyTask(id, title, date, content, priority, estimatedDuration, completed);
    }


    public ArrayList<Task> getAllTask() throws RepositoryException {
        return repository.getAllTask();
    }

    public List<Task> getUncompletedTasks() throws RepositoryException {
        return repository.getUncompletedTasks();
    }

    // Finalizacion de la aplicacion
    public void saveData() throws RepositoryException {
        repository.saveData();
    }

    public void importFromCSV() throws ExporterException {
        try {
            List<Task> tasksFromFile = exporter.importTasks();
            
            // Iterar sobre las tareas importadas
            for (Task task : tasksFromFile) {
                try {
                    addTask(task);
                } catch (RepositoryException e) {
                    // No hacemos nada si la tarea esta duplicada
                }
            }
        } catch (ExporterException e) {
            throw new ExporterException("Error al importar tareas desde CSV: " + e.getMessage(), e);
        }
    }

    public void exportToCSV() throws RepositoryException, ExporterException {
        ArrayList<Task> exportList = repository.getAllTask();
        exporter.exportTasks(exportList);
    }

    public void importFromJSON() throws ExporterException {
        try {
            List<Task> tasksFromFile = exporter.importTasks();
            
            // Iterar sobre las tareas importadas
            for (Task task : tasksFromFile) {
                try {
                    addTask(task);
                } catch (RepositoryException e) {
                    // No hacemos nada si la tarea esta duplicada
                }
            }
        } catch (ExporterException e) {
            throw new ExporterException("Error al importar tareas desde CSV: " + e.getMessage(), e);
        }
    }

    public void exportToJSON() throws RepositoryException, ExporterException {
        ArrayList<Task> exportList = repository.getAllTask();
        exporter.exportTasks(exportList);
    }

}
