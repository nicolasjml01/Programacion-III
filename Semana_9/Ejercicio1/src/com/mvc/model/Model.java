package com.mvc.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Model {
    ArrayList<Libro> libros;

    // Path a binario
    Path rutaBinario = Paths.get(System.getProperty("user.home"), "Desktop", "libros.bin");
    Path rutaJSON = Paths.get(System.getProperty("user.home"), "Desktop", "libros.json");

    public Model()
    {
        libros = new ArrayList<>();
    }
    public Model(int personasRandom){
        for (int i = 0; i < personasRandom; i++) {
            Libro l = new Libro(""+i, "Libro"+i, "Autor"+i, 1990+i);
            libros.add(l);
        }
    }


    public boolean cargarDatosBinario() {
        /* 
        File f = rutaBinario.toFile();

        if (f.exists() && f.isFile())
        {
            try {
                // Leo el binario
                FileInputStream fileInputStream = new FileInputStream(f);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<Libro> miObjeto = (ArrayList<Libro>) objectInputStream.readObject();
                objectInputStream.close();

                // Guardarlo en el modelo
                this.libros = miObjeto;
                return true;
            } catch (Exception e) {
                // Mala practica no ocultar excepciones
                return false;
            }
            

        }else return false;*/
        return true;
    }

    public boolean salvarDatosBinario() {
        File f = rutaBinario.toFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.libros);
            objectOutputStream.close();
            return true;
        } catch (Exception e) {
            // Mala practica ocultar excepciones
            return false;
        }
    }
    
    public boolean exportarJSON() {
        // Fichero
        try {
            Gson gson = new Gson();
            String json = gson.toJson(libros);
            Files.write(rutaJSON, json.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    public boolean importarLibrosJSON() {
       /*  File f= rutaJSON.toFile();
        
        if(f.exists() && f.isFile())
        {
            Gson gson = new Gson();
            // Lee todo el archivo en un String
            String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
            // Obtiene el tipo de la lista
            Type libros = new TypeToken<List<Persona>>() {}.getType();
            ArrayList<Libro> librosImportados = gson.fromJson(json, libros);

            for (Libro libro: librosImportados) {
                if (!libros.contains(libro))
                {
                    libros.add(libro);
                }
            }
            return true;
        }else return false;*/
        return true;
    }

    // Importar XML


    // Exportar XML
}
