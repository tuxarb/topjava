<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="name" required="true" description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" description="Field label" %>
<%@ attribute name="inputType" required="false" description="Input type" %>

<spring:bind path="${name}">
    <div class="form-group ${status.error ? 'error' : '' }" style='font-size: large'>
        <label class="control-label col-xs-2">${label}</label>

        <div class="col-xs-8">
            <c:choose>
                <c:when test="${inputType == 'password'}"><form:password path="${name}"/>
                    <button type="button" onclick="showPassword('${name}')">
                        <img id="showPass" src="resources/images/show_pass.png"/>
                    </button>
                </c:when>
                <c:when test="${inputType == 'number'}"><form:input path="${name}" type="number"/></c:when>
                <c:otherwise><form:input path="${name}"/></c:otherwise>
            </c:choose>
            &nbsp;<span class="help-inline" style="color: #000000; font-size: x-large">${status.errorMessage}</span>
        </div>
    </div>
</spring:bind>


