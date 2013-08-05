<%@ page import="com.zhadan.bean.Movie" %>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
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

    <div class="page-header" align="center">
        <h3>Movie page</h3>
    </div>
    <%Movie movie = (Movie) request.getAttribute("movie");%>
    <customTags:movieTag movie="<%=movie%>"/>
</div>
</body>
</html>