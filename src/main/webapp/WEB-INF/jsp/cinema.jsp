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
  <form action="form.html" method="post">
    <h2>Hello ${visitor.name} ${visitor.lastName}</h2>
    <h3>"${cinema.name}"</h3>
    <p>
      Cinema location:
    </p>
    <p>
        ${cinema.location}
    </p>
    <h4>
      Schedules in this cinema:
    </h4>
    <ul>
      <c:forEach var="movie" items="${movies}">
        <li>
          <a href="/movie/${movie.id}.html"><c:out value="${movie.name}"/></a>
        </li>
      </c:forEach>
    </ul>
    <ul>
      <c:forEach var="session" items="${sessions}">
        <li>
          <input type="radio" name="sheduleId" value="${session.key}"/>
          <c:out value="${session.value}"/>
        </li>
      </c:forEach>
    </ul>
  </form>
</div>

<footer>
  <div>&copy; 2015 Zavgorodniy</div>
</footer>

</body>
</html>