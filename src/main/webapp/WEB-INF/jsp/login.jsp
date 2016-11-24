<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand" style="font-size: 160%; color: deepskyblue; margin-left: -90px"><spring:message
                code="app.title"/></div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right" style="margin-right: -60px">
                <li>
                    <form:form class="navbar-form navbar-right" action="spring_security_check" method="post">
                        <label style="font-weight: 900; font-size: 145%; color: #a94442"><spring:message
                                code="app.authorization"/></label>
                        <div class="form-group">
                            <div class="col-xs-3 col-xs-offset-2">
                                <input type="text" class="form-control" name="username"
                                       placeholder="<spring:message code="user.name"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-3">
                                <input type="password" class="form-control" name="password"
                                       placeholder="<spring:message code="user.password"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-3">
                                <button class="btn btn-success" type="submit"><spring:message
                                        code="app.login"/></button>
                            </div>
                        </div>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/register"
                           style="margin-left: -19px"><spring:message code="app.register"/></a>
                    </form:form>
                </li>
                <jsp:include page="lang.jsp"/>
            </ul>
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
        <c:if test="${not empty message}">
            <div class="message">
                <fmt:message key="${message}"/>
            </div>
        </c:if>
    </div>
</div>

<div class="img">
    <img src="resources/images/login_image.jpg"/>
</div>

</body>
</html>
