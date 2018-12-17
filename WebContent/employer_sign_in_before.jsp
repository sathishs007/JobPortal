<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyjobKart</title>
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Login To Save your Resumes</h4>
	</div>
	<div class="block-section box-side-account">
		<div class="box-list" style="margin-left: -8%; padding: 50px">
			<div class="item">
				<div class="row">
					<div class="body-content clearfix">
						<div class="panel panel-md">
							<div class="panel-body">
								<div class="row">
									<c:if test="${not empty message}">
										<div class="message red">${message}</div>
									</c:if>
									<div class="col-md-12">
										<!-- buttons top -->
										<p>
											<a href="#" class="btn btn-primary btn-theme btn-block"><i
												class="fa fa-facebook pull-left bordered-right"></i> Login
												with Facebook</a>
										</p>
										<p>
											<a href="#" class="btn btn-danger btn-theme btn-block"><i
												class="fa fa-google-plus pull-left bordered-right"></i>
												Login with Google</a>
										</p>
										<!-- end buttons top -->

										<div class="white-space-10"></div>
										<p class="text-center">
											<span class="span-line">OR</span>
										</p>
										<form:form id="myForm" method="post"
											class="login-form clearfix" commandName="employerLogin">
											<div class="form-group">
												<label>Email</label>
												<form:input name="emailAddress" type="email"
													path="emailAddress" class="form-control"
													id="emailAddressInput" placeholder="Your Email" />
												<form:errors path="emailAddress" cssClass="error" />
											</div>
											<div class="form-group">
												<label>Password</label>
												<form:input type="password" class="form-control"
													path="password" id="passwordInput"
													placeholder="Your Password" />
												<form:errors path="password" cssClass="error" />
											</div>
											<div class="form-group">
												<div class="row">
													<div class="col-xs-6">
														<div class="checkbox flat-checkbox">
															<label> <input type="checkbox"> <span
																class="fa fa-check"></span> Remember me?
															</label>
														</div>
													</div>
													<div class="col-xs-6 text-right">
														<p class="help-block">
															<a href="jobseeker_forget_password.html">Forgot
																password?</a>
														</p>
													</div>
												</div>
											</div>
											<div class="form-group no-margin">
												<button class="btn btn-theme btn-lg btn-t-primary btn-block">Log
													In</button>
											</div>
											<div class="error">
												<div class="col-md-12" style="margin-left: 50px;"></div>
											</div>
										</form:form>
										<!-- form login -->

									</div>
								</div>
							</div>
						</div>

						<div class="white-space-20"></div>
						<div class="text-center ">
							Not a member? &nbsp; <a href="jobseeker_sign_up.html"><strong>Create
									an account free</strong></a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<!-- jQuery Bootstrap -->
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<!-- Lightbox -->
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<!-- Theme JS -->
	<script src="resources/theme/js/theme.js"></script>

	<!-- maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<!-- maps single marker -->
	<script src="resources/theme/js/map-detail.js"></script>

</body>
</html>