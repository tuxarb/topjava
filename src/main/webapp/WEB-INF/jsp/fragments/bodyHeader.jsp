<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="base" value="${pageContext.request.contextPath}"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals" class="navbar-brand" style="color: #a94442;text-decoration: underline; font-size: large">
            <fmt:message key="app.title"/>
        </a>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form navbar-right" action="${base}/logout">
                        <sec:authorize access="isAuthenticated()">
                            <a class="btn btn-info" href="${base}/profile"><fmt:message key="app.profile"/></a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" role="button" href="users"><fmt:message key="user.title"/></a>
                            </sec:authorize>
                            <input type="submit" class="btn btn-danger" value="<fmt:message key="app.logout"/>"/>
                        </sec:authorize>
                    </form:form>
                </li>
                <jsp:include page="/WEB-INF/jsp/lang.jsp"/>
            </ul>
        </div>
    </div>
</div>