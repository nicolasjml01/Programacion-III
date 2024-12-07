package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.coti.tools.Rutas;

import model.exceptions.RepositoryException;
import model.interfacesClases.IRepository;

public class Model {

    // Lista de tareas
    private ArrayList<Task> tasks;
    // Gestiona la importación de datos de fuentes externas
    private IRepository repository;
    File ficheroEstadoSerializado;
    
    
    public Model(IRepository repository) {
        this.repository = repository;
        ficheroEstadoSerializado = Rutas.fileToFileOnDesktop("tasks.bin");
        tasks = new ArrayList<>();
    }

    public boolean importTaskBin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importTaskBin'");
    }
    

    public boolean createTask(int identifier, String title, String date, String content, int priority,
            int estimatedDuration, boolean completed) {
        Task task = new Task(identifier, title, date, content, priority, estimatedDuration, completed);
        try {
            repository.addTask(task);
            return true;
        } catch (RepositoryException e) {
            System.err.println("Error adding task: " + e.getMessage());
            return false;
        }
    }


}
