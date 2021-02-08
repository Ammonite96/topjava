<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Create</title>
</head>
<body>

<h2>Create new meal</h2>

<form method="post" action="/topjava/meals">

    <label><input type="datetime-local" name="date">Дата</label><br>
    <label><input type="text" name="description">Описание</label><br>
    <label><input type="number" name="calories">Калории</label><br>
    <input type="submit" value="Create new meal">

</form>

</body>
</html>