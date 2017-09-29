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

    <style>
        body {
            background: #333;
        }
    </style>

    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <script>
        var messages = [];
        <c:forEach var="key" items='<%=new String[]{
            "success", "failed", "deleted", "saved", "user.enabled", "user.disabled", "update", "delete", "meal.filtered", "user.duplicatedMail", "search", "meal.duplicatedDate", "show", "datatables.empty", "error.mealTo.calories.convert"}
            %>'>
        messages['${key}'] = "<fmt:message key="${key}"/>";
        </c:forEach>

        function showPassword(id) {
            var password = document.getElementById(id);
            if (password.getAttribute("type") == "password") {
                password.setAttribute("type", "text");
            } else {
                password.setAttribute("type", "password");
            }
        }

        $(function () {
            $('#name, #password, #email, #oldPassword, #newPassword').on('input', function (e) {
                var target = e.target,
                        position = target.selectionStart;
                $(this).val($(this).val().replace(/\s/g, ''));
                target.selectionEnd = position;
            });
        });
    </script>
</head>

