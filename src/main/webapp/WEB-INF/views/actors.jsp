<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/30/13
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Actors page</title>
</head>

<body>
<div class="container">
    <%@include file="header.jsp" %>
    <c:if test="${!empty actors}">
        <h3>List of actors</h3>

        <p>
            <a href="<c:url value="/addActor"/>" class="btn btn-primary">Add actor </a>
        </p>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Country</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="actor" items="${actors}">
                <tr>
                    <td>${actor.id}</td>
                    <td>${actor.firstName}</td>
                    <td>${actor.lastName}</td>
                    <td>${actor.birthday}</td>
                    <td>${actor.country}</td>
                    <td>
                        <a href="/editActor/${actor.id}" class="btn btn-warning">Edit </a>
                    </td>
                    <td>
                        <a href="/deleteActor/${actor.id}" class="btn btn-danger">Delete </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <ul class="pager">
            <c:choose>
            <c:when test="${sessionScope.param == 'prev'}">
            <li class="previous disabled">
                </c:when>
                <c:otherwise>
            <li class="previous"></c:otherwise>
                </c:choose>
                <a href="<c:url value="/actors/prev"/>">&larr; Prev</a>
            </li>

            <c:choose>
            <c:when test="${sessionScope.param == 'next'}">
            <li class="next disabled">
                </c:when>
                <c:otherwise>
            <li class="next"></c:otherwise>
                </c:choose>
                <a href="<c:url value="/actors/next"/>">Next &rarr;</a>
            </li>
        </ul>
    </c:if>
</div>

</body>
</html>