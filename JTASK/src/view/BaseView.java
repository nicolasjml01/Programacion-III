package view;

import controller.Controller;

public abstract class BaseView {
    
    // Atributo para el controlador
    protected Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public abstract void init(); 
    public abstract void showMessage(); 
    public abstract void showErrorMessage();

}

/*
     public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showMainMenu() {
        // Menu por consola en bucle
        boolean exit = false;
        while (!exit) {
            mostrarMenuOpciones();
            //int opcion = Esdia.readInt("Introduzca una opcion", 1, 11);
            switch(opcion)
            
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
 */