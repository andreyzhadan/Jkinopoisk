<%@ page import="com.zhadan.bean.Movie" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie page</title>
</head>
<body>
<%@include file="header.jsp" %>
<a href="/jkinopoisk/movies" align="left">Back to movies page</a>

<h2 align="center">Movie page</h2>
<%
    Movie movie = (Movie) request.getAttribute("movie");
%>
Id <%=movie.getId()%>
Name <%=movie.getName()%>
Russian name <%=movie.getRussianName()%>
Rating <%=movie.getRating()%>
Slogan <%=movie.getSlogan()%>
Year <%=movie.getYear()%>
Country <%=movie.getCountry()%>
</body>
</html>