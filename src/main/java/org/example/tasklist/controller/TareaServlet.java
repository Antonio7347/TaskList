package org.example.tasklist.controller;

import org.example.tasklist.model.Task;

import javax.gitservlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TareaServlet")
public class TareaServlet extends HttpServlet {

    // Lista que almacenará las tareas
    private List<Tarea> listaTareas = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "agregar":
                // Agregar nueva tarea
                String descripcion = request.getParameter("descripcion");
                listaTareas.add(new Tarea(descripcion, true)); // Asumimos que una nueva tarea está pendiente
                break;

            case "eliminar":
                // Eliminar tarea específica
                String descripcionEliminar = request.getParameter("descripcion");
                listaTareas.removeIf(tarea -> tarea.getDescripcion().equals(descripcionEliminar));
                break;

            case "limpiar":
                // Limpiar todas las tareas
                listaTareas.clear();
                break;

            case "descargarTxt":
                // Convertir la lista de tareas en un archivo de texto
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment;filename=tareas.txt");
                PrintWriter out = response.getWriter();
                for (Tarea tarea : listaTareas) {
                    out.println(tarea.getDescripcion() + " - " + (tarea.isPendiente() ? "Pendiente" : "Completada"));
                }
                out.flush();
                return; // Detener la ejecución para evitar el forward

            default:
                break;
        }

        // Actualizar la lista de tareas en la vista (index.jsp)
        request.setAttribute("listaTareas", listaTareas);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir el GET a la vista principal
        doPost(request, response);
    }
}
