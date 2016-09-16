<%@ page contentType="text/html;charse<bt=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="TimeUtil" class="ru.javawebinar.topjava.util.TimeUtil"/>
<html>
<head>
    <title>Подсчет калорий</title>
</head>
<body>
<h2>Список еды</h2>
<table border="4" style="font-size: large; font-family: Aparajita; font-weight: bold">
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <% List<MealWithExceed> meals = (List<MealWithExceed>) request.getAttribute("mealList"); %>
    <c:forEach items="${meals}" var="meal">
        <c:if test="${meal.isExceed()}">
            <tr bgcolor="red">
                <td>${TimeUtil.decorateTimeForDisplay(meal.getDateTime())}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:if>
        <c:if test="${!meal.isExceed()}">
            <tr bgcolor="green">
                <td>${TimeUtil.decorateTimeForDisplay(meal.getDateTime())}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
