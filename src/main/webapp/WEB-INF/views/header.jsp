<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/2/13
  Time: 3:18 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link type="text/css" href="<c:url value="/resources/css/jkinopoisk.css"/>" rel="stylesheet">
</head>
<div class="navbar">
    <div class="navbar-inner">
        <sec:authorize access="isAuthenticated()">
            <ul class="nav">
                <li>
                    <a href="<c:url value="/home"/>"/>
                    <i class="icon-home"></i>
                    Home
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/movies"/>">
                        Movies
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/actors"/>">
                        Actors
                    </a>
                </li>
            </ul>
            <ul class="nav nav-pills pull-right">
                <li>
                    <a href="#">
                        Signed in as
                        <sec:authentication property="principal.username"/>
                    </a>
                </li>
                <li>
                    <a href="/j_spring_security_logout">
                        <i class="icon-off"></i>
                        Sign Out
                    </a>
                </li>
            </ul>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <ul class="nav nav-pills pull-right">
                <li>
                    <a href="/spring_security_login">
                        SignIn
                    </a>
                </li>
                <li>
                    <a href="/signUp"/>
                    SignUp
                    </a>
                </li>
            </ul>
        </sec:authorize>
    </div>
</div>