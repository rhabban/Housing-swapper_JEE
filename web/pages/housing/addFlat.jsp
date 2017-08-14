<jsp:include page="../fragments/header.jsp" />

<div class="wrapper container">
  <c:if test="${!empty param['message']}">
    <p class="alert alert-info">${param["message"]}</p>
  </c:if>
  <h1>New flat</h1>

  <form action="<%= request.getContextPath()+"/" %>save/house" method="post">
    <div class="form-group">
      <label for="name">Name</label>
      <input type="text" class="form-control" name="name" placeholder="Name" required>
    </div>
    <div class="form-group">
      <label for="country">Country</label>
      <input type="text" class="form-control" name="country" placeholder="Country" required>
    </div>
    <div class="form-group">
      <label for="surface">Indoor surface</label>
      <input type="number" class="form-control" name="surface" required>
    </div>
    <div class="form-group">
      <label for="address">Address</label>
      <input type="text" class="form-control" name="address" required>
    </div>
    <div class="form-group">
      <label for="roomCount">Room count</label>
      <input type="number" class="form-control" name="roomCount" required>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

<jsp:include page="../fragments/footer.html" />
