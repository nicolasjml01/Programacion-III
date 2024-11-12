package com.mvc.view;

import com.coti.tools.Esdia;
import com.mvc.controller.Controller;

public class View {
    Controller c;

    public void setController(Controller controller) {
        this.c = controller;
    }

    public void showMainMenu() {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            mostrarMenuOpciones();
            int opcion = Esdia.readInt("Introduzca una opcion", 1, 11);
            switch(opcion)
                        {
                            case 1:  
                                System.out.println("No implemented");                              
                                break;
                            case 2:
                                System.out.println("No implemented");
                                break;
                            case 3:
                                System.out.println("No implemented");
                                break;
                            case 4:
                                System.out.println("No implemented");
                                break;
                            case 5:

                                break;
                            case 6:
                                c.expostarJSON();
                                break;
                            case 7:
                                break;
                            case 8:
                                if (c.importarLibrosJSON())
                                {
                                    System.out.println("Se ha importado correctamente");
                                }else
                                {
                                    System.err.println("Problema Leyendo JSON");
                                }
                                break;
                            case 9:
                                break;
                            case 10:
                                break;
                            case 11:
                                exit = true;
                                break;
            
                        }
                    }
                }
            
    private void mostrarMenuOpciones() {
        System.out.println("Menu Quiniela...");
        System.out.println("1. Mostrar el listado de libros en formato tabla.");
        System.out.println("2. Añadir nuevo libro al modelo");
        System.out.println("3. Eliminar libro de una lista de libros disponibles");
        System.out.println("4. Modificar libro de una lista de libros disponibles");
        System.out.println("5. Exportar libros en formato delimitado por comas CSV.");
        System.out.println("6. Exportar libros en formato JSON");
        System.out.println("7. Exportar libros en formato XML");
        System.out.println("8. Importar libros de un fichero JSON");
        System.out.println("9. Importar libros de un fichero XML");
        System.out.println("10. Importar libros de un fichero en formato delimitado por comas CSV");
        System.out.println("11. Salir del programa.");
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void showError(String string) {
        System.out.println(string);
    }
}
