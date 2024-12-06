package view;

import controller.Controller;

/**
 *
 * Se trata de una clase abstracta que marcará los métodos mínimos que se deben implementar
 * para interactuar con el usuario.
 * 
 * - Mostrar la inicialización del programa.
 * - Mostrar un menú.
 * - Mostrar una finalización del programa.
 * 
 * 
 * @author Loza
 */
public abstract class AppView {
    
    protected Controller c;

    public abstract void mostrarInicio(String msgBienvenida);
    
    public abstract void mostrarMenuPrincipal();
    
    public abstract void mostrarFinPrograma(String msgDespedida);

    public void setController(Controller c) {
        this.c = c;
    }

}
