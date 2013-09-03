<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags/v1servlet" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/7/13
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie add page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>
    <form class="form-horizontal well well-large" action="/v1servlet/movieAdd" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Movie add page</legend>
            </div>
            <customTags:movieTag/>

            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-success">Add</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>