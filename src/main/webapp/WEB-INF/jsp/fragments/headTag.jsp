<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="shortcut icon" href="resources/images/icon-meal.png">
    <link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="webjars/datetimepicker/2.4.7/jquery.datetimepicker.css">

    <script>
        var messages = [];
        <c:forEach var="key" items='<%=new String[]{"success", "failed", "deleted", "saved", "user.enabled", "user.disabled", "update", "delete", "meal.filtered"}%>'>
        messages['${key}'] = "<fmt:message key="${key}"/>";
        </c:forEach>
    </script>
</head>

