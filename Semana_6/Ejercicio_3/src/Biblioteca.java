import java.util.HashMap;

import com.coti.tools.Esdia;

public class Biblioteca {
    private HashMap<String, Libro> libros;

    public Biblioteca() {
        libros = new HashMap<>();
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        
        int opcion;

        do {
            System.out.println("\n--- Menú de Biblioteca ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver libro");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Mostrar todos los libros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Esdia.readInt("Introduzca una opcion: ");

            switch (opcion) {
                case 1:
                    // Agregar libro
                    String titulo = Esdia.readString("Ingrese el titulo del libro: ");
                    String autor = Esdia.readString("Ingrese el autor del libro: ");
                    String isbn = Esdia.readString("Ingrese el ISBN del libro: ");
                    Libro libro = new Libro(titulo, autor, isbn);
                    biblioteca.agregarLibro(libro);
                    break;

                case 2:
                    // Ver libro
                    isbn = Esdia.readString("Ingrese el ISBN del libro a consultar: ");
                    biblioteca.verLibro(isbn);
                    break;

                case 3:
                    // Actualizar libro
                    isbn = Esdia.readString("Ingrese el ISBN del libro a actualizar: ");
                    String nuevoTitulo = Esdia.readString("Ingrese el nuevo título: ");
                    String nuevoAutor = Esdia.readString("Ingrese el nuevo autor: ");
                    biblioteca.actualizarLibro(isbn, nuevoTitulo, nuevoAutor);
                    break;

                case 4:
                    // Eliminar libro
                    isbn = Esdia.readString("Ingrese el ISBN del libro a eliminar: ");
                    biblioteca.eliminarLibro(isbn);
                    break;

                case 5:
                    // Mostrar todos los libros
                    biblioteca.mostrarLibros();
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    // Crear un nuevo libro
    public void agregarLibro(Libro libro) {
        if (libros.containsKey(libro.getIsbn())) {
            System.out.println("El libro con el ISBN " + libro.getIsbn() + " ya existe.");
        } else {
            libros.put(libro.getIsbn(), libro);
            System.out.println("Libro agregado exitosamente.");
        }
    }

    // Leer un libro por su ISBN
    public void verLibro(String isbn) {
        if (libros.containsKey(isbn)) {
            System.out.println(libros.get(isbn));
        } else {
            System.out.println("No se encontró el libro con el ISBN " + isbn);
        }
    }

    // Actualizar información de un libro
    public void actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor) {
        if (libros.containsKey(isbn)) {
            Libro libro = libros.get(isbn);
            libro = new Libro(nuevoTitulo, nuevoAutor, isbn); // Crea un nuevo objeto con la nueva información
            libros.put(isbn, libro);
            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ISBN " + isbn);
        }
    }

    // Eliminar un libro
    public void eliminarLibro(String isbn) {
        if (libros.containsKey(isbn)) {
            libros.remove(isbn);
            System.out.println("Libro eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ISBN " + isbn);
        }
    }

    // Mostrar todos los libros
    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            libros.forEach((isbn, libro) -> System.out.println(libro));
        }
    }
}
