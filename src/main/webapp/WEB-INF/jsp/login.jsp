<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>

<style>
    body {
        background: #333;
    }
</style>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand" style="font-size: 160%"><fmt:message key="app.title"/></div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="spring_security_check" method="post">
                <label style="font-weight: 900; font-size: 145%; color: #a94442"><fmt:message
                        key="app.authorization"/></label>
                <div class="form-group">
                    <div class="col-md-3 col-md-offset-2">
                        <input type="text" class="form-control" name="username"
                               placeholder="<fmt:message key="user.name"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <input type="password" class="form-control" name="password"
                               placeholder="<fmt:message key="user.password"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <button class="btn btn-success" type="submit"><fmt:message key="app.login"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="jumbotron">
    <div class="container">
        <c:if test="${error}">
            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <%--<c:if test="${not empty message}">
            <div class="message">
                <fmt:message key="${message}"/>
            </div>
        </c:if>--%>
    </div>
</div>
</body>
</html>
