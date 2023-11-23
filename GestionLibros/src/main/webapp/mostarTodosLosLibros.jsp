<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 22-11-2023
  Time: 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Todos los Libros</title>
</head>
<body>
<form action="mostrarTodosLibrosServlet" method="post">
</form>
<h1>Lista de Todos los Libros</h1>

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
