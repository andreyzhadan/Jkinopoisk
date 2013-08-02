<%--
  Created by azhadan on 7/29/13.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<h2 align="center">Register page </h2>

<a href="/login.jsp">Login</a>

<form action="/register" method="post">
    <div>
        <label>Name:
            <input type="text" name="login"/>
        </label></div>
    <div>
        <label>Password:
            <input type="password" name="password"/>
        </label></div>
    <div>
        <label>Confirm password :
            <input type="password" name="password2"/>
        </label>
    </div>
    <input type="submit" value="Register"/>
</form>
</body>
</html>