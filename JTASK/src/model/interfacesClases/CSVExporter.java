package model.interfacesClases;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.coti.tools.Rutas;
import model.Task;
import model.exceptions.ExporterException;

public class CSVExporter implements IExporter{

    @Override
    public List<Task> importTasks() throws ExporterException {
        File file = Rutas.fileToFileOnDesktop("TasksCSV.csv");
        List<Task> taskList = new ArrayList<>(); // Lista temporal de tareas válidas

        if (!file.exists() || !file.isFile()) {
            throw new ExporterException("El archivo no existe o no es un archivo válido.");
        }

        try {
            List<String> lines = Files.readAllLines(file.toPath());

            if (lines.isEmpty()) {
                throw new ExporterException("El archivo está vacío.");
            }

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] taskData = line.split("\t");

                // Validar que la línea tenga 7 elementos
                if (taskData.length != 7) {
                    throw new ExporterException("Formato inválido en la línea " + (i + 1));
                }

                try {
                    // Crear el objeto Task
                    int identifier = Integer.parseInt(taskData[0]);
                    String title = taskData[1];
                    String dateString = taskData[2];
                    String content = taskData[3];
                    int priority = Integer.parseInt(taskData[4]);
                    int estimatedDuration = Integer.parseInt(taskData[5]);
                    boolean completed = Boolean.parseBoolean(taskData[6]);

                    Task task = new Task(identifier, title, dateString, content, priority, estimatedDuration, completed);
                    taskList.add(task);

                } catch (NumberFormatException e) {
                    throw new ExporterException("Error de formato en la línea " + (i + 1) + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new ExporterException("Error al leer el archivo: " + e.getMessage(), e);
        }

        return taskList; // Retornar la lista de tareas
    }

    @Override
    public void exportTasks(ArrayList<Task> exportList) throws ExporterException {
        // Comprobamos si la lista de tareas está vacía
        if (exportList == null || exportList.isEmpty()) {
            throw new ExporterException("La lista de tareas está vacía o es nula. No se puede exportar.");
        }

        // Ordenamos la lista por fecha
        sortByDate(exportList);

        // Ruta donde se guardará el archivo
        File file = Rutas.fileToFileOnDesktop("TasksCSV.csv");
        final String DELIMITADOR = "\t";

        // Creamos una lista temporal para almacenar las tareas como cadenas delimitadas
        ArrayList<String> tmp = new ArrayList<>();

        // Añadimos las tareas a la lista temporal usando el método getInstanceAsDelimitedString
        for (Task task : exportList) {
            String taskData = task.getInstanceAsDelimitedString(DELIMITADOR);
            tmp.add(taskData);
        }

        try {
            Files.write(file.toPath(), tmp, Charset.defaultCharset(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            throw new ExporterException("Error al exportar las tareas a CSV: " + ex.getMessage(), ex);
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
