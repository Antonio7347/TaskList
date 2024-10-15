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
            // Obtener el TaskManager de la sesión
            TaskManager manager = (TaskManager) session.getAttribute("taskManager");
            if (manager == null) {
                manager = new TaskManager();
                session.setAttribute("taskManager", manager);
            }

            // Obtener la lista de tareas pendientes
            List<Task> tasks = manager.getPendingTasks();
            if (tasks.isEmpty()) {
        %>
        <tr>
            <td colspan="5" class="text-center">No hay tareas pendientes.</td>
        </tr>
        <%
        } else {
            for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getName() %></td>
            <td><%= task.getDescription() %></td>
            <td><%= task.getFecha() %></td>
            <td><%= task.isPendiente() ? "Pendiente" : "Completada" %></td>
            <td>
                <!-- Formulario para eliminar una tarea específica -->
                <form action="TareaServlet" method="post" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="descripcion" value="<%= task.getName() %>">
                    <button class="btn btn-danger">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-primary">Volver</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
