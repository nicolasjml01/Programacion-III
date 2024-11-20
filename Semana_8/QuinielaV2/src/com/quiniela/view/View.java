package com.quiniela.view;

import java.util.ArrayList;

import com.coti.tools.Esdia;
import com.quiniela.controller.Controller;
import com.quiniela.model.Partido;

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
            int opcion = Esdia.readInt("Introduzca una opcion", 1, 4);
            switch(opcion)
            {
                case 1:
                    LeerFicheroPartidos();
                    break;
                case 2:
                    // Pedimos resultados cada partidos
                    ArrayList<Partido> partidos = c.getPartidos();
                    for (Partido partido : partidos) {
                        System.out.println("Partido: " + partido.getGolesEquipoLocal() + "-" + partido.getGolesEquipoVisitante());
                        int golesLocal = Esdia.readInt("Goles Local: ");
                        int golesVisitante = Esdia.readInt("Goles Visitante: ");
                        partido.setGolesEquipoLocal(golesLocal);
                        partido.setGolesEquipoVisitante(golesVisitante);
                    }
                    c.setPartidos();
                case 3:
                    break;
                case 4:
                    exit = true;
                    break;

            }
        }
    }

    private void mostrarMenuOpciones() {
        System.out.println("Menu Quiniela...");
        System.out.println("1) Cargar datos de fichero del escritorio");
        System.out.println("2) Pedir resultados al usuario");
        System.out.println("3) Mostrar resultados al usuario");
        System.out.println("4) Salir");
    }

    private void LeerFicheroPartidos() {
        // Cargar Fichero del escritorio
        if(c.cargarEquiposDeFichero())
        {
            System.out.println("Fichero cargado con exito");
        }else
        {
            System.err.println("Error Cargando");
        }
    }

    public void mostrarMnsg(String string) {
        System.out.println(string);
    }

    public void mostrarMnsgError(String string) {
        System.out.println(string);
    }
}
