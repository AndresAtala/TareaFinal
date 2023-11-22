<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Categorías de Libros</title>
</head>
<body>
<h2>Agregar Categorías de Libros</h2>
<form action="AgregarCategoriasServlet" method="post">
    <label for="categoria">Nombre de la categoría:</label>
    <input type="text" id="categoria" name="categoria" required>
    <br>
    <input type="submit" value="Agregar Categoría">
</form>
<ul>
    <li><a href="index.jsp">INICIO</a></li>
</ul>
</body>
</html>
