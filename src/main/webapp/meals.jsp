<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>

    <style>
        .normal {
            color: green;
        }
        .excess {
            color: red;
        }
    </style>

</head>
<body>
<h2>Meals List</h2>
<table border="1" cellpadding="5" cellspacing="0">
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
            <%--@elvariable id="mealTo" type="ru.javawebinar.topjava.model.MealTo"--%>
            <c:forEach items="${mealTo}" var="mealTo">
                <tr class="${mealTo.excess ? "excess" : "normal"}">
                    <td>${mealTo.id}</td>
                    <td><fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parseDate}"/>
                    </td>
                    <td>${mealTo.description}</td>
                    <td>${mealTo.calories}</td>
                    <td><a href="update?id=${mealTo.id}">Update</a></td>
                    <td>
                        <form method="get" action="/topjava/delete" style="display:inline">
                            <input type="hidden" name="id" value="${mealTo.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>

</table>

    </br>
       <a href="create.jsp"> <input type="submit" value="Create new meal"></a>

    <hr>
    <a href="index.html">TopJava</a>
</body>
</html>




