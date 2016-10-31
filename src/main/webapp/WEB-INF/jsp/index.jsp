<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form method="post" action="users">
            <fmt:message key="app.login"/>: <select name="userId">
            <option value="1" selected>User</option>
            <option value="2">Admin</option>
        </select>
            <button type="submit"><fmt:message key="select"/></button>
        </form>
        <ul>
            <li><a href="users"><fmt:message key="user.title"/></a></li>
            <li><a href="meals"><fmt:message key="meal.title"/></a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>