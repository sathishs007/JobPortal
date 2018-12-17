<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			$('input[type="email"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});

			if (isValid == false)
				e.preventDefault();

		});
	});

	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid1 = true;
			$('input[type="password"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid1 = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			if (isValid1 == false)
				e.preventDefault();

		});
	});
</script>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyJobKart</title>

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
	<div class="body-content clearfix">
		<div class="block-section bg-color4">
			<div class="container">
				<div class="panel panel-md">
					<div class="panel-body">
						<div class="warning" style="width: 100%;text-align: center; padding-left: 2px;padding-right: 2px;">
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Success!</strong>
									<c:out value="${sucessmessage}"></c:out>
								</div>
							</c:if>
							
							<c:if test="${not empty infomessage}">
								<div class="alert alert-info" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Info!</strong>
									<c:out value="${infomessage}"></c:out>
								</div>
							</c:if>
							
													
							</div>
					
						<%-- <div class="warning">
								 <c:if test="${not empty message}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_info">
									<h1>Warning</h1>
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
						</div> --%>
						
						
						<div class="row">
							<div class="col-md-12">
								<!-- <p>
									<a href="#" class="btn btn-primary btn-theme btn-block"><i
										class="fa fa-facebook pull-left bordered-right"></i> Login
										with Facebook</a>
								</p> -->
								<p>
									<a href="#" class="btn btn-danger btn-theme btn-block">Sign In										</a>
								</p>
								<!-- <div class="white-space-10"></div>
								<p class="text-center">
									<span class="span-line">OR</span>
								</p> -->

								<!-- form login -->
								<form:form id="myForm" method="post" class="login-form clearfix"
									commandName="employerLogin">
									<div class="form-group">
										<label>Email</label> <font style="color: red;">* </font>
										<form:input name="emailAddress" type="email"
											path="emailAddress" class="form-control"
											id="emailAddressInput" placeholder="Email" maxlength="50" />
										<form:errors path="emailAddress" cssClass="error" />
									</div>
									<div class="form-group">
										<label>Password</label> <font style="color: red;">* </font>
										<form:input type="password" class="form-control"
											path="password" id="passwordInput"
											placeholder="Password" maxlength="8" />
										<form:errors path="password" cssClass="error" />
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-xs-6">
													<label> <form:checkbox path="rememberMe"/>  Remember me?
													</label>
											</div>
											<div class="col-xs-6 text-right">
												<p class="help-block">
													<a href="employer_forget_password.html">Forgot
														password?</a>
												</p>
											</div>
										</div>
									</div>
									<div class="form-group no-margin">
										<button type="submit" id="btnSubmit"
											class="btn btn-theme btn-lg btn-t-primary btn-block">Log
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

				<div class="text-center color-white">
					Not a member? &nbsp; <a href="employer_sign_up.html" style="text-decoration: underline;"
						class="link-white">Create an account free</a>
				</div>

			</div>
		</div>

		<!-- box bottom -->
		<%-- <div class="block-section bg-color2">
			<div class="container text-center">
				<i class="fa fa-mobile-phone fa-5x box-icon"></i>
				<h2 class="">Find jobs with your phone</h2>

				<p>Download the MyJobKart app from the</p>
				<a href="#" class="btn btn-theme btn-default"><i
					class="fa fa-android bordered-right dark"></i> Android</a> <span
					class="space-inline-10"></span> <a href="#"
					class="btn btn-theme btn-default"><i
					class="fa fa-apple bordered-right dark"></i> Iphone</a>
			</div>
		</div>
		<div class="modal fade" id="myModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					
					<form>
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Forgot Password</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Enter Your Email</label> <input type="email"
									class="form-control " name="text" placeholder="Email">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-theme"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success btn-theme">Send</button>
						</div>
					</form>
				</div>
			</div>
		</div> --%>
	</div>
</body>
</html>