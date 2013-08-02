<%@ page import="com.zhadan.bean.Movie" %>
<%@ page import="java.util.List" %>
<%--
Created by IntelliJ IDEA.
User: Andrew
Date: 01.08.13
Time: 23:23
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies page</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 align="center">Movies page</h2>
<table border>
    <thead>
    <td>Id</td>
    <td>Name</td>
    <td>Russian name</td>
    <td>Rating</td>
    <td>Slogan</td>
    <td>Year</td>
    <td>Country</td>
    </thead>
    <%for (Movie movie : (List<Movie>) request.getAttribute("movies")) {%>
    <tr>
        <td>
            <a href="/jkinopoisk/movie?id=<%=movie.getId()%>"><%=movie.getId()%>
            </a>
        </td>
        <td><%=movie.getName()%>
        </td>
        <td><%=movie.getRussianName()%>
        </td>
        <td><%=movie.getRating()%>
        </td>
        <td><%=movie.getSlogan()%>
        </td>
        <td><%=movie.getYear()%>
        </td>
        <td><%=movie.getCountry()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>