<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
	//]]>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MyjobKart</title>
</head>
<body>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#btnSubmit').click(function(e) {
				var isValid = true;
				var nicInstance = nicEditors.findEditor('message');
				var address = nicInstance.getContent();
				if (address.length < 5) {
					isValid = false;
					$("#errorAddress").html("Please enter the Message field");
				} else {
					$("#errorAddress").hide();
				}
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
	</script>
	<div class="body-content clearfix">
		<div class="bg-color1 block-section line-bottom">
			<div class="container"
				style="border: 1px solid #e1e1e1; width: 80%; margin: 0% 1% 0 10% 0%;box-shadow: 0 2px 8px #a5a5a5;">
				<div class="white-space-20"></div>
				<div class="row">

					<div class="col-md-8">
						<h3 class="text-center">
							Contact Details</small>
						</h3>

						<h4 class="text-center">
							We're here to help in any way we can.<br /> <small>
								Please submit your request below and we'll get back to you
								shortly.</small>
						</h4>
						<div class="block-section-sm box-list-area">


							<div class="text-center">
								<div class="warning">
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
								</div>
							</div>

							<div style="margin-top: 0%; margin-left: 52px;">
								<form:form name="myform" method="post" modelAttribute="contact">

									<div class="form-group">
										<label>Name</label> <font style="color: red;">*</font>
										<form:input type="text" class="form-control" path="name"
											id="nameInput" placeholder="Name" maxlength="20" />
										<form:errors path="name" cssClass="error" />
									</div>

									<div class="form-group">
										<label>Email</label> <font style="color: red;">*</font>
										<form:input type="email" class="form-control" path="email"
											id="lemailIdInput" placeholder="Email Id" maxlength="60" />
										<form:errors path="email" cssClass="error" />
									</div>
									<div class="form-group">
										<label>Phone Number</label> <font style="color: red;">*</font>
										<form:input type="text" class="form-control" path="phoneno"
											id="phone" placeholder="Phone Number" maxlength="10" />
										<form:errors path="phoneno" cssClass="error" />
									</div>

									<div class="form-group">
										<label>Subject</label> <font style="color: red;">*</font>
										<form:input type="text" class="form-control" path="subject"
											id="subject" placeholder="Subject" maxlength="60" />
										<form:errors path="subject" cssClass="error" />
									</div>

									<div class="form-group">
										<label>Message</label> <font style="color: red;">*</font>
										<form:textarea type="text1" class="form-control"
											path="message" id="message" cssStyle=" width : 598px;"/>
										<form:errors path="message" cssClass="error" />
										<div id="errorAddress" style="color: red;"></div>

									</div>
									<div class="form-group text-center">
										<div class="white-space-10"></div>
										<button type="submit" id="btnSubmit"
											class="btn btn-theme btn-lg btn-long btn-t-primary btn-pill">Send</button>
									</div>
								</form:form>
							</div>
							<!-- end form contact -->

							<div class="white-space-10"></div>
							<p class="text-center">
								<span class="span-line">OR</span>
							</p>

							<!-- box with icon -->
							<div class="row">
								<div class="col-md-6">
									<div class="box-ic-center ">
										<i class="fa fa-phone ic-box"></i>
										<h4>Phone</h4>
										<p>044 42824663</p>
									</div>

								</div>
								<div class="col-md-6">
									<div class="box-ic-center ">
										<i class="fa fa-skype ic-box"></i>
										<h4>Skype</h4>
										<p>info.myjobkart@gmail.com</p>
									</div>

								</div>
							</div>
							<!-- end box with icon -->

						</div>
					</div>




					<div class="col-md-3">
						<div class="block-section-sm side-right">
							<div class="row">
								<div class="col-xs-6">

									<h5 class="titleunderline"
										style="font-weight: bold; font-size: 16px;">Chat With Us</h5>
								</div>
							</div>

							<div class="result-filter">
								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_2" data-toggle="collapse">Sales
										Enquiries <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<div style="border: 1px solid #000; padding: 16px;">
									<i class="fa fa-globe" aria-hidden="true"></i> <span>From
										India</span> <br /> <i class="fa fa-mobile" aria-hidden="true"></i>
									<span>Mobile: +91-044 42824663</span> <br /> <i
										class="fa fa-envelope-o" aria-hidden="true"></i> <span>Email:
										sales@myjobkart.com</span>

								</div>
							</div>

							<div class="result-filter">
								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_2" data-toggle="collapse">Customer
										Support <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<div style="border: 1px solid #000; padding: 16px;">
									<i class="fa fa-globe" aria-hidden="true"></i> <span>From
										India</span> <br /> <i class="fa fa-mobile" aria-hidden="true"></i>
									<span>Mobile: +91-8098097691</span> <br /> <i
										class="fa fa-envelope-o" aria-hidden="true"></i> <span>Email:
										info.myjobkart@gmail.com</span>

								</div>
							</div>


						</div>
					</div>







				</div>




			</div>
		</div>

		<!-- link map toogle -->
		<!-- <div class="bg-color2 block-section-xs line-bottom">
			<div class="container text-center">
				<a href="#map-toogle" id="btn-map-toogle" data-toggle="collapse">Check
					Our Office</a>
			</div>
		</div> -->
		<!-- end link map toogle -->

		<!-- block map -->
		<div class="collapse" id="map-toogle">
			<div class=" bg-color2" id="map-area">
				<div class="container">

					<!-- map description -->
					<div class="marker-description">
						<div class="inner">
							<h4 class="no-margin-top">Office Location</h4>
							Central Jakarta No 1234, Jakarta, Indonesia
						</div>
					</div>
					<!-- end map description -->
				</div>
				<div class="map-area-detail">

					<!-- change the data lat and lang here -->
					<div class="box-map-detail" id="map-detail-job"
						data-lat="48.856318" data-lng="2.351866"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>