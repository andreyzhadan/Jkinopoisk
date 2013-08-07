<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/7/13
  Time: 12:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actor add page</title>
</head>
<body>
<div class="container">
    <%@include file="/header.jsp" %>
    <form class="form-horizontal well well-large" action="/actorAdd" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Actor add page</legend>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">First Name</label>

                <div class="controls">
                    <input type="text" id="firstName" name="firstName" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lastName">Last Name</label>

                <div class="controls">
                    <input type="text" id="lastName" name="lastName" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="birthday">Birthday</label>

                <div class="controls">
                    <input type="text" id="birthday" name="birthday" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="country">Country</label>

                <div class="controls">
                    <input type="text" id="country" name="country" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-success">Add</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>