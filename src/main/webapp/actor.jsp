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

    <div class="page-header" align="center">
        <h3>Actor page</h3>
    </div>
    <%Actor actor = (Actor) request.getAttribute("actor");%>
    <customTags:actorTag actor="<%=actor%>"/>
</div>
</div>
</body>
</html>