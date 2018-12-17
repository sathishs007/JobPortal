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
	<div class="body-content clearfix">
		<div class="bg-color2 block-section-xs line-bottom">
			<div class="container">
				<div class="row">
					<div class="col-sm-5 hidden-xs" style="margin-left: 4%;"></div>
					<div class="col-sm-6">
						<div class="text-right">
							<a href="walkin_jobs.html" onclick="javascript:history.go(-1)">&laquo; Go
								Back to Job List</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<c:if test="${!empty jobPostDetail}">
							<div class="block-section box-item-details"
								style="margin-left: 4%;">
								<div class="row">
									<div class="col-md-8">
										<h2>
											<i class="fa fa-building"></i>
											<c:out value="${jobPostDetail.companyName}"></c:out>
										</h2>

									</div>
									<div class="col-md-4">
										<p class="text-right">
											<a href="#">Go to our website &raquo;</a>
										</p>
									</div>
								</div>

								<h2 class="title">
									<c:out value="${jobPostDetail.jobTitle}"></c:out>
								</h2>
								<div class="job-meta">
									<ul class="list-inline">
										<li><i class="fa fa-briefcase"></i> ${jobPostDetail.minExp} - ${jobPostDetail.maxExp}
											Years</li>
										<li><i class="fa fa-map-marker"></i> <c:out
												value="${jobPostDetail.jobLocation}" /></li>
									</ul>
								</div>
								<h4>
									<i class="fa fa-tags"></i> Job Description:
								</h4>
								<p class="richtext-word-wrap" style = "text-align: justify;">
									<c:out value="${jobPostDetail.jobDescription}"
										escapeXml="false">
									</c:out>
								</p>
								<ul>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Salary : </strong></span><c:out
											value="${jobPostDetail.minSalary} - ${jobPostDetail.maxSalary}"></c:out>
										P.A</li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Function Area : </strong></span> <c:out
											value="${jobPostDetail.functionArea}"></c:out></li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Industry Type : </strong></span> <c:out
											value="${jobPostDetail.industryType}"></c:out></li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Role : </strong></span> <c:out value="${jobPostDetail.role}"></c:out></li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Role Category : </strong></span> <c:out
											value="${jobPostDetail.roleCategory}"></c:out></li>
								</ul>
								<h4>
									<i class="fa fa-sign-in"></i> What we're looking for :
								</h4>
								
								<ul>
									<li><p class="richtext-word-wrap" style = "text-align: justify;">
											<c:out value="${jobPostDetail.keywords}" escapeXml="false"></c:out>
										</p></li>
								</ul>
								<h4>
									<i class="fa fa-graduation-cap"></i> Desired Candidate Profile
									:

								</h4>
								<h5>Education-</h5>
								<ul>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>PG : </strong></span><c:out
											value="${jobPostDetail.pgQualification}"></c:out></li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>UG : </strong></span><c:out
											value="${jobPostDetail.ugQualification}"></c:out></li>
									<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Doctorate : </strong></span> <c:out
											value="${jobPostDetail.doctorate}"></c:out></li>

								</ul>
								<h4>
									<i class="fa fa-book"></i> Company Profile :
								</h4>
								<p class="richtext-word-wrap" style = "text-align: justify;">
									<c:out value="${jobPostDetail.companyProfile}"
										escapeXml="false"></c:out>
								<p>
								<h4>
										<i class="fa fa-mobile"></i> View Contact Details
									</h4>
								
								<ul class="list-unstyled" style="text-align: justify;">
									<li><i class="fa fa-user"></i> <span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Contact Person : </strong></span>  <c:out
											value="${jobPostDetail.contactPerson}"></c:out></li>
									<li><i class="fa fa-phone"></i> <span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Contact Number : </strong></span> <c:out
											value="${jobPostDetail.contactNo}"></c:out></li>
									<li><i class="fa fa-map-marker"></i> <span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Address : </strong></span>  <c:out
											value="${jobPostDetail.address}" escapeXml="false"></c:out></li>
								</ul>
								<hr />
							</div>
						</c:if>
					</div>
					<div class="col-md-3">
						<div class="block-section " >

							<div class="box-list" style="border: 1px solid #e1e1e1;">

								<div class="result-filter">
									<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
										Search Walk-in jobs</h5>
									<div>

										<p style="padding: 15px;">
											<i class="fa fa-check-square" aria-hidden="true"></i>
											Myjobkart Resume Score - Free Get your FREE resume feedback
											report and know the improvement areas in your resume within
											30 seconds
										</p>


										<p style="padding: 15px;">
											<i class="fa fa-check-square" aria-hidden="true"></i> Reach
											out to more recruiters Become a Featured Applicant on
											Myjobkart and Increase your profile views by up to 3 times.
											Know More.
										</p>
										<p style="padding: 15px;">Call 1800-3010-5557 now!
											(Toll-Free)</p>
										<a href="find_jobs.html"
											class="btn btn-theme btn-success btn-block"><strong>Search
												Walk-in jobs</strong></a>
										<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
											View Recruiters</h5>
										<p style="padding: 15px;">
											<i class="fa fa-check-square" aria-hidden="true"></i> Connect
											to recruiters directly. More than 20000 Recruiters looking
											for candidates like you
										</p>
										<a href="view_recruiters.html"
											class="btn btn-theme btn-success btn-block"><strong>View
												Recruiters</strong></a>
										<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
											Job Alert</h5>
										<p style="padding: 15px;">
											<i class="fa fa-check-square" aria-hidden="true"></i> Get
											best matched jobs on your email. No registration needed
										</p>
										<a href="create_jobalert.html"
											class="btn btn-theme btn-success btn-block"><strong>Create
												a Job Alert</strong></a>
									</div>
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