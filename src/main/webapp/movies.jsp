<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%--
Created by IntelliJ IDEA.
User: Andrew
Date: 01.08.13
Time: 23:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <div class="page-header" align="center">
        <h3>Movies page</h3>
    </div>

    <a href="/movieAdd.jsp" class="btn btn-primary myButton">New movie</a>

    <customTags:moviesTag/>
</div>
</body>
</html>