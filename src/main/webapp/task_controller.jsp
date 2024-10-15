<%--
  Created by IntelliJ IDEA.
  User: Joseb
  Date: 15/10/2024
  Time: 10:28 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.tasklist.model.Task" %>
<%@ page import="org.example.tasklist.model.TaskManager" %>
<%@ page import="java.util.List" %>
<%
    String action = request.getParameter("action");
    TaskManager manager = (TaskManager) session.getAttribute("taskManager");

    if (manager == null) {
        manager = new TaskManager();
        session.setAttribute("taskManager", manager);
    }

    if ("add".equals(action)) {
        String taskName = request.getParameter("taskName");
        String description = request.getParameter("description");
        String fecha = request.getParameter("fecha");

        Task newTask = new Task(taskName, description, fecha, true);
        manager.addTask(newTask);

        response.sendRedirect("tasks.jsp");
    } else if ("delete".equals(action)) {
        String taskName = request.getParameter("taskName");
        manager.deleteTask(taskName);
        response.sendRedirect("tasks.jsp");
    }
%>

