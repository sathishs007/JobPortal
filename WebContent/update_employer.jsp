<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


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




<html>

<body>
	<!-- wrapper page -->
	<!-- end main-header -->


	<!-- body-content -->
	<!-- <div class="container">
		<div class="col-sm-12"> -->
			<div class="box-list">
				<div class="item">
					<div class="row">
						<div class="text-center">
							<!-- <h3 class="margin-top">
								<i class=" fa fa-pencil"></i>Update Employer <i
									class="fa fa-user"></i>
							</h3> -->
							<h3 class="text-center no-margin titleunderline">Update Employer</h3>
				         <hr />
						</div>

						<form:form id="myForm" method="post" class="login-form clearfix"
							commandName="updateProfile" enctype="multipart/form-data"
							modelAttribute="updateProfile">
							<c:if test="${not empty message}">
								<div class="message red">${message}</div>
							</c:if>
							<div class="form-group">
								<label>First Name</label> <font style="color:red">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="text" class="form-control" path="firstName"
											id="firstNameInput" placeholder="First Name" />
										<form:errors path="firstName" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="text" class="form-control" path="lastName"
											id="lastNameInput" placeholder="Last Name" />
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Email</label> <font style="color:red">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="email" class="form-control"
											path="emailAddress" id="emailAddressInput"
											placeholder="Email Address" />
										<form:errors path="emailAddress" cssClass="error" />
									</div>
									<div class="col-xs-6">

										<form:input type="text" class="form-control"
											path="confirmEmailAddress" id="confirmEmailAddressInput"
											placeholder="Confirm EmailAddress" />
										<form:errors path="confirmEmailAddress" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Password</label> <font style="color:red">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="password" class="form-control"
											path="password" id="passwordInput" placeholder="Password" />
										<form:errors path="password" cssClass="error" />
									</div>
									<div class="col-xs-6">

										<form:input type="password" class="form-control"
											path="confirmPassword" id="confirmPasswordInput"
											placeholder="Confirm Password" />
										<form:errors path="confirmPassword" cssClass="error" />
									</div>
								</div>
							</div>
							<%-- <div class="form-group">
								<label>Company Name</label><font style="color:red">*</font>
								<form:input type="text" class="form-control" path="companyName"
									id="companyNameInput" placeholder="Company Name" />
								<form:errors path="companyName" cssClass="error" />
							</div> --%>
							<div class="form-group" style="display: block" id="dropCompany">
									<label>Company Name</label> <font style="color: red;">*
									</font>
									<form:select type="texty" path="companyName"
											id="companyNameInput" class="form-control">
											<form:option value="">Select Company Name</form:option>
											<form:options items="${CompanyList}" />
										</form:select>
								</div>
								
								<div class="form-group" style="display: none" id = "textCompany">
										<label>Company Name</label> <font style="color: red;">*
										</font>
										<form:input type="texty" path="otherCompany"
											id="otherCompanyInput" class="form-control" />
										<form:errors path="otherCompany" cssClass="error" />
									</div>
									
									<div class="form-group">
						<div class="row clearfix">
						<div class="col-xs-3" id="newCompany" style="display: block">
				          <input type="button" id="otherCompany" value="Other Names" 
										class="btn btn-theme  btn-block" onclick="newCompany()" />
				       	</div>
						</div>
						</div>	
									
							<div class="form-group">
								<label>Website</label> <font style="color:red">*</font>
								<form:input type="text" class="form-control" path="webSite"
									id="webSiteInput" placeholder="Website" />
								<form:errors path="webSite" cssClass="error" />
							</div>
							<%-- <div class="form-group">
								<label>Company Type</label>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="text" class="form-control"
											path="companyType" id="companyTypeInput"
											placeholder="Company Type" />
										<form:errors path="companyType" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="text" class="form-control"
											path="industryType" id="industryTypeInput"
											placeholder="Industry Type" />
										<form:errors path="industryType" cssClass="error" />
									</div>
								</div>
							</div> --%>
							<div class="form-group">
								<label>Contact Number</label><font style="color:red">*</font>
								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="text" class="form-control"
											path="contactNumber" id="contactNumberInput"
											placeholder="Contact Number" maxlength="11" />
										<form:errors path="contactNumber" cssClass="error" />
									</div>
									<div class="col-xs-6">
										<form:input type="text" class="form-control"
											path="mobileNumber" id="mobileNumberInput"
											placeholder="Mobile Number" maxlength="11" />
										<form:errors path="mobileNumber" cssClass="error" />
									</div>
								</div>
							</div>
							<!-- <div class="form-group">
								<label>Company Logo</label> <font style="color:red">*</font><input type="file"
									name="companyLogos" path="companyLogo" id="PhotoInput"
									placeholder="Photo" onchange="fileCheck(this)" />
							</div> -->
							<%-- <div class="form-group">
								<form:checkbox path="termsConditionsAgreed" />
								I agree the terms and conditions? <a href="terms_privacy.jsp"
									style="color: blue">Terms and conditions</a>
							</div> --%>
							<div class="white-space-10"></div>
							<!-- <div class="text-center"> -->
								<div class="form-group no-margin">
									<button id="btnSubmit" class="btn btn-t-primary btn-theme ">Register</button>
									<a href="employer_details.html"> <input type="button"
										value="Cancel" class="btn btn-t-primary btn-theme" /></a>
								</div>
							<!-- </div> -->
						</form:form>
						<!-- form login -->
					</div>
				</div>
			</div>
		<!-- </div> -->
		<div class="white-space-20"></div>
	<!-- </div> -->
</body>
</html>