<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 3:18 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link type="text/css" href="/resources/css/bootstrap.css" rel="stylesheet">
    <link type="text/css" href="/resources/css/jkinopoisk.css" rel="stylesheet">
</head>
<div class="navbar">
    <div class="navbar-inner">
        <% if (request.getSession().getAttribute("login") != null) {%>
        <ul class="nav">
            <li>
                <a href="/home.jsp">
                    <i class="icon-home"></i>
                    Home
                </a>
            </li>
            <li>
                <a href="/movies">
                    Movies
                </a>
            </li>
            <li>
                <a href="/actors">
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
                <a href="/signOut"><i class="icon-off"></i> Sign Out</a>
            </li>
        </ul>
        <%} else {%>
        <ul class="nav nav-pills pull-right">
            <li>
                <a href="/signIn.jsp">
                    SignIn
                </a>
            </li>
            <li>
                <a href="/signUp.jsp">
                    SignUp
                </a>
            </li>
        </ul>
        <%}%>
    </div>
</div>