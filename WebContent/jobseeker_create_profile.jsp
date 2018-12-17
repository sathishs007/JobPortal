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
	var listSizes = 0;
	$(document)
			.ready(
					function() {
						$('#eductionShow')
								.click(
										function(e) {
											var isValid = true;
											
											
											var characters = new RegExp(
													"^[a-zA-Z\\s]+$");
											var charNumber = new RegExp(
													"^[a-zA-Z0-9\\s]+$");
											var nicInstance = nicEditors
													.findEditor('keySkillsInput');
											var keyskill = nicInstance
													.getContent();
											var keyskillsValue=keyskill.replace('<br>',' ');
											/* alert("The Skilss values     "+keyskillsValue);
											alert("The Skilss values  Length   "+keyskill); */
											if (keyskill.length <= 4) {
												isValid = false;
												$("#errorSkill")
														.html("Please enter the KeySkill field");
											} else {
												$("#errorSkill").hide();
											}
											$('input[type="text"]')
													.each(
															function() {
																if ($.trim($(
																		this)
																		.val()) == '') {
																	isValid = false;
																	$(this)
																			.css(
																					{
																						"border" : "1px solid red",
																					});
																} else {
																	$(this)
																			.css(
																					{
																						"border" : "",
																						"background" : ""
																					});

																}
															});

											$('select[type="text"]')
													.each(
															function() {
																if ($.trim($(
																		this)
																		.val()) == '') {
																	isValid = false;
																	$(this)
																			.css(
																					{
																						"border" : "1px solid red",
																					});
																} else {
																	$(this)
																			.css(
																					{
																						"border" : "",
																						"background" : ""
																					});
																}
															});
											$('input[type="email"]')
													.each(
															function() {
																if ($.trim($(
																		this)
																		.val()) == '') {
																	isValid = false;
																	$(this)
																			.css(
																					{
																						"border" : "1px solid red",
																					});
																} else {
																	$(this)
																			.css(
																					{
																						"border" : "",
																						"background" : ""
																					});
																}
															});

											if (isValid == true) {
												
												 if(listSizes>0){
												
										    		$("#eduNext").show();	
												}else{
													$("#eduNext").hide();
												} 
												document
														.getElementById("educationDetail").style.display = 'block';
												document
														.getElementById("personalDetail").style.display = 'none';
											}

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


<script>
	$(function() {

		/* $("#datepicker").datepicker({
			minDate : 0
		}); */
		
		$("#expStartDateInput").datepicker({
			numberOfMonths : 1,
			/* minDate : 0, */
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate());
				$("#expEndDateInput").datepicker("option", "minDate", dt);

			}
		});
		$("#expEndDateInput").datepicker({
			numberOfMonths : 1,
			onSelect : function(selected) {
				var dt = new Date(selected);
				var dt2 = new Date(selected);
				dt.setDate(dt.getDate() - 1);
				dt2.setDate(dt.getDate() + 1);
				/* $("#expStartDateInput").datepicker("option", "maxDate", dt);
				$("#datepicker").datepicker("option", "minDate", dt2); */
			}
		});
		
		
	});
</script>



</head>
<body>
	<!-- <script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

	<script>
	
	var len =0;
		$(document)
				.ready(
						function() {
							$('#aa1')
									.click(

											function() {
												var isValid = true;
												var companyName = null;

												if ($("#companyNameInput")
														.val() == '') {
													var companyNameIn = $(
															"#companyNameIn")
															.val();
													companyName = companyNameIn;
												} else {
													companyName = $(
															"#companyNameInput")
															.val();
												}

												var companyType = $(
														"#companyTypeId").val();
												var designation = $(
														"#designationInput")
														.val();
												var salary = $("#lastSalaryId")
														.val();
												var startDate = $(
														"#expStartDateInput")
														.val();
												
												var endDate = $(
														"#expEndDateInput")
														.val();
												
												var exprience = $(
														"input[name='getStatus']:checked")
														.val();
														
												
												/* input[name='getStatus']:checked */
												var html = "";
												var date_regex = /^\d{2}\/\d{2}\/\d{4}$/;
												var percentage = new RegExp(
														"^[0-9\\s]+$");
												if (companyName == null) {
													$('#selectName')
															.html(
																	"select company name");
													isValid = false;
												} else {
													$('#selectName').html("");

												}
												if (salary != '') {
													if (!percentage
															.test(salary)) {
														$('#errorSalary')
																.html(
																		"invalid format");
														isValid = false;
													} else {
														$('#errorSalary').html(
																"");
													}
												} else {
													$("#lastSalaryId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												}
												
												if (companyType == '') {
													
													$("#companyTypeId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												}else{

													$("#companyTypeId")
															.css("");
													isValid = true;
												}

												if (exprience == null) {

													$("#expstatus")
															.html(
																	"Choose Experience");
													isValid = false;

												} else {
													$("#expstatus").html("");

												}

												if (startDate != '') {
													if (!date_regex
															.test(startDate)) {
														$('#errorSTDate')
																.html(
																		"invalid format");
														isValid = false;
													} else {
														$('#errorSTDate').html(
																"");
													}
												} else {
													$("#expStartDateInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												}

												if (endDate != '') {
													if (!date_regex
															.test(endDate)) {
														$('#errorEDDate')
																.html(
																		"invalid format");
														isValid = false;
													} else {
														$('#errorEDDate').html(
																"");
													}
												} else {
													$("#expEndDateInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												}

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
																		+ endDate
																		+ '&exprience='
																		+ exprience,

																success : function(
																		jp_professoinal_list) {
																	$(
																			'#modelPersonalizeId')
																			.hide();
																	len = jp_professoinal_list.length;
																	
																	if(len>0){
															    		$("#btnSubmit").show();	
																	}else{
																		$("#btnSubmit").hide();
																	}
																
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
																		/* 	+ '<th style="text-align: center; color: #fff;">Experience</th>' */
																			+ '<th style="text-align: center; color: #fff;"></th>'
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
																				+ '</tr>'
																				+ jp_professoinal_list[i].exprience
																				+ '</td>'
																				+ '</tr>';
																	}

																	html = html
																			+ '</tbody></table></div>';

																	$(
																			'#professionalTable')
																			.html(
																					html);
																	$(
																			"#companyNameInput")
																			.val(
																					'');
																	$(
																			"#companyTypeId")
																			.val(
																					'');
																	$(
																			"#designationInput")
																			.val(
																					'');
																	$(
																			"#lastSalaryId")
																			.val(
																					'');

																	$(
																			"#expStartDateInput")
																			.val(
																					'');
																	$(
																			"#expEndDateInput")
																			.val(
																					'');
																	$(
																	"#getStatus")
																	.val(
																			'');
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
												var dateFilter = new RegExp(
														"(^[1-2][0-9][0-9][0-9]+$)");
												var regex = new RegExp(
														"^[a-zA-Z\\s]+$");
												var percentage = new RegExp(
														"^[0-9.\\s]+$");

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

												if (qualifications == '') {

													$("#qualificationId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													var isValid = false;
												} else {
													$("#qualificationId").css({
														"border" : "",
														"background" : ""
													});
												}

												if (passYear == '') {
													var isValid = false;

													$("#passyearId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
												} else {
													$("#passyearId").css({
														"border" : "",
														"background" : ""
													});
												}
												if (isValid == true) {
													if (dateFilter
															.test(passYear) ) {
														$('#yearofPassingError')
																.hide();
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
																		$(
																				'#modelEduId')
																				.hide();
																		
																	
																		var len = profileList_global.length;
																		listSizes = profileList_global.length;
																		
																		
																
																		$("#eduNext").show();	
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
																		$(
																				'#collegeId')
																				.val(
																						'');
																		$(
																				'#passyearId')
																				.val(
																						'');
																		$(
																				'#percentId')
																				.val(
																						'');
																		$(
																				'#qualificationId')
																				.val(
																						'');
																		$(
																				'#departmentId')
																				.val(
																						'');
																		$(
																				'#gradeId')
																				.val(
																						'');
																	},

																});
													} else {

														$('#yearofPassingError')
																.show();
														$('#yearofPassingError')
																.html(
																		"Invalid Format");

													}

												}
												if (isValid == false)
													e.preventDefault();
											});
						});
	</script>
	<script>
		/*  function educationShow() {
			document.getElementById("educationDetail").style.display = 'block';
			document.getElementById("personalDetail").style.display = 'none';

		} */
		function educationNextBt(status) {
			var isvalue = false;
			if (listSizes == 0 && status == 'notExist') {
				$('#eduListEmptyDiv').show();
				$('#eduListEmptyInfo').html("Education list may not be empty.");
			} else {
				if($("#noOfExperienceInput").val()>0){
					isvalue = true;
				}else{
					isvalue = false;
				}				
				if (isvalue) {
				$('#eduListEmptyDiv').hide();
				document.getElementById("professionalDetail").style.display = 'block';
				document.getElementById("educationDetail").style.display = 'none';
				document.getElementById("personalDetail").style.display = 'none';
				}else{
					$('#eduListEmptyDiv').hide();
					document.getElementById("fresherDetail").style.display = 'block';
					document.getElementById("professionalDetail").style.display = 'none';
					document.getElementById("educationDetail").style.display = 'none';
					document.getElementById("personalDetail").style.display = 'none';
					
				}
			}
		}

		/* function professinoal(){
			
			if(listSizes>0){
				document.getElementById("eductionShow").style.display = 'none';
			}else{
				document.getElementById("eductionShow").style.display = 'block';
			}
			
			
		} */
		
		
		function personalDetailShowPrev() {
			document.getElementById("professionalDetail").style.display = 'none';
			document.getElementById("educationDetail").style.display = 'none';
			document.getElementById("personalDetail").style.display = 'block';
			document.getElementById("fresherDetail").style.display = 'none';

		}
		function educationDetailShowPrev() {
			document.getElementById("professionalDetail").style.display = 'none';
			document.getElementById("fresherDetail").style.display = 'none';
			document.getElementById("educationDetail").style.display = 'block';
			document.getElementById("personalDetail").style.display = 'none';

		}
	</script>

	<script type="text/javascript">
		function companyNameShowOther() {
			document.getElementById("companyName").style.display = 'none';
			document.getElementById("other").style.display = 'none';
			document.getElementById("addCompany").style.display = 'block';
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
			var fileExtension = [ 'doc', 'pdf', 'docx' ];

			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {

				document.getElementById("uploadResumeInput").value = '';
				alert("Only '.Word','PDF' formats are allowed.");
			}
		}
	</script>

   <script type="text/javascript">
$(document).ready(function(){
    $("#eduNext").click(function(){
    	/* $("#btnSubmit").hide(); */
    	 if(len>0){
    		$("#btnSubmit").show();	
		}else{
			$("#btnSubmit").hide();
		}
		     
    });
   
});



</script>   
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Create Your
					Profile</h3>
				<hr />

				<form:form id="myForm" method="post" class="login-form clearfix" onsubmit="get_action(this);"
					commandName="profile" enctype="multipart/form-data"
					modelAttribute="profile">
					<!--personal detail  -->
					<div class="row" style="padding: 20px;" id="personalDetail">
						<div class="warning" style="width: 100%; text-align: left;">

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

						<h3>Personal details</h3>



						<div class="form-group">
							<label>First Name</label> <font style="color: red;">*</font>
							<div class="row clearfix">
								<div class="col-xs-6">

									<form:input type="text" class="form-control" path="firstName"
										id="firstNameInput" placeholder="First Name" maxlength="30" />
									<form:errors path="firstName" cssClass="error" />
								</div>

								<div class="col-xs-6">

									<form:input type="text" class="form-control" path="lastName"
										id="lastNameInput" placeholder="Last Name" maxlength="30" />
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
										<form:option value="FeMale">Female</form:option>
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
									<form:input type="text" path="nationality"
										id="nationalityInput" placeholder="Nationality" maxlength="20"
										readonly="true" class="form-control" />
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
										<form:option value="">Select Company Type</form:option>
										<form:options items="${IndustryList}" />
									</form:select>
									<form:errors path="currentIndustry" cssClass="error" />
								</div>
								<div class="col-xs-4">
									<label>No Of Experience</label> <font style="color: red;">*</font>
									<form:input type="text" path="noOfExperience"
										class="form-control" id="noOfExperienceInput"
										placeholder="No Of Experience" maxlength="2"/>
									<form:errors path="noOfExperience" cssClass="error" />
								</div>

								<%-- <form:input type="text1" class="form-control"
										path="currentIndustry" id="currentIndustryInput"
										placeholder="Current Industry" maxlength="20"/>
									<form:errors path="currentIndustry" cssClass="error" /> --%>
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
									<label>Expected Salary</label>
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
										placeholder="Job Title" maxlength="300" />
									<form:errors path="profiledescription" cssClass="error" />
								</div>
								<div class="col-xs-6">

									<label>Languages Known</label> <font style="color: red;">*
									</font>
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
										<form:options items="${IndustryList}" />
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

						<input type="button" value="Next"
							class="btn btn-t-primary btn-theme" id="eductionShow" />
					</div>

					<div class="row" style="padding: 20px; display: none"
						id="educationDetail">
						<div class="alert alert-success" role="alert" id="eduListEmptyDiv"
							style="display: none;">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>INFO</strong> <span id="eduListEmptyInfo"></span>
						</div>
						<h3>Education Profile</h3>
						<ul id="eduList">
							<li>
								<div class="form-group">
									<label>Qualifications</label> <font style="color: red;">*</font>
									<div class="row clearfix">
										<div class="col-xs-4">
											<select type="textt" id="qualificationId" name="degree"
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
											<input type="text1" class="form-control" name="department"
												id="departmentId" placeholder="Department" maxlength="50" />
										</div>
										<div class="col-xs-4">
											<input type="text1" class="form-control" name="college"
												id="collegeId" placeholder="College" maxlength="50" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>Year Of Passing</label> <font style="color: red;">*</font>

									<div class="row clearfix">
										<div class="col-xs-4">
											<input type="textt" class="form-control" id="passyearId"
												name="yearOfPassing" maxlength="4"
												placeholder="Year Of Passing" />
											<div class="color-black-mute">
												<small>Format: yyyy</small>
											</div>
											<div id="yearofPassingError" style="color: red;"></div>
										</div>
										<div class="col-xs-4">
											<input type="text1" class="form-control" name="percentage"
												maxlength="4" id="percentId" placeholder="Percentage/Grade" />

											<div id="percentageError" style="color:red;" ></div> 
										</div>
										<div class="col-xs-4">
											<select id="gradeId" name="grade" name="grade"
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

						<input type="button" id="addEducation" value="Add Educations"
							style="" class="btn btn-t-primary btn-theme" />
						<input type="button" value="Prev" class="btn btn-t-primary btn-theme"
							onclick="personalDetailShowPrev()" />
						<%-- <c:if test="${empty education_details}">
							<input type="button" value="Next" id="eduNext"
								class="btn btn-t-primary btn-theme"
								onclick="educationNextBt('notExist');" />
						</c:if>
						<c:if test="${!empty education_details}">
							<input type="button" value="Next" id="eduNext"
								class="btn btn-t-primary btn-theme"
								onclick="educationNextBt('exist');" 
								/>
						</c:if> --%>
						<br /> <br />

						<!-- Education List -->

						<c:if test="${!empty education_details}">
							<div id="modelEduId">
								<div class="pi-responsive-table-sm">
									<table style="border: 1px solid; width: 100%;">
										<thead style="background-color: #2a3f54">
											<tr>
												<th style="text-align: center; color: #fff;">S.NO</th>
												<th style="text-align: center; color: #fff;">Qualifications</th>
												<th style="text-align: center; color: #fff;">Department</th>
												<th style="text-align: center; color: #fff;">College</th>
												<th style="text-align: center; color: #fff;">Year of
													Passing</th>
												<th style="text-align: center; color: #fff;">Percentage</th>
												<th style="text-align: center; color: #fff;">Grade</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="numberOfRows" value="0" />
											<c:forEach items="${education_details}" var="searchResult">
												<c:set var="numberOfRows" value="${numberOfRows+1}" />
												<tr>
													<td class="td-border list-capitalize"><c:out
															value="${numberOfRows}" /></td>


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
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</c:if>
						<div id="eduTable"></div>
						<br>
						<c:if test="${empty education_details}">
							<input type="button" value="Next" id="eduNext"
								class="btn btn-t-primary btn-theme" 
								onclick="educationNextBt('notExist');" style="margin-left:95%;" />
						</c:if>
						<c:if test="${!empty education_details}">
							<input type="button" value="Next" id="eduNext"
								class="btn btn-t-primary btn-theme" style="margin-left:95%;"
								onclick="educationNextBt('exist');" />
						</c:if>
						
						
						
					</div>
					<!--professional details  -->
					<div class="row" style="padding: 20px; display: none"
						id="professionalDetail">

						<h4>Professional Profile</h4>
						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-5" style="display: block;" id="companyName">
									<label>Company</label>

									<form:select type="text1" class="form-control"
										path="companyName" id="companyNameInput">
										<form:option value="">Select Company Name</form:option>
										<form:options items="${CompanyList}" />
									</form:select>
									<form:errors path="companyName" cssClass="error" />
									<div id="selectName" style="color: red;"></div>
								</div>

								<div class="col-xs-5" style="display: none;" id="addCompany">
									<label>Company</label>
									<form:input type="text1" id="companyNameIn"
										class="form-control" path="companyName"
										placeholder="Company Name" maxlength="100" />
									<form:errors path="companyName" cssClass="error" />
									<div id="selectName" style="color: red;"></div>
								</div>

								<div class="col-xs-3" class="form-control">
									<label>Experience</label> <font style="color: red;">*</font><br>
									<form:radiobutton path="" id="exprience" name="getStatus"
										value="flase" />
									<label for="expStatus">Previous</label>
									<form:radiobutton path="" id="exprience" name="getStatus"
										value="true" style="margin-left:10px;" />
									<label for="expStatus">Current</label>
									<form:errors path="expStatus" cssClass="error" />
									<div id="expstatus" style="color: red;"></div>
								</div>

								<div class="col-xs-4">
									<label>Industry Type</label>
									<form:select type="text1" class="form-control"
										path="companyType" id="companyTypeId">
										<form:option value="">Select Company Type</form:option>
										<form:options items="${IndustryList}" />
									</form:select>
									<form:errors path="companyType" cssClass="error" />
								</div>

							</div>
						</div>
						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-6" style="display: block;" id="other">
									<input type="button" id="otherCompany" value="Other Names"
										class="btn btn-t-primary btn-theme"
										onclick="companyNameShowOther()" />
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
									<label>Last Drawn Salary </label> <font style="color: red;">*</font>
									<form:input type="text1" class="form-control" path="lastSalary"
										id="lastSalaryId" placeholder="Last Salary Ex: 25000"
										maxlength="100" />
									<form:errors path="lastSalary" cssClass="error" />
									<div id="errorSalary" style="color: red"></div>
								</div>
							</div>
						</div>


						<div class="form-group">
							
							<div class="row clearfix">
								<div class="col-xs-6">
                                    <label>Start Date</label> <font style="color: red;">*</font>
									<form:input type="textx" class="form-control"
										id="expStartDateInput" path="expStartDate"
										placeholder="Exp Start Date" />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
									<form:errors path="expStartDate" cssClass="error" />
									<div id="errorSTDate" style="color: red"></div>
								</div>


								<!-- <label>End Date</label> <font style="color: red;">*</font> -->
								<div class="col-xs-6">
                                    <label>End Date</label> <font style="color: red;">*</font>
									<form:input type="textx" class="form-control"
										id="expEndDateInput" path="expEndDate"
										placeholder="Exp End Date" />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
									<form:errors path="expEndDate" cssClass="error" />
									<div id="errorEDDate" style="color: red"></div>
								</div>
							</div>
						</div>



						<input type="button" id="aa1" value="Add"
							class="btn btn-t-primary btn-theme" /> <input type="button"
							value="Prev" class="btn btn-t-primary btn-theme"
							onclick="educationDetailShowPrev()" /> 
							
							<!-- <input type="submit"
							id="btnSubmit" value="Submit" class="btn btn-t-primary btn-theme" />
 -->


						<c:if test="${!empty professional_details}">
							<br />
							<div id="modelPersonalizeId">
								<div class="pi-responsive-table-sm" style="margin-top: 25px;">
									<table style="border: 1px solid; width: 100%;">
										<thead style="background-color: #2a3f54">
											<tr>
												<th style="text-align: center; color: #fff;">S.NO</th>
												<th style="text-align: center; color: #fff;">Company
													Name</th>
												<th style="text-align: center; color: #fff;">Company
													Type</th>
												<th style="text-align: center; color: #fff;">Designation</th>
												<th style="text-align: center; color: #fff;">Salary</th>
												<th style="text-align: center; color: #fff;">Start Date</th>
												<th style="text-align: center; color: #fff;">End Date</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="numberOfRows" value="0" />
											<c:forEach items="${professional_details}" var="searchResult">
												<c:set var="numberOfRows" value="${numberOfRows+1}" />
												<tr>
													<td class="td-border list-capitalize"><c:out
															value="${numberOfRows}" /></td>


													<td class="td-border list-capitalize"><c:out
															value="${searchResult.companyName }"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.companyType }"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.designation }"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.lastSalary }"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.expStartDate }"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.expEndDate }"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</c:if>

						<div id="professionalTable"></div>
						<br>
						<input type="submit"
							id="btnSubmit" value="Submit" class="btn btn-t-primary btn-theme" style="margin-left: 94%;"/>
						
						
					</div>
					
					
					<div class="row" style="padding: 20px; display: none"
						id="fresherDetail">

						
						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-10" style="display: block;">
								<h4>You Are the fresher Candidate,Please submit your profile</h4>
								</div>
						</div>
						</div>
						<input type="button"
							value="Prev" class="btn btn-t-primary btn-theme"
							onclick="educationDetailShowPrev()" />
					 <input type="submit"
							id="btnSubmit" value="Submit" class="btn btn-t-primary btn-theme" />



						
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>