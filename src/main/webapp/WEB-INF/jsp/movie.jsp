<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <a href="../home.html"><img src="/img/logo.svg" alt="Fandango" width="222" height="33"/></a>
  </div>
    <div class="search">
        <div><a href="../login.html">Log in</a></div>
        <form action="../search.html" method="get">
            <p>
                <input name="req"/>
                <input type="submit" value="Search"/>
            </p>
        </form>
    </div>
</header>

<div class="main">
    <h2>Hello ${visitor.name} ${visitor.lastName}</h2>
    <div class="movie">
      <div class="poster">
        <img src="${movie.imgDirectory}" width="200" height="300" alt="${movie.name}">
      </div>
      <div class="info">
        <div class="name">"<c:out value="${movie.name}"/>"</div>
        <div class="desc"><c:out value="${movie.description}"/></div>
        <div class="dur">Duration: <c:out value="${movie.movieDuration} min"/></div>
      </div>
    </div>
    <h4>Shedules for this movie:</h4>
    <ul>
      <c:forEach var="cinema" items="${shed}">
        <li>
          <a href="/cinema/${cinema.key.id}.html"><c:out value="${cinema.key.name}"/></a>
            <ul>
                <c:forEach var="session" items="${cinema.value}">
                    <form action="../form.html" method="post">
                        <p>
                            ${session.dateOfSession.getTime()}
                            <input type="hidden" value="${session.id}" name="id">
                            <input type="submit" value="Book it"/>
                        </p>
                    </form>
                </c:forEach>
            </ul>
        </li>
      </c:forEach>
    </ul>
</div>

<footer>
  <div>&copy; 2015 Zavgorodniy</div>
</footer>

</body>
</html>
