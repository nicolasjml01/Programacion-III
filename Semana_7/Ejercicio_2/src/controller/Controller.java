package controller;

import model.Almacen;
import model.Model;
import view.View;

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
        v.showMainMenu();
    }

    public boolean leerTablaVentas() {
        return m.leerTablaVentas();
    }

    public boolean leerTablaPrecios() {
        return m.leerTablaPrecios();
    }

    public boolean calcularIngresosTotales() {
        return m.calcularIngresosTotales();
    }

    public void mostrarResultados() {
        Almacen[] copiaAlmacenes = m.obtenerAlmacenes();
        v.mostrarResultados(copiaAlmacenes);
    }
    
}