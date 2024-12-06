package model;

import java.util.ArrayList;

/**
 *
 * 
 * Repositorio en memoria que sirve para simular un numero de alumnos que se carguen
 * o se guarden en una fuente externa que se implemente PersistenceRepository.
 * 
 * El constructor permite crear varios alumnos genéricos.
 * 
 * @author Loza
 */
public class MemoryRepository implements PersistenceRepository {

    ArrayList<Alumno> alumnos;

    public MemoryRepository(int numeroAlumnosACrear) {

        alumnos = new ArrayList<>();
        // Fake repository
        for (int i = 0; i < numeroAlumnosACrear; i++) {
            alumnos.add(new Alumno("" + i, "Nombre" + i, "Apellido" + i, "Apellido" + i));
        }
    }

    @Override
    public ArrayList<Alumno> importarAlumnos() {
        return alumnos;
    }

    @Override
    public boolean exportarAlumnos(ArrayList<Alumno> alumnos) {
        
        // Si queremos matener esto en memoria como otro objeto
        // deberemos hacer una copia
        this.alumnos.clear();
        for (Alumno alumno : alumnos) {
            // Creando copias.
            this.alumnos.add(new Alumno(alumno));
        }
        
        // De otro modo se borraría aquí también ya que apuntan al mismo objeto.

        return true;
    }

}
