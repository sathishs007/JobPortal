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

			$('textarea[type="text"]').each(function() {
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
</script>
<!-- textarea Descriptions put edited pages -->
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
	//]]>
</script>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<title>Myjobkart</title>
</head>
<body>

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
	<script language="javascript" type="text/javascript">
		function limitText(limitField, limitCount, limitNum) {
			if (limitField.value.length > limitNum) {
				limitField.value = limitField.value.substring(0, limitNum);
			} else {
				limitCount.value = limitNum - limitField.value.length;
			}
		}
	</script>
	<div class="container">
		<div class="col-sm-12">
			<div class="box-list">
				<div class="item">
					<div class="row">
						<h3 class="text-center no-margin titleunderline">Update
							Subuser Profile</h3>
						<hr />
						<!-- form post a job -->
						<form:form id="myForm" method="post" class="login-form clearfix"
							modelAttribute="subuserupdate">

							<center>
								<div class="warning" style="width: 100%; text-align: left;">

									<c:if test="${not empty successMessage}">
										<div class="alert alert-success" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>Success</strong>
											<c:out value="${successMessage}"></c:out>
										</div>
									</c:if>

									<c:if test="${not empty errorMessage}">
										<div class="alert alert-danger" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>Oops!</strong>
											<c:out value="${errorMessage}"></c:out>
										</div>
									</c:if>
								</div>
							</center>


							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>First Name</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="firstName"
											id="firstNameInput" placeholder="First Name" maxlength="20" />
										<form:errors path="firstName" cssClass="error" />
									</div>

									<div class="col-xs-6">
										<label>Last Name</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="lastName"
											id="lastNameInput" placeholder="Last Name" maxlength="20" />
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>Email</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control"
											path="emailAddress" id="emailIdInput" placeholder="Email Id"
											maxlength="40" />
										<form:errors path="emailAddress" cssClass="error" />
									</div>


									<div class="col-xs-6">
										<label>Confirm EmailAddress</label> <font style="color: red;">*
										</font>
										<form:input type="text" class="form-control"
											path="confirmEmailAddress" id="confirmemailaddressInput"
											placeholder="Confirm EmailAddress" maxlength="40" />
										<form:errors path="confirmEmailAddress" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class=" row clearfix">
									<div class="col-xs-6">
										<label>Password</label> <font style="color: red;">* </font>
										<form:input type="password" class="form-control"
											path="password" id="passwordInput" placeholder="password"
											maxlength="30" readonly="true" />
										<form:errors path="password" cssClass="error" />
									</div>

									<div class="col-xs-6">
										<label>Confirm Password</label> <font style="color: red;">*
										</font>
										<form:input type="password" class="form-control"
											path="confirmPassword" id="confirmPasswordInput"
											placeholder="Confirm Password" maxlength="30" readonly="true" />
										<form:errors path="confirmPassword" cssClass="error" />
									</div>
								</div>
							</div>


							<div class="form-group">
								<div class=" row clearfix">
									<div class="col-xs-6">
										<label>Phone Number</label> <font style="color: red;">*</font>
										<form:input type="text" class="form-control"
											path="contactNumber" id="contactNumberInput"
											placeholder="Phone Number" maxlength="10" />
										<form:errors path="contactNumber" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<div class="form-group" style="margin-top: 30px;">
											<button id="btnSubmit" class="btn btn-t-primary btn-theme">Update
												Subuser</button>
											<a href="employer_subuser_view.html"> <input
												type="button" value="Cancel"
												class="btn btn-t-primary btn-theme" /></a>
										</div>
									</div>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>

			<!-- <div class="modal fade" id="myModal">

				<div class="modal-dialog ">
					<div class="modal-content"></div>
				</div>

			</div> -->
		</div>
	</div>
</body>
</html>
