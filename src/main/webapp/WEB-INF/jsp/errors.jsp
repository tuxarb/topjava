<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <br>
        <h2 align="center"><fmt:message key="app.errors"/></h2>
        <h3 style="color: #ff0000">${exception.message}</h3>

        <%--<c:forEach items="${exception.stackTrace}" var="stackTrace">
            ${stackTrace}
        </c:forEach>--%>
    </div>
</div>


</body>
</html>
