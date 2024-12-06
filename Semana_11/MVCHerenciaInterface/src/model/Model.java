package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loza
 */
public class Model {

    // Representa la información del estado de la aplicación. Podrían ser
    // muchos más objetos colecciones, etc.
    private ArrayList<Alumno> alumnos;
    // Gestiona la importación de datos de fuentes externas
    private PersistenceRepository repository;
    File ficheroEstadoSerializado;

    public Model(PersistenceRepository repository) {
        this.repository = repository;
        ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "Desktop", "model.bin").toFile();
        alumnos = new ArrayList<>();
    }

    /**
     * *
     *
     * Permite exportar los alumnos empleando el repository cargado.
     *
     * @return
     */
    public boolean exportarAlumnos() {
        return repository.exportarAlumnos(alumnos);
    }

    /**
     * *
     *
     * Permite importar los alumnos en el modelo, añadiendo los existentes a los
     * que estén si estos no son considerados iguales a los presentes en el
     * modelo.
     *
     *
     * @return
     */
    public boolean importarAlumnos() {

        ArrayList<Alumno> alumnosImportados = repository.importarAlumnos();
        // OJO LÓGICA DE NEGOCIO. ¿SE AÑADEN O SE SUSTIYEN?
        // Aquí habría que decidirlo, a que llamamos importar.
        // Aquí añadimos, en otro proyecto pordría ser distinto
        if (alumnosImportados != null) {
            for (Alumno alumnoImportado : alumnosImportados) {
                // Al igual que sucede en este ejemplo, esto se puede hacer por 
                // la implementación de equals y hashcode hecha en Alumno
                if (!alumnos.contains(alumnoImportado)) {
                    alumnos.add(alumnoImportado);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     *
     * Función CRUD en colecciones que añade un alumno si este no existe ya. En
     * este caso se saca partido de haber implementado equals y hashcode en la
     * clase Alumno, definiendo a que se considera que "ya existe", es decir,
     * como se define que dos instancias de Alumno son iguales.
     *
     *
     * @param alumno
     * @return true si no existía el alumno, false en caso contrario.
     */
    public boolean agregarAlumno(Alumno alumno) {

        // Lógica de negocio
        if (alumnos.contains(alumno)) {
            // El alumno ya existe. Esto es posible porque hemos 
            // implementado en Alumno equals y hashcode que define como comparar instancias,
            // que es lo que utiliza internamente las colecciones en este caso.
            return false;
        } else {
            alumnos.add(alumno);
            return true;
        }

    }

    /**
     * *
     *
     * Similar a agregar pero para eliminar.
     *
     * @param DNI
     * @return true si existía el alumno, false en caso contrario.
     */
    public boolean eliminarAlumnoPorDNI(String DNI) {

        // Se genera una istancia temporal con el DNI y se borra con el método
        // que ya proporciona las colecciones. Esto se puede hacer porque hemos
        // implementado en Alumno equals y hashcode que define como comparar instancias,
        // que es lo que utiliza internamente las colecciones en este caso.
        Alumno alumnoParaBorrar = new Alumno(DNI, "", "", "");
        return alumnos.remove(alumnoParaBorrar);
    }

    /**
     *
     * Función que intenta cargar el estado previo de la aplicación, guardado en
     * un fichero binario con serialización en un lugar concreto fijo.
     *
     * Implementado devolviendo con true/false, en siguientes proyectos
     * trataremos excepciones y propagaremos excepciones y veremos alternativas.
     *
     * @return true si ha conseguido cargarlo, false en caso contrario.
     */
    public boolean cargarEstadoAplicación() {

        if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
                this.alumnos = (ArrayList<Alumno>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                // Dejamos el error para la depuración, por el canal err.
                System.err.println("Error durante la deserialización: " + ex.getMessage());
                return false;
            } finally {
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
        } else {
            return false;
        }

    }

    /**
     *
     * Función que intenta guardar el estado previo de la aplicación, en un
     * lugar concreto fijo.
     *
     * Implementado devolviendo con true/false, en siguientes proyectos
     * trataremos excepciones y propagaremos excepciones y veremos alternativas.
     *
     * @return true si ha conseguido guardarlo, false en caso contrario.
     */
    public boolean guardarEstadoAplicación() {

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado));
            oos.writeObject(alumnos);
            return true;
        } catch (IOException ex) {
            // Dejamos el error para la depuración, por el canal err.
            System.err.println("Error durante la serialización: " + ex.getMessage());
            return false;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    // Dejamos el error para la depuración, por el canal err.
                    System.err.println("Error al cerrar el flujo: " + ex.getMessage());
                    return false;
                }
            }
        }

    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public PersistenceRepository getRepository() {
        return repository;
    }

    public void setRepository(PersistenceRepository repository) {
        this.repository = repository;
    }

    public List<Alumno> obtenerAlumnosInmutable() {

        // Discusión sobre MVC y mutabilidad.
        // Imaginemos que devolvemos el arraylist original, en ese caso la
        // vista tendría acceso a los objetos del modelo directamente y podría
        // modificarlos. 
        // Si se quiere ser muy estricto en este tema se puede 
        // devolver una copia de la lista original, para preservar la integridad
        // del estado del modelo
        List<Alumno> listaCopia = new ArrayList<>(alumnos.size());

        for (Alumno alumno : alumnos) {
            listaCopia.add(new Alumno(alumno));
            // Debes tener un constructor de copia en tu clase Alumno
            // básicamente crea un Alumno a partir de los datos de otro Alumno
        }

        return listaCopia;

        // Se dara por bueno también devolver la lista original en estas aplicaciones sencillas,
        // pero es importante darse cuenta que si devolvemos una instancia del objeto mutable
        // podrían modificarla desde la vista, ya que tienen una referencia al objeto original.
    }

}
