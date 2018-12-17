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
	
		<div class=" main-header">
		<div class="inner-hero-header">
			<div class="container ">
				<div class="row">
					
			<div
				style="border-top: 0px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; background: #000;"
				class="parallax">

				<form:form id="myForm" method="post" class="login-form clearfix"
					action="find_jobs.html" commandName="searchjob">
					<%-- <c:if test="${not empty message}">
						<div class="message red">${message}</div>
					</c:if> --%>


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
							</div></div></div></form:form></div>
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
										<%--  <form:option value="">Select Experience</form:option>
											<form:option value="0">0-1 Year</form:option>
											<form:option value="1">1 Year</form:option>
											<form:option value="2">2 Years</form:option>
										</form:select>--%>
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

		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">

						<!-- box listing -->
						<div class="block-section-sm box-list-area">

							<!-- desc top -->
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



							<div class="box-list col-md-12">
								<c:if test="${!empty Description}">

									<c:forEach items="${Description}" var="searchResult">



										<div class="col-md-3" style="height: 300px">
											<div class="box-testimonial ">
												<p class="desc">
													<strong><a
														href="company_details.html?companyName=${searchResult.companyName}"><c:out
																value="${searchResult.companyName }"></c:out></a></strong><br /> <strong>
														<c:out value="${searchResult.searchElement }"></c:out>
													</strong><br />
													<c:out value="${searchResult.noVacancies }"></c:out>
													<br />
													<%-- <c:out value="${searchResult.contactPerson }"></c:out> --%>

												</p>
												<img src="./resources/theme/images/people/1.jpg" alt="">
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
											<li><a href="search_jobs.html?salary=1000">$1000+</a>
												(16947)</li>
											<li><a href="search_jobs.html?salary=50000">$50,000+</a>
												(13915)</li>
											<li><a href="search_jobs.html?salary=90000">$90,000+</a>
												(9398)</li>
											<li><a href="search_jobs.html?salary=110000">$110,000+</a>
												(4112)</li>
											<li><a href="search_jobs.html?salary=130000">$130,000+</a>
												(1864)</li>
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
											<li><a href="search_jobs.html?jobtype=permanent">Permanent
											</a> (558)</li>
											<li><a href="search_jobs.html?jobtype=parttime">Part-time
											</a> (438)</li>
											<li><a href="search_jobs.html?jobtype=contract">Contract
											</a> (313)</li>
											<li><a href="search_jobs.html?jobtype=internship">Internship</a>
												(169)</li>
											<li><a href="search_jobs.html?jobtype=temporary">Temporary
											</a> (156)</li>
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
											<li><a href="search_jobs.html?jobtitle=javadeveloper">javaDeveloper</a>
											</li>
											<li><a
												href="search_jobs.html?jobtitle=senior Technical Analyst">Senior
													Technical Analyst</a></li>
											<li><a
												href="search_jobs.html?jobtitle=lead HR / Assistance">Lead
													HR / Assistance </a></li>
											<li><a
												href="search_jobs.html?jobtitle=software Engineer - Trainee(Java)">Software
													Engineer - Trainee(Java) </a></li>
											<li><a
												href="search_jobs.html?jobtitle=junior Java Developer">Junior
													Java Developer </a></li>
											<li><a href="" data-toggle="modal"
												data-target="#myModal4">More ... </a></li>
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
											<li><a href="search_jobs.html?id=scube Technologies">Scube
													Technologies</a></li>
											<li><a href="search_jobs.html?id=scube">Scube</a></li>
											<li><a href="search_jobs.html?id=ibm">ibm </a></li>
											<li><a href="search_jobs.html?id=tcs">tcs </a></li>
											<li><a href="search_jobs.html?id=cts">cts </a></li>
											<li><a href="#" data-toggle="modal"
												data-target="#myModal11">More ... </a></li>
										</ul>

									</div>
								</div>
								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_4" data-toggle="collapse"
										class="collapsed">Location <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<div class="collapse in" id='s_collapse_4'>
									<div class="list-area">
										<ul class="list-unstyled ">
											<li><a href="search_jobs.html?location=bangalore">Bangalore
											</a></li>
											<li><a href="search_jobs.html?location=chennai">Chennai
											</a> (438)</li>
											<li><a href="search_jobs.html?location=Delhi">Delhi
											</a> (313)</li>
											<li><a href="search_jobs.html?location=hyderabad">Hyderabad</a>
												(169)</li>
											<li><a href="search_jobs.html?location=trichy">Trichy
											</a> (156)</li>
											<li><a href="#" data-toggle="modal"
												data-target="#myModal3">More ... </a></li>
										</ul>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- modal need login -->
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
	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>
	<!-- <script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="resources/theme/js/theme.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<script src="resources/theme/js/map-detail.js"></script> -->
</body>
</html>