package com.mvc.controller;

import com.mvc.model.Model;
import com.mvc.view.View;

public class Controller {
    Model m;
    View v;

    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        // Inyectamos el controlador a la vista
        v.setController(this);
    }

    public void init() {
        // Iniciar la aplicacion -> Comprobar si existe fichero bin y cargar registros
        // Se debe notificar a la vista si va bien o mal
        if (m.cargarDatosBinario())
        {
            //Cargar el binario
            v.showMessage("Se han cargado libros del binario");
        }else
        {
            v.showError("Ha habido un error en la lectura del binario");
        }
        
        // Mostrar Menu al usuario
        v.showMainMenu();

        // Finalizacion de la app
        // Volcar el fichero bin la info del modelo
        // Notificar a la vista si ha ido bien o mal
        if (m.salvarDatosBinario())
        {
            v.showMessage("Datos guardados correctamente");
        }else
        {
            v.showError("Error volcando informacion de la APP");
        }

    }

    public boolean expostarJSON() {
        return m.exportarJSON();
    }

    public boolean importarLibrosJSON() {
        return m.importarLibrosJSON();
    }

}
