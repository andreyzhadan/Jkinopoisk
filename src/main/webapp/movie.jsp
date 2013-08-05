<%@ page import="com.zhadan.bean.Movie" %>
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
    <ul class="list-group">
        <%Movie movie = (Movie) request.getAttribute("movie");%>
        <li class="list-group-item">
            Id <%=movie.getId()%>
        </li>
        <li class="list-group-item">
            Name <%=movie.getName()%>
        </li>
        <li class="list-group-item">
            Russian name <%=movie.getRussianName()%>
        </li>
        <li class="list-group-item">
            Rating <%=movie.getRating()%>
        </li>
        <li class="list-group-item">
            Slogan <%=movie.getSlogan()%>
        </li>
        <li class="list-group-item">
            Year <%=movie.getYear()%>
        </li>
        <li class="list-group-item">
            Country <%=movie.getCountry()%>
        </li>
    </ul>
</div>
</body>
</html>