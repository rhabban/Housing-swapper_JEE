<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.devoir.HousingDBHandler" %>
<jsp:include page="/fragments/header.jsp" />

<div class="wrapper container">
  <div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <h1>All Housings</h1>
      <div class="container-fluid">
        <div class="row">
           <c:set var="userMail" value="${user.getMail()}"/>
          <c:forEach var="housing" items="<%= HousingDBHandler.getDb().retrieveHousingByUserId((String)pageContext.getAttribute("userMail")) %>">

                <div class="card col-xs-12 col-sm-6 col-lg-4 well">
                  <img class="card-img-top" src="http://emploi-btp.lemoniteur.fr/mediatheque/5/0/6/000002605_article.jpg" alt="Card image cap">
                  <div class="card-block">
                    <h4 class="card-title">Name : ${housing.getName()}</h4>
                    <p class="card-text">Id : ${housing.getId()}</p>
                    <p class="card-text">Housing in ${housing.getCountry()}</h4>
                    <p class="card-text"> Rooms : ${housing.getRoomCount()} </p>
                    <p class="card-text"> Surface : ${housing.getSurface()} m2 </p>
                    <a href="<%= request.getContextPath()+"/" %>add/offer?id=${housing.getId()}" class="btn btn-info">Make an offer</a>
                    <a href="<%= request.getContextPath()+"/" %>delete/housing?id=${housing.getId()}" class="btn btn-danger">Delete</a>
                  </div>
                </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/fragments/footer.html" />
