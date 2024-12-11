package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Task implements Serializable{
    private int identifier;
    private String title;
    private LocalDate date;
    private String content;
    private int priority;
    private int estimatedDuration;
    private boolean completed;
    
    public Task(int identifier, String title, String dateString, String content, int priority, int estimatedDuration,
            boolean completed) {
        this.identifier = identifier;
        this.title = title;
        this.date = parseDate(dateString);
        this.content = content;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
        this.completed = completed;
    }
    
    // Método para parsear la fecha
    private LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            // Mostrar un mensaje informativo de error
            System.err.println("Fecha inválida: \"" + dateString);
            return null; // O puedes asignar LocalDate.now() si prefieres
        }
    }

    public String getInstanceAsDelimitedString(String delimiter) {
        String stringDate = parseDateToString(date);
                // Cuidado con el Locale en el caso de numeros de coma flotante
        return String.format(Locale.ENGLISH, "%d" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%d" + delimiter + "%d" + delimiter + "%s", 
                                            identifier, title, stringDate, content, priority, estimatedDuration, completed);
    }
        
    // Método para convertir LocalDate a String
    private String parseDateToString(LocalDate dateToParse) {
        if (dateToParse == null) {
            System.err.println("Error parseando la fecha a String");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateToParse.format(formatter);
    }

        
    public int getIdentifier() {
        return identifier;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = parseDate(date);
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getEstimatedDuration() {
        return estimatedDuration;
    }
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }    
}
