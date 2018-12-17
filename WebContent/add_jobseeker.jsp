<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<html>

<body>
	<!-- wrapper page -->
	<!-- end main-header -->

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
<script type="text/javascript">
    function fileCheck(obj) {
            var fileExtension = ['jpeg', 'jpg', 'png',  'bmp'];
            if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1){
            	document.getElementById("PhotoInput").value='';
                alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");
            }     
    }
</script> 


	<!-- body-content -->
	<!-- <div class="container">
		<div class="col-sm-12"> -->
			<div class="box-list">
				<div class="item">
				
				<!-- <div id="search" class="pull-right">
					<a><i class="fa fa-search fa-lg"></i></a>
				</div> -->
					<div class="row">
						<div class="text-center">
						<h3 class="text-center no-margin titleunderline">Create Jobseeker</h3>
				         <hr />
						</div>

						<form:form id="myForm" method="post" class="login-form clearfix" action="add_jobseeker.html"
							commandName="jobseeker" enctype="multipart/form-data">
							<c:if test="${not empty message}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Success</strong>
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
							<div class="form-group">
								<label> Name</label> <font style="color: red;">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="text" class="form-control " path="firstName"
											id="firstNameInput" placeholder="First Name" maxlength="50"/>
										<form:errors path="firstName" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="text" class="form-control" path="lastName"
											id="lastNameInput" placeholder="Last Name" maxlength="50"/>
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Email</label> <font style="color: red;">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="email" class="form-control"
											path="emailAddress" id="emailAddressInput"
											placeholder="Email Address" maxlength="50"/>
										<form:errors path="emailAddress" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="email" class="form-control"
											path="confirmEmailAddress" id="confirmEmailAddressInput"
											placeholder="Confirm Email Address" maxlength="50"/>
										<form:errors path="confirmEmailAddress" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Password</label> <font style="color: red;">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">

										<form:input type="password" class="form-control"
											path="password" id="passwordInput" placeholder="Password"
											maxlength="8" />
										<form:errors path="password" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="password" class="form-control"
											path="confirmPassword" id="confirmPasswordInput"
											placeholder="Confirm Password" maxlength="8" />
										<form:errors path="confirmPassword" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Mobile Number</label> <font style="color: red;">*</font>
								<form:input type="text" class="form-control" path="mobileNo"
									id="mobileNoInput" placeholder="Mobile Number" maxlength="10" />
								<form:errors path="mobileNo" cssClass="error" />
							</div>

							<div class="form-group">
								<label>Photo(Optional)</label> <input type="file"
									name="profileImages" path="profileImage" id="PhotoInput"
									placeholder="Photo" onchange="fileCheck(this)" />
							</div>
							<div class="form-group ">
								<form:checkbox path="termsConditionsAgreed" /> I agree the terms and conditions?<a href="" style="color: blue"></a>
								<form:errors path="termsConditionsAgreed" cssClass="error" /></br>
							</div>
							
							
							
							<div class="white-space-10"></div>
							<!-- <div class="form-group"> -->
								<div class="form-group no-margin">
									<button id="btnSubmit" class="btn btn-t-primary btn-theme">Register</button>
									<a href="admin_jobseekers.html"> <input type="button"
										value="Cancel" class="btn btn-t-primary btn-theme" /></a>
								</div>
							<!-- </div> -->
						</form:form>
					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->
</body>
</html>