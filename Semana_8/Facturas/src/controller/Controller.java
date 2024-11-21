package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Factura;
import model.Model;
import view.View;

public class Controller {
    Model m;
    View v;

    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        // Inyectamos el controaldor en la vista
        v.setController(this);
    }

    public void init() throws IOException {

        boolean lecturaFichero = m.leerFicheroCSV();
        if (lecturaFichero) v.mostrarMnsgExito();
        else v.mostrarMnsgError();
        
        v.showMainMenu();
    }

    public void filtrarFacturasporValorMin(float valorMinimo) {
        ArrayList<String> facturasFiltras = m.filtrarFacturasporValorMin(valorMinimo);
        v.mostrarFacturasFiltradas(facturasFiltras);
    }

    public void mostrarFacturas() {
        ArrayList<Factura> copiaFacturas = m.copiarFacturas();
        v.mostrarFacturas(copiaFacturas);
    }

    public void exportarHTML(String ficheroHTML) {
        m.exportarHTML(ficheroHTML);
    }

    public void exportarCSV() {
        m.exportarCSV();
    }

    
}
