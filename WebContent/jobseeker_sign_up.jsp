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
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		var captcha = true;
		$(document).ready(function() {
			$('#myForm').submit(function(e) {

				var isValid = true;
				var values = $("#captcha").val();
				if (values == "") {

					isValid = false;
				}
				$("#captcha").css({
					"border" : "1px solid red",
				});

				$('input[type="text"]').each(function() {
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

				if (isValid == false || captcha == false)
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

		$(document).ready(function() {
			$('#btnSubmit').click(function(e) {
				var isValid1 = true;
				$('input[type="text"]').each(function() {
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

		$(document).ready(function() {
			$('#btnSubmit').click(function(e) {
				var isValid2 = true;
				$('input[type="email"]').each(function() {
					if ($.trim($(this).val()) == '') {
						isValid2 = false;
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
				if (isValid2 == false)
					e.preventDefault();
			});
		});

		$(document).ready(function() {
			$('#close').click(function(e) {
				location.reload();
			});
		});

		$(document).ready(function() {
			$('#btnSubmit').click(function(e) {
				var isValid1 = true;
				$('input[type="text"]').each(function() {
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

		$(document).ready(function() {
			$('#captcha').change(function() {
				var value = $("#captcha").val();
				if (value != "") {
					$.ajax({
						type : "POST",
						data : "values=" + value,
						url : "captcha_varificationss",
						success : function(data) {
							if (data != "success") {
								captcha = false;
								$("#errorCaptcha").html("Incorrect Captcha");
								$("#errorCaptcha").show();
							} else {
								captcha = true;
								$("#errorCaptcha").hide();
							}
						}
					});
				}
			});

		});

		var value = $("#captcha").val();
		if (value == "") {
			isValid = false;
		}
		$("#captcha").css({
			"border" : "1px solid red",
		});
	</script>


	<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("PhotoInput").value = '';
				alert("Only '.jpeg','.jpg', '.png',  '.bmp' formats are allowed.");
			}
		}
	</script>

	<div class="body-content clearfix">
		<div class="block-section bg-color4">
			<div class="container cont-width">
				<div class="panel panel-mw">
					<div class="panel-body">
						<div class="row">
							<div class="warning" style="width: 100%; text-align: center;">
								<c:if test="${not empty message}">
									<%@ include file="successToast.jsp"%>
								</c:if>
								<c:if test="${not empty errormessage}">
									<div class="wpb_alert wpb_alert_alt">
										<h1>Error</h1>
										<c:out value="${errormessage}"></c:out>
									</div>
								</c:if>
								<c:if test="${not empty Infomessage}">
									<div class="alert alert-info" role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<strong>Info!</strong>
										<c:out value="${Infomessage}"></c:out>
									</div>
								</c:if>
							</div>
							<div class="col-md-12">

								<p>
									<a href="#" class="btn btn-danger btn-theme btn-block">
										Sign Up</a>
								</p>
								<!-- form login -->
								<form:form id="myForm" method="post" class="login-form clearfix"
									commandName="jobseeker" enctype="multipart/form-data"
									modelAttribute="jobseeker">

									<div class="form-group">
										<label>First Name</label> <font style="color: red;">* </font>
										<div class="row clearfix">
											<div class="col-xs-6">
												<form:input type="text" class="form-control"
													path="firstName" id="firstNameInput"
													placeholder="First Name" maxlength="20" />
												<form:errors path="firstName" cssClass="error" />
											</div>
											<div class="col-xs-6">
												<form:input type="text" class="form-control" path="lastName"
													id="lastNameInput" placeholder="Last Name" maxlength="20" />
												<form:errors path="lastName" cssClass="error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>Email</label> <font style="color: red;">* </font>
										<div class="row clearfix">
											<div class="col-xs-6">
												<form:input type="email" class="form-control"
													path="emailAddress" id="emailAddressInput"
													placeholder="Email Address" maxlength="50" />
												<form:errors path="emailAddress" cssClass="error" />
												<div id="errorEmailId" style="color: red"></div>
											</div>
											<div class="col-xs-6">
												<form:input type="email" class="form-control"
													path="confirmEmailAddress" id="confirmEmailAddressInput"
													placeholder="Confirm Email Address" maxlength="50" />
												<form:errors path="confirmEmailAddress" cssClass="error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>Password</label> <font style="color: red;">* </font>
										<div class="row clearfix">
											<div class="col-xs-6">

												<form:input type="password" class="form-control"
													path="password" id="passwordInput" placeholder="Password"
													maxlength="8" oncopy="return false" onpaste="return false" />
												<form:errors path="password" cssClass="error" />
											</div>
											<div class="col-xs-6">
												<form:input type="password" class="form-control"
													path="confirmPassword" id="confirmPasswordInput"
													placeholder="Confirm Password" maxlength="8"
													oncopy="return false" onpaste="return false" />
												<form:errors path="confirmPassword" cssClass="error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>Mobile Number</label><font style="color: red;">
											* </font>
										<form:input type="text" class="form-control" path="mobileNo"
											id="mobileNoInput" placeholder="Mobile Number" maxlength="10" />
										<form:errors path="mobileNo" cssClass="error" />
										<div id="errormobileNo" style="color: red"></div>
									</div>
									<div class="form-group">
									<label>Captcha</label> <font style="color: red;"> * </font>
										<div class="row clearfix">
											<div class="col-xs-6">
												
												<input id="captcha" type="text" class="form-control" />
												<div id="errorCaptcha" style="color: red;"></div>
											</div>
											<div class="col-xs-6">
												<div
													style="background-color:#394e63; height: 40px; text-align: center; background-repeat: no-repeat;">
													<div style="padding-top:6px;color: #FFFFFF; font-size: 25px; font-weight: 600px;">
														
															<c:out value="${captcha}"></c:out>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- <div class="form-group">
										<label>Photo(Optional)</label> <input type="file"
											name="profileImages" path="profileImage" id="PhotoInput"
											placeholder="Photo" onchange="fileCheck(this)" />

									</div> -->

									<div class="form-group">
										<form:checkbox path="termsConditionsAgreed" />

										I agree the terms and conditions?<a
											href="employer_terms_privacy.html" style="color: blue">Terms
											and conditions</a><br>
										<form:errors path="termsConditionsAgreed" cssClass="error" />
									</div>
									<div class="white-space-10"></div>
									<div class="form-group no-margin">
										<button id="btnSubmit"
											class="btn btn-theme btn-lg btn-t-primary btn-block"
											type="submit">Register</button>
									</div>
								</form:form>
								<!-- form login -->

							</div>
						</div>
					</div>
				</div>

				<div class="white-space-20"></div>
				<div class="text-center color-white">
					By creating an account, you agree to MyJobKart <br /> <a href="#"
						class="link-white"><strong>Terms of Service</strong></a> and
					consent to our <a href="#" class="link-white"><strong>Privacy
							Policy</strong></a>.
				</div>
			</div>
		</div>

		<div class="block-section bg-color2">
			<div class="container text-center">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<i class="fa fa-question fa-5x box-icon"></i>
						<h2 class="">Why create an account?</h2>
						<hr />
						<p>
							<i class="fa fa-check"></i> Manage, create and delete your job
							alerts
						</p>
						<p>
							<i class="fa fa-check"></i> Access your saved jobs and notes from
							any computer
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
