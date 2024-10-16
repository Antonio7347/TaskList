package org.example.tasklist.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.tasklist.model.GestorDeTareas;
import org.example.tasklist.model.Tarea;
import org.example.tasklist.model.GestorDeTareas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "TareaServlet", value = "/TareaServlet")
public class TareaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        GestorDeTareas gestor = (GestorDeTareas) sesion.getAttribute("gestorDeTareas");

        if (gestor == null) {
            gestor = new GestorDeTareas();
            sesion.setAttribute("gestorDeTareas", gestor);
        }
        String accion = request.getParameter("accion");

        boolean operacionExitosa = false;
        String mensaje = "";

        switch (accion) {
            case "agregar":
                operacionExitosa = agregarTarea(request, gestor);
                mensaje = operacionExitosa ? "Tarea agregada con éxito" : "Error al agregar la tarea";
                break;

            case "eliminar":
                operacionExitosa = eliminarTarea(request, gestor);
                mensaje = operacionExitosa ? "Tarea eliminada con éxito" : "Error al eliminar la tarea";
                break;

            case "cambiarEstado":
                operacionExitosa = cambiarEstadoTarea(request, gestor);
                mensaje = operacionExitosa ? "Estado de la tarea cambiado con éxito" : "Error al cambiar el estado de la tarea";
                break;

            case "limpiar":
                gestor.limpiarTareas();
                mensaje = "Lista de tareas limpiada con éxito";
                operacionExitosa = true;
                break;

            default:
                mensaje = "Acción desconocida";
                break;
        }

        sesion.setAttribute("mensaje", mensaje);

        if (operacionExitosa) {
           response.sendRedirect("task.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mensaje);
        }
    }

    private boolean agregarTarea(HttpServletRequest request, GestorDeTareas gestor) {
        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String fechaStr = request.getParameter("fecha");

            // Convertir la fecha
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);

            // Crear y agregar nueva tarea
            Tarea nuevaTarea = new Tarea(nombre, descripcion, fecha);
            gestor.agregarTarea(nuevaTarea);
            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean eliminarTarea(HttpServletRequest request, GestorDeTareas gestor) {
        try {
            String tareaAEliminar = request.getParameter("nombre");
            gestor.eliminarTarea(tareaAEliminar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean cambiarEstadoTarea(HttpServletRequest request, GestorDeTareas gestor) {
        try {
            String tareaACambiar = request.getParameter("nombre");
            gestor.cambiarEstadoTarea(tareaACambiar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
