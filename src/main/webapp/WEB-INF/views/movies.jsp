<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/28/13
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <h1>Movies</h1>
            <form:form method="post" action="add" commandName="movie" class="form-horizontal">
            <div class="control-group">
                <form:label cssClass="control-label" path="name">Name:</form:label>
                <div class="controls">
                    <form:input path="name"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="russianName">Russian Name:</form:label>
                <div class="controls">
                    <form:input path="russianName"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="country">Country:</form:label>
                <div class="controls">
                    <form:input path="country"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add Movie" class="btn"/>
                    </form:form>
                </div>
            </div>

            <%--<c:if test="${!empty movies}">--%>
            <h3>List of movies</h3>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Russian name</th>
                    <th>&nbsp;</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${movies}" var="item">
                    <tr>qqq</tr>
                    <tr>
                        <td>${item.name}, ${item.russianName}</td>
                        <td>${item.country}</td>
                        <td>
                            <form action="delete/${item.id}" method="post"><input type="submit"
                                                                                  class="btn btn-danger btn-mini"
                                                                                  value="Delete"/></form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--</c:if>--%>
        </div>
    </div>
</div>

</body>
</html>