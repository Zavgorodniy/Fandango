<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h4>Cinemas found:</h4>
        <table>
            <tr>
                <td><h4>Name</h4></td>
                <td><h4>Location</h4></td>
            </tr>
        <c:forEach var="cinema" items="${cinemas}">
            <tr>
                <td><a href="/cinema/${cinema.id}.html"><c:out value="${cinema.name}"/></a></td>
                <td><c:out value="${cinema.location}"/></td>
            </tr>
        </c:forEach>
        </table>

        <h4>Movies found:</h4>
        <c:forEach var="movie" items="${movies}">
            <div class="movie">
                <div class="poster">
                    <a href="/movie/${movie.id}.html"><img src="${movie.imgDirectory}" width="200" height="300" alt="${movie.name}"></a>
                </div>
                <div class="info">
                    <div class="name"><a href="/movie/${movie.id}.html"><c:out value="${movie.name}"/></a></div>
                    <div class="desc"><c:out value="${movie.description}"/></div>
                    <div class="dur">Duration: <c:out value="${movie.movieDuration} min"/></div>
                </div>
            </div>
        </c:forEach>

    </div>

    <footer>
        <div>&copy; 2015 Zavgorodniy</div>
    </footer>

</body>
</html>
