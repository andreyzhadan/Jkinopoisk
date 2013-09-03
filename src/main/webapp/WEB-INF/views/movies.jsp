<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
Created by IntelliJ IDEA.
User: azhadan
Date: 8/28/13
Time: 5:45 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Movies page</title>
</head>

<body>
<div class="container">
    <%@include file="header.jsp" %>
    <c:if test="${!empty movies}">
        <h3>List of movies</h3>

        <p>
            <a href="<c:url value="/addMovie"/>" class="btn btn-primary">Add movie </a>
        </p>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Russian name</th>
                <th>Rating</th>
                <th>Slogan</th>
                <th>Year</th>
                <th>Country</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="movie" items="${movies}">
                <tr>
                    <td>${movie.id}</td>
                    <td>${movie.name}</td>
                    <td>${movie.russianName}</td>
                    <td>${movie.rating}</td>
                    <td>${movie.slogan}</td>
                    <td>${movie.year}</td>
                    <td>${movie.country}</td>
                    <td>
                        <a href="/editMovie/${movie.id}" class="btn btn-warning">Edit </a>
                    </td>
                    <td>
                        <a href="/deleteMovie/${movie.id}" class="btn btn-danger">Delete </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>