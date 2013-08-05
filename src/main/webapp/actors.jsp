<%@ page import="com.zhadan.bean.Actor" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/4/13
  Time: 1:29 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actors page</title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <div class="page-header" align="center">
        <h3>Actors page</h3>
    </div>
    <table class="table table-striped table-hover">
        <thead>
        <td>Id</td>
        <td>First name</td>
        <td>Last name</td>
        <td>Date</td>
        <td>Country</td>
        </thead>
        <tbody>
        <%for (Actor actor : (List<Actor>) request.getAttribute("actors")) {%>
        <tr>
            <td>
                <a href="/actor?id=<%=actor.getId()%>">
                    <%=actor.getId()%>
                </a>
            </td>
            <td>
                <%=actor.getFirstName()%>
            </td>
            <td>
                <%=actor.getLastName()%>
            </td>
            <td>
                <%=actor.getDate()%>
            </td>
            <td>
                <%=actor.getCountry()%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>