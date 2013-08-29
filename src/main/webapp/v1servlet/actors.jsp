<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/4/13
  Time: 1:29 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actors page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <div class="page-header" align="center">
        <h3>Actors page</h3>
    </div>

    <a href="/v1servlet/actorAdd.jsp" class="btn btn-primary myButton">New actor</a>

    <customTags:actorsTag/>
</div>
</body>
</html>