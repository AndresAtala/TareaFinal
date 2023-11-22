<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 19-11-2023
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar Libro</title>
</head>
<body>
<h2>Eliminar Libro</h2>

<form action="EliminarLibroServlet" method="post">
    <label for="idLibro">Nombre del Libro:</label>
    <input type="text" id="idLibro" name="idLibro" required>
    <br>

    <input type="submit" value="Eliminar Libro">
</form>
<ul>
    <li><a href="index.jsp">INICIO</a></li>
</ul>
</body>
</html>
