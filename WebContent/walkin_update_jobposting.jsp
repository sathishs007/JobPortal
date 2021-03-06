<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

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
<title>MyjobKart</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">

</head>
<body>

	<script>
		$(document)
				.ready(
						function() {
							$('#btnSubmit')
									.click(
											function(e) {
												var isValid = true;

												
												var nicInstance1 = nicEditors
												.findEditor('jobDescriptionInput');
										var address1 = nicInstance1
												.getContent();
										if (address1.length < 5) {
											isValid = false;
											$("#errorAddress1")
													.html(
															"Please Enter The Job Description Field");
										} else {
											$("#errorAddress1").hide();
										}
										
												var nicInstance2 = nicEditors
												.findEditor('keyWordsInput');
										var address2 = nicInstance2
												.getContent();
										if (address2.length < 5) {
											isValid = false;
											$("#errorAddress2")
													.html(
															"Please Enter The KeySkills Field");
										} else {
											$("#errorAddress2").hide();
										} 
										
										var nicInstance3 = nicEditors
										.findEditor('companyProfileInput');
								var address3 = nicInstance3
										.getContent();
								if (address3.length < 5) {
									isValid = false;
									$("#errorAddress3")
											.html(
													"Please Enter The Address Field");
								} else {
									$("#errorAddress3").hide();
								} 


												$('input[type="text"]')
														.each(
																function() {
																	if ($
																			.trim($(
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
																	if ($
																			.trim($(
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

												$('textarea[type="text"]')
														.each(
																function() {
																	if ($
																			.trim($(
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
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				alert("Only '.jpeg','.jpg', '.png',  '.bmp' formats are allowed.");
				location.reload();
			}

		}
	</script>

	<script type="text/javascript">
		function fileChecks(obj) {
			var fileExtension = [ 'doc', 'pdf', ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1)
				alert("Only '.Word','PDF' formats are allowed.");

		}
	</script>








	<script>
		$(function() {

			$("#startDateInput").datepicker({
				numberOfMonths : 1,
				minDate : 0,
				onSelect : function(selected) {
					var dt = new Date(selected);
					dt.setDate(dt.getDate());
					$("#endDateInput").datepicker("option", "minDate", dt);

				}
			});
			$("#endDateInput").datepicker({
				numberOfMonths : 1,
				minDate : 0,
				onSelect : function(selected) {
					var dt = new Date(selected);
					dt.setDate(dt.getDate());
					$("#startDateInput").datepicker("option", "maxDate", dt);

				}
			});

		});
	</script>



	<script type="text/javascript">
		function limitText(limitField, limitCount, limitNum) {
			if (limitField.value.length > limitNum) {
				limitField.value = limitField.value.substring(0, limitNum);
			} else {
				limitCount.value = limitNum - limitField.value.length;
			}
		}

		function limitText1(limitField, limitCount, limitNum) {
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


				<h3 class="text-center no-margin titleunderline">Walkin Job Post</h3>
				<hr />
				<div class="bg-color1 block-section line-bottom">

					<!-- form post a job -->
					<form:form id="myForm" method="post" class="login-form clearfix"
						commandName="walkinupdate" modelAttribute="walkinupdate">
						<c:if test="${not empty message}">
							<div class="error">${message}</div>
						</c:if>
						<div class="warning">
							<c:if test="${not empty Infomessage}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_info">
									<h1>Info</h1>
									<c:out value="${Infomessage}"></c:out>
								</div>
							</c:if>
						</div>


						<div class="form-group">
							<div class="row clearfix">

								<div class="col-xs-6">
									<label>Job Title</label> <font style="color: red;">* </font>
									<form:input type="text" class="form-control" path="jobTitle"
										id="jobTitleInput" placeholder="Job Title" maxlength="100" />
									<form:errors path="jobTitle" cssClass="error" />
								</div>

								<div class="col-xs-6">
									<label>No Of Vacancies</label> <font style="color: red;">*</font>
									<form:input type="text" class="form-control" path="noVacancies"
										id="vacanciesInput" placeholder="No Of Vacancies"
										maxlength="4" />
									<form:errors path="noVacancies" cssClass="error" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<label>Job Description</label> <font style="color: red;">*
							</font>
							<form:textarea type="text" class="form-control" rows="10"
								maxlength="50000" path="jobDescription" id="jobDescriptionInput"
								onKeyUp="limitText(this.form.jobDescription,this.form.countdown,50000);"
								onKeyDown="limitText(this.form.jobDescription,this.form.countdown,50000);"
								placeholder="Job Description" />

							<form:errors path="jobDescription" cssClass="error" />
							<div id="errorAddress1" style="color: red;"></div>
						</div>

						<div class="form-group">
							<label>Key Skills</label> <font style="color: red;">* </font>
							<form:textarea type="text" class="form-control" path="keywords"
								rows="5" id="keyWordsInput" placeholder="Key Skills"
								onKeyDown="limitText1(this.form.keywords,this.form.countdown1,20000);"
								onKeyUp="limitText1(this.form.keywords,this.form.countdown1,20000);" />
							<form:errors path="keywords" cssClass="error" />
							<div id="errorAddress2" style="color: red;"></div>

						</div>

						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-6">
									<label>Start Date</label> <font style="color: red;">* </font>
									<form:input type="text" class="form-control" path="startDate"
										id="startDateInput" placeholder="Start Date"
										data-date-format="yyyy/mm/dd" />
									<div class="color-white-mute">
										<small>mm/dd/yyy</small>
									</div>
									<form:errors path="startDate" cssClass="error" />
								</div>
								<div class="col-xs-6">

									<label>End Date</label> <font style="color: red;">* </font>

									<form:input type="text" class="form-control" path="endDate"
										id="endDateInput" placeholder="End Date"
										data-date-format="yyyy/mm/dd" />
									<div class="color-white-mute">
										<small>mm/dd/yyy</small>
									</div>
									<form:errors path="endDate" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-4">
									<label> Minimum Experience</label><font style="color: red;">*
									</font>
									<form:select type="text" id="minumInput" name="minum"
										path="minExp" class="form-control">
										<form:option value="">Select Minimum Experience</form:option>
										<form:options items="${minExperiences}" />
									</form:select>
									<form:errors path="minExp" cssClass="error" />
								</div>
								<div class="col-xs-4">
									<label>Maximum Experience</label><font style="color: red;">*
									</font>
									<form:select type="text" id="maxiumInput"
										name="working exp maximum" path="maxExp" class="form-control">
										<form:option value="">Select Maximum Experience</form:option>
										<form:options items="${maxExperiences}" />
									</form:select>
									<form:errors path="maxExp" cssClass="error" />
								</div>

								<div class="col-xs-4">
									<label>Job Type</label> <font style="color: red;">* </font>
									<form:select type="text" id="jobTypeInput" name="jobType"
										path="jobType" class="form-control">
										<form:option value="">Select Job Type </form:option>
										<form:options items="${jobTypeList}" />
									</form:select>
									<form:errors path="jobType" cssClass="error" />
								</div>
							</div>
						</div>


						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-4">
									<label>Currency Type</label> <font style="color: red;">*
									</font>
									<form:select type="text" id="currencyInput" name="currency"
										path="currencyType" class="form-control">
										<form:option value="">Select Currency Type </form:option>
										<form:options items="${currencyTypeList}" />

									</form:select>
									<form:errors path="currencyType" cssClass="error" />
								</div>


								<div class="col-xs-4">
									<label>Minimum Salary</label> <font style="color: red;">*
									</font>
									<form:select type="text" id="minSalaryInput" name="minSalary"
										path="minSalary" class="form-control">
										<form:option value=" ">Select Minimum Salary </form:option>
										<form:options items="${minimumSalary}" />
									</form:select>
									<form:errors path="minSalary" cssClass="error" />
								</div>

								<div class="col-xs-4">
									<label>Maximum Salary</label> <font style="color: red;">*
									</font>
									<form:select type="text" id="maxSalaryInput" name="maxSalary"
										path="maxSalary" class="form-control">
										<form:option value="">Select Maximum salary </form:option>
										<form:options items="${maximumSalary}" />
									</form:select>
									<form:errors path="maxSalary" cssClass="error" />
								</div>
							</div>
						</div>


						<div class="form-group">
							<div class="row clearfix">
							
								<div class="col-xs-4">
									<label>Job Location </label> <font style="color: red;">*
									</font>
									<form:select type="text" id="jobLocationInput"
										name="jobLocation" path="jobLocation" class="form-control">
										<form:option value="">Select Job Location</form:option>
										<form:options items="${locationList}" />
									</form:select>
									<form:errors path="jobLocation" cssClass="error" />
								</div>

							<div class="col-xs-4">
									<label>Industry Type</label> <font style="color: red;">*</font>
									<form:select type="text" id="industryTypeInput"
										name="industryType" path="industryType" class="form-control">
										<form:option value="">Select Industry Type</form:option>
										<form:options items="${IndustryList}" />
									</form:select>
									<form:errors path="industryType" cssClass="error" />
								</div>

								<div class="col-xs-4">
									<label>Function Area</label> <font style="color: red;">*
									</font>
									<form:select type="text" id="functionareaInput"
										name="functionArea" path="functionArea" class="form-control">
										<form:option value="">Select Function Area</form:option>
										<form:options items="${functionAreaList}" />
									</form:select>
									<form:errors path="functionArea" cssClass="error" />
								</div>

							</div>
						</div>



						<div class="form-group">
							<h4 class=" no-margin with-ic">Desired Candidate Profile</h4>
						</div>

						<div class="form-group">
							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-4">
										<label>UG Qualification</label> <font style="color: red;">*
										</font>
										<form:select type="text" id="ugQualificationInput"
											name="ugQualification" path="ugQualification"
											class="form-control">
											<form:option value="">Select UG Qualification</form:option>
											<form:options items="${UgqualificationsList}" />
										</form:select>
										<form:errors path="ugQualification" cssClass="error" />
									</div>
									<div class="col-xs-4">
										<label>PG Qualification</label>
										<form:select id="pgQualificationInput" name="pgQualification"
											path="pgQualification" class="form-control">
											<form:option value="">Select PG Qualification</form:option>
											<form:options items="${pgqualificationsList}" />
										</form:select>
										<form:errors path="pgQualification" cssClass="error" />
									</div>
									<div class="col-xs-4">
										<label>Doctorate</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="doctorate"
											placeholder="Doctorate" maxlength="100" />
										<form:errors path="doctorate" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="form-group">
								<h4 class=" no-margin with-ic">Employer and Recruiter</h4>
							</div>

							<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-6" style="display: block" id="dropCompany">
									<label>Company Name</label> <font style="color: red;">*
									</font>
									<form:select type="text" path="companyName"
										class="form-control" id="companyNameInput"
										placeholder="Company Name" disable="true">
										<form:option value="">Select Company Name</form:option>
										<form:options items="${CompanyList}" />
									</form:select>
								</div>

								<div class="col-xs-6" style="display: none" id="textCompany">
									<label>Company Name</label> <font style="color: red;">*
									</font>
									<form:input type="texty" path="otherCompany"
										id="otherCompanyInput" class="form-control" />
									<form:errors path="otherCompany" cssClass="error" />
								</div>

								<div class="col-xs-6">

									<label>Contact Person</label> <font style="color: red;">*
									</font>
									<form:input type="text" class="form-control"
										path="contactPerson" id="contactPersonInput"
										placeholder="Contact Person" maxlength="75" />
									<form:errors path="contactPerson" cssClass="error" />
								</div>


							</div>
						</div>
							<!-- <div class="form-group">
						<div class="row clearfix">
						<div class="col-xs-3" id="newCompany" style="display: block">
				          <input type="button" id="otherCompany" value="Other Names" 
										class="btn btn-theme  btn-block" onclick="newCompany()" />
						</div>
						</div>
						</div>				 -->
							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>Roles</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="role"
											placeholder="Role" maxlength="100" />
										<form:errors path="role" cssClass="error" />
									</div>

									<div class="col-xs-6">

										<label>Role Category</label> <font style="color: red;">*
										</font>
										<form:input type="text" class="form-control"
											path="roleCategory" id="roleCategoryInput"
											placeholder="Role Category" maxlength="100" />
										<form:errors path="roleCategory" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Company Profile</label> <font style="color: red;">*
								</font>
								<form:textarea class="form-control" path="companyProfile"
									id="companyProfileInput" placeholder="Company Profile" />
								<form:errors path="companyProfile" cssClass="error" />
								<div id="errorAddress3" style="color: red;"></div>
							</div>

							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-4">
										<label>Contact Number</label> <font style="color: red;">*</font>
										<form:input type="text" class="form-control" path="contactNo"
											id="contactNoInput" placeholder="Contact Number"
											maxlength="10" />
										<form:errors path="contactNo" cssClass="error" />
									</div>
									<div class="col-xs-4">

										<label>Posted By</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="postedBy"
											id="postedByInput" placeholder="Posted By" maxlength="75" />
										<form:errors path="postedBy" cssClass="error" />
									</div>
									<div class="col-xs-4">

										<label>Company Address</label> <font style="color: red;">*
										</font>
										<form:input type="text" class="form-control" path="address"
											id="addresseInput" placeholder="Company Address"
											maxlength="75" />
										<form:errors path="address" cssClass="error" />
										<div id="errorAddress" style="color: red;"></div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<h4 class=" no-margin with-ic">Manage Response</h4>
							</div>

							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-4">
										<form:radiobutton path="jobResponse" value="Email" />
										<label>Email</label>
									</div>
									<div class="col-xs-4">
										<form:radiobutton path="jobResponse" value="skype" />
										<label>Skype</label>
									</div>



									<div class="col-xs-4">
										<form:radiobutton path="jobResponse" value="offline" />
										<label>Offline</label>
									</div>
								</div>
							</div>


							<div class="form-group ">
								<input type="submit" value="Walkin Update" id="btnSubmit"
									class="btn btn-t-primary btn-theme" /> <a
									href="walkin_job_view..html"> <input type="button"
									value="Cancel" class="btn btn-t-primary btn-theme" /></a>
							</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>