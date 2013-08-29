<%@ page import="com.zhadan.bean.Actor" %>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
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
    <form class="form-horizontal well well-large" action="#" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Actor edit page</legend>
            </div>
            <%Actor actor = (Actor) request.getAttribute("actor");%>
            <customTags:actorTag actor="<%=actor%>"/>
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