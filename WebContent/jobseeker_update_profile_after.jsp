<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>MyjobKart</title>
</head>
<body>
	<h4 class="modal-title">Update your Profile</h4>
	<div class="block-section box-side-account">
		<div class="box-list"
			style="width: 100%; margin-left: -25px; padding: 20px">
			<div class="item">
				<div class="row">

					<div class="bg-color1 block-section line-bottom">
						<div class="container">
							<div class="row">
								<div class="col-md-9 col-sm-9">
									<form:form id="myForm" method="post"
										class="login-form clearfix" commandName="profile"
										enctype="multipart/form-data" modelAttribute="profile">
										<c:if test="${not empty message}">
											<div class="message red">${message}</div>
										</c:if>


										<div class="form-group">
											<label>Name</label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="firstName" id="firstNameInput"
														placeholder="First Name"  maxlength="20"/>
													<form:errors path="firstName" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="lastName" id="lastNameInput" placeholder="Last Name"  maxlength="20"/>
													<form:errors path="lastName" cssClass="error" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label>Profile Description</label>
											<form:textarea type="text" class="form-control"
												path="resumeTitle" id="resumeTitleInput"
												placeholder="Profile Description" maxlength="30000"/>
											<form:errors path="resumeTitle" cssClass="error" />

										</div>
										<div class="form-group">
											<label>Email</label>
											<form:input type="email" class="form-control" path="emailId"
												id="lemailIdInput" placeholder="Email Id" disabled="true" />
											<form:errors path="emailId" cssClass="error" />
										</div>
										<div class="form-group">
											<label>Photo(Optional)</label> <input type="file"
												name="file1" path="profileImage" id="PhotoInput"
												placeholder="Photo" />
										</div>

										<div class="form-group">
											<d1> <label>MaritalStatus</label> <label>Gender</label></d1>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:select id="maritalStatusInput" name="maritalStatus"
														path="maritalStatus" class="form-control">
														<form:option value="">Select</form:option>
														<form:option value="Single">Single</form:option>
														<form:option value="Married">Married</form:option>
													</form:select>
													<form:errors path="maritalStatus" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:select id="genderInput" name="gender" path="gender"
														class="form-control">
														<form:option value="">Select</form:option>
														<form:option value="Male">Male</form:option>
														<form:option value="FeMale">FeMale</form:option>
													</form:select>

													<form:errors path="gender" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Location && Nationality</label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="location" id="locationInput" placeholder="Location" maxlength="20"/>
													<form:errors path="location" cssClass="error" />
												</div>

												<div class="col-xs-6">
													<form:input type="text" path="nationality"
														id="nationalityInput" placeholder="Nationality"
														class="form-control" maxlength="20"/>
													<form:errors path="nationality" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Mobile Number</label>
											<form:input type="text" class="form-control" path="phone"
												id="phoneInput" placeholder="Mobile Number" maxlength="10"/>
											<form:errors path="phone" cssClass="error" />
										</div>
										<div class="form-group">
											<d> <label>jobType</label> <label>CurrentIndustry</label>
											<d>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:select id="jobTypeInput" name="jobType"
														path="jobType" class="form-control">
														<form:option value="">Select</form:option>
														<form:option value="Permanent">Permanent</form:option>
														<form:option value="Contract">Contract</form:option>
														<form:option value="PartTime">PartTime</form:option>

													</form:select>
													<form:errors path="jobType" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="currentIndustry" id="currentIndustryInput"
														placeholder="Current Industry" maxlength="20"/>
													<form:errors path="currentIndustry" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Function</label>
											<form:input type="text" class="form-control" path="function"
												id="functionInput" placeholder="Function" maxlength="500"/>
											<form:errors path="function" cssClass="error" />
										</div>
										<div class="form-group">
											<label>keySkills</label>
											<form:textarea type="text" class="form-control"
												path="keySkills" rows="4" id="keySkillsInput"
												placeholder="Key Skills"  maxlength="100"/>
											<form:errors path="keySkills" cssClass="error" />
										</div>
										<div class="form-group">
											<label>Domain Skills</label>
											<form:input type="text" class="form-control"
												path="domainSkills" id="domainSkillsInput"
												placeholder="Domain Skills"  maxlength="100"/>
											<form:errors path="domainSkills" cssClass="error" />
										</div>
										<div class="form-group">
											<label>Specialisation</label>
											<form:input type="text" class="form-control"
												path="specialisation" id="specialisationInput"
												placeholder="Specialisation"  maxlength="100"/>
											<form:errors path="specialisation" cssClass="error" />
										</div>
										<div class="form-group">
											<label>Current Salary </label>
											<div class="row clearfix">
												<div class="col-xs-6">

													<form:input type="text" class="form-control"
														path="currentSalary" id="currentSalaryInput"
														placeholder="Example: 50,000.00"  maxlength="15"/>
													<form:errors path="currentSalary" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:select path="currentSalaryPer" class="form-control">
													     <form:option value="per year">per year</form:option>
														<form:option value="per day">per day</form:option>
														<form:option value="per week">per week</form:option>
														<form:option value="per month">per month</form:option>
														<form:option value="per hour">per hour</form:option>
													</form:select>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label>Expected Salary </label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="expectedCtc" id="expectedCtcInput"
														placeholder="Example: 50,000.00" maxlength="15"/>
													<form:errors path="expectedCtc" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:select path="expectedCtcPer" class="form-control">
														<form:option value="per year">per year</form:option>
														<form:option value="per day">per day</form:option>
														<form:option value="per week">per week</form:option>
														<form:option value="per month">per month</form:option>
														<form:option value="per hour">per hour</form:option>
													</form:select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<d1> <label>CollegeName</label> <label>Degree</label></d1>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control" path="college"
														id="collegeInput" placeholder="College"  maxlength="50"/>
													<form:errors path="college" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:select id="degreeInput" name="degreeInput"
														path="degree" class="form-control">
														<form:option value="">Select</form:option>
														<form:option value="BE">BE</form:option>
														<form:option value="Bsc">Bsc</form:option>
														<form:option value="Msc">Msc</form:option>
														<form:option value="MBBS">MBBS</form:option>
														<form:option value="ME">ME</form:option>
													</form:select>
													<form:errors path="degree" cssClass="error" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<d1> <label>yearOfPassing</label> <label> Grade</label></d1>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="yearOfPassing" id="yearOfPassingInput"
														placeholder="Year Of Passing" />
													<!-- <div class="color-white-mute">
														<small>mm/dd/yyy</small>
													</div> -->
													<form:errors path="yearOfPassing" cssClass="error" />
												</div>

												<div class="col-xs-6">

													<form:select id="gradeInput" name="grade" path="grade"
														class="form-control">
														<form:option value="">Select</form:option>
														<form:option value="First Class">First Class</form:option>
														<form:option value="Second Class">Second Class</form:option>

													</form:select>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label>Company</label>

											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="companyName" id="companyNameInput"
														placeholder="Company Name" maxlength="100"/>
													<form:errors path="companyName" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="companyType" id="companyTypeInput"
														placeholder="Company Type" maxlength="20"/>
													<form:errors path="companyType" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Designation</label>
											<form:input type="text" class="form-control"
												path="designation" id="designationInput"
												placeholder="Designation" maxlength="100"/>
											<form:errors path="designation" cssClass="error" />
										</div>
										<div class="form-group">
											<label>Experience In</label>

											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text1" class="form-control" path="experienceInYear"
								                    id="experienceInYearInput" placeholder="Year" />

													<form:errors path="experienceInYear" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text1" class="form-control" path="experienceInMonth"
								          id="experienceInMonthInput" placeholder="Month" />
													<form:errors path="experienceInMonth" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Date</label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="expStartDate" id="expStartDateInput"
														placeholder="Exp Start Date" />
													<div class="color-white-mute">
														<small>mm/dd/yyyy</small>
													</div>
													<form:errors path="expStartDate" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="expEndDate" id="expEndDateInput"
														placeholder="Exp End Date" />
													<div class="color-white-mute">
														<small>mm/dd/yyyy</small>
													</div>
													<form:errors path="expEndDate" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Languages Known</label>
											<form:input type="text" class="form-control"
												path="languagesKnown" id="languagesKnownInput"
												placeholder="Languages Known" maxlength="100"/>
											<form:errors path="languagesKnown" cssClass="error" />
										</div>

										<div class="form-group">
											<label>Resume</label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="profiledescription" id="profiledescriptionInput"
														placeholder="Profile Title" maxlength="3000"/>
													<form:errors path="profiledescription" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<input type="file" name="file" path="uploadResume"
														id="uploadResumeInput" size="50" placeholder="RESUME" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Preferred</label>
											<div class="row clearfix">
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="preferredIndustry" id="preferredIndustryInput"
														placeholder="Preferred Industry" maxlength="20"/>
													<form:errors path="preferredIndustry" cssClass="error" />
												</div>
												<div class="col-xs-6">
													<form:input type="text" class="form-control"
														path="preferredLocation" id="preferredLocationInput"
														placeholder="Preferred Location" maxlength="20"/>
													<form:errors path="preferredLocation" cssClass="error" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Get Privilege</label>
											<form:input name="text" path="getPrivilege"
												placeholder="Get Privilege" id="getPrivilegeInput" maxlength="100"
												class="form-control"></form:input>
											<form:errors path="getPrivilege" cssClass="error" />
										</div>
										<div class="form-group">
											<form:checkbox path="termsConditionsAgreed" />
											I agree the terms and conditions?
										</div>

										<div class="form-group ">
											<input type="submit" value="Save Profile"
												class="btn btn-t-primary btn-theme" /><a
												href="jobseeker_home.html"> <input type="button"
												value="Cancel" class="btn btn-t-primary btn-theme" /></a>
										</div>
									</form:form>
									<!-- end form post a job -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>