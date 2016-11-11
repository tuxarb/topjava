<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h2><fmt:message key="meal.title"/></h2>
        <hr>
        <div class="row">
            <div class="col-md-6 col-md-offset-4">
                <form class="form-horizontal" method="post" id="filterForm">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label for="startDate"><h4><fmt:message key="date.from"/></h4></label>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control datepicker" type="text" id="startDate"
                                           name="startDate">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <label for="endDate"><h4><fmt:message key="date.to"/></h4></label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control datepicker" type="text" id="endDate" name="endDate">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label for="startTime"><h4><fmt:message key="time.from"/></h4></label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control timepicker" type="text" id="startTime" name="startTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <label for="endTime"><h4><fmt:message key="time.to"/></h4></label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control timepicker" type="text" id="endTime" name="endTime">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-2">
                            <button class="btn btn-primary pull-left" type="button" onclick="updateTableByFilter()">
                                <fmt:message key="meal.filter"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <br><br>
        <a class="btn btn-info" onclick=add('<fmt:message key="meal.add"/>')><fmt:message key="meal.add"/></a><br><br>

        <table class="table table-bordered" id="mealsTable">
            <thead style="font-weight: bold">
            <td><fmt:message key="meal.date"/></td>
            <td><fmt:message key="meal.description"/></td>
            <td><fmt:message key="meal.calories"/></td>
            <td></td>
            <td></td>
            </thead>
            <%--
            <c:forEach items="${mealList}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                <tr class="${meal.exceed ? 'danger' : 'normal'}">
                    <td>
                            ${TimeUtil.toString(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a class="btn btn-primary edit" onclick="updateRow('${meal.id}')"><fmt:message key="update"/></a></td>
                    <td><a class="btn btn-danger delete" onclick="deleteRow('${meal.id}')"><fmt:message key="delete"/></a></td>
                </tr>
            </c:forEach>
               --%>
        </table>
    </div>
</div>
</body>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content" style="background-color: burlywood; color: black">
            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true" style="color: red">&otimes;</button>
                <h2 id="modal-title" class="modal-title"><fmt:message key="update"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><fmt:message key="meal.date"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control datetimepicker" id="dateTime" name="dateTime">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><fmt:message
                                key="meal.description"/></label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder=
                            <fmt:message key="meal.description"/>>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3"><fmt:message key="meal.calories"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="calories" name="calories"
                                   placeholder=1000>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-3">
                            <button type="button" class="btn btn-primary" onclick="save()"><fmt:message
                                    key="save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script src="resources/js/skins/jquery-ui-1.10.1.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
<script src="resources/js/jQuery.js"></script>
<script src="resources/js/mealsDatatable.js"></script>

</html>
