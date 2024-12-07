package model.interfacesClases;

import java.util.ArrayList;

import model.Task;
import model.exceptions.RepositoryException;

public class NotionRepository implements IRepository{

    @Override
    public Task addTask(Task t) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTask'");
    }

    @Override
    public void removeTask(Task t) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTask'");
    }

    @Override
    public void modifyTask(Task t) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyTask'");
    }

    @Override
    public ArrayList<Task> getAllTask() throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTask'");
    }
    
}
