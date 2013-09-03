<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 9/2/13
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>
    <security:authorize access="!isAuthenticated()">

        <form method="post" class="form-horizontal well well-large" action="j_spring_security_check">
            <fieldset>
                <div id="legend">
                    <legend class="">Sign In</legend>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username_or_email">Login</label>

                    <div class="controls">
                        <input type="text" id="username_or_email" name="j_username" placeholder="" class="myInput">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="password">Password</label>

                    <div class="controls">
                        <input type="password" id="password" name="j_password" placeholder="" class="myInput">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label inline" for="remember_me">Remember me</label>

                    <div class="controls">
                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
                    </div>
                </div>


                <div class="control-group">
                    <c:if test="${error}">
                        <div style="color: red">Could not sign in, please check your login/password...</div>
                    </c:if>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button class="btn btn-warning">Login</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </security:authorize>
</div>
</body>
</html>