<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<h2><fmt:message key="meal.title"/></h2>
<hr>
<form action="meals?action=filter" method="post">
    <table border="2" cellspacing="0">
        <tr>
            <td>
                <dl>
                <dt><fmt:message key="date.from"/></dt>
                <dd><input type="date" name="startDate" value="${startDate}"></dd>
                </dl>
            </td>
            <td>
                <dl>
                    <dt><fmt:message key="date.to"/></dt>
                    <dd><input type="date" name="endDate" value="${endDate}"></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl>
                <dt><fmt:message key="time.from"/></dt>
                <dd><input type="time" name="startTime" value="${startTime}"></dd>
                </dl>
            </td>
            <td>
                <dl>
                    <dt><fmt:message key="time.to"/></dt>
                    <dd><input type="time" name="endTime" value="${endTime}"></dd>
                </dl>
            </td>
        </tr>
    </table>
    <pre>
    <button type="submit" style="margin-left: 255px"><fmt:message key="meal.filter"/></button>
        </pre>
</form>


<a href="meals?action=create"><fmt:message key="meal.add"/> </a><br><br>
<hr>
<table border="3" cellpadding="6" cellspacing="0">
    <tr style="font-weight: bold">
        <td><fmt:message key="meal.date"/></td>
        <td><fmt:message key="meal.description"/></td>
        <td><fmt:message key="meal.calories"/></td>
    </tr>
    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td>
                    ${TimeUtil.toString(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}"><fmt:message key="update"/> </a></td>
            <td><a href="meals?action=delete&id=${meal.id}"><fmt:message key="delete"/> </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
