package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.coti.tools.Rutas;

public class Model {

    ArrayList<Factura> factura = new ArrayList<>();

    public boolean leerFicheroCSV() throws IOException {
        Path ruta = Rutas.pathToFileInFolderOnDesktop("datosProgra", "facturas.tsv");
        File f = ruta.toFile();

        if (f.exists() && f.isFile())
        {
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas)
            {
                Factura fact = Factura.crearFacturaPorString(linea, "\t");
                if (fact != null) factura.add(fact);
            }
            return true;
        } 
        return false;
    }

    public ArrayList<String> filtrarFacturasporValorMin(float valorMinimo) {
        ArrayList<String> facturasFiltradas = new ArrayList<>();
        for (Factura fac : factura)
        {
            float valorFactura = fac.calcularValorFactura();
            if (valorFactura > valorMinimo)
            {
                facturasFiltradas.add(fac.getNombre());
            }
        }
            return facturasFiltradas;
    }

    public ArrayList<Factura> copiarFacturas() {
        return factura;
    }

    public void exportarHTML(String ficheroHTML) {
        // Plantilla HTML con un marcador %%TABLA%% para insertar la tabla de facturas
        String plantillaHTML = """
            <html>
            <head>
                <title>Listado de Facturas</title>
                <style>
                    table { width: 100%; border-collapse: collapse; }
                    th, td { border: 1px solid black; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <h1>Listado de Facturas</h1>
                <table>
                    <tr>
                        <th>Concepto</th>
                        <th>Descuento</th>
                        <th>Fecha</th>
                        <th>Importe</th>
                        <th>NIF</th>
                        <th>Nombre</th>
                        <th>Dirección</th>
                        <th>Valor Total</th>
                    </tr>
                    %%TABLA%%
                </table>
            </body>
            </html>
            """;

        // Generar la tabla HTML con las facturas
        StringBuilder tablaHTML = new StringBuilder();
        for (Factura fac : factura) {
            float valorTotal = fac.calcularValorFactura();
            tablaHTML.append("<tr>")
                     .append("<td>").append(fac.getConcepto()).append("</td>")
                     .append("<td>").append(fac.getDescuentoAplicado()).append("</td>")
                     .append("<td>").append(fac.getFecha()).append("</td>")
                     .append("<td>").append(fac.getImporte()).append("</td>")
                     .append("<td>").append(fac.getNIF()).append("</td>")
                     .append("<td>").append(fac.getNombre()).append("</td>")
                     .append("<td>").append(fac.getDireccion()).append("</td>")
                     .append("<td>").append(valorTotal).append("</td>")
                     .append("</tr>");
        }

        // Reemplazar el marcador %%TABLA%% con la tabla generada
        String contenidoFinal = plantillaHTML.replace("%%TABLA%%", tablaHTML.toString());

        // Guardar el contenido en un archivo HTML
        try (FileWriter escritor = new FileWriter(ficheroHTML)) {
            escritor.write(contenidoFinal);
            System.out.println("Archivo HTML generado correctamente: " + ficheroHTML);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo HTML: " + e.getMessage());
        }
    }

    public void exportarCSV(String delimitador) {
        Path ruta = Rutas.pathToFileInFolderOnDesktop("datosProgra", "facturas.tsv");
        System.out.println(ruta.toString());

        ArrayList<String> lineas = new ArrayList<>();

        for (Factura facturasExportar: factura)
        {
            lineas.add(facturasExportar.instanciasDelimitadas(delimitador));
        }
        try {
            Files.write(ruta, lineas, StandardCharsets.UTF_8);
            System.out.println("Datos exportados correctamente a: " + ruta.toFile().getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Ocurrió un error al intentar exportar las personas: " + e.getMessage());
        }
    }
   
}
