package model.interfacesClases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Task;
import model.exceptions.RepositoryException;

public class BinaryRepository implements IRepository{

    
    // Gestiona la importación de datos de fuentes externas
    private IRepository repository;
    File ficheroEstadoSerializado;

    public BinaryRepository(File ficheroEstadoSerializado) {
        this.ficheroEstadoSerializado = ficheroEstadoSerializado;
    }
    
    // Metodo para importar el fichero binario
    public boolean importTaskBin()
    {
            if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile())
            {
                ObjectInputStream ois = null;
                try
                {
                    ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
                    this.tasks = (ArrayList<Task>) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    // Dejamos el error para la depuración, por el canal err.
                    System.err.println("Error durante la deserialización: " + ex.getMessage());
                    return false;
                }finally {
                    if (ois != null) {
                        try {
                            ois.close();
                        } catch (IOException ex) {
                            // Dejamos el error para la depuración, por el canal err.
                            System.err.println("Error durante la deserialización: " + ex.getMessage());
                            return false;
                        }
                    }
                }
                return true;
            } else return false;
    }

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
