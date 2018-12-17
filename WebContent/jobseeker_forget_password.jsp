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
			if (isValid == false)
				e.preventDefault();
		});
	});
	$(document).ready(function() {
		$('#close').click(function(e) {
			location.reload();
		});
	});
</script>


<html>
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
						<div class="row">
							<div class="warning" style="padding:5px 10px 10px 10px;">
							 <c:if test="${not empty successmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Success</strong>
									<c:out value="${successmessage}"></c:out>
								</div>
							</c:if>
							
								<c:if test="${not empty message}">
									<div class="alert alert-info" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								 <strong>Info!</strong>
										<c:out value="${message}"></c:out>
									</div>
								</c:if>
							</div>
							<div class="col-md-12">
								<div class="white-space-10"></div>
								<div class="modal-header">
									<h4 class="modal-title">
										<i class="fa fa-unlock-alt pull-left bordered-right"></i>
										Forgot Password
									</h4>
								</div>
								<form:form id="myForm" method="post" class="login-form clearfix"
									commandName="forgetLogin">
									<div class="modal-body">
										<div class="form-group">
											 <label>Email</label><font style="color: red;">* </font>
											<form:input name="emailAddress" type="text"
												path="emailAddress" class="form-control"
												id="emailAddressInput" placeholder="Email Address" />
											<form:errors path="emailAddress" cssClass="error" />
										</div>
									</div>
									<div class="white-space-10"></div>
									<div class="modal-footer">
										<button id="btnSubmit" class="btn btn-theme btn-success ">Submit</button>
										<a href="jobseeker_sign_in.html?param=cancel"> <input
											type="button" value="Cancel"
											class="btn btn-default btn-theme" /></a>
									</div>
								</form:form>
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