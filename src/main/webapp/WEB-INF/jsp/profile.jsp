<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                    <c:set var="profile" value="true"/>
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


            <c:if test="${register}">
                <fmt:message key="user.password" var="userPassword"/>
                <topjava:inputField label='${userPassword}' name="password" inputType="password"/>
            </c:if>

            <fmt:message key="user.caloriesPerDay" var="caloriesPerDay"/>
            <topjava:inputField label='${caloriesPerDay}' name="caloriesPerDay" inputType="number"/>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">${save}</button>
                </div>

            </div>
        </form:form>

        <c:set value="${param.message}" var="message"/>
        <c:if test="${fn:containsIgnoreCase(message, 'profile.success')}">
            <div class="message">
                <fmt:message key="${message}"/>
            </div>
        </c:if>

        <c:if test="${profile}">
        <hr class="hr">
        <br>
        <h2 style="margin-left: 185px">
            <fmt:message key="app.security"/>
        </h2>
        <br>
        <form:form modelAttribute="passwordUserTo" class="form-horizontal" method="post"
                   action="profile?password/update" charset="utf-8" accept-charset="UTF-8">
        <fmt:message key="user.oldPassword" var="userPassword"/>
        <topjava:inputField label='${userPassword}' name="oldPassword" inputType="password"/>

        <fmt:message key="user.newPassword" var="userPassword"/>
        <topjava:inputField label='${userPassword}' name="newPassword" inputType="password"/>

        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" class="btn btn-primary">${save}</button>
            </div>
            </form:form>
            </c:if>
        </div>

        <c:choose>
            <c:when test="${fn:containsIgnoreCase(message, 'profile.password.success')}">
                <div class="message">
                    <fmt:message key="${message}"/>
                </div>
            </c:when>
            <c:when test="${fn:containsIgnoreCase(message, 'profile.testAdmin.password.immutable')}">
                <div class="error">
                    <fmt:message key="${message}"/>
                </div>
            </c:when>
        </c:choose>

    </div>
</div>
</body>
</html>
