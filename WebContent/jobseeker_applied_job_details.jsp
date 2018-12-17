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
<style>
p {
	white-space: pre-line;
}
</style>
<body>

	<div class="container">
		<div class="col-sm-12">
			<div class="box-list">
				<div class="item">
					<div class="row">
						<c:if test="${!empty appliedJobs}">
							<div class="resume-block">
								<div class="img-profile">
									<img src="appliedJobImageDisplay.html?id=${appliedJobs.id}"
										alt="">
								</div>
								<div class="desc list-capitalize">
									<h2>
										<c:out value="${appliedJobs.jobTitle}"></c:out>
									</h2>

									<p class="richtext-word-wrap">
										<c:out value="${appliedJobs.address}"  escapeXml="false"></c:out>
									</p>

									<h5>
										Function Area - <span class="color-white-mute"><c:out
												value="${appliedJobs.functionArea}"></c:out></span>
									</h5>
									<h5>
										Industry Type- <span class="color-white-mute"><c:out
												value="${appliedJobs.industryType}"></c:out></span>
									</h5>
									<h3 class="resume-sub-title list-capitalize">
										<span><i class="fa fa-mobile"></i> Contact Details</span>
									</h3>

									<h4>
										<c:out value="${appliedJobs.contactNo}"></c:out>
										-
										<c:out value="${appliedJobs.contactPerson}"></c:out>
									</h4>
									<h5>
										<c:out value="${appliedJobs.companyName}"></c:out>
										<span class="color-white-mute"> - <c:out
												value="${appliedJobs.jobLocation}"></c:out></span>
									</h5>
									<p class="color-white-mute richtext-word-wrap" >
										<c:out value="${appliedJobs.jobDescription}" escapeXml="false"></c:out>
									</p>
									<h3 class="resume-sub-title">
										<span><i class="fa fa-tags"></i> Required Skills</span>
									</h3>
									<ul style="margin-left: -30px;">
										
										<h5>
											Key Skills- <p class="richtext-word-wrap"> <span class="color-white-mute"><c:out
													value="${appliedJobs.keywords}"  escapeXml="false"></c:out></p></span>
										</h5>
										<h5>
											Minimum Experience -<span class="color-white-mute"> <c:out
													value="${appliedJobs.minExp}"></c:out>
											</span>
										</h5>
										<h5>
											Maximum Experience -<span class="color-white-mute"> <c:out
													value="${appliedJobs.maxExp}"></c:out>
											</span>
										</h5>
										<h5>
											Minimum Salary -<span class="color-white-mute"> <c:out
													value="${appliedJobs.minSalary}"></c:out>
											</span>
										</h5>
										<h5>
											Maximum Salary -<span class="color-white-mute"> <c:out
													value="${appliedJobs.maxSalary}"></c:out>
											</span>
										</h5>
									</ul>
									<h3 class="resume-sub-title">
										<span><i class="fa fa-graduation-cap"></i> Qualification</span>
									</h3>
									<h5>
										UG Qualification -<span class="color-white-mute"> <c:out
												value="${appliedJobs.ugQualification}"></c:out>
										</span>
									</h5>
									<h5>
										PG Qualification -<span class="color-white-mute"> <c:out
												value="${appliedJobs.pgQualification}"></c:out>
										</span>
										<c:out value="${userType}"  />
									</h5>
								</div>
							</div>
							 <br>
							<div class="box-list">
			  <c:if test="${userType=='Jobseeker'}" > 
								<a href="jobseeker_applied_jobs.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
								</a>
				 </c:if>  
				 <c:if test="${userType=='admin'}" > 
							<a href="admin_jobseeker_applied_jobs.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
				</a>
				</c:if>						
							</div> 
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>