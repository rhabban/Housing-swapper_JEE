<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/fragments/header.jsp" />
<%@page import="com.devoir.OfferDBHandler" %>


<div class="wrapper container">
  <c:if test="${!empty param['message']}">
    <p class="alert alert-info">${param["message"]}</p>
  </c:if>
  <h1>All users</h1>

  <div class="container-fluid">
    <div class="row">

      <c:forEach var="offer" items="<%= OfferDBHandler.retrieveAll() %>">
        ${offer}
      </c:forEach>

    </div>
</div>

<jsp:include page="/fragments/footer.html" />
