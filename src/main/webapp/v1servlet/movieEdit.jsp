<%@ page import="com.zhadan.bean.Movie" %>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags/v1servlet" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 6:48 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <form class="form-horizontal well well-large" action="#" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Movie edit page</legend>
            </div>
            <%Movie movie = (Movie) request.getAttribute("movie");%>
            <customTags:movieTag movie="<%=movie%>"/>
            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-warning">Edit</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>