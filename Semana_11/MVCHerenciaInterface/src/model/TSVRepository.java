package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loza
 */
public class TSVRepository implements PersistenceRepository {

    Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "datos.tsv");
    String delimitador = "\t";

    @Override
    public ArrayList<Alumno> importarAlumnos() {

        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                Alumno p = Alumno.getPersonFromDelimitedString(linea, delimitador);
                if (p != null) {
                    alumnos.add(p);
                }
            }
            return alumnos;
        } catch (IOException e) {
            // En otros ejemplos propagaremos una exception
            return null;
        }

    }

    @Override
    public boolean exportarAlumnos(ArrayList<Alumno> alumnos) {

        ArrayList<String> lineas = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            lineas.add(alumno.getInstanceAsDelimitedString(delimitador));
        }
        try {
            Files.write(ruta, lineas, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            System.err.println("Ocurrió un error al intentar exportar las personas: " + e.getMessage());
            return false;
        }

    }

}
