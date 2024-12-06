package model;

import java.util.ArrayList;

/**
 *
 * Esta interfaz representa un contrato para la persistencia de los datos de la aplicación.
 * 
 * Solo se implementa la importación y exportación de todos los alumnos (no se implementan todas las operaciones CRUD).
 * 
 *  Reading: https://www.baeldung.com/java-dao-vs-repository
 * 
 * @author Loza
 */
public interface PersistenceRepository {
    
    public ArrayList<Alumno> importarAlumnos();
    
    public boolean exportarAlumnos(ArrayList<Alumno> alumnos);
    
}
