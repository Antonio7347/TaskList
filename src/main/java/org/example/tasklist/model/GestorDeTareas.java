package org.example.tasklist.model;

import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas {
    private List<Tarea> tareas;

    public GestorDeTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public List<Tarea> obtenerTareasPendientes() {
        List<Tarea> tareasPendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.isPendiente()) {
                tareasPendientes.add(tarea);
            }
        }
        return tareasPendientes;
    }

    public void eliminarTarea(String nombre) {
        tareas.removeIf(t -> t.getNombre().equalsIgnoreCase(nombre));
    }

    public void limpiarTareas() {
        tareas.clear();
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return tareas;
    }

    public void cambiarEstadoTarea(String nombre) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombre)) {
                tarea.setPendiente(!tarea.isPendiente());
                break;
            }
        }
    }
}
