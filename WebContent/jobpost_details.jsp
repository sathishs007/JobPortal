<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="col-md-9">
					<c:if test="${!empty jobPostDetail}">

						<!-- box item details -->
						<div style="margin-left: 5%;">
							<div class="row">
								<div class="col-md-8">
									<h2>
										<i class="fa fa-building"></i>
										<c:out value="${jobPostDetail.companyName}"></c:out>
									</h2>
								</div>
							</div>

							<h3 class="title">
								<a href="#"><c:out value="${jobPostDetail.jobTitle}"></c:out></a>
							</h3>
							<div class="job-meta">
								<ul class="list-inline color-black">
									<li><i class="fa fa-briefcase"></i>
										${jobPostDetail.otherSalaryDetails}</li>
									<li><i class="fa fa-map-marker"></i> <c:out
											value="${jobPostDetail.jobLocation}" /></li>
									<li><i class="fa fa-money"></i> <c:out
											value="${jobPostDetail.salary}" /> P.A</li>
								</ul>
							</div>
							<h4>
								<i class="fa fa-tags"></i> Job Description:
							</h4>
							<p class="richtext-word-wrap color-black"
								style="text-align: justify;">
								<c:out value="${jobPostDetail.jobDescription}" escapeXml="false"></c:out>
							</p>
							<ul class="color-black">
								<li><span class="color-white-mute list-capitalize "
									style="font-size: 14px;"><strong>Experience :</strong></span> <c:out
										value="${jobPostDetail.minExp} - ${jobPostDetail.maxExp}" /></li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Number Of
											Vacancies :</strong></span> <c:out value="${jobPostDetail.noVacancies}" /></li>

								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Salary :</strong></span> <c:out
										value="${jobPostDetail.salary}"></c:out> P.A</li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Function Area
											:</strong></span> <c:out value="${jobPostDetail.functionArea}"></c:out></li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Industry Type
											: </strong></span> <c:out value="${jobPostDetail.industryType}"></c:out></li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Job Title : </strong></span> <c:out
										value="${jobPostDetail.jobTitle}"></c:out></li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Posted By : </strong></span> <c:out
										value="${jobPostDetail.postedBy}" /></li>
							</ul>
							<h4>
								<i class="fa fa-sign-in"></i> What we're looking for :
							</h4>
							<ul class="color-black">
								<li><p class="richtext-word-wrap "
										style="text-align: justify;">
										<c:out value="${jobPostDetail.keywords}" escapeXml="false"></c:out>
									</p></li>
							</ul>
							<h4>
								<i class="fa fa-graduation-cap color-black"></i> Desired
								Candidate Profile :
							</h4>
							<ul class="color-black">
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>PG : </strong></span> <c:out
										value="${jobPostDetail.pgQualification}"></c:out></li>
								<li><span class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>UG : </strong></span> <c:out
										value="${jobPostDetail.ugQualification}"></c:out></li>
							</ul>
							<h4>
								<i class="fa fa-book"></i> Company Profile :
							</h4>
							<p class="richtext-word-wrap color-black"
								style="text-align: justify;">
								<c:out value="${jobPostDetail.employerProfile.companyProfile}"
									escapeXml="false"></c:out>
							</p>
							<h4>
								<i class="fa fa-mobile"></i> View Contact Details
							</h4>
							<ul class="list-unstyled color-black" style="text-align: left">
								<li><i class="fa fa-user"></i> <span
									class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Contact Person
											: </strong></span> <c:out value="${jobPostDetail.contactPerson}"></c:out></li>
								<li><i class="fa fa-phone"></i> <span
									class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Contact Number
											: </strong></span> <c:out value="${jobPostDetail.contactNo}"></c:out></li>
								<li><i class="fa fa-envelope-o"></i> <span
									class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>e-mail : </strong></span> <c:out
										value="${jobPostDetail.employerProfile.emailId}"></c:out></li>
								<li style="text-align: justify;"><i
									class="fa fa-map-marker"></i> <span
									class="color-white-mute list-capitalize"
									style="font-size: 14px;"><strong>Address : </strong></span> <c:out
										value="${jobPostDetail.address}" escapeXml="false"></c:out></li>
							</ul>
							<hr />
						</div>
					</c:if>
				</div>
				 
			</div>
						<div >

						<button id="btnCancel" onclick="history.go(-1);" type="button"
							class="btn btn-t-primary btn-theme">Back</button>
						
					</div>
		</div>
		
	</div>
</div>

