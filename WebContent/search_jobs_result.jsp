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
		<nav class="navbar navbar-default main-navbar hidden-sm hidden-xs">
			<div class="container">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">

					<ul class="nav navbar-nav">
						<li class="active"><a href="find_jobs.html">Find a Job</a></li>
						<li class=""><a href="resume_details.html">Find Resumes</a></li>
						<li class=""><a href="jobseekers_services.html">Post a
								Job</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Pages <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="about_as.html">About Us</a></li>
								<li><a href="contact.html">Contact Us</a></li>
								<li><a href="blog.jsp">Articles &amp; Blog</a></li>
								<li><a href="terms_privacy.jsp">Terms &amp; Privacy</a></li>
								<!--  <li><a href="shortcode.jsp">Short Code</a></li> -->
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Features <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="find_jobs.html">Resume Details</a></li>
								<li><a href="resume_details.html">Job Details</a></li>
								<!-- <li><a href="jobseekers_services.html">Post a Job</a></li> -->
							</ul></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Login<b class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
								<li><a href="employer_sign_in.html">Employer</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Register<b class="caret"></b></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="jobseeker_sign_up.html">Job Seeker</a></li>
								<li><a href="employer_sign_up.html">Employer</a></li>
							</ul></li>
					</ul>

				</div>
			</div>
		</nav>
		<div class="container">
			<nav class="mobile-nav hidden-md hidden-lg">
				<a href="#" class="btn-nav-toogle first"> <span class="bars"></span>
					Menu
				</a>
				<div class="mobile-nav-block">
					<h4>Navigation</h4>
					<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
						Close
					</a>

					<ul class="nav navbar-nav">
						<li class="active"><a href="find_jobs.html">Find a Job</a></li>
						<li class=""><a href="resume_details.html">Find Resumes</a></li>
						<li class=""><a href="jobseekers_services.html">Post a
								Job</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Login<b class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
								<li><a href="employer_sign_in.html">Employer</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Register<b class="caret"></b></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="jobseeker_sign_up.html">Job Seeker</a></li>
								<li><a href="employer_sign_up.html">Employer</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Pages <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="about_as.html">About Us</a></li>
								<li><a href="contact.html">Contact Us</a></li>
								<li><a href="blog.jsp">Articles &amp; Blog</a></li>
								<li><a href="terms_privacy.jsp">Terms &amp; Privacy</a></li>
								<!--  <li><a href="shortcode.jsp">Short Code</a></li> -->
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button">Features <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="find_jobs.html">Resume Details</a></li>
								<li><a href="resume_details.html">Job Details</a></li>
								<!-- <li><a href="jobseekers_services.html">Post a Job</a></li> -->
							</ul></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="hero-header">
			<div class="inner-hero-header">
				<div class="container">
					<div class="row">

						<div class="col-md-12">
							<!-- logo -->
							<div class="logo text-center">
								<a href="index.html"><img
									src="resources/theme/images/logo_myjobkart.png" alt=""></a>
							</div>
						</div>
					</div>
				</div>


				<!-- form search -->
				<form:form id="myForm" method="post" class="job_filters"
					commandName="searchjob">
					<div class=" col-xs-12 ">
						<div class="row">
							<div class=" col-md-3 ">
								<div class="form-group">
									<label class="color-white">Company Name</label>
									<form:input type="text" class="form-control "
										path="companyName" placeholder="Type Company Name  "></form:input>

								</div>
							</div>

							<div class=" col-md-3 ">
								<div class="form-group">
									<label class="color-white">Location</label>


									<form:input type="text" class="form-control  "
										path="searchElement" placeholder=" Job Location"></form:input>

								</div>
							</div>

							<div class=" col-md-3 ">
								<div class="form-group">
									<label class="color-white">Experience</label>
									<form:select class="form-control  " path="minExp">
										<form:option value="">Select experience</form:option>
										<form:option value="0">0-1 Year</form:option>
										<form:option value="1">1 Year</form:option>
										<form:option value="2">2 Years</form:option>
									</form:select>


								</div>
							</div>

							<div class=" col-md-2 ">
								<div class="form-group">
									<label class="hidden-xs">&nbsp;</label>
									<button class="btn btn-theme btn-success btn-block">
										<small>Search</small>
									</button>
									<p class="text-right">
										<a href="#modal-advanced" data-toggle="modal"
											class="fa fa-search-plus">Advanced Search</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</form:form>
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
											<label>companyName</label>
											<form:input type="text" class="form-control "
												path="companyName" placeholder="Type Company Name  "></form:input>
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
									<label>Experience</label>
									<form:select class="form-control  " path="minExp">
										<form:option value="">Select experience</form:option>
										<form:option value="0">0-1 Year</form:option>
										<form:option value="1">1 Year</form:option>
										<form:option value="2">2 Years</form:option>
									</form:select>
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
											<label>Keywords </label>
											<form:input type="text" class="form-control  "
												path="keywords" placeholder=" Keywords"></form:input>
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
	</header>
	<div class="body-content clearfix">
		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="block-section-sm box-list-area">
							<div class="row hidden-xs">
								<div class="col-sm-6  ">
									<p>
										<strong class="color-black">Search jobs Result in
											India</strong>
									</p>
								</div>
								<div class="col-sm-6">
									<p class="text-right">Jobs 1 to 10 of 578</p>
								</div>
							</div>
							<div class="box-list">
								<c:if test="${!empty JobDescription}">
									<c:forEach items="${JobDescription}" var="searchResult">
										<div class="item">
											<div class="row">
												<div class="col-md-1 hidden-sm hidden-xs">
													<div class="img-item">
														<img src="./resources/theme/images/company-logo/1.jpg"
															alt="">
													</div>
												</div>
												<div class="col-md-11">
													<h3 class="no-margin-top">
														<a href="job_post_details.html?id=${searchResult.id} "><c:out
																value="${searchResult.jobTitle}" /> <i
															class="fa fa-link color-white-mute font-1x"></i></a>
													</h3>
													<h5>
														<span class="color-black"><c:out
																value="${searchResult.companyName}" /></span> - <span
															class="color-white-mute"><c:out
																value="${searchResult.jobLocation}" /></span>
													</h5>
													<p class="richtext-word-wrap">
														KeySkills-
														<c:out value="${searchResult.keywords}" escapeXml="false"
														 />
													</p>
													JobDescription-
													<p class="richtext-word-wrap"><c:out value="${searchResult.jobDescription}..." escapeXml="false"></c:out></p>
													<div>
														<span class="color-white-mute"><c:out
																value="${searchResult.created}" /></span> - <a
															href="save_jobs.html" data-toggle="modal"
															data-target="#myModal"
															class="btn btn-xs btn-theme btn-success">save in List</a>
														<a href="#modal-email" data-toggle="modal"
															class="btn btn-theme btn-xs btn-info">Apply by E-mail</a>
														<a href="job_post_details.html?id=${searchResult.id}"
															class="btn btn-theme btn-xs btn-primary">more Details
															...</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>
							<div class="get_alert">
								<h4>
									Get email updates for the latest <span class=" ">PHP
										jobs in United States</span>
								</h4>
								<form>
									<div class="row">
										<div class="col-md-9">
											<div class="form-group">
												<label>My </label> <input class="form-control"
													placeholder="Enter Email">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="hidden-sm hidden-xs ">&nbsp;</label>
												<button class="btn btn-theme btn-success btn-block">Activate</button>
											</div>
										</div>
									</div>
									<small>You can cancel email alerts at any time.</small>
								</form>
							</div>
							<nav class="text-center">
								<ul class="pagination pagination-theme pagination-lg">
									<li><a href="#" aria-label="Previous"> <span>&larr;</span>
									</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#" aria-label="Next"> <span>&rarr;</span>
									</a></li>
								</ul>
							</nav>
						</div>
					</div>
					<div class="col-md-3">
						<div class="block-section-sm side-right">
							<div class="row">
								<div class="col-xs-6">
									<p>
										<strong>Sort by: </strong>
									</p>
								</div>
								<div class="col-xs-6">
									<p class="text-right">
										<strong>Relevance</strong> - <a href="#" class="link-black"><strong>Date</strong></a>
									</p>
								</div>
							</div>

							<div class="result-filter">
								<h5 class="no-margin-top font-bold margin-b-20 ">
									<a href="#s_collapse_1" data-toggle="collapse">Salary
										Estimate <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<div class="collapse in" id="s_collapse_1">
									<div class="list-area">
										<ul class="list-unstyled">
											<li><a href="#">$50,000+</a> (16947)</li>
											<li><a href="#">$70,000+</a> (13915)</li>
											<li><a href="#">$90,000+</a> (9398)</li>
											<li><a href="#">$110,000+</a> (4112)</li>
											<li><a href="#">$130,000+</a> (1864)</li>
										</ul>
									</div>
								</div>

								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_5" data-toggle="collapse">Job Type <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>
								<div class="collapse in" id='s_collapse_5'>
									<div class="list-area">
										<ul class="list-unstyled ">
											<li><a href="#">Full-time </a> (558)</li>
											<li><a href="#">Part-time </a> (438)</li>
											<li><a href="#">Contract </a> (313)</li>
											<li><a href="#">Internship</a> (169)</li>
											<li><a href="#">Temporary </a> (156)</li>
										</ul>

									</div>
								</div>


								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_2" data-toggle="collapse">Title <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>
								<div class="collapse in" id="s_collapse_2">
									<div class="list-area">
										<ul class="list-unstyled ">
											<li><a href="#">web developer</a> (558)</li>
											<li><a href="#">PHP Developer</a> (438)</li>
											<li><a href="#">Software Engineer </a> (313)</li>
											<li><a href="#">Senior Software Engineer </a> (169)</li>
											<li><a href="#">Front End Developer </a> (156)</li>
											<li><a href="#">More ... </a></li>
										</ul>

									</div>
								</div>
								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_3" data-toggle="collapse">Company <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>
								<div class="collapse in" id="s_collapse_3">
									<div class="list-area">
										<ul class="list-unstyled ">
											<li><a href="#">Unlisted Company</a> (558)</li>
											<li><a href="#">CyberCoders</a> (438)</li>
											<li><a href="#">Smith & Keller </a> (313)</li>
											<li><a href="#">Robert Half Technology </a> (169)</li>
											<li><a href="#">Jobspring Partners </a> (156)</li>
											<li><a href="#">More ... </a></li>
										</ul>

									</div>
								</div>
								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_4" data-toggle="collapse"
										class="collapsed">Location <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<div class="collapse" id='s_collapse_4'>
									<div class="list-area">
										<ul class="list-unstyled ">
											<li><a href="#">New York, NY </a> (558)</li>
											<li><a href="#">San Francisco, CA </a> (438)</li>
											<li><a href="#">Washington, DC </a> (313)</li>
											<li><a href="#">Chicago, IL</a> (169)</li>
											<li><a href="#">Austin, TX </a> (156)</li>
											<li><a href="#">More ... </a></li>
										</ul>

									</div>
								</div>
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
		<div class="modal fade" id="modal-email">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<form>
						<div class="modal-header ">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Send this job to yourself or a
								friend:</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>From my email address</label> <input type="email"
									class="form-control " placeholder="Enter Email">
							</div>
							<div class="form-group">
								<label>To email address</label> <input type="email"
									class="form-control " placeholder="Enter Email">
							</div>

							<div class="form-group">
								<label>Comment (optional)</label>
								<textarea class="form-control" rows="6"
									placeholder="Something Comment"></textarea>
							</div>
							<div class="checkbox flat-checkbox">
								<label> <input type="checkbox"> <span
									class="fa fa-check"></span> Send a copy to my email address?
								</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-theme"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success btn-theme">Send</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal"
		style="width: 50%; margin-left: 25%">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>
	<script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="resources/theme/js/theme.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<script src="resources/theme/js/map-detail.js"></script>

</body>
</html>