package view;

import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import java.util.List;
import model.Alumno;

/**
 *
 * @author Loza
 */
public class ConsolaSubMenusView extends AppView {

    @Override
    public void mostrarInicio(String msgBienvenida) {
        System.out.println(msgBienvenida);
    }

    @Override
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENU ALUMNOS SUBMENUS ---");
            System.out.println("1. Altas y bajas");
            System.out.println("2. Importación/Exportación");
            System.out.println("3. Listar alumnos");
            System.out.println("4. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    subMenuCRUD();
                    break;
                case 2:
                    subMenuImportacionExportacion();
                    break;
                case 3:
                    subMenuListados();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    @Override
    public void mostrarFinPrograma(String msgDespedida) {
        System.out.println(msgDespedida);
    }

    private void subMenuCRUD() {
        int opcion;
        do {
            System.out.println("\n--- SUBMENU ALTAS Y BAJAS ---");
            System.out.println("1. Alta alumno");
            System.out.println("2. Baja alumno por DNI");
            System.out.println("3. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    agregarAlumno();
                    break;
                case 2:
                    eliminarAlumnoPorDNI();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void subMenuImportacionExportacion() {
        int opcion;
        do {
            System.out.println("\n--- SUBMENU IMPORTACIÓN EXPORTACIÓN ---");
            System.out.println("1. Exportar");
            System.out.println("2. Importar");
            System.out.println("3. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    exportarAlumnos();
                    break;
                case 2:
                    importarAlumnos();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void subMenuListados() {
        int opcion;
        do {
            System.out.println("\n--- SUBMENU LISTADOS ---");
            System.out.println("1. Listar alumnos");
            System.out.println("2. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    listarAlumnos();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
    }

    private void agregarAlumno() {

        System.out.println("Introduzca los datos del nuevo alumno:");
        String DNI = readString_ne("DNI:");
        String nombre = readString("Nombre:");
        String apellido1 = readString("Primer apellido:");
        String apellido2 = readString("Segundo apellido:");
        if (c.agregarAlumno(new Alumno(DNI, nombre, apellido1, apellido2))) {
            System.out.println("Alumno agregado con exito");
        } else {
            // Veremos alternativas para dar más información de como modificar esto para ser
            // más informativos.
            System.out.println("No se pudo agregar el alumno con esos datos.");
        }

    }

    private void eliminarAlumnoPorDNI() {
        System.out.println("Introduzca el DNI que quiere eliminar:");
        String DNI = readString_ne("DNI:");
        if (c.eliminarAlumnoPorDNI(DNI)) {
            System.out.println("Alumno eliminado con exito");
        } else {
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
