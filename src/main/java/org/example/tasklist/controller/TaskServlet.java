package org.example.tasklist.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tasklist.model.Task;
import org.example.tasklist.model.TaskManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    // Instancia de TaskManager para gestionar las tareas
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "agregar":
                // Agregar nueva tarea
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String fecha = request.getParameter("fecha");
                boolean pendiente = Boolean.parseBoolean(request.getParameter("pendiente"));

                Task nuevaTarea = new Task(nombre, descripcion, fecha, pendiente);
                taskManager.addTask(nuevaTarea);
                break;

            case "eliminar":
                // Eliminar tarea específica
                String nombreEliminar = request.getParameter("nombre");
                taskManager.deleteTask(nombreEliminar);
                break;

            case "limpiar":
                // Limpiar todas las tareas
                taskManager.getPendingTasks().clear();
                break;

            case "descargarTxt":
                // Convertir la lista de tareas en un archivo de texto
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment;filename=tareas.txt");
                PrintWriter out = response.getWriter();
                List<Task> tareasPendientes = taskManager.getPendingTasks();
                for (Task task : tareasPendientes) {
                    out.println(task.getName() + " - " + task.getDescription() + " - " + task.getFecha() + " - " + (task.isPendiente() ? "Pendiente" : "Completada"));
                }
                out.flush();
                return; // Detener la ejecución para evitar el forward

            default:
                break;
        }

        // Actualizar la lista de tareas en la vista (index.jsp)
        request.setAttribute("listaTareas", taskManager.getPendingTasks());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir el GET a la vista principal
        doPost(request, response);
    }
}
