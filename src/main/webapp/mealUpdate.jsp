<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update meal</title>
</head>
<body>
<h3>${pa}Добавить еду</h3>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input name="id" type="hidden" value="${meal.id}">
    Date:
    <input name="dateTime" type="datetime-local" value="${meal.dateTime}">
    Description:
    <input name="description" type="text" value="${meal.description}">
    Calories:
    <input name="calories" type="number" value="${meal.calories}">
    <input type="submit" value="Сохранить">
    </form>
</body>
</html>
