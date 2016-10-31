<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h2><fmt:message key="user.title"/></h2>
        <br><br>
        <table class="table table-striped">
            <tr style="font-weight: bold">
                <td>Name</td>
                <td>Email</td>
                <td>Active</td>
                <td>Role</td>
                <td>Registered</td>
            </tr>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                <tr>
                    <td>${user.name}</td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.enabled}</td>
                    <td>${user.roles}</td>
                    <td><fmt:formatDate value="${user.registered}" pattern="yyyy-MM-dd hh:mm"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
