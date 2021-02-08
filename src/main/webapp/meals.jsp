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
<table border="1">

        <tr>
            <th>id</th>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>


        <%--@elvariable id="mealTo" type="ru.javawebinar.topjava.model.MealTo"--%>
        <c:forEach items="${mealTo}" var="mealTo">

            <tr class="${mealTo.excess ? "excess" : "normal"}">

                <td>${mealTo.id}</td>
                <td><fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both"/>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parseDate}"/>
                </td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action = update&id=${mealTo.id}">Update</a></td>
                <td><a href="meals?action = delete&id=${mealTo.id}">Delete</a></td>

            </tr>
        </c:forEach>

</table>

    </br>
       <a href="create.jsp"> <input type="submit" value="Create"></a>
</body>
</html>


