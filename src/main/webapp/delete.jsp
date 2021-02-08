<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Delete Meal</title>
</head>
<body>

<div>Id:
<%--@elvariable id="meal" type="ru.javawebinar.topjava.model.Meal"--%>
<c:out value="${meal.id}"/></div>
<div>Description: <c:out value="${meal.description}"/></div>


<form method="post" action="/topjava/delete">
    <label>
        <input type="number" hidden name="id" value="${meal.id}">
    </label>
    <input type="submit" value="Delete">
</form>

</body>
</html>
