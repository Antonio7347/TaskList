<%@ page import="org.example.tasklist.model.GestorDeTareas" %>
<%@ page import="org.example.tasklist.model.Tarea" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Tareas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Lista de Tareas</h1>
    <div class="table-responsive mt-4">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Fecha</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                GestorDeTareas gestor = (GestorDeTareas) session.getAttribute("gestorDeTareas");
                if (gestor == null) {
                    gestor = new GestorDeTareas();
                    session.setAttribute("gestorDeTareas", gestor);
                }
                List<Tarea> tareas = gestor.obtenerTodasLasTareas();
                for (Tarea tarea : tareas) {
            %>
            <tr>
                <td><%= tarea.getNombre() %></td>
                <td><%= tarea.getDescripcion() %></td>
                <td><%= tarea.getFecha() %></td>
                <td><%= tarea.isPendiente() ? "Pendiente" : "Completada" %></td>
                <td>
                    <form action="TareaServlet" method="post" class="d-inline">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="nombre" value="<%= tarea.getNombre() %>">
                        <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                    </form>
                    <form action="TareaServlet" method="post" class="d-inline">
                        <input type="hidden" name="accion" value="cambiarEstado">
                        <input type="hidden" name="nombre" value="<%= tarea.getNombre() %>">
                        <button type="submit" class="btn btn-warning btn-sm">Cambiar Estado</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="mt-4">
        <form action="TareaServlet" method="post">
            <input type="hidden" name="accion" value="limpiar">
            <button type="submit" class="btn btn-danger">Limpiar Lista</button>
        </form>
    </div>

    <div class="mt-3">
        <form action="descargarTxt" method="post">
            <button type="submit" class="btn btn-secondary">Descargar lista en TXT</button>
        </form>
    </div>

    <div class="mt-3">
        <a href="index.jsp" class="btn btn-primary">Volver al inicio</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
