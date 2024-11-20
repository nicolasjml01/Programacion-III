package com.quiniela.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.coti.tools.Rutas;

public class QuinielaModel {

    ArrayList<Partido> partidos;

    public boolean cargarDatosPartidos() {
        // Leemos archivo equipos.txt de la carpeta datos del escritorio
        // Opcion 1
        // Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "datos",
        // "equipos.txt");

        // Opcion 2 -> Usamos la bibilioteca
        Path ruta = Rutas.pathToFileInFolderOnDesktop("datosProgra", "equipos.txt");
        File f = ruta.toFile();

        // Comprobamos si existe
        if (f.exists() && f.isFile()) {
            // Lo leemos, utilizando files.readallLines
            try {
                List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

                // Crear coleccion de partidos vacia
                ArrayList<Partido> partidos = new ArrayList<>();

                // Para cada linea debemos intentar tranformarla en un objeto Partido
                for (String string : lineas) {
                    // Usamos factory Method
                    Partido p = Partido.createEquipoFromString(string, "-");
                    if (p != null) {
                        partidos.add(p);
                    }
                }

                // Si todo ha ido bien, debemos tener 15 partidos, en cambio si no ha habido un
                // error
                if (partidos.size() == 15) {
                    // Todo ha ido bien, cargo los partidos en el modelo
                    this.partidos = partidos;
                    return true;
                } else {
                    return false;
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ArrayList<Partido> setPartidos() {
        // Devolvemos una copia de los partidos del modelo. Para evitar modificaciones de la vista
        return new ArrayList<Partido>(this.partidos);
    }
}
