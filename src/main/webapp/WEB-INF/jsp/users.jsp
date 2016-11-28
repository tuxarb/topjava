<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h2><fmt:message key="user.title"/></h2>
        <br><br>
        <a class="btn btn-info" onclick="add('<fmt:message key="user.add"/>')"><fmt:message key="user.add"/></a>
        <br><br>
        <table class="table table-striped display" id="usersTable">
            <thead>
            <tr style="font-weight: bold">
                <td><fmt:message key="user.name"/></td>
                <td><fmt:message key="user.email"/></td>
                <td><fmt:message key="user.roles"/></td>
                <td><fmt:message key="user.active"/></td>
                <td><fmt:message key="user.registered"/></td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <%-- <c:forEach items="${users}" var="user">
                 <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                 <tr>
                     <td>${user.name}</td>
                     <td><a href="mailto:${user.email}">${user.email}</a></td>
                     <td>${user.roles}</td>
                     <td>
                         <input type="checkbox"
                                <c:if test="${user.enabled}">checked</c:if>
                                onclick="check($(this), ${user.id})">
                     </td>
                     <td><fmt:formatDate value="${user.registered}" pattern="yyyy-MM-dd hh:mm"/></td>
                     <td><a class="btn btn-primary edit" onclick="updateRow('${user.id}')"><fmt:message key="update"/></a></td>
                     <td><a class="btn btn-danger delete" onclick="deleteRow('${user.id}')"><fmt:message
                             key="delete"/></a></td>
                 </tr>
             </c:forEach>--%>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content" style="background-color: burlywood; color: black">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        style="color: red">&otimes;</button>
                <h2 id="modal-title" class="modal-title"><fmt:message key="update"/></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><fmt:message key="user.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="<fmt:message key="user.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><fmt:message key="user.email"/></label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="email" name="email"
                                   placeholder="<fmt:message key="user.email"/>">
                        </div>
                    </div>

                    <div id="pass" class="form-group">
                            <label for="password" class="control-label col-xs-3"><fmt:message
                                    key="user.password"/></label>

                            <div class="col-xs-9">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="<fmt:message key="user.password"/>">
                                <a id="showPassAdmin" onclick=showPassword('password')><fmt:message key="show"/></a>
                            </div>
                    </div>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-3">
                            <button type="button" class="btn btn-primary" onclick="save()"><fmt:message
                                    key="save"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/jQuery.js"></script>
<script src="resources/js/usersDatatable.js"></script>
</html>
