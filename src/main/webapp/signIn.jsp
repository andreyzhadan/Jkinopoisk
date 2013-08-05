<%--
  Created by azhadan on 7/29/13.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<div class="container">
    <%@include file="/header.jsp" %>
    <form class="form-horizontal well well-large" action="/signIn" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Sign In</legend>
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
                <div class="controls">
                    <button class="btn btn-warning">Login</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>