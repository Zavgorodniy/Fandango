<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <input name="req"/>
                <input type="submit" value="Search"/>
            </form>
        </div>
    </header>

    <div class="main">

        <c:choose>

            <c:when test="${visitor.checkAdmin}">
                <c:redirect url="/admin.html"/>
            </c:when>

            <c:otherwise>

                <h2>Hello ${visitor.name} ${visitor.lastName}</h2>

                <div class="list">
                    <p>Movies:</p>
                    <ul>
                        <c:forEach var="movie" items="${movies}">
                            <li>
                                <a href="/movie/${movie.id}.html"><c:out value="${movie.name}"/></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="list">
                    <p>Cinemas:</p>
                    <ul>
                        <c:forEach var="cinema" items="${cinemas}">
                            <li>
                                <a href="/cinema/${cinema.id}.html"><c:out value="${cinema.name}"/></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </c:otherwise>

        </c:choose>

    </div>

    <footer>
        <div>&copy; 2015 Zavgorodniy</div>
    </footer>

</body>
</html>