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
</head>
<body>
	
	<div class="block-section box-side-account">
		<div class="box-list" style="margin-left: -20px; padding: 5px; margin-top: -1px;">
			<div class="item">
				<div class="row">
					<div class="container">

						<div class="row">

							<div class="col-sm-6">
								<c:if test="${!empty appliedJobseeker}">
									<div class="resume-block">
										<div class="img-profile">
											<img
												src="jobseeker_image.html.html?id=${appliedJobseeker.id}"
												alt="">
										</div>
										<div class="desc">
											<h2>
												<c:out value="${appliedJobseeker.jobTitle}"></c:out>
											</h2>

											<p>
												<c:out value="${appliedJobseeker.address}"></c:out>
											</p>

											<h5>
												Function Area - <span class="color-white-mute"><c:out
														value="${appliedJobseeker.functionArea}"></c:out></span>
											</h5>
											<h5>
												Industry Type- <span class="color-white-mute"><c:out
														value="${appliedJobseeker.industryType}"></c:out></span>
											</h5>
											<h3 class="resume-sub-title">
												<span>Contact Details</span>
											</h3>

											<h4>
												<c:out value="${appliedJobseeker.contactNo}"></c:out>
												-
												<c:out value="${appliedJobseeker.contactPerson}"></c:out>
											</h4>
											<h5>
												<c:out value="${appliedJobseeker.companyName}"></c:out>
												<span class="color-white-mute"> - <c:out
														value="${appliedJobs.jobLocation}"></c:out></span>
											</h5>
											<p class="color-white-mute">
												<c:out value="${appliedJobseeker.jobDescription}"></c:out>
											</p>
											<h3 class="resume-sub-title">
												<span>Required Skills</span>
											</h3>
											<ul>
												<h5>
													Designation - <span class="color-white-mute"><c:out
															value="${appliedJobseeker.functionArea}"></c:out></span>
												</h5>
												<h5>
													Key Skills- <span class="color-white-mute"><p class="richtext-word-wrap"><c:out
															value="${appliedJobseeker.keywords}" escapeXml="false"></c:out></p></span>
												</h5>
												<h5>
													Minimum Experience -<span class="color-white-mute">
														<c:out value="${appliedJobseeker.minExp}"></c:out>
													</span>
												</h5>
												<h5>
													Maximum Experience -<span class="color-white-mute">
														<c:out value="${appliedJobseeker.maxExp}"></c:out>
													</span>
												</h5>
												<h5>
													Minimum Salary -<span class="color-white-mute"> <c:out
															value="${appliedJobseeker.minSalary}"></c:out>
													</span>
												</h5>
												<h5>
													Maximum Salary -<span class="color-white-mute"> <c:out
															value="${appliedJobseeker.maxSalary}"></c:out>
													</span>
												</h5>
											</ul>
											<h3 class="resume-sub-title">
												<span>Qualification</span>
											</h3>
											<h5>
												UG Qualification -<span class="color-white-mute"> <c:out
														value="${appliedJobseeker.ugQualification}"></c:out>
												</span>
											</h5>
											<h5>
												PG Qualification -<span class="color-white-mute"> <c:out
														value="${appliedJobseeker.pgQualification}"></c:out>
												</span>
											</h5>
										</div>
									</div>
									<div class="form-group">
							<br /> <a href="admin_jobseekers_appliedjobs.html"> <input
										type="button" value="Back" class="btn btn-t-primary btn-theme" /></a>
							</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>