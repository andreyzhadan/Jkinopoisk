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

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link type="text/css" href="<c:url value="/resources/css/jkinopoisk.css"/>" rel="stylesheet">
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
    </c:if>
</div>

</body>
</html>