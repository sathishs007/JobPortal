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
				var nicInstance = nicEditors.findEditor('details');
				var detail = nicInstance.getContent();
				if (detail.length < 5) {
					isValid = false;
					$("#errorDetail").html("Please enter the Details Concern field");
				} else {
					$("#errorDetail").hide();
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
				$('select[type="text"]').each(function() {
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

	<!-- body-content -->
	<div class="body-content clearfix">
		<div class="container  bg-color4 " style="width: 100%">


			
			<h3 class="color-white text-center ">Report a Problem</h3>
		</div>
		<div class="bg-color1 block-section line-bottom">
			<div class="container">
				<h2 class="text-center">
					We're here to help in any way we can.<br /> <small> Please
						submit your grievance below and we'll get back to you shortly.</small>
				</h2>
				
				<div class="white-space-20"></div>
				<div class="row" >
					<div class="col-md-8 col-md-offset-2" style="border:1px solid #e1e1e1;padding:50px;box-shadow: 0 2px 8px #a5a5a5;">
					<div class="warning" style="width: 100%;text-align:left;">

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
							
						<!-- form contact -->
						<form:form name="myform" method="post" modelAttribute="feedback">
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
								<label>Area of Concern</label><font style="color: red;">*</font>
								<form:select type="text" path="area" class="form-control">
								<form:option value="">Select Any Available Option</form:option>
									<form:option value="Unable To Login">Unable To Login</form:option>
									<form:option value="Unable To Search Resumes">Unable To Search Resumes</form:option>
									<form:option value="Getting Logged out Frequently">Getting Logged out Frequently</form:option>
									<form:option value="How to Create a sub user">How to Create a sub user</form:option>
									<form:option value="Not Receiving response for posted
										jobs">Not Receiving response for posted
										jobs</form:option>
									<form:option value="Unable to edit job">Unable to edit job</form:option>
									<form:option value="Getting error while posting a job">Getting error while posting a job</form:option>
									<form:option value="Getting junk resumes in search
										results">Getting junk resumes in search
										results</form:option>
									<form:option value="Unable to open resumes">Unable to open resumes</form:option>
									<form:option value="Unable to download resumes">Unable to download resumes</form:option>
									<form:option value="Unable to forward resumes">Unable to forward resumes</form:option>
									<form:option value="Unable to make payments for new
										product">Unable to make payments for new
										product</form:option>
										</form:select>
										<form:errors path="area" cssClass="error" />
							</div>
							<div class="form-group">
								<label>Subject</label> <font style="color: red;">*</font>
								<form:input type="text" class="form-control" path="subject"
									id="subject" placeholder="Subject" maxlength="60" />
								<form:errors path="subject" cssClass="error" />
							</div>
							<div class="form-group">
								<label>Details of Concern</label> <font style="color: red;">*</font>
								<form:textarea type="text1" class="form-control" path="details"
									id="details" />
								<form:errors path="details" cssClass="error" />
								<div id="errorDetail" style="color: red;"></div>

							</div>
							<div class="form-group text-center">
								<div class="white-space-10"></div>
								<button type="submit" id="btnSubmit"
									class="btn btn-theme btn-lg btn-long btn-t-primary btn-pill">Send</button>
							</div>
						</form:form>
						<!-- end form contact -->

						<!-- end box with icon -->

					</div>
				</div>
			</div>
		</div>

	</div>
	<!--end body-content -->

</body>
</html>