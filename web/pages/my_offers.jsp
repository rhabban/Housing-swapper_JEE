<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.devoir.OfferDBHandler" %>
<jsp:include page="/fragments/header.jsp" />

<div class="wrapper container">
  <c:if test="${!empty param['message']}">
    <p class="alert alert-info">${param["message"]}</p>
  </c:if>

  <div class="row">
  <div class="col-lg-12 col-md-12 col-sm-12">
    <h1>My offers</h1>
    <div class="container-fluid">
      <div class="row">
         <c:set var="userMail" value="${user.getMail()}"/>
        <c:forEach var="offer" items="<%= OfferDBHandler.retrieveByUserId((String)pageContext.getAttribute("userMail")) %>">
          <div class="card col-xs-12 col-sm-6 col-lg-4 well">
            <img class="card-img-top" src="http://emploi-btp.lemoniteur.fr/mediatheque/5/0/6/000002605_article.jpg" alt="Card image cap">
            <div class="card-block">
              <p class="card-text">From : ${offer.getDateStart()}</p>
              <p class="card-text">To : ${offer.getDateEnd()}</p>
              <hr>
              <h4 class="card-title">Name : ${offer.getHousing().getName()}</h4>
              <p class="card-text">Id : ${offer.getHousing().getId()}</p>
              <p class="card-text">Housing in ${offer.getHousing().getCountry()}</h4>
              <p class="card-text"> Rooms : ${offer.getHousing().getRoomCount()} </p>
              <p class="card-text"> Surface : ${offer.getHousing().getSurface()} m2 </p>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/fragments/footer.html" />
