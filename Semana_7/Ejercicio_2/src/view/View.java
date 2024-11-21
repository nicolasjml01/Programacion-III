package view;

import com.coti.tools.Esdia;
import controller.Controller;
import model.Almacen;

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
            int opcion = Esdia.readInt("Introduzca una opcion: ", 1, 5);
            switch(opcion)
            {
                case 1: 
                    if(c.leerTablaVentas() == true) System.out.println("Tabla de ventas leida Correctamente");
                    else System.out.println("ERROR, valores introducidos no validos");
                    break;
                case 2:
                    if (c.leerTablaPrecios() ==  true) System.out.println("Tabal de precios añadida correctamente");
                    else System.out.println("ERROR, valores introducidos no valdios");
                    break;
                case 3:
                    if (c.calcularIngresosTotales() == true) System.out.println("Ingresos Calculados correctamente");
                    else System.out.println("ERROR, no se han añadido ventas o precios");
                    break;
                case 4:
                    c.mostrarResultados();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }
                                       
    private void mostrarMenuOpciones() {
        System.out.println("Menu");
        System.out.println("1.- Leer tabla de ventas");
        System.out.println("2.- Leer tabla de precios");
        System.out.println("3.- Calcular ingresos totales");
        System.out.println("4.- Mostrar resultados");
        System.out.println("5.- Salir");
    }

    public void mostrarResultados(Almacen[] almacenes) {
        for (int i = 0; i < almacenes.length; i++) {
            Almacen almacen = almacenes[i];
            System.out.println("Almacen " + (i + 1) + ":");
            System.out.println("  Ventas Producto 1: " + almacen.getVentasProd1());
            System.out.println("  Ventas Producto 2: " + almacen.getVentasProd2());
            System.out.println("  Precio Total: $" + almacen.getPrecioTotal());
            System.out.println("-----------------------------");
        }
    }
    
    
}
