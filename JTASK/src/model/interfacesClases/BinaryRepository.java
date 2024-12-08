package model.interfacesClases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.coti.tools.Rutas;

import model.Task;
import model.exceptions.RepositoryException;

public class BinaryRepository implements IRepository{

    // Lista de tareas con las que trabajaremos
    ArrayList<Task> tasks;

    @SuppressWarnings("unchecked") // PARA QUITAR EL WARNING (BORRAR)
    @Override
    public void loadData() throws RepositoryException {
        File file = Rutas.fileToFileOnDesktop("tasks.bin");

        if (!file.exists() || !file.isFile()) {
            // Si el archivo no existe, inicializa una lista vacía
            this.tasks = new ArrayList<>();
            return; // No lanzamos una excepción aquí, porque no es un error crítico
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            this.tasks = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RepositoryException("Error durante la deserialización del archivo de tareas.", ex);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    // Si no se puede cerrar el flujo, lanzamos una excepción
                    throw new RepositoryException("Error al cerrar el archivo de tareas.", ex);
                }
            }
        }
    }
    
    // Comprobamos que el identificador exista
    @Override
    public boolean existsTask(int identifier) throws RepositoryException {
        for (Task task : tasks) {
            if (task.getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addTask(Task task) throws RepositoryException {
        // Comprobamos si el ID es único
        if (existsTask(task.getIdentifier())) { 
            throw new RepositoryException("El identificador " + task.getIdentifier() + " ya existe. No se puede añadir la tarea.");
        }
        
        // Comprobamos si la fecha no es nula
        if (task.getDate() == null) {
            throw new RepositoryException("La fecha de la tarea no es válida. No se puede añadir la tarea.");
        }
        
        // Si todo está correcto, añadimos la tarea
        tasks.add(task);
    }

    @Override
    public void removeTask(int identifier) throws RepositoryException {
        for (Task task : tasks) {
            if (task.getIdentifier() == identifier) {
                tasks.remove(task);
                return;
            }
        }
        // Si no se encuentra la tarea
        throw new RepositoryException("No se encontró ninguna tarea con el identificador: " + identifier);
    }

    @Override
    public void modifyTask(int identifier, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException {
        for (Task task : tasks) {
            if (task.getIdentifier() == identifier) {
                // Actualizamos los atributos de la tarea, excepto el identificador
                task.setTitle(title);
                task.setDate(date); 
                task.setContent(content);
                task.setPriority(priority);
                task.setEstimatedDuration(estimatedDuration);
                task.setCompleted(completed);
                return;
            }
        }
        // Si no se encuentra la tarea
        throw new RepositoryException("No se encontró ninguna tarea con el identificador: " + identifier);
    
    }

    @Override
    public ArrayList<Task> getAllTask() throws RepositoryException {
        // Creamos una lista con los mismos elementos
        return new ArrayList<>(tasks);
    }

    @Override
    public void saveData() throws RepositoryException {
        File file = Rutas.fileToFileOnDesktop("tasks.bin");

        // Si el archivo ya existe, lo eliminamos
        if (file.exists()) {
            if (!file.delete()) {
                throw new RepositoryException("No se pudo eliminar el archivo existente: " + file.getAbsolutePath());
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream escribir = new ObjectOutputStream(fos);
            escribir.writeObject(tasks);
            escribir.close();
        } catch (IOException e) {
            throw new RepositoryException("Error al guardar los datos en el archivo: " + file.getAbsolutePath(), e);
        }

    }
    
}
