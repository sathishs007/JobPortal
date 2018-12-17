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

			if (isValid == false)
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

<title>MyjobKart</title>
</head>
<body>

	<script language="javascript" type="text/javascript">
		function limitText(limitField, limitCount, limitNum) {
			if (limitField.value.length > limitNum) {
				limitField.value = limitField.value.substring(0, limitNum);
			} else {
				limitCount.value = limitNum - limitField.value.length;
			}
		}
	</script>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Update
					JobAlert Details</h3>
				<hr />
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					enctype="multipart/form-data" modelAttribute="updateAlert">


					<!-- Start Warning Message  -->
					<center>
						<div class="warning" style="width: 100%; text-align: left;">

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
						</div>
					</center>
					<!-- End Warning Message  -->

					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Key Skills</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="keySkills"
									id="keyskillsInput" placeholder="Key Skill" maxlength="20" />
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
									id="roleInput" placeholder="Role" maxlength="20" />
							</div>

							<div class="col-xs-6">
								<label>Name of the Alert</label> <font style="color: red;">*
								</font>
								<form:input type="text" class="form-control" path="alertName"
									id="alertNameInput" placeholder="Name of the Alert"
									maxlength="20" />
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
								<div class="form-group " style="margin-top: 30px;">
									<input type="submit" id="btnSubmit" value="Submit"
										class="btn btn-t-primary btn-theme" /><a
										href="jobseeker_jobalert_view.html"> <input type="button"
										value="Cancel" class="btn btn-t-primary btn-theme" /></a>
								</div>
							</div>
						</div>
					</div>
				</form:form>
				<!-- end form post a job -->
			</div>
		</div>
	</div>


</body>
</html>