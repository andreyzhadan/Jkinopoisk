<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/30/13
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add actor page</title>
</head>

<body>
<div class="container">
    <%@include file="header.jsp" %>
    <form:form method="post" action="addActor" commandName="actor" class="form-horizontal well well-large">
        <fieldset>
            <div id="legend">
                <legend class="">Actor add page</legend>
            </div>

            <customTags:actorTag/>

            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-success">Add actor</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>