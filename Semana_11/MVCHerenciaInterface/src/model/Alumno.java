package model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * Esta clase implementa la interfaz serializable.
 *
 * @author Loza
 */
public class Alumno implements Serializable {

    private String DNI;
    private String nombre;
    private String apellido1;
    private String apellido2;

    public Alumno(String DNI, String nombre, String apellido1, String apellido2) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    // Constructor copia
    public Alumno(Alumno otroAlumno) {
        this.DNI = otroAlumno.DNI;
        this.nombre = otroAlumno.nombre;
        this.apellido1 = otroAlumno.apellido1;
        this.apellido2 = otroAlumno.apellido2;
    }

    public static Alumno getPersonFromDelimitedString(String delimitedString, String delimiter) {

        // Con split es posible separar por el delimitador
        String[] chunks = delimitedString.split(delimiter);

        if (chunks.length != 4) {
            // Esto proximamente será una excepción
            // Tomamos linea como inválida
            return null;
        }

        try {
            String dni = chunks[0];
            String nombre = chunks[1];
            String apellido1 = chunks[2];
            String apellido2 = chunks[3];
            Alumno p = new Alumno(dni, nombre, apellido1, apellido2);
            return p;
        } catch (Exception e) {
            // Tomamos linea como inválida
            return null;
        }
    }

    public static String getHeaderTableStringForAlumno() {
        return String.format("|%40s|%40s|%40s|%40s|", "DNI", "Nombre", "Apellido1", "Apellido2");
    }

    public String getAsRowString() {
        return String.format("|%40s|%40s|%40s|%40s|", this.DNI, this.nombre, this.apellido1, this.apellido2);
    }

    public String getInstanceAsDelimitedString(String delimiter) {
        // Cuidado con el Locale en el caso de numeros de coma flotante
        return String.format(Locale.ENGLISH, "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s", DNI, nombre, apellido1, apellido2);
    }

    // Implementación de equals y hashCode
    // insertCode --> equals y hashcode.
    // En este caso se ha establecido que la propiedad que se tendrá en
    // cuenta para establecer dos alumnos iguales es su DNI.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.DNI);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        return Objects.equals(this.DNI, other.DNI);
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

}
