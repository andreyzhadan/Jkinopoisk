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
    <form class="form-horizontal well well-large" action="/v1servlet/signUp" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Sign Up</legend>
            </div>
            <div class="control-group">
                <label class="control-label" for="login">Login</label>

                <div class="controls">
                    <input type="text" id="login" name="login" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">Password</label>

                <div class="controls">
                    <input type="password" id="password" name="password" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password2">Confirm password</label>

                <div class="controls">
                    <input type="password" id="password2" name="password2" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-primary">Register</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>