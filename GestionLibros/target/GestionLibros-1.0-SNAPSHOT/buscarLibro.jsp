<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Libros</title>
</head>
<body>
<h1>Buscar Libros</h1>
<br/>
<h2>Buscar Libro:</h2>
<form action="<c:url value="/mostrarLibros"/>" method="get">
    <label for="criterio">Seleccione el criterio de búsqueda:</label>
    <select id="criterio" name="criterio">
        <option value="nombre">Nombre</option>
        <option value="categoria">Categoría</option>
        <option value="ano">Año</option>
    </select>
    <br/>
    <label for="valor">Ingrese el valor de búsqueda:</label>
    <input type="text" id="valor" name="valor" required>
    <br/>
    <button type="submit">Buscar Libro</button>
</form>
<ul>
    <li><a href="index.jsp">INICIO</a></li>
</ul>
</body>
</html>
