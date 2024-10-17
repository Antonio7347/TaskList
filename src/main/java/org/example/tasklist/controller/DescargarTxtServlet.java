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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "DescargarTxtServlet", value = "/descargarTxt")
public class DescargarTxtServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        GestorDeTareas gestor = (GestorDeTareas) sesion.getAttribute("gestorDeTareas");

        if (gestor == null || gestor.obtenerTodasLasTareas().isEmpty()) {
            sesion.setAttribute("mensaje", "No hay tareas disponibles para descargar.");
            response.sendRedirect("index.jsp");
            return;
        }

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=lista_tareas.txt");

        PrintWriter out = response.getWriter();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Tarea tarea : gestor.obtenerTodasLasTareas()) {
            out.println("Nombre: " + tarea.getNombre());
            out.println("Descripción: " + tarea.getDescripcion());

            Date fecha = tarea.getFecha();
            String fechaStr = formatter.format(fecha);
            out.println("Fecha: " + fechaStr);

            out.println("Estado: " + (tarea.isPendiente() ? "Pendiente" : "Completada"));
            out.println("-----");
        }

        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
