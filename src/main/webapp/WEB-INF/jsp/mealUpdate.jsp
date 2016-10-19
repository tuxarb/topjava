<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<c:if test="${param.action == 'create'}">
<h3><fmt:message key="meal.create"/></h3>
</c:if>
<c:if test="${param.action == 'update'}">
    <h3><fmt:message key="meal.update"/></h3>
</c:if>
<hr>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input name="id" type="hidden" value="${meal.id}">
    <dl>
        <dt><fmt:message key="meal.date"/>:</dt>
        <dd><input name="dateTime" type="datetime-local" value="${meal.dateTime}"></dd>
    </dl>
    <dl>
        <dt><fmt:message key="meal.description"/>:</dt>
        <dd><input name="description" type="text" value="${meal.description}" size="30"></dd>
    </dl>
    <dl>
        <dt><fmt:message key="meal.calories"/>:</dt>
        <dd><input name="calories" type="number" value="${meal.calories}"></dd>
    </dl>
    <button type="submit"><fmt:message key="save"/></button>
    <button onclick="window.history.back()"><fmt:message key="cancel"/></button>
</form>
</body>
</html>
