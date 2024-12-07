package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    private LocalDate parseDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida: " + date);
        }
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
    public void setDate(LocalDate date) {
        this.date = date;
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
