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
			
			$('input[type="password"]').each(function() {
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
										Create JobAlert</a>
								</p>

								<form:form id="myForm" modelAttribute="jobalert" method="POST">

									<h4 class="resume-sub-title list-capitalize">
										<span>Personal :</span>
									</h4>

									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">
												<label>First Name</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control"
													path="firstName" id="firstnameInput"
													placeholder="First Name" maxlength="50" />
												<form:errors path="firstName" cssClass="error" />
											</div>
											<div class="col-xs-6">
												<label>Email Address</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control" path="emailId"
													id="emailIDInput" placeholder="Email Address"
													maxlength="40" />
												<form:errors path="emailId" cssClass="error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">
												<label>Name of the Alert</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control"
													path="alertName" id="alertNameInput"
													placeholder="Name of the Alert" maxlength="50" />
												<form:errors path="alertName" cssClass="error" />
											</div>
											<div class="col-xs-6">

												<label>Password</label> <font style="color: red;">* </font>
												<form:input type="password" class="form-control"
													path="password" id="passwordInput" placeholder="Password"
													maxlength="8" />
												<form:errors path="password" cssClass="error" />

											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">
												<label>Qualifications</label> <font style="color: red;">*
												</font>
												<form:select type="text" id="qualificationId" name="degree"
													path="degree" class="form-control">
													<form:option value="">Select Educational Qualification</form:option>
													<form:option value="BE">BE</form:option>
													<form:option value="B.COM">B.COM</form:option>
													<form:option value="BSC">BSC</form:option>
													<form:option value="BCA">BCA</form:option>
													<form:option value="BA">BA</form:option>
													<form:option value="BBA">BBA</form:option>
												</form:select>
												<form:errors path="degree" cssClass="error" />
											</div>

											<div class="col-xs-6">
												<label>Year Of Passing</label> <font style="color: red;">*
												</font>
												<form:input type="text" id="passyearId" name="yearOfPassing"
													path="yearOfPassing" class="form-control" maxlength="4"
													placeholder="Year Of Passing" />

												<form:errors path="yearOfPassing" cssClass="error" />
											</div>
										</div>
									</div>

									<h4 class="resume-sub-title list-capitalize">
										<span>Professional :</span>
									</h4>

									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">
												<label>Key Skills</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control"
													path="keySkills" id="keyskillsInput"
													placeholder="Key Skills" maxlength="50" />
												<form:errors path="keySkills" cssClass="error" />
											</div>

											<div class="col-xs-6">

												<label>Experience</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control"
													path="experienceInYear" id="experienceInput"
													placeholder="Experience" maxlength="2" />
												<form:errors path="experienceInYear" cssClass="error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">
												<label>Role</label> <font style="color: red;">* </font>
												<form:input type="text" class="form-control" path="role"
													id="roleInput" placeholder="Role" maxlength="50" />
												<form:errors path="role" cssClass="error" />
											</div>

											<div class="col-xs-6">
												<label>Expected Salary</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control" path="salary"
													id="salaryInput" placeholder="Salary" maxlength="20" />
												<form:errors path="salary" cssClass="error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">

												<label>Preferred Industry</label> <font style="color: red;">*
												</font>
												<form:select type="text" id="preferredIndustryInput"
													name="preferredIndustry" path="preferredIndustry"
													class="form-control">
													<form:option value="">Select Preferred Industry</form:option>
													<form:options items="${IndustryList}" />
												</form:select>
												<form:errors path="preferredIndustry" cssClass="error" />

											</div>

											<div class="col-xs-6">

												<label>Job Type</label> <font style="color: red;">* </font>
												<form:select type="text" id="jobTypeInput" name="jobType"
													path="jobType" class="form-control">
													<form:option value="">Select Job Type</form:option>
													<form:option value="Permanent">Permanent</form:option>
													<form:option value="Part-time">Part-time</form:option>
												</form:select>
												<form:errors path="jobType" cssClass="error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row clearfix">
											<div class="col-xs-6">

												<label>Preferred Location</label> <font style="color: red;">*
												</font>
												<form:input type="text" class="form-control"
													path="preferredLocation" id="firstNameInput"
													placeholder="Location" maxlength="20" />
												<form:errors path="preferredLocation" cssClass="error" />

											</div>
											<div class="col-xs-6">
												<button id="btnSubmit" class="btn btn-theme btn-success "
													style="margin-top: 30px;">Create Alert</button>
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>