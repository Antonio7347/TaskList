package org.example.tasklist.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.tasklist.model.GestorDeTareas;
import org.example.tasklist.model.Tarea;

import java.io.IOException;

@WebServlet("/TareaServlet")
public class TareaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        GestorDeTareas gestor = (GestorDeTareas) sesion.getAttribute("gestorDeTareas");

        if (gestor == null) {
            gestor = new GestorDeTareas();
            sesion.setAttribute("gestorDeTareas", gestor);
        }

        String accion = request.getParameter("accion");

        switch (accion) {
            case "agregar":
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String fecha = request.getParameter("fecha");
                Tarea nuevaTarea = new Tarea(nombre, descripcion, fecha);
                gestor.agregarTarea(nuevaTarea);
                break;
            case "eliminar":
                String tareaAEliminar = request.getParameter("nombre");
                gestor.eliminarTarea(tareaAEliminar);
                break;
            case "cambiarEstado":
                String tareaACambiar = request.getParameter("nombre");
                gestor.cambiarEstadoTarea(tareaACambiar);
                break;
            case "limpiar":
                gestor.limpiarTareas();
                break;
        }

        response.sendRedirect("listaTareas.jsp");
    }
}
