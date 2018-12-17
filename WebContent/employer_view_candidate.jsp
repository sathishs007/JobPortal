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
	
			<div class="box-list">
				<div class="item">
					<div class="row">

						<c:if test="${!empty appliedJobs}">
							<div class="resume-block">
								<div class="img-profile">
									<img src="showcandidateImage.html?id=${appliedJobs.id}" alt="">
								</div>
								<div class="desc">
									<h2>
										<c:out value="${appliedJobs.firstName}"></c:out>
										-<small><c:out value="${appliedJobs.degree}"></c:out></small>
									</h2>
									<h4>
										Email- <span class="color-white-mute"><c:out
												value="${appliedJobs.emailId}"></c:out></span>
									</h4>
									<h4>
										Phone - <span class="color-white-mute"><c:out
												value="${appliedJobs.phone}"></c:out></span>
									</h4>
									

									<h3 class="resume-sub-title">
										<span><i class="fa fa-suitcase"></i>
										 Experience Details</span>
									</h3>

									<p>
										<c:out value="${appliedJobs.resumeTitle}"></c:out>
									</p>
									<h5>
										KeySkills:-
										<c:out value="${appliedJobs.keySkills}" escapeXml="false"></c:out>
									</h5>
									<h5>
										Role in Current Industry-
										<c:out value="${appliedJobs.profiledescription}"></c:out>
									</h5>
									Experience :-
									<c:out value="${appliedJobs.minExp}"></c:out>
									-
									<c:out value="${appliedJobs.maxExp}"></c:out>
									
									<h5>Expected :</h5>
									<ul>
										<li>Salary:-<c:out value="${appliedJobs.minSalary}"></c:out>-<c:out
												value="${appliedJobs.maxSalary}"></c:out></li>
										<li>Location:-<c:out
												value="${appliedJobs.preferredLocation}"></c:out></li>
										<li>Industry:-<c:out
												value="${appliedJobs.preferredIndustry}"></c:out></li>
									</ul>
									<h3 class="resume-sub-title">
										<span><i class="fa fa-info-circle"></i>
</i>
										 Applied Details</span>
									</h3>

									<ul>
										<h5>
											Title - <span class="color-white-mute"><c:out
													value="${appliedJobs.jobTitle}"></c:out></span>
										</h5>
										<h5>
											Date- <span class="color-white-mute"><c:out
													value="${appliedJobs.created}"></c:out></span>
										</h5>
										
									</ul>
									<c:if test="${appliedJobs.resumevisibile==true}">
											<h4><i class="fa fa-file" ></i>
											 Candidate Resume</h4>
											<ul>
											<li><a
												href="applycandidateresumeDisplay.html?id=${appliedJobs.id}">Click
													To DownLoad Resume</a></li>
											</ul>
										</c:if>
								</div>
							</div>
						</c:if>
					</div>
					<br/>
					<div >
								<a href="employer_applied_jobs.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
								</a>
							</div>
				</div>
			</div>
		
</body>
</html>