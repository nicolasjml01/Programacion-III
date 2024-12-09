package model.interfacesClases;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.coti.tools.Rutas;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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
          Gson gson = new Gson();
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
        // Comprobamos si la lista de tareas está vacía
        if (exportList == null || exportList.isEmpty()) {
            throw new ExporterException("La lista de tareas está vacía o es nula. No se puede exportar.");
        }
    
        // Ordenamos la lista por fecha
        sortByDate(exportList);
    
        // Creamos el archivo JSON en el escritorio
        File file = Rutas.fileToFileOnDesktop("TasksJSON.json");
    
        // Serializamos la lista de tareas a JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(exportList);
    
        // Escribimos el contenido JSON en el archivo
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(json);
        } catch (IOException e) {
            throw new ExporterException("Error al exportar las tareas a JSON: " + e.getMessage(), e);
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