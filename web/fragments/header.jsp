<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>Housing Swapper</title>

    <link rel="stylesheet" href="//bootswatch.com/yeti/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="${cssPath}css/main.css" />
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/united/bootstrap.min.css" rel="stylesheet" integrity="sha384-pVJelSCJ58Og1XDc2E95RVYHZDPb9AVyXsI8NoVpB2xmtxoZKJePbMfE4mlXw7BJ" crossorigin="anonymous">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </head>

  <body>

    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="<%= request.getContextPath()+"/" %>home" class="navbar-brand">Housing Swapper</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <a href="<%= request.getContextPath()+"/" %>home" id="home">Home</a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Add Housing <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<%= request.getContextPath()+"/" %>add/house">New House</a></li>
                <li><a href="<%= request.getContextPath()+"/" %>add/flat">New Flat</a></li>
              </ul>
            </li>
            <li>
              <a href="<%= request.getContextPath()+"/" %>users">Users</a>
            </li>
          </ul>


          <c:set var="user" value="<%= request.getSession().getAttribute("user") %>" />
          <ul class="nav navbar-nav navbar-right">

            <c:if test="${empty user}">
              <li><a href="<%= request.getContextPath()+"/" %>login"><span class="glyphicon glyphicon-user"></span> Login</a></li>

            </c:if>
            <c:if test="${!empty user}">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                   ${user.getFirstName()} ${user.getLastName()} <span class="caret"></span>
                </a>

                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="<%= request.getContextPath()+"/" %>logout"> Logout</a>
                  </li>

                  <li>
                    <a href="<%= request.getContextPath()+"/" %>my_offers">My offers</a>
                  </li>

                  <li>
                    <a href="<%= request.getContextPath()+"/" %>my_housing">My housing</a>
                  </li>
                </li>
            </c:if>
          </ul>
        </div>
      </div>
    </div>
