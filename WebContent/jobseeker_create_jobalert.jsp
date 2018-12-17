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

<title>MyjobKart|Jobseeker|home</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">

<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas();
	});
	//]]>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Create Job
					Alert</h3>
				<hr />

				<form:form id="myForm" modelAttribute="jobAlertBO" method="POST">

					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Key Skills</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="keySkills"
									id="keyskillsInput" placeholder="Key Skill" maxlength="50" />
								<form:errors path="keySkills" cssClass="error" />
							</div>
							<div class="col-xs-6">
								<label>Salary</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="salary"
									id="salaryInput" placeholder="Salary" maxlength="20" />
								<form:errors path="salary" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Location</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control"
									path="preferredLocation" id="firstNameInput"
									placeholder="Location" maxlength="20" />
								<form:errors path="preferredLocation" cssClass="error" />
							</div>

							<div class="col-xs-6">
								<label>Experience</label> <font style="color: red;">* </font>
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
								<label>Name of the Alert</label> <font style="color: red;">*
								</font>
								<form:input type="text" class="form-control" path="alertName"
									id="alertNameInput" placeholder="Name of the Alert"
									maxlength="50" />
								<form:errors path="alertName" cssClass="error" />
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
								<label>Email Address</label> <font style="color: red;">*
								</font>
								<form:input type="text" class="form-control" path="emailId"
									id="emailIDInput" placeholder="Email Address" maxlength="40" />
								<form:errors path="emailId" cssClass="error" />
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
</body>
</html>