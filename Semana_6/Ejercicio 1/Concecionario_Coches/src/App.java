import java.util.ArrayList;

import model.Coche;
import static com.coti.tools.Esdia.*;

public class App {

    private static ArrayList<Coche> coches = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        int opcion;
        do {
            System.out.println("\n--- MENU CRUD ---");
            System.out.println("1. Agregar coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Modificar coche");
            System.out.println("4. Listar coches");
            System.out.println("5. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    agregarCoche();
                    break;
                case 2:
                    eliminarCcoche();
                    //eliminarPersonaConIterator();
                    break;
                case 3:
                    modificarCoche();
                    break;
                case 4:
                    listarCoches();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void agregarCoche() {
        String marca = readString("Ingrese la marca del coche: ");
        String modelo = readString("Introduzca el modelo del coche: ");
        String matricula = readString("Ingrese la matricula del coche: ");
        coches.add(new Coche(marca, modelo, matricula));
    }

    private static void eliminarCcoche() {
        String matricula = readString("Ingrese la matricula del coche que desea eliminar: ");
        Coche cocheAEliminar = null;

        for(Coche coche : coches)
        {
            if (coche.getMatricula().equals(matricula)){
                cocheAEliminar = coche;
                break;
            }
        }
        if (cocheAEliminar != null)
        {
            coches.remove(cocheAEliminar);
            System.out.println("Coche con matricula: " + cocheAEliminar.getMatricula() + "eliminado correctamente");
        }else 
        {
            System.out.println("Matricula no encontrada");
        }
    }

    private static void modificarCoche() {
        String matricula = readString("Ingrese la matricula del coche que desea modificar: ");
        Coche cocheAModificar = null;

        for(Coche coche : coches)
        {
            if (coche.getMatricula().equals(matricula)){
                cocheAModificar = coche;
                break;
            }
        }
        if (cocheAModificar != null)
        {
            String nuevaMarca = readString("Ingrese la marca del vehiculo: ");
            String nuevoModelo = readString("Introduzca el modelo del vehiculo: ");
            String nuevaMatricula = readString("Introduzca la matricula del vehiculo: ");

            cocheAModificar.setMarca(nuevaMarca);
            cocheAModificar.setModelo(nuevoModelo);
            cocheAModificar.setMatricula(nuevaMatricula);
        }else 
        {
            System.out.println("Matricula no encontrada");
        }
    }
    /*
    */
    private static void listarCoches() {
        if (coches.isEmpty())
        {
            System.out.println("No hay coches en en concesionario.");
            return;
        }else
        {
            for (Coche coche : coches)
            {
                System.out.println(coche.getEstadoComoString());
            }
        }
    }
}
