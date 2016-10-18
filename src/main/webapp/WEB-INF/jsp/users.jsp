<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title><fmt:message key="user.title"/></title>
<body>
<h1><fmt:message key="user.title"/></h1>
<a href="${pageContext.request.contextPath}/">Go Home</a>
<br><br>
<table border="3" cellpadding="6" cellspacing="0">
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
</body>
</html>
