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
    public Model(IRepository repository) {
        this.repository = repository;
    }

    public Model(IExporter exporter) {
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

    public boolean existsTask(int id) throws RepositoryException {
        return repository.existsTask(id);
    }

    public void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        repository.modifyTask(id, title, date, content, priority, estimatedDuration, completed);
    }


    public ArrayList<Task> getAllTask() throws RepositoryException {
        return repository.getAllTask();
    }

    // Exportar al fichero binario
    public void saveData() throws RepositoryException {
        repository.saveData();
    }

    public void importFromCSV() throws ExporterException {
        try {
            List<Task> tasksFromFile = exporter.importTasks(); // Obtener tareas desde el exporter
    
            for (Task task : tasksFromFile) {
                try {
                    addTask(task); // Intentar añadir la tarea al repositorio
                } catch (RepositoryException e) {
                    // Reemplazar el System.err con un ExporterException más descriptivo
                    throw new ExporterException("Error al añadir la tarea con ID " 
                            + task.getIdentifier() + ": " + e.getMessage(), e);
                }
            }
        } catch (ExporterException e) {
            throw new ExporterException("Error al importar tareas desde CSV: " + e.getMessage(), e);
        }
    }

    public void exportToCSV() throws RepositoryException, ExporterException {
        ArrayList<Task> exportList = getAllTask();
        exporter.exportTasks(exportList);
    }
    




}
