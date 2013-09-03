<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by azhadan on 7/29/13.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>
    <form:form class="form-horizontal well well-large" action="/signUp" commandName="user" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Sign Up</legend>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="userName">
                    Login:
                </form:label>
                <div class="controls">
                    <form:input path="userName" class="myInput"/>
                    <form:errors path="userName" cssStyle="color: red;"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="password">
                    Password:
                </form:label>
                <div class="controls">
                    <form:input path="password" class="myInput" type="password"/>
                    <form:errors path="password" cssStyle="color: red;"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="password2">
                    Confirm password:
                </form:label>
                <div class="controls">
                    <form:input path="password2" class="myInput" type="password"/>
                    <form:errors path="password2" cssStyle="color: red;"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-primary">Register</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>