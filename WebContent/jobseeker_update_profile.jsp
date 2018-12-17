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
		nicEditors.allTextAreas()
	});
	//]]>
</script>



<script>
	$(document).ready(function() {
		$('#eductionShow').click(function(e) {
			var isValid = true;
			var nicInstance = nicEditors.findEditor('keySkillsInput');
			var keyskill = nicInstance.getContent();
			if (keyskill.length < 5) {
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
			

	

if (isValid == true){
	
		document.getElementById("educationDetail").style.display = 'none';
		$('#educationDetail1').show();
		document.getElementById("personalDetail").style.display = 'none';
	

	
}	
		
/*

function professionalShow() {
	alert(isValid);
	document.getElementById("professionalDetail").style.display = 'block';
	document.getElementById("educationDetail").style.display = 'none';
	document.getElementById("personalDetail").style.display = 'none';
}

function personalDetailShowPrev() {
	document.getElementById("professionalDetail").style.display = 'none';
	document.getElementById("educationDetail").style.display = 'none';
	document.getElementById("personalDetail").style.display = 'block';

}
function educationDetailShowPrev() {
	document.getElementById("professionalDetail").style.display = 'none';
	document.getElementById("educationDetail").style.display = 'block';
	document.getElementById("personalDetail").style.display = 'none';

} */



if (isValid == false)
	e.preventDefault();
});
});



	
</script>
<style type="text/css">

/* located in demo.css and creates a little calendar icon
 * instead of a text link for "Choose date"
 */
a.dp-choose-date {
	float: left;
	width: 16px;
	height: 16px;
	padding: 0;
	margin: 5px 3px 0;
	display: block;
	text-indent: -2000px;
	overflow: hidden;
	background: url(calendar.png) no-repeat;
}

a.dp-choose-date.dp-disabled {
	background-position: 0 -20px;
	cursor: default;
}
/* makes the input field shorter once the date picker code
 * has run (to allow space for the calendar icon
 */
input.dp-applied {
	width: 140px;
	float: left;
}
</style>
</head>
<body>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	
	<script>
	$(document)
	.ready(
			function() {
				$('#aa1')
						.click(

								function() {
									var isValid = true;
									var companyName = $("#companyNameInput")
											.val();
									var companyType = $("#companyTypeId")
											.val();
									var designation = $("#designationInput")
											.val();
									var salary = $(
											"#lastSalaryId")
											.val();
									var startDate = $(
											"#expStartDateInput").val();
									var endDate = $("#expEndDateInput").val();
									var html = "";
									



									
									 if (isValid == true) {
										$
												.ajax({
													type : "GET",
													url : 'add_professional_details',
													data : 'companyName='
															+ companyName
															+ '&companyType='
															+ companyType
															+ '&designation='
															+ designation
															+ '&salary='
															+ salary
															+ '&startDate='
															+ startDate
															+ '&endDate='
															+ endDate,
													success : function(
															jp_professoinal_list) {
														var len = jp_professoinal_list.length;
														html = '<br/><div class="pi-responsive-table-sm">'
																+ '<table style="border: 1px solid; width:100%;" >'
																+ '<thead style="background-color: #2a3f54" >'
																+ '<tr>'
																+ '<th style="text-align: center; color: #fff;">S.NO</th>'
																+ '<th style="text-align: center; color: #fff;">Company Name</th>'
																+ '<th style="text-align: center; color: #fff;">Company Type</th>'
																+ '<th style="text-align: center; color: #fff;">Designation</th>'
																+ '<th style="text-align: center; color: #fff;">Salary</th>'
																+ '<th style="text-align: center; color: #fff;">Start Date</th>'
																+ '<th style="text-align: center; color: #fff;">End Date</th>'
																+ '</tr></thead><tbody>';
														for ( var i = 0; i < len; i++) {
															html = html
																	+ '<tr>'
																	+ '<td class="td-border list-capitalize">'
																	+ (i + 1)
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].companyName
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].companyType
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].designation
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].lastSalary
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].startDate
																	+ '</td>'
																	+ '<td class="td-border list-capitalize">'
																	+ jp_professoinal_list[i].endDate
																	+ '</td>'
																	+ '</tr>';
														}
														
														html = html
																+ '</tbody></table></div>';

														$(
																'#professionalTable')
																.html(
																		html);
														$("#companyNameInput").val('');
												 		$("#companyTypeId").val('');
												 		$("#designationInput").val('');
												 		$("#lastSalaryId").val('');
														$("#expStartDateInput").val('');
														$("#expEndDateInput").val('');
													},

												});
									}
									if (isValid == false)
										e.preventDefault(); 
								});
			});
	</script>
	
	<script>
		$(document)
				.ready(
						function() {
							$('#addEducation')
									.click(

											function() {
												var isValid = true;
												var colName = $("#collegeId")
														.val();
												var passYear = $("#passyearId")
														.val();
												var percent = $("#percentId")
														.val();
												var qualifications = $(
														"#qualificationId")
														.val();
												var department = $(
														"#departmentId").val();
												var grade = $("#gradeId").val();
												var html = "";


											if ( passYear == ''
												|| qualifications ==  ' '
												|| department == ' '
												) {
											
												var isValid = false;
											$('input[type="textt"]').each(function() {
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

											
									$('select[type="textt"]').each(function() {
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
											
										} 
																				
												
										
												if (isValid == true) {
													$
															.ajax({
																type : "GET",
																url : 'update_education',
																data : 'colName='
																		+ colName
																		+ '&passYear='
																		+ passYear
																		+ '&percent='
																		+ percent
																		+ '&qualifications='
																		+ qualifications
																		+ '&department='
																		+ department
																		+ '&idVal='
																		+ idVal
																		+ '&grade='
																		+ grade,
																success : function(
																		profileList_update) {
																	var len = profileList_update.length;
																	html = '<br/><div class="pi-responsive-table-sm">'
																			+ '<table style="border: 1px solid; width:100%;" >'
																			+ '<thead style="background-color: #2a3f54" >'
																			+ '<tr>'
																			+ '<th style="text-align: center; color: #fff;">S.NO</th>'
																			+ '<th style="text-align: center; color: #fff;">Qualifications</th>'
																			+ '<th style="text-align: center; color: #fff;">Department</th>'
																			+ '<th style="text-align: center; color: #fff;">College</th>'
																			+ '<th style="text-align: center; color: #fff;">Year of Passing</th>'
																			+ '<th style="text-align: center; color: #fff;">Percentage</th>'
																			+ '<th style="text-align: center; color: #fff;">Grade</th>'
																			+ '</tr></thead><tbody>';
																	for ( var i = 0; i < len; i++) {
																		html = html
																				+ '<tr>'
																				+ '<td class="td-border list-capitalize">'
																				+ (i + 1)
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].degree
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].department
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].college
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].yearOfPassing
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].percentage
																				+ '</td>'
																				+ '<td class="td-border list-capitalize">'
																				+ profileList_global[i].grade
																				+ '</td>'
																				+ '</tr>';
																	}
																	html = html
																			+ '</tbody></table></div>';

																	$(
																			'#eduTable')
																			.html(
																					html);
																	$('#collegeId').val('');
															 		$('#passyearId').val('');
															 		$('#percentId').val('');
															 		$('#qualificationId').val('');
																	$('#departmentId').val('');
																	$('#gradeId').val('');
																},

															});
												}
												if (isValid == false)
													e.preventDefault();
											});
						});
	</script>
	<script>
		
		function professionalShow() {

			document.getElementById("professionalDetail").style.display = 'block';
			document.getElementById("educationDetail").style.display = 'none';
			document.getElementById("personalDetail").style.display = 'none';
			document.getElementById("educationDetail1").style.display = 'none';
		}

		function personalDetailShowPrev() {
			document.getElementById("professionalDetail").style.display = 'none';
			document.getElementById("educationDetail").style.display = 'none';
			document.getElementById("personalDetail").style.display = 'block';

		}
		function educationDetailShowPrev() {
			document.getElementById("professionalDetail").style.display = 'none';
			document.getElementById("educationDetail").style.display = 'block';
			document.getElementById("personalDetail").style.display = 'none';

		} 
	</script>



	<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'gif', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("PhotoInput").value = '';
				alert("Only '.jpeg','.jpg', '.png', '.gif', '.bmp' formats are allowed.");

			}
		}
	</script>

	<script type="text/javascript">
		function fileChecks(obj) {
			var fileExtension = [ 'doc', 'pdf', ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("uploadResumeInput").value = '';
				alert("Only '.Word','PDF' formats are allowed.");
			}
		}
	</script>


	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin">Update Your Profile</h3>
				<hr />
				
				
					<div class="pi-responsive-table-sm"  id="educationDetail1" style="display: none;">
						<table style="border: 1px solid;" >
							<thead style="background-color: #2a3f54">
								<tr>
									<th style="text-align: center; color: #fff; width: 15%;">Qualifications</th>
									<th style="text-align: center; color: #fff; width: 15%;">Department</th>
									<th style="text-align: center; color: #fff; width: 15%;">College</th>
									<th style="text-align: center; color: #fff; width: 15%;">Year of Passing</th>
									<th style="text-align: center; color: #fff; width: 15%;">Percentage</th>
									<th style="text-align: center; color: #fff; width: 20%;">Grade</th>
									<th style="text-align: center; color: #fff; width: 20%;">Edit</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${eduupdateProfile}" var="searchResult">
									<tr>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.degree }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.department }"></c:out></td>
												<td class="td-border list-capitalize"><c:out
												value="${searchResult.college }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.yearOfPassing }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.percentage }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.grade }"></c:out></td>
															
										<td class="td-border"><span id="editId" onclick="editfun(${searchResult.educationId});"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-pencil"></li></span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br />

				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="updateProfile" enctype="multipart/form-data"
					modelAttribute="updateProfile">
					<!--personal detail  -->
					<div class="row" style="padding: 20px;" id="personalDetail">
						<h3>Personal details</h3>



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
									<label>Email</label><font style="color: red;">*</font>
									<form:input type="email" class="form-control" path="emailId"
										id="lemailIdInput" placeholder="Email Id" disabled="true" />
									<form:errors path="emailId" cssClass="error" />
								</div>
								<div class="col-xs-6">
									<label>Mobile Number</label> <font style="color: red;">*
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
									<label>Marital Status</label> <font style="color: red;">*
									</font>
									<form:select type="text" id="maritalStatusInput"
										name="maritalStatus" path="maritalStatus" class="form-control">
										<form:option value="">Select</form:option>
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
										placeholder="Pincode" maxlength="6" class="form-control" />
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
										<form:option value="">City</form:option>
										<form:options items="${cityList}" />
									</form:select>
									<form:errors path="location" cssClass="error" />
								</div>



								<div class="col-xs-4">
									<label>State</label> <font style="color: red;">*</font>
									<form:select type="text" path="state" id="nationalityInput"
										class="form-control">
										<form:option value="">State</form:option>
										<form:options items="${stateList}" />
									</form:select>
									<form:errors path="state" cssClass="error" />
								</div>


								<div class="col-xs-4">
									<label>Nationality</label> <font style="color: red;">*</font>
									<form:input type="text" path="nationality"
										id="nationalityInput" placeholder="Nationality" maxlength="20"
										readonly="true" class="form-control" />
									<form:errors path="nationality" cssClass="error" />
								</div>


							</div>
						</div>
						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-6">
									<label>Job Type</label>
									<form:select type="text1" id="jobTypeInput" name="jobType"
										path="jobType" class="form-control">
										<form:option value="">Select JobType</form:option>
										<form:options items="${jobType}" />

									</form:select>
									<form:errors path="jobType" cssClass="error" />
								</div>
								<div class="col-xs-6">
									<label>Company Type</label> <font style="color: red;">*</font>
									<form:select type="text" path="currentIndustry"
										class="form-control">
										<form:option value="">Company Type</form:option>
										<form:options items="${companyTypeList}" />

									</form:select>
									<form:errors path="currentIndustry" cssClass="error" />

									<%-- <form:input type="text1" class="form-control"
										path="currentIndustry" id="currentIndustryInput"
										placeholder="Current Industry" maxlength="20"/>
									<form:errors path="currentIndustry" cssClass="error" /> --%>
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
						<%-- <div class="form-group">
							<label>Specialisation</label>
							<form:input type="text1" class="form-control"
								path="specialisation" id="specialisationInput"
								placeholder="Specialisation" maxlength="100"/>
							<form:errors path="specialisation" cssClass="error" />
						</div> --%>

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
									<label>Excepted Salary</label>
									<form:input type="text1" class="form-control"
										path="expectedCtc" id="expectedCtcInput"
										placeholder="Example: 50,000.00" maxlength="15" />
									<form:errors path="expectedCtc" cssClass="error" />
								</div>






							</div>
						</div>





						<div class="form-group">

							<div class="row clearfix">

								<div class="col-xs-6">
									<label>Job Title</label><font style="color: red;">*</font>
									<form:input type="text" class="form-control"
										path="profiledescription" id="profiledescriptionInput"
										placeholder="Job Title" maxlength="3000" />
									<form:errors path="profiledescription" cssClass="error" />
								</div>
								<div class="col-xs-6">

									<label>Languages Known</label> <font style="color: red;">*
									</font>
									<form:select type="text" class="form-control"
										path="languagesKnown" id="languagesKnownInput"
										>
										<form:option value="">Select Languages</form:option>
										<form:options items="${langList}" />
										</form:select>
									<form:errors path="languagesKnown" cssClass="error" />



									<!-- <input type="file" name="resumeFile" path="uploadResume"
										onchange="fileChecks(this)" id="uploadResumeInput" size="50"
										placeholder="RESUME" /> -->
								</div>
							</div>
						</div>



						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-6">
									<label>Preferred Industry</label>
									<form:select type="text1" class="form-control"
										path="preferredIndustry" id="companyTypeInput">
										<form:option value="">Select Company Type</form:option>
										<form:options items="${companyTypeList}" />
									</form:select>
									<form:errors path="preferredIndustry" cssClass="error" />
								</div>
								<div class="col-xs-6">
									<label>Preferred Location</label>
									<form:select type="text1" class="form-control"
										path="preferredLocation" id="preferredLocationInput">

										<form:option value="">Preferred Location</form:option>
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



						<input type="button" value="Next"
							class="btn btn-t-primary btn-theme" id="eductionShow"
							/><!-- onclick="educationShow()"  -->

					</div>

					<!--educational details  -->
					<div class="row" style="padding: 20px; display: none"
						id="educationDetail">

						<h3>Edit Education Profile</h3>

						<ul id="eduList">
							<li>
								<div class="form-group">
									<label>Qualifications</label> <font style="color: red;">*</font>
									<div class="row clearfix">
										<div class="col-xs-4">
											<select  type="textt" id="qualificationId" name="degree"
												class="form-control">
												<option value="">Select Educational Qualification</option>
												<c:forEach items="${qualification}" var="i">
													<option value="${i}">
														<c:out value="${i}"></c:out>
													</option>
												</c:forEach>
											</select>
											<form:errors path="degree" cssClass="error" />
										</div>

										<div class="col-xs-4">

											<input type="textt" class="form-control" name="department"
												id="departmentId" placeholder="Department" maxlength="50" />
										</div>


										<div class="col-xs-4">

											<input  class="form-control" name="college"
												id="collegeId" placeholder="College" maxlength="50" />
											<div id="cityExistId"></div>
										</div>


									</div>
								</div>

								<div class="form-group">
									<label>Year Of Passing</label> <font style="color: red;">*</font>

									<div class="row clearfix">
										<div class="col-xs-4">
											<input type="textt" class="form-control" id="passyearId"
												name="yearOfPassing" maxlength="4"
												placeholder="Year Of Passing" data-date-format="yyyy" />
											<div class="color-black-mute">
												<small>Format: yyyy</small>
											</div>
										</div>
										<div class="col-xs-4">
											<input  class="form-control" name="percentage"
												maxlength="4" id="percentId" placeholder="Percentage/Grade"
												data-date-format="yyyy" />
											<div class="color-black-mute">
												<small>Percentage/Grade</small>
											</div>
										</div>


										<div class="col-xs-4">

											<select  id="gradeId" name="grade" name="grade"
												class="form-control">
												<option value="">Select Grade</option>
												<option value="First Class">First Class</option>
												<option value="Second Class">Second Class</option>

											</select>
										</div>
									</div>
								</div>
							</li>
						</ul>

						<input type="button" id="addEducation" value="Edit Educations"
							style="" class="btn btn-t-primary btn-theme" /> 
							<input
							type="button" value="Prev" class="btn btn-t-primary btn-theme"
							onclick="personalDetailShowPrev()" /> 
							<input type="button" id="eduNext"
							value="Next" class="btn btn-t-primary btn-theme"
							onclick="professionalShow()" /> <br /> <br />
						<div id="eduTable"></div>

					</div>
					<!--professional details  -->
					<div class="row" style="padding: 20px; display: none"
						id="professionalDetail">
						<h4>Professional Profile</h4>
						<div class="form-group">
							<label>Company</label>

							<div class="row clearfix">
								<div class="col-xs-6">
									<form:input type="text1" class="form-control"
										path="companyName" id="companyNameInput"
										placeholder="Company Name" maxlength="100" />
									<form:errors path="companyName" cssClass="error" />
								</div>
								<div class="col-xs-6">
									<form:select type="text1" class="form-control"
										path="companyType" id="companyTypeId">
										<form:option value="">Company Type</form:option>
										<form:options items="${companyTypeList}" />
									</form:select>
									<form:errors path="companyType" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-6">
									<label>Designation</label>
									<form:input type="text1" class="form-control"
										path="designation" id="designationInput"
										placeholder="Designation" maxlength="100" />
									<form:errors path="designation" cssClass="error" />
								</div>

								<div class="col-xs-6">
									<label>Last Drawn Salary </label>
									<form:input type="text1" class="form-control" path="lastSalary"
										id="lastSalaryId" placeholder="Last Salary Ex: 25000"
										maxlength="100" />
									<form:errors path="lastSalary" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label>Start Date</label> <font style="color: red;"></font>
							<div class="row clearfix">
								<div class="col-xs-6">

									<form:input type="textx" class="form-control"
										id="expStartDateInput" path="expStartDate"
										placeholder="Exp Start Date" data-date-format="yyyy/mm/dd" />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
									<form:errors path="expStartDate" cssClass="error" />
								</div>
								<div class="col-xs-6">
									<form:input type="textx" class="form-control"
										id="expEndDateInput" path="expEndDate"
										placeholder="Exp End Date" data-date-format="yyyy/mm/dd" />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
									<form:errors path="expEndDate" cssClass="error" />
								</div>
							</div>
						</div>
						<input type="button" id="aa1" value="Add"
							class="btn btn-t-primary btn-theme" /> <input type="button"
							value="Prev" class="btn btn-t-primary btn-theme"
							onclick="educationDetailShowPrev()" /> <input type="submit"
							id="btnSubmit" value="Submit" class="btn btn-t-primary btn-theme" />

						<div id="professionalTable"></div>
					</div>
				</form:form>
			</div>

		</div>
	</div>
	<script>
	var idVal = 0;
	function editfun(id){
		idVal = id;
		alert(id);
		if(idVal > 0){
			document.getElementById("educationDetail").style.display = 'block';
		}
	}
	
	$(document)
			.ready(
					function() {
						$('#addEducation')
								.click(

										function() {
											var isValid = true;
											var colName = $("#collegeId")
													.val();
											var passYear = $("#passyearId")
													.val();
											var percent = $("#percentId")
													.val();
											var qualifications = $(
													"#qualificationId")
													.val();
											var department = $(
													"#departmentId").val();
											var grade = $("#gradeId").val();
											var html = "";


										if ( passYear == ''
											|| qualifications ==  ' '
											|| department == ' '
											) {
										
											var isValid = false;
										$('input[type="textt"]').each(function() {
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

										
								$('select[type="textt"]').each(function() {
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
										
									} 
																			
											
									
											if (isValid == true) {
												$
														.ajax({
															type : "GET",
															url : 'add_education',
															data : 'colName='
																	+ colName
																	+ '&passYear='
																	+ passYear
																	+ '&percent='
																	+ percent
																	+ '&qualifications='
																	+ qualifications
																	+ '&department='
																	+ department
																	+ '&grade='
																	+ grade,
															success : function(
																	profileList_global) {
																var len = profileList_global.length;
																html = '<br/><div class="pi-responsive-table-sm">'
																		+ '<table style="border: 1px solid; width:100%;" >'
																		+ '<thead style="background-color: #2a3f54" >'
																		+ '<tr>'
																		+ '<th style="text-align: center; color: #fff;">S.NO</th>'
																		+ '<th style="text-align: center; color: #fff;">Qualifications</th>'
																		+ '<th style="text-align: center; color: #fff;">Department</th>'
																		+ '<th style="text-align: center; color: #fff;">College</th>'
																		+ '<th style="text-align: center; color: #fff;">Year of Passing</th>'
																		+ '<th style="text-align: center; color: #fff;">Percentage</th>'
																		+ '<th style="text-align: center; color: #fff;">Grade</th>'
																		+ '</tr></thead><tbody>';
																for ( var i = 0; i < len; i++) {
																	html = html
																			+ '<tr>'
																			+ '<td class="td-border list-capitalize">'
																			+ (i + 1)
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].degree
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].department
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].college
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].yearOfPassing
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].percentage
																			+ '</td>'
																			+ '<td class="td-border list-capitalize">'
																			+ profileList_global[i].grade
																			+ '</td>'
																			+ '</tr>';
																}
																html = html
																		+ '</tbody></table></div>';

																$(
																		'#eduTable')
																		.html(
																				html);
																$('#collegeId').val('');
														 		$('#passyearId').val('');
														 		$('#percentId').val('');
														 		$('#qualificationId').val('');
																$('#departmentId').val('');
																$('#gradeId').val('');
															},

														});
											}
											if (isValid == false)
												e.preventDefault();
										});
					});

	
	</script>
	
	<%-- <c:if test="${!empty eduupdateProfile}">
					<div class="pi-responsive-table-sm">
						<table style="border: 1px solid;">
							<thead style="background-color: #2a3f54">
								<tr>
									<th style="text-align: center; color: #fff; width: 25%;">Qualifications</th>
									<th style="text-align: center; color: #fff; width: 25%;">Department</th>
									<th style="text-align: center; color: #fff; width: 20%;">College</th>
									<th style="text-align: center; color: #fff; width: 15%;">Year of Passing</th>
									<th style="text-align: center; color: #fff; width: 15%;">Percentage</th>
									<th style="text-align: center; color: #fff; width: 20%;">Grade</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${eduupdateProfile.list}" var="searchResult">
									<tr>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.degree }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.department }"></c:out></td>
												<td class="td-border list-capitalize"><c:out
												value="${searchResult.college }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.yearOfPassing }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.percentage }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.grade }"></c:out></td>
															
										<td class="td-border"><a
											href="jobseeker_update_profile.html?id=${searchResult.id}"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-pencil"></li></a></td>
										

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<br />
					<!-- pagination -->
					<nav class="text-center">
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${searchJobseeker.currentPage !=1}">
								<li><a aria-label="Previous"
									href="jobseeker_profile_view.html?page=${searchJobseeker.currentPage - 1}">
										<span aria-hidden="true">&larr;</span>
								</a></li>
							</c:if>

							<li><c:forEach begin="${searchJobseeker.start}"
									end="${searchJobseeker.records}" var="i">
									<c:choose>
										<c:when test="${searchJobseeker.page==i}">
											<a href="jobseeker_profile_view.html?page=${i}"
												style="color: #fff; background-color: #34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="jobseeker_profile_view.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${searchJobseeker.currentPage lt searchJobseeker.totalPages}">
								<li><a
									href="jobseeker_profile_view.html?page=${searchJobseeker.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if> --%>
	
	
	
</body>
</html>