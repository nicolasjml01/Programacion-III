import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        int numAlumnos = Esdia.readInt("Introduzca el numeros de alumnos: ");
        Alumno[] alumnos = new Alumno[numAlumnos];

        for (int i = 0; i < numAlumnos; i++)
        {
            System.out.println();
            System.out.println("Datos del alumno " + (i+1) + ":");
            String nombre = Esdia.readString("Introduzca el nombre del alumno: ");
            float parcial1 = Esdia.readFloat("Introduca la nota del primer parcial: ");
            float parcial2 = Esdia.readFloat("Introduca la nota del segundo parcial: ");
            float examenfinal = Esdia.readFloat("Introduca la nota del examen final: ");

            alumnos[i] = new Alumno(nombre, parcial1, parcial2, examenfinal);
        }

        // Mostrar las calificaciones
        double sumaParcial1 = 0, sumaParcial2 = 0, sumaFinalExamen = 0, sumaNotaFinal = 0;
        System.out.println();
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", "Nombre", "Parcial 1", "Parcial 2", "Final", "Nota Final");
        System.out.println("------------------------------------------------------------");

        for (Alumno alumno : alumnos) {
            System.out.printf("%-15s %-10.2f %-10.2f %-10.2f %-10.2f%n",
                    alumno.nombre, alumno.parcial1, alumno.parcial2, alumno.finalExamen, alumno.notaFinal);
            sumaParcial1 += alumno.parcial1;
            sumaParcial2 += alumno.parcial2;
            sumaFinalExamen += alumno.finalExamen;
            sumaNotaFinal += alumno.notaFinal;
        }

        // Calcular las medias
        double mediaParcial1 = sumaParcial1 / numAlumnos;
        double mediaParcial2 = sumaParcial2 / numAlumnos;
        double mediaFinalExamen = sumaFinalExamen / numAlumnos;
        double mediaNotaFinal = sumaNotaFinal / numAlumnos;

        // Mostrar las medias
        System.out.println("\nMedias de la clase:");
        System.out.printf("Media Parcial 1: %.2f%n", mediaParcial1);
        System.out.printf("Media Parcial 2: %.2f%n", mediaParcial2);
        System.out.printf("Media Final: %.2f%n", mediaFinalExamen);
        System.out.printf("Media Nota Final: %.2f%n", mediaNotaFinal);
    }
}
