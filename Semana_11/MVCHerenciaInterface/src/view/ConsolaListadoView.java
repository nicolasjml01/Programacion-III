package view;

import static com.coti.tools.Esdia.*;
import controller.Controller;
import java.util.List;
import model.Alumno;

/**
 *
 * Esta clase implementa una vista pero con un listado único, sin submenus.
 * Hereda de AppView e implementa sus métodos abstractos. Además tiene otros
 * propios específicos.
 * 
 * 
 * @author Loza
 */
public class ConsolaListadoView extends AppView {


    @Override
    public void mostrarInicio(String msgBienvenida) {
        System.out.println(msgBienvenida);
    }

    @Override
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENU ALUMNOS LISTADO COMPLETO ---");
            System.out.println("1. Agregar Alumno");
            System.out.println("2. Eliminar Alumno por DNI");
            System.out.println("3. Importar alumnos");
            System.out.println("4. Exportar alumnos");
            System.out.println("5. Listar alumnos");
            System.out.println("6. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    agregarAlumno();
                    break;
                case 2:
                    eliminarAlumnoPorDNI();
                    break;
                case 3:
                    importarAlumnos();
                    break;
                case 4:
                    exportarAlumnos();
                    break;
                case 5:
                    listarAlumnos();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    @Override
    public void mostrarFinPrograma(String msgDespedida) {
        System.out.println(msgDespedida);
    }

    private void agregarAlumno() {
        
        System.out.println("Introduzca los datos del nuevo alumno:");
        String DNI = readString_ne("DNI:");
        String nombre = readString("Nombre:");
        String apellido1 = readString("Primer apellido:");
        String apellido2 = readString("Segundo apellido:");
        if(c.agregarAlumno(new Alumno(DNI, nombre, apellido1, apellido2))){
            System.out.println("Alumno agregado con exito");
        }else{
            // Veremos alternativas para dar más información de como modificar esto para ser
            // más informativos.
            System.out.println("No se pudo agregar el alumno con esos datos.");
        }
        
    }

    private void eliminarAlumnoPorDNI() {
        System.out.println("Introduzca el DNI que quiere eliminar:");
        String DNI = readString_ne("DNI:");
        if(c.eliminarAlumnoPorDNI(DNI)){
            System.out.println("Alumno eliminado con exito");
        }else{
            // Veremos alternativas para dar más información de como modificar esto para ser
            // más informativos.
            System.out.println("No se pudo eliminar el alumno con ese DNI.");
        }
    }

    private void importarAlumnos() {
        if(c.importarAlumnos()){
            System.out.println("Exportación realizada con exito");
        }else{
            // Veremos alternativas para dar más información de como modificar esto para ser
            // más informativos.
            System.out.println("Hubo un error en la importación");
        } 
    }

    private void exportarAlumnos() {
        if(c.exportarAlumnos()){
            System.out.println("Exportación realizada con exito");
        }else{
            // Veremos alternativas para dar más información de como modificar esto para ser
            // más informativos.
            System.out.println("Hubo un error en la exportación");
        }
    }

    private void listarAlumnos() {
        List<Alumno> alumnos = c.obtenerAlumnosDelModelo();
        System.out.println(Alumno.getHeaderTableStringForAlumno());
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.getAsRowString());
        }
    }

}
