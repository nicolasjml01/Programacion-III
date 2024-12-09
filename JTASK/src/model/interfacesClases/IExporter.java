package model.interfacesClases;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import model.exceptions.ExporterException;

public interface IExporter {
    // Importa las tareas
    List<Task> importTasks() throws ExporterException;

    // Exporta las tareas
    void exportTasks(ArrayList<Task> exportList) throws ExporterException;

    // Ordenarlas por fecha
    void sortByDate(ArrayList<Task> task) throws ExporterException;
}

