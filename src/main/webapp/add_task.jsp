<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agregar Tarea</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Agregar Nueva Tarea</h2>

  <!-- Formulario para agregar una tarea -->
  <form action="task_controller.jsp" method="post">
    <!-- Definir la acción de agregar -->
    <input type="hidden" name="action" value="add">

    <!-- Campo para el nombre de la tarea -->
    <div class="mb-3">
      <label for="taskName" class="form-label">Nombre de la tarea</label>
      <input type="text" class="form-control" id="taskName" name="taskName" required>
    </div>

    <!-- Campo para la descripción -->
    <div class="mb-3">
      <label for="description" class="form-label">Descripción</label>
      <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
    </div>

    <!-- Campo para la fecha -->
    <div class="mb-3">
      <label for="fecha" class="form-label">Fecha</label>
      <input type="date" class="form-control" id="fecha" name="fecha" required>
    </div>

    <!-- Botón para agregar la tarea -->
    <button type="submit" class="btn btn-success">Agregar tarea</button>
  </form>

  <!-- Botón para volver al índice -->
  <a href="index.jsp" class="btn btn-primary mt-3">Volver</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
