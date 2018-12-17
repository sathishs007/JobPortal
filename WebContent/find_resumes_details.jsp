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

<title>MyjobKart</title>

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
	<header class="main-header">
		<div class="inner-hero-header">
			<!-- Start form search -->
			<div
				style="border-top: 0px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; background: #000;"
				class="parallax">
				<form:form id="myForm" method="post" class="login-form clearfix"
					action="find_resumes.html" commandName="searchjobseeker">
					<c:if test="${not empty message}">
						<div class="message red">${message}</div>
					</c:if>
					<!-- form search -->
					<div class=" col-xs-12 ">
						<div class="row">
							<div class=" col-md-3 ">
								<div class="form-group">
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="text" class="form-control" path="keySkills"
										id="keySkillsInput" placeholder="Key Skills" />
									<form:errors path="keySkills" cssClass="error" />
								</div>
							</div>

							<div class=" col-md-3 ">
								<div class="form-group">

									<label class="hidden-xs">&nbsp;</label>

									<form:input type="text" class="form-control"
										path="preferredIndustry" id="preferredIndustryInput"
										placeholder="Industry" />
									<form:errors path="preferredIndustry" cssClass="error" />
								</div>
							</div>

							<div class=" col-md-3 ">
								<div class="form-group">
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="text" class="form-control"
										path="preferredLocation" id="preferredLocationInput"
										placeholder="Location" />
									<form:errors path="preferredLocation" cssClass="error" />

								</div>
							</div>

							<div class=" col-md-3">
								<div class="form-group">
									<label class="hidden-xs">&nbsp;</label>
									<button class="btn btn-theme btn-success btn-block">
										<small>Search</small>
									</button>
									<p class="text-right" style="padding-top: 10px;">
										<a href="#modal-advanced" data-toggle="modal"
											style="color: white;" class="fa fa-search-plus">Advanced
											Search</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<!--End form search -->

		<!--Start Advanced job Search form search -->

		<div class="modal fade" id="modal-advanced">
			<div class="modal-dialog ">
				<div class="modal-content">
					<form:form id="myForm" method="post" class="login-form clearfix"
						action="find_resumes.html" commandName="searchjobseeker">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Advanced Job
								Search</h4>
						</div>
						<div class="modal-body">
							<h5>Find Resume</h5>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Key Skills</label>
										<form:input type="text" class="form-control" path="keySkills"
											id="keySkillsInput" placeholder="Key Skills" />
										<form:errors path="keySkills" cssClass="error" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Location</label>
										<form:input type="text" class="form-control"
											path="preferredLocation" id="preferredLocationInput"
											placeholder="Location" />
										<form:errors path="preferredLocation" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Industry</label>
								<form:input type="text" class="form-control"
									path="preferredIndustry" id="preferredIndustryInput"
									placeholder="Industry" />
							</div>
							<div class="white-space-10"></div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Domain Skills </label>
										<form:input type="text" class="form-control"
											path="domainSkills" id="domainSkillsInput"
											placeholder="Domain Skills" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Designation </label>
										<form:input type="text" class="form-control"
											path="designation" id="designationInput"
											placeholder="Designation" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>ExpectedCtc </label>
										<form:input type="text" class="form-control"
											path="expectedCtc" id="expectedCtcInput"
											placeholder="ExpectedCtc" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Experience</label>
										<form:input type="text" class="form-control"
											path="experienceInYear" id="experienceInYearInput"
											placeholder="experience In Year" />
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-theme"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success btn-theme">Find
								Jobs</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<!--End Advanced job Search form search -->

	</header>
	<div class="body-content clearfix">
		<div class="bg-color2 block-section-xs line-bottom">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 hidden-xs">
						<div>
							<b>Resume details :</b>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="text-right">

							<a href="" onclick="javascript:history.go(-1)">&laquo; Go
								back to resume list</a>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">

						<!-- box item details -->
						<div class="block-section box-item-details">
							<c:if test="${!empty jobseekerDetail}">
								<div class="resume-block">
									<div class="img-profile">
										<img src="imageLoginDisplay.html?id=${jobseekerDetail.id}" />
									</div>
									<div class="desc">
										<h2>
											<c:out value="${jobseekerDetail.firstName}"></c:out>
										</h2>
										<h4>
											<c:out value="${jobseekerDetail.designation}"></c:out>
										</h4>
										<p>
											<c:out value="${jobseekerDetail.companyName}"></c:out>

											<c:out value="${jobseekerDetail.location}"></c:out>
										</p>

										<h3 class="resume-sub-title">

											<span><i class="fa fa-graduation-cap"></i> Education</span>
										</h3>
										<c:if test="${!empty educationalDetail}">
											<div class="pi-responsive-table-sm">
												<table style="border: 1px solid;">
													<thead style="background-color: #2a3f54">
														<tr>
															<th style="text-align: center; color: #fff; width: 10%;">Degree
															</th>
															<th style="text-align: center; color: #fff; width: 20%;">Department
															</th>
															<th style="text-align: center; color: #fff; width: 20%;">College</th>
															<th style="text-align: center; color: #fff; width: 15%;">Year
																Of passing</th>
															<th style="text-align: center; color: #fff; width: 15%;">Grade</th>
															<th style="text-align: center; color: #fff; width: 10%;">Percentage</th>
														</tr>
													</thead>
													<tbody>

														<c:forEach items="${educationalDetail}" var="searchResult">
															<tr>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.degree}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.department}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.college}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.yearOfPassing}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.grade}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.percentage}"></c:out></td>

															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</c:if>

										<h3 class="resume-sub-title">
											<span> <i class="fa fa-suitcase" aria-hidden="true"></i>

												Work Experience
											</span>
										</h3>
										<div class="warning" style="width: 100%; text-align: left;">
											<c:if test="${empty experienceDetail}">
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
										<c:if test="${!empty experienceDetail}">
											<div class="pi-responsive-table-sm">
												<table style="border: 1px solid;">
													<thead style="background-color: #2a3f54">
														<tr>
															<th style="text-align: center; color: #fff; width: 10%;">Company
																Name</th>
															<th style="text-align: center; color: #fff; width: 10%;">Company
																Type</th>
															<th style="text-align: center; color: #fff; width: 10%;">Designation</th>
															<th style="text-align: center; color: #fff; width: 10%;">Salary</th>
															<th style="text-align: center; color: #fff; width: 5%;">Start
																Date</th>
															<th style="text-align: center; color: #fff; width: 5%;">End
																Date</th>
														</tr>
													</thead>
													<tbody>

														<c:forEach items="${experienceDetail}" var="searchResult">
															<tr>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.companyName}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.companyType}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.designation}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.lastSalary}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.startDate}"></c:out></td>
																<td class="td-border list-capitalize"><c:out
																		value="${searchResult.endDate}"></c:out></td>

															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</c:if>
										<h3 class="resume-sub-title">
											<span><i class="fa fa-info-circle" aria-hidden="true"></i>
												Additional Information</span>
										</h3>
										<h4>SKILLS:</h4>
										<ul>
											<li><c:out value="${jobseekerDetail.keySkills}"
													escapeXml="false"></c:out></li>
											<%-- <li><c:out value="${jobseekerDetail.domainSkills}"></c:out></li>
											<li><c:out value="${jobseekerDetail.specialisation}"></c:out> --%>
											</li>
										</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-md-3">
						<div class="block-section " id="affix-box">
							<!-- <div class="text-right"> -->

							<div>
								<c:choose>
									<c:when test="${jobseekerDetail.isActive==false}">

										<p>
											<a onclick="return confirm('This Resume is Already Saved')"
												class="btn btn-theme btn-line dark btn-block-xs">Already
												Saved Resume</a>
										</p>
									</c:when>

									<c:otherwise>


										<p>
											<a href="save_resume.html?id=${jobseekerDetail.id}"
												class="btn btn-theme btn-line dark btn-block-xs">Save
												Resume</a>
										</p>



									</c:otherwise>


								</c:choose>



								<p>
									<b>Updated : <c:out value="${jobseekerDetail.modified}"></c:out>
									</b>
								</p>
								<p>
									<a href="save_resume.html" data-toggle="modal"
										data-target="#myModal"
										class="btn btn-theme btn-t-primary btn-block-xs">Forward
										by Email</a>
								</p>
								<p>
									<a href="save_resume.html" data-toggle="modal"
										data-target="#myModal"
										class="btn btn-theme btn-t-primary btn-block-xs">Contact
										Bryon</a>
								</p>
								<c:choose>

									<c:when test="${jobseekerDetail.resumeVisibility==true}">
										<p>
											<!-- <a href="save_resume.html" data-toggle="modal"
										data-target="#myModal"
										class="btn btn-theme btn-t-primary btn-block-xs">Download
										This Resume</a> -->

											<a href="resumeDisplay.html?id=${jobseekerDetail.id}"
												class="btn btn-theme btn-t-primary btn-block-xs">Download
												This Resume</a>

										</p>
									</c:when>
									<c:otherwise>



										<p>
											<!-- <a href="save_resume.html" data-toggle="modal"
										data-target="#myModal"
										class="btn btn-theme btn-t-primary btn-block-xs">Download
										This Resume</a> -->

											<a href="#" class="btn btn-theme btn-t-primary btn-block-xs">
												Resume Not Uploaded</a>

										</p>

									</c:otherwise>
								</c:choose>

								<a href="download_resume.html?id=${jobseekerDetail.id}"
									class="btn btn-theme btn-t-primary btn-block-xs">Download
									Jobseeker Resume </a>

								<p>
									Share This Resume <span class="space-inline-10"></span> :
								</p>
								<p class="share-btns">
									<a href="#" class="btn btn-primary"><i
										class="fa fa-facebook"></i></a> <a href="#" class="btn btn-info"><i
										class="fa fa-twitter"></i></a> <a href="#" class="btn btn-danger"><i
										class="fa fa-google-plus"></i></a> <a href="#"
										class="btn btn-warning"><i class="fa fa-pinterest"></i></a>
								</p>
								<!-- </div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- <script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script> -->
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="resources/theme/js/theme.js"></script>
	<!-- <script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<script src="resources/theme/js/map-detail.js"></script> -->

</body>
</html>