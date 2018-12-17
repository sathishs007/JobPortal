
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    function fileCheck(obj) {
            var fileExtension = ['jpeg', 'jpg', 'png',  'bmp'];
            if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1)
                alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");
    }
</script>
<script type="text/javascript">
		function fileChecks(obj) {
			var fileExtension = ['doc','pdf','docx'];
			
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("uploadResumeInput").value = '';
				alert("Only '.Word','PDF' formats are allowed.");
			}
		}
	</script>
<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			
			var nicInstance = nicEditors.findEditor('keySkillsInput');
			var keyskill = nicInstance.getContent();
			if (keyskill.length < 1) {
				isValid = false;
				$("#errorSkill").html("Please enter the KeySkill field");
			} else {
				$("#errorSkill").hide();
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
	
	$(document).ready(function() {
		$('#proceed').click(function(e) {
			var product = $("#productType").val();
			var months = $("#noofmonths").val();
			var ajaxdata = $(
			"#productType")
			.val();
	var ajaxdata1 = $(
			"#noofmonths")
			.val();
	var value = 'productType='+ ajaxdata+ '&&noofmonths='+ ajaxdata1;
	$
	.ajax({
		url : "product_enroll",
		type : "post",
		data : value,
		cache : false,
		success : function(paymentBO) {
			alert("susses");
			$('#modal-advanced').modal('hide');
			$('#removeshow').hide();
		document.getElementById("productEnrolled").innerHTML = "product enrolled";
		document.getElementById("enroll").value = paymentBO.productType;
		$('#content').hide();
		
		}

	});
					
	  });
	});
	
	$(document).ready(function() {
		$('#trailproceed').click(function(e) {
	$.ajax({
		url : "product_enroll_trail",
		type : "post",
		cache : false,
		success : function(paymentBO) {
			alert("susses");
			document.getElementById("productEnrolled").innerHTML = "product enrolled";
			document.getElementById("enrolltrail").value = paymentBO.productType;
			$('#modal-trail').modal('hide');
		
		}

	});
					
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

<script>
	$(document)
			.ready(
					function() {
						$('#stateInput')
								.change(
										function() {
											$
													.ajax({
														type : "GET",
														url : 'locations',
														data : 'state='
																+ $(
																		"#stateInput")
																		.val(),
														success : function(data) {
															//	var obj = JSON.parse(data);
															var len = data.length;

															var location = data;

															$('#cityInput')
																	.text(data);
															var html = '<option value="">Select</option>';
															var len = data.length;
															delimiter: ",";
															for ( var i = 0; i < len; i++) {
																html += '<option value="' + data[i]+ '">'
																		+ data[i]
																		+ '</option>';
															}
															html += '</option>';

															$('#cityInput')
																	.html(html);

														},
													});
										});
					});
</script>
<title>MyjobKart</title>
</head>
<body>

	<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'gif', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("PhotoInput").value = '';
				alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");

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
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Update
					Personal Details</h3>
				<hr />
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="updateProfile" enctype="multipart/form-data"
					modelAttribute="updateProfile">


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
						<label>First Name</label> <font style="color: red;">*</font>
						<div class="row clearfix">
							<div class="col-xs-6">

								<form:input type="text" class="form-control" path="firstName"
									id="firstNameInput" placeholder="First Name" maxlength="20" />
								<form:errors path="firstName" cssClass="error" />
							</div>

							<div class="col-xs-6">

								<form:input type="text" class="form-control" path="lastName"
									id="lastNameInput" placeholder="Last Name" maxlength="20" />
								<form:errors path="lastName" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Email</label><font style="color: red;"> *</font>
								<form:input type="email" class="form-control" path="emailId"
									id="lemailIdInput" placeholder="Email Id" disabled="true" />
								<form:errors path="emailId" cssClass="error" />
							</div>
							<div class="col-xs-6">
								<label>Mobile Number</label> <font style="color: red;"> *
								</font>
								<form:input type="text" class="form-control" path="phone"
									id="phoneInput" placeholder="Mobile Number" maxlength="10" />
								<form:errors path="phone" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Marital Status</label> <font style="color: red;">
									* </font>
								<form:select type="text" id="maritalStatusInput"
									name="maritalStatus" path="maritalStatus" class="form-control">
									<form:option value="">Select Marital Status</form:option>
									<form:option value="Single">Single</form:option>
									<form:option value="Married">Married</form:option>
								</form:select>
								<form:errors path="maritalStatus" cssClass="error" />
							</div>
							<div class="col-xs-6">
								<label>Gender</label> <font style="color: red;">* </font>
								<form:select type="text" id="genderInput" name="gender"
									path="gender" class="form-control">
									<form:option value="">Select Gender</form:option>
									<form:option value="Male">Male</form:option>
									<form:option value="FeMale">FeMale</form:option>
								</form:select>

								<form:errors path="gender" cssClass="error" />
							</div>
						</div>
					</div>


					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Address</label> <font style="color: red;">*</font>
								<form:input type="text" class="form-control" path="address"
									id="locationInput" placeholder="Enter Permenant Address"
									maxlength="150" />

								<form:errors path="address" cssClass="error" />
							</div>

							<div class="col-xs-6">
								<label>Pincode</label> <font style="color: red;">*</font>
								<form:input type="text" path="pincode" id="nationalityInput"
									placeholder="Pincode" maxlength="6" minlength="6"
									class="form-control" />
								<form:errors path="pincode" cssClass="error" />
							</div>


						</div>
					</div>

					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-4">
								<label>City</label> <font style="color: red;">*</font>
								<form:select type="text" class="form-control" path="location"
									id="locationInput">
									<form:option value="">Select City</form:option>
									<form:options items="${cityList}" />
								</form:select>
								<form:errors path="location" cssClass="error" />
							</div>



							<div class="col-xs-4">
								<label>State</label> <font style="color: red;">*</font>
								<form:select type="text" path="state" id="nationalityInput"
									class="form-control">
									<form:option value="">Select State</form:option>
									<form:options items="${stateList}" />
								</form:select>
								<form:errors path="state" cssClass="error" />
							</div>


							<div class="col-xs-4">
								<label>Nationality</label> <font style="color: red;">*</font>
								<form:input type="text" path="nationality" id="nationalityInput"
									placeholder="Nationality" maxlength="20" readonly="true"
									class="form-control" />
								<form:errors path="nationality" cssClass="error" />
							</div>


						</div>
					</div>
					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-4">
								<label>Job Type</label>
								<form:select type="text1" id="jobTypeInput" name="jobType"
									path="jobType" class="form-control">
									<form:option value="">Select JobType</form:option>
									<form:options items="${jobType}" />

								</form:select>
								<form:errors path="jobType" cssClass="error" />
							</div>
							<div class="col-xs-4">
								<label>Industry Type</label> <font style="color: red;">*</font>
								<form:select type="text" path="currentIndustry"
									class="form-control">
									<form:option value="">Select Industry Type</form:option>
									<form:options items="${IndustryList}" />

								</form:select>
								<form:errors path="currentIndustry" cssClass="error" />
							</div>
							<div class="col-xs-4">
								<label>No Of Experience</label> <font style="color: red;">*</font>
								<form:input type="text" path="noOfExperience"
									class="form-control" id="noOfExperienceInput"
									placeholder="No Of Experience" />
								<form:errors path="noOfExperience" cssClass="error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label>Key Skills</label> <font style="color: red;">* </font>
						<form:textarea type="text1" class="form-control" path="keySkills"
							rows="4" id="keySkillsInput" placeholder="Key Skills" />
						<form:errors path="keySkills" cssClass="error" />
						<div id="errorSkill" style="color: red;"></div>
					</div>

					<div class="form-group">
						<label>Profile Description</label>
						<form:textarea type="text1" class="form-control"
							path="profileDescriptions" rows="4" id="descriptionInput"
							placeholder="Profile Descriptions" />
						<form:errors path="profileDescriptions" cssClass="error" />
					</div>


					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Domain Skills</label>
								<form:input type="text1" class="form-control"
									path="domainSkills" id="domainSkillsInput"
									placeholder="Domain Skills" maxlength="100" />
								<form:errors path="domainSkills" cssClass="error" />
							</div>

							<div class="col-xs-6">
								<label>Function Area</label>
								<form:select type="text1" class="form-control" path="function"
									id="functionInput">
									<form:option value="">Select Function Area</form:option>
									<form:options items="${functionArea}" />

								</form:select>
								<form:errors path="function" cssClass="error" />
							</div>
						</div>

					</div>


					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Current Salary</label>
								<form:input type="text1" class="form-control"
									path="currentSalary" id="currentSalaryInput"
									placeholder="Example: 50,000.00" maxlength="15" />
								<form:errors path="currentSalary" cssClass="error" />
							</div>

							<div class="col-xs-6">
								<label>Expected Salary</label>
								<form:input type="text1" class="form-control" path="expectedCtc"
									id="expectedCtcInput" placeholder="Example: 50,000.00"
									maxlength="15" />
								<form:errors path="expectedCtc" cssClass="error" />
							</div>

						</div>
					</div>

					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Job Title</label><font style="color: red;"> *</font>
								<form:input type="text" class="form-control"
									path="profiledescription" id="profiledescriptionInput"
									placeholder="Job Title" maxlength="50" />
								<form:errors path="profiledescription" cssClass="error" />
							</div>
							<div class="col-xs-6">

								<label>Languages Known</label> <font style="color: red;">
									* </font>
								<form:select type="text" class="form-control"
									path="languagesKnown" id="languagesKnownInput" multiple="true">
									<form:option value="">Select Languages</form:option>
									<form:options items="${langList}" />
								</form:select>
								<form:errors path="languagesKnown" cssClass="error" />
							</div>
						</div>
					</div>

					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Preferred Industry</label>
								<form:select type="text1" class="form-control"
									path="preferredIndustry" id="companyTypeInput">
									<form:option value="">Select Preferred Industry</form:option>
									<form:options items="${companyTypeList}" />
								</form:select>
								<form:errors path="preferredIndustry" cssClass="error" />
							</div>
							<div class="col-xs-6">
								<label>Preferred Location</label>
								<form:select type="text1" class="form-control"
									path="preferredLocation" id="preferredLocationInput"
									multiple="true">

									<form:option value="">Select Preferred Location</form:option>
									<form:options items="${cityList}" />

								</form:select>
								<form:errors path="preferredLocation" cssClass="error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
								<label>Resume(Optional)</label> <input type="file"
									name="resumeFile" path="uploadResume"
									onchange="fileChecks(this)" id="uploadResumeInput" size="50"
									placeholder="RESUME" />
							</div>
							<div class="col-xs-6">

								<label>Photo(Optional)</label> <input type="file"
									name="profileImages" path="profileImage" id="PhotoInput"
									placeholder="Photo" onchange="fileCheck(this)" />
							</div>
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" id="btnSubmit" value="Submit"
							class="btn btn-t-primary btn-theme" /><a
							href="jobseeker_profile_view.html"> <input type="button"
							value="Cancel" class="btn btn-t-primary btn-theme" /></a>
					</div>
				</form:form>
				<!-- end form post a job -->
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>

</body>
</html>