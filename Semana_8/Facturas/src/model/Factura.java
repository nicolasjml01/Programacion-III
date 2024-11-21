package model;

import java.util.Locale;

public class Factura {
    private String concepto;
    private float descuentoAplicado;
    private String fecha;
    private float importe;
    private String NIF;
    private String nombre;
    private String direccion;
    private float IVA;

    public Factura(String concepto, float descuentoAplicado, String fecha, float importe, String nIF, String nombre,
            String direccion, float IVA) {
        this.concepto = concepto;
        this.descuentoAplicado = descuentoAplicado;
        this.fecha = fecha;
        this.importe = importe;
        NIF = nIF;
        this.nombre = nombre;
        this.direccion = direccion;
        this.IVA = IVA;
    }

    public static Factura crearFacturaPorString(String linea, String delimitador) {
        String[] trozosFacturas = linea.split(delimitador);
        if (trozosFacturas.length == 8) {
            try {
                // Convertir los valores de String a los tipos apropiados
                String concepto = trozosFacturas[0];
                float descuentoAplicado = Float.parseFloat(trozosFacturas[1]);
                String fecha = trozosFacturas[2];
                float importe = Float.parseFloat(trozosFacturas[3]);
                String NIF = trozosFacturas[4];
                String nombre = trozosFacturas[5];
                String direccion = trozosFacturas[6];
                float iva = Float.parseFloat(trozosFacturas[7]);

                // Crear la factura
                return new Factura(concepto, descuentoAplicado, fecha, importe, NIF, nombre, direccion, iva);

            } catch (NumberFormatException e) {
                // Manejar cualquier excepción en caso de error en el parseo
                System.err.println("Error al parsear la línea: " + linea);
                e.printStackTrace();
            }
        } return null;
    }

    public float calcularValorFactura() {
        return importe * (1 - descuentoAplicado) * (1 + IVA);
    }
    
    public String instanciasDelimitadas(String delimitador) {
        // Construir la cadena con el delimitador y el Locale.ENGLISH para decimales
        return String.format(Locale.ENGLISH, "%s%s%.2f%s%s%s%.2f%s%s%s%s%s%.2f",
                concepto, delimitador,
                descuentoAplicado, delimitador,
                fecha, delimitador,
                importe, delimitador,
                NIF, delimitador,
                nombre, delimitador,
                direccion, delimitador,
                IVA
        );

    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(float descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String nIF) {
        NIF = nIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getIVA() {
        return IVA;
    }

    public void setIVA(float iVA) {
        IVA = iVA;
    }
    
}

