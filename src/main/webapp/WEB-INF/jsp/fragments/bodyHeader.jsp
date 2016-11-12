<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="base" value="${pageContext.request.contextPath}"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals" class="navbar-brand"><fmt:message key="app.title"/></a>
        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-info" href="${base}/profile"><fmt:message key="app.profile"/></a>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-info" role="button" href="users"><fmt:message key="user.title"/></a>
                    </sec:authorize>
                    <a class="btn btn-danger" role="button" href="${base}/logout"><fmt:message key="app.logout"/></a>
                </sec:authorize>
            </form>
        </div>
    </div>
</div>