<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h3>${param.action == "create" ? 'Создать еду' : 'Добавить еду'}</h3>
<hr>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input name="id" type="hidden" value="${meal.id}">
    <dl>
        <dt>Date:</dt>
        <dd><input name="dateTime" type="datetime-local" value="${meal.dateTime}"></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input name="description" type="text" value="${meal.description}" size="30"></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input name="calories" type="number" value="${meal.calories}"></dd>
    </dl>
    <input type="submit" value="Сохранить">
    <button onclick="window.history.back()">Отменить</button>
</form>
</body>
</html>
