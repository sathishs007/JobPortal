<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
								<label>Industry</label>
								<form:input type="text" class="form-control"
									path="preferredIndustry" id="preferredIndustryInput"
									placeholder="Industry" />
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
		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="block-section-sm box-list-area">
							<div class="row hidden-xs">
								<div class="col-sm-6 " style="margin-left: 5.5%;">
									<p>
										<strong class="color-black">Search Resumes Details In
											India</strong>
									</p>
								</div>

							</div>
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Success!</strong>
									<c:out value="${sucessmessage}"></c:out>
								</div>
							</c:if>

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

							<div style="margin-top: 0%; margin-left: 52px;">
								<c:if test="${!empty ResumeDescriptions.list}">
									<c:forEach items="${ResumeDescriptions.list}" var="searchResult">
										
										<!-- item listing  -->
										<div class="item box-list box-padding"
											style="background-color: #fafafa; margin-top: 10px;">
											<div class="row">
												<div class="col-md-1 hidden-sm hidden-xs">
													<div class="img-item">
														<img src="imageLoginDisplay.html?id=${searchResult.id}" />
													</div>
												</div>
												<div class="col-md-11">
													<h3 class="no-margin-top list-capitalize">
														<a href="jobseeker_profile_details.html?id=${searchResult.id}&&status=${searchResult.isVisiable}"><c:out
																value="${searchResult.firstName }"></c:out> </a>
													</h3>
													<h4>
													<span class="color-black list-capitalize"><p
															class="richtext-word-wrap">
															<c:out value="${searchResult.profiledescription }"
																escapeXml="false"></c:out>
																</p>
														</span> 
														</h4>
														
														
														<i class="fa fa-tags"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Key Skills</strong></span> - <span
														class="color-black list-capitalize"> <c:out
															value="${searchResult.keySkills}" escapeXml="false"></c:out>

													</span> <br /> 
													
													<h5 style="text-align: justify;">
													<i class="fa fa-book"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong>profile Descriptions</strong></span>
													- <span class="color-black list-capitalize"
														style="line-height: 20px;"><c:out
															value="${searchResult.profileDescriptions}" escapeXml="false"></c:out></span>
												</h5>
													
													
													
													<%-- <h4 style="text-align: justfy;">
													<i class="fa fa-tags"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>profile Descriptions</strong></span> - <span
														class="color-black list-capitalize"> <c:out
															value="${searchResult.profileDescriptions}" escapeXml="false"></c:out>
															</h4>
														</span> <br />  --%>
													
													<i class="fa fa-phone"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Phone</strong></span> - <span
														class="color-black list-capitalize"> <c:out
															value="${searchResult.phone}" escapeXml="false"></c:out>

													</span> <br /> 
													
													<i class="fa fa-envelope-o"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Email Address</strong></span> - <span
														class="color-black list-capitalize"> <c:out
															value="${searchResult.emailId}" escapeXml="false"></c:out>

													</span> <br /> 
													
													
														
													<c:choose>
														<c:when test="${searchResult.isVisiable=='0'}">
															<div>
															<i class="fa fa-calendar-o"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Created Date</strong></span>
																-<c:out
																			value="${searchResult.createdDate }"></c:out>- <a
																	onclick="return confirm('This Resume is Already Saved')"
																	class="btn btn-xs btn-theme btn-success">Already
																	Saved</a>
															</div>
															<br />
														</c:when>
														<c:otherwise>
															<div>
																<i class="fa fa-calendar-o"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Created Date</strong></span>
																-<c:out
																			value="${searchResult.createdDate }"></c:out>-<a
																	href="save_resume.html?id=${searchResult.id}"
																	class="btn btn-xs btn-theme btn-success">Save in
																	List</a>
															</div>
															<br />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>

							<c:if test="${!empty ResumeDescriptions.list}">
								<center>
									<nav>
										<ul class="pagination pagination-theme  no-margin">
											<c:if test="${ResumeDescriptions.currentPage != 1}">
												<li><a aria-label="Previous"
													href="find_resumes.html?page=${ResumeDescriptions.currentPage - 1}">
														<span aria-hidden="true">&larr;</span>
												</a></li>
											</c:if>
											<li><c:forEach begin="${ResumeDescriptions.start}"
													end="${ResumeDescriptions.records}" var="i">
													<c:choose>
														<c:when test="${ResumeDescriptions.page == i}">
															<a href="find_resumes.html?page=${i}"
																style="color: #fff; background-color: #34495e">${i}</a>
														</c:when>
														<c:otherwise>
															<a href="find_resumes.html?page=${i}">${i}</a>
														</c:otherwise>
													</c:choose>
												</c:forEach></li>
											<c:if
												test="${ResumeDescriptions.currentPage lt ResumeDescriptions.totalPages}">
												<li><a
													href="find_resumes.html?page=${ResumeDescriptions.currentPage + 1}"><span
														aria-hidden="true">&rarr;</span></a></li>
											</c:if>
										</ul>
									</nav>
								</center>
								<br />
							</c:if>

							
						</div>
					</div>
					<div class="col-md-3">
						<div class="block-section-sm side-right">
							<div class="row">
								<div class="col-xs-6">
									<p>
										<strong>Sort By: </strong>
									</p>
								</div>
							</div>

							<div class="result-filter box-list"
								style="background-color: #fafafa; border: 1px solid #e1e1e1; padding: 10px;">

								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_2" data-toggle="collapse">Title <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>

								<c:if test="${!empty countTitleList}">
									<div class="collapse in" id="s_collapse_2">
										<div class="list-area">
											<c:forEach items="${countTitleList}" var="countprofile">
												<ul class="list-unstyled ">
													<li><a href="find_resumes.html?title=${countprofile.profiledescription}">${countprofile.profiledescription}
															(${countprofile.titleCount})</a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>

								<c:if test="${empty countTitleList}">
									<div class="collapse in" id="s_collapse_2">
										<div class="list-area">
											<c:forEach items="${ResumeDescriptions.list}"
												var="searchResult">
												<ul class="list-unstyled ">
													<li><a href="find_resumes.html?title=${countprofile.profiledescription}">${searchResult.profiledescription}</a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="need-login">
			<div class="modal-dialog modal-md">
				<div class="modal-content">

					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">You must login to save jobs</h4>
					</div>
					<div class="modal-footer text-center">
						<a href="#" class="btn btn-default btn-theme">Login</a> <a
							href="#" class="btn btn-success btn-theme">Create account
							(it's free)</a>
					</div>

				</div>
			</div>
		</div>
		
	</div>
</body>
</html>