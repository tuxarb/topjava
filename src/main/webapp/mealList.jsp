<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Подсчет калорий</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Список еды</h2>
<hr>
<form action="meals?action=filter" method="post">
    <table border="2" cellspacing="0">
        <tr>
            <td>
                <dt>Дата от:</dt>
                <dd><input type="date" name="startDate" value="${startDate}"></dd>
                </dl>
            </td>
            <td>
                <dl>
                    <dt>Дата до:</dt>
                    <dd><input type="date" name="endDate" value="${endDate}"></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dt>Время от:</dt>
                <dd><input type="time" name="startTime" value="${startTime}"></dd>
                </dl>
            </td>
            <td>
                <dl>
                    <dt>Время до:</dt>
                    <dd><input type="time" name="endTime" value="${endTime}"></dd>
                </dl>
            </td>
        </tr>
    </table>
    <pre>
    <button type="submit" style="margin-left: 255px">Отфильтровать</button>
        </pre>
</form>


<a href="meals?action=create">Добавить еду</a><br><br>
<hr>
<table border="3" cellpadding="6" cellspacing="0">
    <tr style="font-weight: bold">
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td>
                    ${TimeUtil.toString(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Править</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
