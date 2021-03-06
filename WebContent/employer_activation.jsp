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

<!--favicon-->
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">

<!-- bootstrap -->
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Icons -->
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- lightbox -->
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">


<!-- Themes styles-->
<link href="resources/theme/css/theme.css" rel="stylesheet">
<!-- Your custom css -->
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">


</head>
<body>
	<!-- wrapper page -->
	<!-- body-content -->
	<div class="body-content clearfix">

		<div class="block-section bg-color4">
			<div class="container">
				<div class="panel panel-md">
					<div class="panel-body">

						<!-- Start Active Employer  -->

						<c:choose>
							<c:when test="${activationEmployer.isActive=='true'}">
								<div class="row">
									<div class="col-md-12">

										<div class="white-space-10"></div>
										<div class="modal-header">
											<h4 class="modal-title">
												<i class="fa fa-thumbs-up pull-left bordered-right"></i>
												Your Account Already Activated
											</h4>
										</div>


										<div class="modal-body">
											<p>Click here to Employer Login in MyJobKart</p>
										</div>

										<div class="modal-footer">
											
												<a class="btn btn-theme btn-success" href="employer_sign_in.html"> Login</a>
											
										</div>
										<c:if test="${not empty message}">
											<div class="message red">${message}</div>
										</c:if>


									</div>
								</div>
							</c:when>
							<c:otherwise>



								<div class="row">
								<center>
						<div class="warning" style="width: 100%;text-align:left;">
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
							</div></center>
								
									<div class="col-md-12">


										<div class="white-space-10"></div>
										<div class="modal-header">
											<h4 class="modal-title">
												<i class="fa fa-thumbs-up pull-left bordered-right"></i>
												Employer Account Activation
											</h4>
										</div>



										<div class="modal-body">
											<p>Click here to confirmation your registration in
												MyJobKart</p>
										</div>
										<form:form id="myForm" method="post"
											class="login-form clearfix" commandName="activation">

											<div class="modal-footer">
												<button class="btn btn-theme btn-success">
													Activate</a>
												</button>
											</div>
											<c:if test="${not empty message}">
												<div class="message red">${message}</div>
											</c:if>

										</form:form>
									</div>
								</div>
							</c:otherwise>



						</c:choose>






					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- box bottom -->
	<div class="block-section bg-color2">
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
	<!-- end box bottom -->

	<!-- modal forgot password -->
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
	</div>
</body>
</html>