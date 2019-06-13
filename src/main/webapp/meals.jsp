<%--
  Created by IntelliJ IDEA.
  User: Мария
  Date: 07.06.2019
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<table border="1">
    <thead>
    <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Кол-во калорий</th>
    <th>Время</th>
    <th>Превышение нормы калорий</th>
    <th colspan=2>Действие</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${pageContext.request.getAttribute(\"list\")}">
        <tr bgcolor="${meal.excess ? "red" : "green"}">
            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.excess}</td>
            <td><a href="MealServlet?action=edit&id=<c:out value=""/>">Update</a></td>
            <td><a href="MealServlet?action=delete&id=<c:out value=""/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
<h2>Meals</h2>
</body>
</html>
