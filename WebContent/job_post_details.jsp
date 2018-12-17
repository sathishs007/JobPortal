<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
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
	<div class="hero-header main-header">
		<div class="inner-hero-header">
			<div class="container ">
				<div class="row">
					<div
						style="border-top: 0px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; background: #000;"
						class="parallax">

						<form:form id="myForm" method="post" class="login-form clearfix"
							action="find_jobs.html" commandName="searchjob">
							<c:if test="${not empty message}">
								<div class="message red">${message}</div>
							</c:if>


							<!-- form search -->
							<div class=" col-xs-12" style="padding-top: 10px;">
								<div class="row">
									<div class=" col-md-3 ">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control" path="keywords"
												placeholder=" Skills  "></form:input>

										</div>
									</div>

									<div class=" col-md-3 ">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control"
												path="searchElement" placeholder=" Job Location"></form:input>

										</div>
									</div>

									<div class=" col-md-3 ">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control" path="minExp"
												placeholder=" Experience"></form:input>

										</div>
									</div>

									<div class=" col-md-3">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<button class="btn btn-theme btn-success btn-block">
												<span
													style="font-size: 100%; font-family: 'Montserrat', Helvetica, Arial, sans-serif;">Search</span>
											</button>
											<p class="text-right">
												<a href="#modal-advanced" data-toggle="modal"
													style="color: white;"><span class="fa fa-search-plus"
													style="padding: 5px;"></span><span
													style="font-family: 'Montserrat', Helvetica, Arial, sans-serif; font-size: 12px;">Advanced
														Search</span></a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<div class="modal fade" id="modal-advanced">
					<div class="modal-dialog ">
						<div class="modal-content">
							<form:form id="myForm" method="post" class="job_filters"
								commandName="searchjob">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Advanced Job
										Search</h4>
								</div>
								<div class="modal-body">
									<h5>Find Jobs</h5>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Company Name</label>
												<form:input type="text" class="form-control "
													path="companyName" placeholder="Company Name  "></form:input>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Location</label>
												<form:input type="text" class="form-control  "
													path="searchElement" placeholder=" Job Location"></form:input>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>Experience</label> <label class="color-white">Experience</label>
										<form:input type="text" class="form-control  " path="minExp"
											placeholder=" Experience"></form:input>
									</div>
									<div class="white-space-10"></div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Job Title </label>
												<form:input type="text" class="form-control  "
													path="jobTitle" placeholder=" Job Title"></form:input>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Key Skills </label>
												<form:input type="text" class="form-control  "
													path="keywords" placeholder=" Key Skills"></form:input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Salary </label>
												<form:input type="text" class="form-control  "
													path="maxSalary" placeholder=" Salary"></form:input>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Job Type</label>
												<form:input type="text" class="form-control  "
													path="otherSalaryDetails" placeholder=" Job Type"></form:input>
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
			</div>
		</div>
	</div>
	<div class="body-content clearfix">
		<div class="bg-color2 block-section-xs line-bottom">
			<div class="container">
				<div class="row">
					<div class="col-sm-5 hidden-xs" style="margin-left: 4%;">
						<div class="color-black">Job Details :</div>
					</div>
					<div class="col-sm-6">
						<div class="text-right">
							<a href="find_jobs" onclick="javascript:history.go(-1)">&laquo;
								Go back to job list</a>
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

								<!-- box item details -->
								<div class="block-section box-item-details"
									style="margin-left: 5%;">
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
									<p class="richtext-word-wrap color-black" style="text-align: justify;">
										<c:out value="${jobPostDetail.jobDescription}"
											escapeXml="false"></c:out>
									</p>
									<ul class = "color-black">
										<li><span class="color-white-mute list-capitalize "
											style="font-size: 14px;"><strong>Experience :</strong></span>
											<c:out
												value="${jobPostDetail.minExp} - ${jobPostDetail.maxExp}" /></li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Number Of
													Vacancies :</strong></span> <c:out value="${jobPostDetail.noVacancies}" /></li>

										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Salary :</strong></span> <c:out
												value="${jobPostDetail.salary}"></c:out> P.A</li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Function
													Area :</strong></span>
										<c:out value="${jobPostDetail.functionArea}"></c:out></li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Industry
													Type : </strong></span>
										<c:out value="${jobPostDetail.industryType}"></c:out></li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Job Title :
											</strong></span> <c:out value="${jobPostDetail.jobTitle}"></c:out></li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Posted By :
											</strong></span>
										<c:out value="${jobPostDetail.postedBy}" /></li>
									</ul>
									<h4>
										<i class="fa fa-sign-in"></i> What we're looking for :
									</h4>
									<ul class ="color-black">
										<li><p class="richtext-word-wrap " style="text-align: justify;">
												<c:out value="${jobPostDetail.keywords}" escapeXml="false"></c:out>
											</p></li>
									</ul>
									<h4>
										<i class="fa fa-graduation-cap color-black"></i> Desired
										Candidate Profile :
									</h4>
									<ul class ="color-black">
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>PG : </strong></span> <c:out
												value="${jobPostDetail.pgQualification}"></c:out></li>
										<li><span class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>UG : </strong></span>
										<c:out value="${jobPostDetail.ugQualification}"></c:out></li>
									</ul>
									<h4>
										<i class="fa fa-book"></i> Company Profile :
									</h4>
									<p class="richtext-word-wrap color-black" style="text-align: justify;">
										<c:out value="${jobPostDetail.employerProfile.companyProfile}"
											escapeXml="false"></c:out>
									</p> 
									<h4>
										<i class="fa fa-mobile"></i> View Contact Details
									</h4>
									<ul class="list-unstyled color-black" style="text-align: left">
										<li><i class="fa fa-user"></i> <span
											class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Contact
													Person : </strong></span>
										<c:out value="${jobPostDetail.contactPerson}"></c:out></li>
										<li><i class="fa fa-phone"></i> <span
											class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Contact
													Number : </strong></span> <c:out value="${jobPostDetail.contactNo}"></c:out></li>
										 <li><i class="fa fa-envelope-o"></i> <span
											class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>e-mail : </strong></span>
										<c:out value="${jobPostDetail.employerProfile.emailId}"></c:out></li> 
										<li style="text-align: justify;"><i class="fa fa-map-marker"></i> <span
											class="color-white-mute list-capitalize"
											style="font-size: 14px;"><strong>Address : </strong></span> <c:out
												value="${jobPostDetail.address}" escapeXml="false"></c:out>
										</li>
									</ul>
									<hr />
								</div>
							</c:if>
					</div>
					<div class="col-md-3">

						<!-- box affix right -->
						<div class="block-section " id="affix-box">
							<!-- <div class="text-right"> -->
							<div>
								<p>
									<a href="#" class="btn btn-theme btn-line dark btn-block-xs">Apply
										With Linkedin</a>
								</p>
								<p>
									<a href="applied_jobs.html?id=${jobPostDetail.id}"
										class="btn btn-theme btn-t-primary btn-block-xs">Apply
										This Job</a>
								</p>
								<p>
									<a href="jobseeker_sign_in.html"
										class="btn btn-theme btn-t-primary btn-block-xs">Login to
										Save This Job</a>
								</p>
								<p>
									<a href="#map-toogle" id="btn-map-toogle"
										data-toggle="collapse"
										class="btn btn-theme btn-t-primary btn-block-xs">Office
										Location</a>
								</p>
								<p>
									Share This Job <span class="space-inline-10"></span> :
								</p>
								<p class="share-btns">
									<a href="#" class="btn btn-primary"><i
										class="fa fa-facebook"></i></a> <a href="#" class="btn btn-info"><i
										class="fa fa-twitter"></i></a> <a href="#" class="btn btn-danger"><i
										class="fa fa-google-plus"></i></a> <a href="#"
										class="btn btn-warning"><i class="fa fa-envelope"></i></a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- block map -->
		<div class="collapse" id="map-toogle">
			<div class=" bg-color2" id="map-area">
				<div class="container">
					<div class="marker-description">
						<div class="inner">
							<h4 class="no-margin-top">Office Location</h4>
							Central Jakarta No 1234, Jakarta, Indonesia
						</div>
					</div>
				</div>
				<div class="map-area-detail">
					<!-- change data  lat abd lng here -->
					<div class="box-map-detail" id="map-detail-job"
						data-lat="48.856318" data-lng="2.351866"></div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-apply">
			<div class="modal-dialog ">
				<div class="modal-content">
					<form>
						<div class="modal-header ">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Apply</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Full name</label> <input type="email"
									class="form-control " placeholder="Enter Email">
							</div>
							<div class="form-group">
								<label>Email address</label> <input type="email"
									class="form-control " placeholder="Enter Email">
							</div>
							<div class="form-group">
								<label>Tell us why you better?</label>
								<textarea class="form-control" rows="6"
									placeholder="Something Comment"></textarea>
							</div>
							<div class="form-group">
								<label>Your Resume</label>
								<div class="input-group">
									<span class="input-group-btn"> <span
										class="btn btn-default btn-theme btn-file"> File <input
											type="file">
									</span>
									</span> <input type="text" class="form-control form-flat" readonly>
								</div>
								<small>Upload your CV/resume. Max. file size: 24 MB.</small>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-theme"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success btn-theme">Send
								Application</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>
	
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<!-- Theme JS -->
	<script src="resources/theme/js/theme.js"></script>

	<!-- maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<!-- maps single marker -->
	<script src="resources/theme/js/map-detail.js"></script>
</body>
</html>