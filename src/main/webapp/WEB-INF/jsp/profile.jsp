<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h2 style="margin-left: 185px">
            <c:choose>
                <c:when test="${register}">
                    <fmt:message key="app.register"/>
                    <fmt:message key="app.toRegister" var="save"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="app.profile"/>
                    <fmt:message key="save" var="save"/>
                </c:otherwise>
            </c:choose>
        </h2>
        <br><br>
        <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                   action="${register ? 'register' : 'profile'}" charset="utf-8" accept-charset="UTF-8">

            <fmt:message key="user.name" var="userName"/>
            <topjava:inputField label='${userName}' name="name"/>

            <fmt:message key="user.email" var="userEmail"/>
            <topjava:inputField label='${userEmail}' name="email"/>

            <fmt:message key="user.password" var="userPassword"/>
            <topjava:inputField label='${userPassword}' name="password" inputType="password"/>

            <fmt:message key="user.caloriesPerDay" var="caloriesPerDay"/>
            <topjava:inputField label='${caloriesPerDay}' name="caloriesPerDay" inputType="number"/>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">${save}</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
