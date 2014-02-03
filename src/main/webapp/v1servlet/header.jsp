<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 3:18 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link type="text/css" href="<c:url value="/resources/css/jkinopoisk.css"/>" rel="stylesheet">
</head>
<div class="navbar">
    <div class="navbar-inner">
        <% if (request.getSession().getAttribute("login") != null) {%>
        <ul class="nav">
            <li>
                <a href="/v1servlet/home.jsp">
                    <i class="icon-home"></i>
                    Home
                </a>
            </li>
            <li>
                <a href="/v1servlet/movies">
                    Movies
                </a>
            </li>
            <li>
                <a href="/v1servlet/actors">
                    Actors
                </a>
            </li>
        </ul>
        <ul class="nav nav-pills pull-right">
            <li>
                <a href="#">
                    Signed in as <%=request.getSession().getAttribute("login")%>
                </a>
            </li>
            <li>
                <a href="/v1servlet/signOut"><i class="icon-off"></i> Sign Out</a>
            </li>
        </ul>
        <%} else {%>
        <ul class="nav nav-pills pull-right">
            <li>
                <a href="/v1servlet/signIn.jsp">
                    SignIn
                </a>
            </li>
            <li>
                <a href="/v1servlet/signUp.jsp">
                    SignUp
                </a>
            </li>
        </ul>
        <%}%>
    </div>
</div>