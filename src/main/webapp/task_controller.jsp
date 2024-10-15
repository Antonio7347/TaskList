<%@ page import="org.example.tasklist.model.Task" %>
<%@ page import="org.example.tasklist.model.TaskManager" %>
<%@ page import="java.util.List" %>
<%
    // Obtener el parámetro 'action' de la solicitud
    String action = request.getParameter("action");

    // Obtener el TaskManager de la sesión
    TaskManager manager = (TaskManager) session.getAttribute("taskManager");

    if (manager == null) {
        manager = new TaskManager();
        session.setAttribute("taskManager", manager);
    }

    // Lógica para agregar una tarea
    if ("add".equals(action)) {
        String taskName = request.getParameter("taskName");
        String description = request.getParameter("description");
        String fecha = request.getParameter("fecha");

        // Crear un nuevo objeto Task y agregarlo al TaskManager
        Task newTask = new Task(taskName, description, fecha, true);
        manager.addTask(newTask);

        // Redireccionar de nuevo a la página de tareas
        response.sendRedirect("tasks.jsp");

        // Lógica para eliminar una tarea
    } else if ("delete".equals(action)) {
        String taskName = request.getParameter("taskName");

        // Eliminar la tarea por nombre
        manager.deleteTask(taskName);

        // Redireccionar de nuevo a la página de tareas
        response.sendRedirect("tasks.jsp");
    }
%>
