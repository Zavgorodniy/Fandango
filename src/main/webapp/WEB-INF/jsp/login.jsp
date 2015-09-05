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
      <form action="search.html" method="get">
        <p>
          <input name="req"/>
          <input type="submit" value="Search"/>
        </p>
      </form>
    </div>
  </header>

  <div class="main">
    <h3>Here you can book movie tickets</h3>
      <form action="home.html" method="post">
        <p>
          Please, enter your name and last name to log in:
        </p>
        <table>
          <tr>
            <td>Name:</td>
            <td><input type="text" required pattern="^[a-zA-Z]+$" name="name"/></td>
          </tr>
          <tr>
            <td>Last name: </td>
            <td><input type="text" required pattern="^[a-zA-Z]+$" name="lastName"/></td>
          </tr>
        </table>
        <p>
          *To add a schedule, log in as administrator
        </p>
        <p>
          <input type="submit" value="Enter" class="button">
        </p>
      </form>
  </div>

  <footer>
    <div>&copy; 2015 Zavgorodniy</div>
  </footer>

</body>
</html>