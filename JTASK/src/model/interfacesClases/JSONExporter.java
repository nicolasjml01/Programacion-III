package model.interfacesClases;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.coti.tools.Rutas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import model.Task;
import model.exceptions.ExporterException;

public class JSONExporter implements IExporter{

    @Override
    public List<Task> importTasks() throws ExporterException {
        File file = Rutas.fileToFileOnDesktop("TasksJSON.json"); // Archivo JSON
        List<Task> taskList = new ArrayList<>();                 // Lista temporal de tareas válidas
    
        if (!file.exists() || !file.isFile()) {
            throw new ExporterException("El archivo JSON no existe o no es un archivo válido.");
        }
        
        try {
            // Leer todo el contenido del archivo como un String
            String jsonContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            
            // Utilizar Gson para convertir el JSON a una lista de objetos Task
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();          

            taskList = gson.fromJson(jsonContent, new TypeToken<List<Task>>() {}.getType());
    
            if (taskList == null || taskList.isEmpty()) {
                throw new ExporterException("El archivo JSON está vacío o no contiene tareas válidas.");
            }
    
        } catch (IOException e) {
            throw new ExporterException("Error al leer el archivo JSON: " + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new ExporterException("Error en el formato del archivo JSON: " + e.getMessage(), e);
        }
    
        return taskList; // Retornar la lista de tareas importadas
    }

    @Override
    public void exportTasks(ArrayList<Task> exportList) throws ExporterException {
        if (exportList == null || exportList.isEmpty()) {
            throw new ExporterException("La lista de tareas está vacía o es nula. No se puede exportar.");
        }

        // Ordenar las tareas por fecha
        sortByDate(exportList);

        // Crear el archivo JSON en el escritorio
        File file = Rutas.fileToFileOnDesktop("TasksJSON.json");

        // Configura Gson con el adaptador de LocalDate como clase interna
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Clase interna
                .setPrettyPrinting()
                .create();

        // Serializar la lista de tareas a JSON
        try (PrintWriter pw = new PrintWriter(file)) {
            String json = gson.toJson(exportList);
            pw.println(json);
        } catch (IOException e) {
            throw new ExporterException("Error al exportar las tareas a JSON: " + e.getMessage(), e);
        }
    }

    // Adaptador de LocalDate como clase interna
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.format(formatter));
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            String dateAsString = in.nextString();
            return LocalDate.parse(dateAsString, formatter);
        }
    }
  
    @Override
    public void sortByDate(ArrayList<Task> task) throws ExporterException {
        try {
        Collections.sort(task, (task1, task2) -> task1.getDate().compareTo(task2.getDate()));
        } catch (Exception e) {
            throw new ExporterException("Error al ordenar las tareas por fecha: " + e.getMessage(), e);
        }
    }
    
}