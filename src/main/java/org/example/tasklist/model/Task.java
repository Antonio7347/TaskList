package org.example.tasklist.model;

public class Task {
    String name;
    String description;
    String fecha;
    boolean pendiente;

    public Task(){

    }

    public Task(String name, String description, String fecha, boolean pendiente) {
        this.name = name;
        this.description = description;
        this.fecha = fecha;
        this.pendiente = pendiente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fecha='" + fecha + '\'' +
                ", pendiente=" + pendiente +
                '}';
    }
}
