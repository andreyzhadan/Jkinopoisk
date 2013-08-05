<%@ page import="com.zhadan.bean.Movie" %>
<%@ page import="java.util.List" %>
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
    <table class="table table-striped table-hover">
        <thead>
        <td>Id</td>
        <td>Name</td>
        <td>Russian name</td>
        <td>Rating</td>
        <td>Slogan</td>
        <td>Year</td>
        <td>Country</td>
        </thead>
        <tbody>
        <%for (Movie movie : (List<Movie>) request.getAttribute("movies")) {%>
        <tr>
            <td>
                <a href="/movie?id=<%=movie.getId()%>">
                    <%=movie.getId()%>
                </a>
            </td>
            <td><%=movie.getName()%>
            </td>
            <td>
                <%=movie.getRussianName()%>
            </td>
            <td>
                <%=movie.getRating()%>
            </td>
            <td>
                <%=movie.getSlogan()%>
            </td>
            <td>
                <%=movie.getYear()%>
            </td>
            <td>
                <%=movie.getCountry()%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>