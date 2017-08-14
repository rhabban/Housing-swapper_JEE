<jsp:include page="../fragments/header.jsp" />

<%@page import="com.devoir.HousingDBHandler" %>
<%@page import="com.housing.*" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.http.HttpServletRequest;" %>

<div class="wrapper container">
  <c:if test="${!empty param['message']}">
    <p class="alert alert-info">${param["message"]}</p>
  </c:if>
  <h1>Add Offer</h1>


  <%
    HousingWithIdModel currentHousing = null;
    List<HousingWithIdModel> housings= HousingDBHandler.getDb().retrieveAll();
    for (HousingWithIdModel housing : housings) {
        if(housing.getId() == Integer.parseInt(request.getParameter("id"))){
        out.println(housing);
        break;
      }
    }
    %>


  <form action="<%= request.getContextPath()+"/" %>save/offer" method="post">
    <div class="form-group">
      <label for="from">From Date</label>
      <input type="text" class="form-control" name="from" placeholder="00-00-0000" required>
    </div>
    <div class="form-group">
      <label for="to">To Date</label>
      <input type="text" class="form-control" name="to" placeholder="00-00-0000" required>
    </div>
    <div class="form-group">
      <label for="country">Destination country</label>
      <input type="text" class="form-control" name="country" required>
    </div>
    <input type="hidden" name="housing_id" value="<%= request.getParameter("id") %>">

    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

<jsp:include page="../fragments/footer.html" />
