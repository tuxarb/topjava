<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="base" value="${pageContext.request.contextPath}"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals" class="navbar-brand"><fmt:message key="app.title"/></a>
        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-info" href="${base}"><fmt:message key="app.home"/></a>
                <a class="btn btn-info" role="button" href="users"><fmt:message key="user.title"/></a>
                <a class="btn btn-danger" role="button" href="${base}/logout"><fmt:message key="app.logout"/></a>
            </form>
        </div>
    </div>
</div>