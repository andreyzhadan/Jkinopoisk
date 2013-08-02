<%--
  Created by azhadan on 7/29/13.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h2 align="center">Login page </h2>

<a href="/register.jsp">Register new user</a>

<form action="/jkinopoisk" method="post">
    <div>
        <label>Name:
            <input type="text" name="login"/>
        </label></div>
    <div>
        <label>Password:
            <input type="password" name="password"/>
        </label>
    </div>
    <input type="submit" value="Login"/>
</form>

</body>
</html>