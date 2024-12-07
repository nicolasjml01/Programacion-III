package model.interfacesClases;

import java.util.ArrayList;

import model.Task;
import model.exceptions.RepositoryException;

public interface IRepository {
    // Agrega una tarea al repositorio
    Task addTask(Task t) throws RepositoryException;

    // Elimina una tarea del repositorio
    void removeTask(Task t) throws RepositoryException;

    // Modifica una tarea existente en el repositorio
    void modifyTask(Task t) throws RepositoryException;

    // Obtiene todas las tareas del repositorio
    ArrayList<Task> getAllTask() throws RepositoryException;
}
