<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>FANDANGO</title>
    <link href="/css/stylesheet.css" rel="stylesheet">
</head>
<body class="parent">

<header>
    <div class="logo">
        <a href="home.html"><img src="/img/logo.svg" alt="Fandango" width="222" height="33"/></a>
    </div>
    <div class="search">
        <div><a href="login.html">Log in</a></div>
        <form action="search.html" method="get">
            <p>
                <input name="req"/>
                <input type="submit" value="Search"/>
            </p>
        </form>
    </div>
</header>

<div class="main">

    <div class="head">Your ticket:</div>

    <form:form action="congratulations.html" method="post">

        <div class="ticket">
            <div class="name">"${form.movie.name}"</div>
            <table>
                <tr>
                    <td><h4>Date:</h4></td>
                    <td><p>${form.sessionDate}</p></td>
                    <td><h4>Cinema:</h4></td>
                    <td><p>${form.cinema.name}</p></td>
                </tr>
                <tr>
                    <td><h4>Time:</h4></td>
                    <td><p>${form.sessionTime}</p></td>
                    <td><h4>Name:</h4></td>
                    <td><p>${form.name}</p></td>
                </tr>
                <tr>
                    <td><h4>Duration:</h4></td>
                    <td><p>${form.movie.movieDuration} min</p></td>
                    <td><h4>Last name:</h4></td>
                    <td><p>${form.lastName}</p></td>
                </tr>
            </table>
        </div>
        <div class="confirm">
            <input type="submit" value="Confirm" class="button">
        </div>
    </form:form>
</div>

<footer>
    <div>© 2015 Zavgorodniy</div>
</footer>

</body>
</html>
