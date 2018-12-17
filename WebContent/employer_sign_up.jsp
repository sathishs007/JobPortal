<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
var captcha = true;
$(document).ready(function() {
	$('#myForm').submit(function(e) {
		
		var isValid = true;
		var values = $("#captcha").val();
		if (values== "") {
			
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
		$('#btnSubmit').click(function(e) {
			var isValid3 = true;
		if (remember.checked == 1){
		}else{
			isValid3 = false;
		document.getElementById('agreeId').innerHTML="Please select terms and conditions."; 
		}
		if (isValid3 == false)
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
			$('input[type="Mobile Number"]').each(function() {
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
			var values = $("#captcha").val();
			if (values != "") {
				$.ajax({
					type : "POST",
					data : "value="+values,
					url : "captcha_varification",
					success: function(data){
						
						if(data != "success"){
							captcha = false;
							$("#errorCaptcha").html("Incorrect Captcha");
							$("#errorCaptcha").show();
						}
						else{
							captcha = true;
							$("#errorCaptcha").hide();
						}
					}
				});
			}
		});

	});
</script>

<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1){
				document.getElementById("PhotoInput").value='';
				alert("Only '.jpeg','.jpg', '.png',  '.bmp' formats are allowed.");
			}	
		}

		
	</script>
<script type="text/javascript">
function newCompany() {
	document.getElementById("newCompany").style.display = 'none';
	document.getElementById("textCompany").style.display = 'block';
	document.getElementById("dropCompany").style.display = 'none';
}


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
			<div class="container cont-width">
				<div class="panel panel-mw">
					<div class="panel-body">
						<div class="row">
							<div class="warning"
								style="width: 100%; text-align: center; padding-left: 20px; padding-right: 19px;">
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
								<c:if test="${not empty sucessmessage}">
									<div class="alert alert-success" role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<strong>Success</strong>
										<c:out value="${sucessmessage}"></c:out>
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
									commandName="employer" enctype="multipart/form-data"
									modelAttribute="employer">

									<div class="form-group">
										<label>First Name</label> <font style="color: red;">* </font>
										<div class="row clearfix">
											<div class="col-xs-6">
												<form:input type="text" class="form-control"
													path="firstName" id="firstNameInput"
													placeholder="First Name" maxlength="50" />
												<form:errors path="firstName" cssClass="error" />
											</div>

											<div class="col-xs-6">
												<form:input type="text" class="form-control" path="lastName"
													id="lastNameInput" placeholder="Last Name" maxlength="50" />
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
													placeholder="Email Address" maxlength="100" />
												<form:errors path="emailAddress" cssClass="error" />
												<div id="errorEmailId" style="color: red"></div>
											</div>
											<div class="col-xs-6">

												<form:input type="email" class="form-control"
													path="confirmEmailAddress" id="confirmEmailAddressInput"
													placeholder="Confirm Email Address" maxlength="100" />
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
									<div class="form-group" style="display: block">
										<div class="row clearfix">
											<div class="col-xs-6" id="dropCompany">
												<label>Company Name</label> <font style="color: red;">*
												</font>

												<form:select type="texty" path="companyName"
													id="companyNameInput" class="form-control">
													<form:option value="">Select Company Name</form:option>
													<form:options items="${CompanyList}" />
												</form:select>
											</div>
											<div class="col-xs-6" id="newCompany" style="display: block">

												<label>Other Company name</label>
												<button type="button" onclick="newCompany()"
													class="btn btn-theme  btn-block ">Other Company</button>
											</div>
										</div>


									</div>
									<div class="form-group" style="display: none" id="textCompany">
										<label>Company Name</label> <font style="color: red;">*
										</font>
										<form:input type="texty" path="otherCompany"
											id="otherCompanyInput" class="form-control" />
										<form:errors path="otherCompany" cssClass="error" />
									</div>
									<div class="form-group">
										<label>WebSite</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="webSite"
											id="webSiteInput" placeholder="Website" maxlength="100" />
										<form:errors path="webSite" cssClass="error" />
										<div class="color-black-mute">
											<small>Format: www.example.com</small>
										</div>
									</div>
									<div class="form-group">
										<label>Contact Number</label> <font style="color: red;">*
										</font>
										<div class="row clearfix">
											<div class="col-xs-6">
												<form:input type="text" class="form-control"
													path="contactNumber" id="contactNumberInput"
													placeholder="Contact Number" maxlength="10" />
												<form:errors path="contactNumber" cssClass="error" />
											</div>
											<div class="col-xs-6">
												<form:input type="text" class="form-control"
													path="mobileNumber" id="mobileNumberInput"
													placeholder="Mobile Number" maxlength="10" />
												<form:errors path="mobileNumber" cssClass="error" />
												<div id="errormobileNumber" style="color: red"></div>
											</div>
										</div>
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
													style="background-color: #394e63; height: 40px; text-align: center; background-repeat: no-repeat;">
													<div
														style="padding-top: 6px; color: #FFFFFF; font-size: 25px; font-weight: 600px;">

														<c:out value="${captcha}"></c:out>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<form:checkbox path="termsConditionsAgreed" />
										I agree the terms and conditions? <a
											href="employer_terms_privacy.html" style="color: blue">Terms
											and conditions</a><br>
										<form:errors path="termsConditionsAgreed" cssClass="error" />
									</div>
									<div class="white-space-10"></div>
									<div class="form-group no-margin">

										<button type="submit" id="btnSubmit"
											class="btn btn-theme btn-lg btn-t-primary btn-block ">Register</button>
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