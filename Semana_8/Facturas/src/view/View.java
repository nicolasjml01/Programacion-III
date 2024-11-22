package view;

import java.util.ArrayList;
import com.coti.tools.Esdia;
import controller.Controller;
import model.Factura;

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
            int opcion = Esdia.readInt("Introduzca una opcion", 1, 5);
            switch(opcion)
            {
                case 1: 
                    valoresFacturasMayor();                        
                    break;
                case 2:
                    c.mostrarFacturas();
                    break;
                case 3:  
                    exportarHTML();                       
                    break;
                case 4:
                    exportarCSV();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }
    private void mostrarMenuOpciones() {
        System.out.println("Menu");
        System.out.println("1. Sacar Facturas a partir de un importe");
        System.out.println("2. Mostrar listado de facturas");
        System.out.println("3. Exportar a HTML");
        System.out.println("4. Exportar a CSV");
        System.out.println("5. Salir");
    }

    public void mostrarMnsgError() {
        System.out.println("ERROR, al importar/exportar el fichero");
    }

    public void mostrarMnsgExito() {
        System.out.println("Lectura realizada satisfactoriamente");
    }

    private void valoresFacturasMayor()
    {
        float valorMinimo = Esdia.readFloat("Introduce el valor minimo de las facturas a buscar: ");
        c.filtrarFacturasporValorMin(valorMinimo);
    }

    public void mostrarFacturasFiltradas(ArrayList<String> facturasFiltras) {
        if (facturasFiltras.isEmpty()) System.out.println("No existen facturas mayores al valor dado");
        else{
            System.out.println("Listado de clientes con facturas mayores");
            for (String clientesFiltrados : facturasFiltras)
            {
                System.out.println("Nombre cliente: " + clientesFiltrados);
            }
        }
    }

    public void mostrarFacturas(ArrayList<Factura> copiaFacturas) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Nombre\tDescuento\tfecha\tImporte\tNIF\tNombre\tDireccion\tIVA\tFactura Total");
        for (Factura f: copiaFacturas)
        {
            System.out.printf("%s\t%.2f\t%s\t%.2f\t%s\t%s\t%s\t%.2f\t%.2f\n" ,f.getConcepto(), f.getDescuentoAplicado(), f.getFecha(), f.getImporte(),
                                                                        f.getNIF(), f.getNombre(), f.getDireccion(), f.getIVA(), f.calcularValorFactura());    
        }
        System.out.println("-----------------------------------------------------------------------------");

    }

    private void exportarHTML()
    {
        String ficheroHTML = Esdia.readString("Introduzca el nombre del fichero HTML: ");
        c.exportarHTML(ficheroHTML);
    }

    private void exportarCSV()
    {
        String delimitador = Esdia.readString("Introduzca el delimitador: ");
        c.exportarCSV(delimitador);
    }
    
}
