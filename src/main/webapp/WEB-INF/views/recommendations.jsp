<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 20.01.14
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Recommendations page</title>
</head>

<body>
<div class="container">
    <%@include file="header.jsp" %>
    <c:if test="${!empty recommendations}">
        <h3>List of recommendations</h3>

        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>MOVIE_NAME</th>
                <th>USER_NAME</th>
                <th>PREFERENCE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recommendation" items="${recommendations}">
                <tr>
                    <td>${recommendation.id}</td>
                    <td>${recommendation.movie.name}</td>
                    <td>${recommendation.user.userName}</td>
                    <td>${recommendation.preference}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>