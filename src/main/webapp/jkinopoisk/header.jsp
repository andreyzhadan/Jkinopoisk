<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="right">
    <div>
        Hello <%=request.getSession().getAttribute("login")%>
    </div>
    <div>
        <a href="/logout">Logout</a>
    </div>
</div>
</body>
</html>