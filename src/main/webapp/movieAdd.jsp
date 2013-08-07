<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/7/13
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie add page</title>
</head>
<body>
<div class="container">
    <%@include file="/header.jsp" %>
    <form class="form-horizontal well well-large" action="/movieAdd" method="post">
        <fieldset>
            <div id="legend">
                <legend class="">Movie add page</legend>
            </div>
            <div class="control-group">
                <label class="control-label" for="name">Name</label>

                <div class="controls">
                    <input type="text" id="name" name="name" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="russianName">Russian Name</label>

                <div class="controls">
                    <input type="text" id="russianName" name="russianName" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="rating">Rating</label>

                <div class="controls">
                    <input type="text" id="rating" name="rating" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="slogan">Slogan</label>

                <div class="controls">
                    <input type="text" id="slogan" name="slogan" placeholder="" class="myInput">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="year">Year</label>

                <div class="controls">
                    <input type="text" id="year" name="year" placeholder="" class="myInput">
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