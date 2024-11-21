package model;

import com.coti.tools.Esdia;

public class Model {

    private Almacen[] almacenes = new Almacen[5];

    public boolean leerTablaVentas() {

        for (int i = 0; i < almacenes.length; i++) {
            almacenes[i] = new Almacen(0, 0, 0, 0);
        }

        for (int i = 0; i < almacenes.length; i++) {
            System.out.println("\nIngresando ventas para el Almacen " + (i + 1) + ":");

            int ventasProd1 = Esdia.readInt("Ingrese las ventas del Producto 1: ");
            almacenes[i].setVentasProd1(ventasProd1);

            int ventasProd2 = Esdia.readInt("Ingrese las ventas del Producto 2: ");
            almacenes[i].setVentasProd2(ventasProd2);
        }
        return true;
    }

    public boolean leerTablaPrecios() {
        
        float precioProducto1 = Esdia.readFloat("Introduce el precio para el producto 1: ");
        float precioProducto2 = Esdia.readFloat("Introduce el precio para el producto 2: ");

        // Asigna los precios a cada almacén
        for (Almacen almacen : almacenes) {
            almacen.setPrecioProd1(precioProducto1);
            almacen.setPrecioProd2(precioProducto2);
        }       
        return true;
    }

    public boolean calcularIngresosTotales() {
        try {
            for (int i = 0; i < almacenes.length; i++) {
                float resultado = almacenes[i].calcularPrecioTotal();
                almacenes[i].setPrecioTotal(resultado);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public Almacen[] obtenerAlmacenes() {
        return almacenes;
    }
    
}
