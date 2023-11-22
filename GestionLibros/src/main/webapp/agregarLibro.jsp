<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Libro</title>
</head>
<body>
<h2>Agregar Libro</h2>

<form action="registroLibroServlet" method="post">
    <label for="nombreLibro">Nombre del Libro:</label>
    <input type="text" id="nombreLibro" name="nombre" required>
    <br>

    <label for="categoriaLibro">Categoría:</label>
    <select id="categoriaLibro" name="categoria" required>
        <c:forEach var="categoria" items="${sessionScope.categorias}">
            <option value="${categoria}">${categoria}</option>
        </c:forEach>
    </select>

    <br>

    <label for="anioLibro">Año:</label>
    <input type="number" id="anioLibro" name="ano" required>
    <br>

    <input type="submit" value="Agregar Libro">
</form>
<ul>
    <li><a href="index.jsp">INICIO</a></li>
</ul>
</body>
</html>
