<jsp:include page="../../fragments/header.jsp" />

<div class="container wrapper">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="<%= request.getContextPath()+"/" %>login/authenticate" method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="mail" name="mail" id="mail" tabindex="1" class="form-control" placeholder="Mail" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="<%= request.getContextPath()+"/" %>save/user" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="firstName" id="firstName" tabindex="1" class="form-control" placeholder="firstName" value="">
									</div>
									<div class="form-group">
										<input type="text" name="lastName" id="lastName" tabindex="1" class="form-control" placeholder="lastName" value="">
									</div>
									<div class="form-group">
										<input type="mail" name="mail" id="mail" tabindex="1" class="form-control" placeholder="Email Address" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<jsp:include page="../../fragments/footer.html" />
