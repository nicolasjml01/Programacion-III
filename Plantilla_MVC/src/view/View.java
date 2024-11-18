package view;

import com.coti.tools.Esdia;
import controller.Controller;

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
                    break;
                case 2:
                    break;
            }
        }
    }
    private void mostrarMenuOpciones() {
        System.out.println("Menu");
        System.out.println("1. ");
        System.out.println("2. ");
    }
}
