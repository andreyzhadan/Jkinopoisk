<%@ page import="com.zhadan.bean.Actor" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/4/13
  Time: 1:28 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actor page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <div class="page-header" align="center">
        <h3>Actor page</h3>
    </div>
    <ul class="list-group">
        <%Actor actor = (Actor) request.getAttribute("actor");%>
        <li class="list-group-item">
            Id <%=actor.getId()%>
        </li>
        <li class="list-group-item">
            First name <%=actor.getFirstName()%>
        </li>
        <li class="list-group-item">
            Last name <%=actor.getLastName()%>
        </li>
        <li class="list-group-item">
            Year <%=actor.getDate()%>
        </li>
        <li class="list-group-item">
            Country <%=actor.getCountry()%>
        </li>
    </ul>
</div>
</div>
</body>
</html>