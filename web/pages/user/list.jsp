<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/fragments/header.jsp" />
<%@page import="com.devoir.UserDBHandler" %>


<div class="wrapper container">
  <c:if test="${!empty param['message']}">
    <p class="alert alert-info">${param["message"]}</p>
  </c:if>
  <h1>All users</h1>

  <div class="container-fluid">
    <div class="row">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Password</th>
            <th>Housings</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="<%= UserDBHandler.getDb().retrieveAll() %>">
            <tr>
              <td>${user.getFirstName()}</td>
              <td>${user.getLastName()}</td>
              <td>${user.getMail()}</td>
              <td>${user.getPassword()}</td>
              <td>
                <c:forEach var="housing_id" items="${user.getHousings()}">
                  ${housing_id} |
                </c:forEach>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
</div>

<jsp:include page="/fragments/footer.html" />
