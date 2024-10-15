<%--
  Created by IntelliJ IDEA.
  User: Joseb
  Date: 15/10/2024
  Time: 10:25 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.tasklist.model.TaskManager" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.tasklist.model.Task" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tareas Pendientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Lista de Tareas Pendientes</h2>

    <table class="table">
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
            TaskManager manager = (TaskManager) session.getAttribute("taskManager");
            if (manager == null) {
                manager = new TaskManager();
                session.setAttribute("taskManager", manager);
            }

            List<Task> tasks = manager.getPendingTasks();
            for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getName() %></td>
            <td><%= task.getDescription() %></td>
            <td><%= task.getFecha() %></td>
            <td><%= task.isPendiente() ? "Pendiente" : "Completada" %></td>
            <td>
                <form action="task_controller.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="taskName" value="<%= task.getName() %>">
                    <button class="btn btn-danger">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-primary">Volver</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

