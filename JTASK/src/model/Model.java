package model;
import java.io.File;
import java.util.ArrayList;

import model.exceptions.RepositoryException;
import model.interfacesClases.IRepository;

public class Model {
    private IRepository repository;
    File ficheroEstadoSerializado;
    
    
    public Model(IRepository repository) {
        this.repository = repository;
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
    




}
