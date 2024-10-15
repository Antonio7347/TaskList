package org.example.tasklist.model;

public class Tarea {
    private String nombre;
    private String descripcion;
    private String fecha;
    private boolean pendiente;

    public Tarea(String nombre, String descripcion, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.pendiente = true;  // Todas las tareas comienzan como pendientes
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}

