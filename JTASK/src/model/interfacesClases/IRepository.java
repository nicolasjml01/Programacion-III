package model.interfacesClases;

import java.util.ArrayList;

import model.Task;
import model.exceptions.RepositoryException;

public interface IRepository {

    // Cargar los datos al iniciar la aplicacion
    void loadData() throws RepositoryException;

    // Guardar los datos al finalizar la aplicacion
    void saveData() throws RepositoryException;

    // Agrega una tarea al repositorio
    void addTask(Task t) throws RepositoryException;

    // Elimina una tarea del repositorio
    void removeTask(int id) throws RepositoryException;

    // Modifica una tarea existente en el repositorio
    void modifyTask(int id, String title, String date, String content, int priority, int estimatedDuration, boolean completed) throws RepositoryException;

    // Comprueba si existe una tarea con ese identificador
    boolean existsTask(int id) throws RepositoryException;

    // Obtiene todas las tareas del repositorio
    ArrayList<Task> getAllTask() throws RepositoryException;
}
