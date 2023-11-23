<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 22-11-2023
  Time: 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Libros</title>
</head>
<body>
<h1>Lista de Libros</h1>

<form action="mostrarLibros" method="get">
    <label for="criterio">Criterio de búsqueda:</label>
    <select id="criterio" name="criterio">
        <option value="nombre">Nombre</option>
        <option value="categoria">Categoría</option>
        <option value="ano">Año</option>
        <option value="todos">Todos</option>
    </select>
    <br>

    <label for="valorBusqueda">Valor de búsqueda:</label>
    <input type="text" id="valorBusqueda" name="valorBusqueda">
    <br>

    <input type="submit" value="Buscar">
</form>

<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Categoría</th>
        <th>Año</th>
    </tr>
    <c:forEach var="libro" items="${libros}">
        <tr>
            <td>${libro.nombre}</td>
            <td>${libro.categoria}</td>
            <td>${libro.ano}</td>
        </tr>
    </c:forEach>
</table>

<ul>
    <li><a href="index.jsp">INICIO</a></li>
</ul>
</body>
</html>
