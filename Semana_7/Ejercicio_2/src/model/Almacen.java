package model;

public class Almacen {
    private int producto1, producto2;
    private int ventasProd1, ventasProd2;
    private float precioProd1;
    private float precioProd2;
    private float precioTotal = 0;

    public Almacen(int producto1, int producto2, int ventasProd1, int ventasProd2) {
        this.producto1 = producto1;
        this.producto2 = producto2;
        this.ventasProd1 = ventasProd1;
        this.ventasProd2 = ventasProd2;
    }

    public float getPrecioProd1() {
        return precioProd1;
    }

    public void setPrecioProd1(float precioProd1) {
        this.precioProd1 = precioProd1;
    }

    public float getPrecioProd2() {
        return precioProd2;
    }

    public void setPrecioProd2(float precioProd2) {
        this.precioProd2 = precioProd2;
    }

    public int getProducto1() {
        return producto1;
    }

    public void setProducto1(int producto1) {
        this.producto1 = producto1;
    }

    public int getProducto2() {
        return producto2;
    }

    public void setProducto2(int producto2) {
        this.producto2 = producto2;
    }

    public int getVentasProd1() {
        return ventasProd1;
    }

    public void setVentasProd1(int ventasProd1) {
        this.ventasProd1 = ventasProd1;
    }

    public int getVentasProd2() {
        return ventasProd2;
    }

    public void setVentasProd2(int ventasProd2) {
        this.ventasProd2 = ventasProd2;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public float calcularPrecioTotal() {
        float total;
        total = ventasProd1 * precioProd1 + ventasProd2 * precioProd2;
        return total;
    }

    
    
}
