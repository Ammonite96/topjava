<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<p>ЕДА</p>
<table border="1">
    <thead>
        <tr>
            <th>id</th>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.meals}" var="meal">
            <tr
        </c:forEach>
        <tr>
            <td>1</td>
            <td>12.02.2020</td>
            <td>Description</td>
            <td>500</td>
            <td>upd</td>
            <td>del</td>
        </tr>
    </tbody>
</table>
</body>
</html>
