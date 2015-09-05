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

        <c:choose>

            <c:when test="${visitor.checkAdmin}">

                <div class="add-shedule, admin-block">
                    <form action="/admin.html" method="post">
                        <h4>To create shedule, enter required elements:</h4>

                        <p>
                            Select cinema:
                            <select required name="cinemaId">
                                <option selected disabled></option>
                                <c:forEach var="cinema" items="${cinemas}">
                                    <option value="${cinema.id}"><c:out value="${cinema.name}"/></option>
                                </c:forEach>
                            </select>
                        </p>

                        <p>
                            Select movie:
                            <select required name="movieId">
                                <option selected disabled></option>
                                <c:forEach var="movie" items="${movies}">
                                    <option value="${movie.id}"><c:out value="${movie.name}"/></option>
                                </c:forEach>
                            </select>
                        </p>

                        <p>Add date and time:</p>
                        <table>
                            <tr>
                                <td>year</td>
                                <td>month</td>
                                <td>day</td>
                                <td>hours</td>
                                <td>minutes</td>
                            </tr>
                            <tr>
                                <td>
                                    <select required name="year">
                                        <c:forEach var="year" begin="2015" end="2017">
                                            <option value="${year}"><c:out value="${year}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select required name="month">
                                        <c:forEach var="month" begin="1" end="12">
                                            <option value="${month}"><c:out value="${month}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select required name="day">
                                        <c:forEach var="day" begin="1" end="31">
                                            <option value="${day}"><c:out value="${day}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select required name="hours">
                                        <c:forEach var="hours" begin="0" end="23">
                                            <option value="${hours}"><c:out value="${hours}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select required name="minutes">
                                        <c:forEach var="minutes" begin="0" end="59">
                                            <option value="${minutes}"><c:out value="${minutes}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>

                        <p>
                            <input type="submit" value="Add shedule" class="button">
                        </p>
                    </form>

                    <c:if test="${sheduleId != null}">
                        <h3>Shedule id = ${sheduleId} created</h3>
                    </c:if>
                </div>

                <div class="add-cinema, admin-block">
                    <h4>To add cinema, enter required elements:</h4>
                    <form action="/admin/add-cinema.html" method="post">
                        <table>
                            <tr>
                                <td>Name:</td>
                                <td><input type="text" required name="cinemaName"/></td>
                            </tr>
                            <tr>
                                <td>Location:</td>
                                <td><input type="text" required name="location"/></td>
                            </tr>
                            <tr>
                                <td>Number of seats:</td>
                                <td><input type="text" required pattern="^[0-9]+$" name="seats"/></td>
                            </tr>
                        </table>
                        <p>
                            <input type="submit" value="Add cinema" class="button">
                        </p>
                    </form>

                    <c:if test="${cinemaId != null}">
                        <h3>Cinema id = ${cinemaId} added</h3>
                    </c:if>
                </div>

                <div class="add-movie, admin-block">
                    <h4>To add movie, enter required elements:</h4>
                    <form action="/admin/add-movie.html" enctype="multipart/form-data" method="post">
                        <table>
                            <tr>
                                <td>Name:</td>
                                <td><input type="text" required name="movieName"/></td>
                            </tr>
                            <tr>
                                <td>Description:</td>
                                <td><input type="text" required name="description"/></td>
                            </tr>
                            <tr>
                                <td>Duration(min):</td>
                                <td><input type="text" required pattern="^[0-9]+$" name="duration"/></td>
                            </tr>
                            <tr>
                                <td>Upload poster:</td>
                                <td><input type="file" required name="file"/></td>
                            </tr>
                        </table>
                        <p>
                            <input type="submit" value="Add movie" class="button">
                        </p>
                    </form>

                    <c:if test="${movieId != null}">
                        <h3>Movie id = ${movieId} added</h3>
                    </c:if>
                </div>

            </c:when>

            <c:otherwise>
                <c:redirect url="/home.html"/>
            </c:otherwise>

        </c:choose>
    </div>

    <footer>
        <div>&copy; 2015 Zavgorodniy</div>
    </footer>

</body>
</html>