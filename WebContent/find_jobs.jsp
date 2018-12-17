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
	<div class="hero-header main-header">
		<div class="inner-hero-header">
			<div class="container ">
				<div class="row">

					<!-- Start form search -->
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
												placeholder=" Skills  " escapeXml="false"></form:input>

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
													style="font-size: 100%; font-family:'Segoe UI','wf_segoe-ui_normal','Arial',sans-serif;">Search</span>
											</button>
											<p class="text-right">
												<a href="#modal-advanced" data-toggle="modal"
													style="color: white;"><span class="fa fa-search-plus"
													style="padding: 5px;"></span><span
													style="font-family:'Segoe UI','wf_segoe-ui_normal','Arial',sans-serif; font-size: 12px;">Advanced
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
												<form:input type="text" class="form-control "
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

										<input type="hidden" value="advancedsearch" name="aid" />

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
						<br />
						<div class="row hidden-xs">
							<div class="col-sm-6 " style="margin-left: 5.5%;">
								<p>
								
								
								
								<a class="btn btn-theme btn-xs btn-default" style="color:#1b1818;font-weight: bold;"><c:out value="${totalJobpost}"></c:out></a>
								
								<%-- <strong  style="background-color:red;">
										<c:out value="${totalJobpost}"></c:out>
										</strong> --%>
										
										
										
										
									<strong class="color-black">Jobs Found</strong>
								</p>
								
							</div>
						</div>
						<c:if test="${empty JobDescriptions.list}">
							<div class="card card-inverse card-warning text-xs-center">
								<div class="card-block" style="background-color: white;">
									<blockquote class="card-blockquote">
										<p class=text-center>We could not find jobs matching your
											search criteria..</p>
										<h6 class=text-center>Did you enter wrong spelling of any
											word?</h6>
										<h6 class=text-center>Only cities/states/country names
											are accepted in location field</h6>
										<h6 class=text-center>You can browse jobs by Functional
											Area, Industry, Company, Skills and Designations</h6>

									</blockquote>
								</div>
							</div>
						</c:if>

						<c:if test="${!empty JobDescriptions.list}">
							<div  style="margin-top: 0%;">
								<c:if test="${not empty message}">
									<div class="error">${message}</div>
								</c:if>

								<c:forEach items="${JobDescriptions.list}" var="searchResult">
									<div class="item box-list box-padding"
										style="background-color: #fafafa; margin-top: 10px;">
										<div class="row ">
											<div class="col-md-1 hidden-sm hidden-xs">
												<div class="img-item">
													<img src="jobImageDisplay.html?id=${searchResult.employerProfile.id}"
														alt="">
												</div>
											</div>
											<div class="col-md-11">
											<c:if test="${not empty searchStatus}">
											<h3 class="no-margin-top list-capitalize">
													<a
														href="search_job_details.html?id=${searchResult.id}&&active=${searchResult.isVisiable}&&disable=${searchResult.isdisable}&&search=${searchStatus}"><c:out
															value="${searchResult.jobTitle}" /> </a>
												</h3>
											</c:if>
											<c:if test="${empty searchStatus}">
												<h3 class="no-margin-top list-capitalize">
													<a
														href="search_job_details.html?id=${searchResult.id}&&active=${searchResult.isVisiable}&&disable=${searchResult.isdisable}"><c:out
															value="${searchResult.jobTitle}" /> </a>
												</h3>
												</c:if>
												<h5>
													<i class="fa fa-building" aria-hidden="true"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong><c:out
															value="${searchResult.companyName}" /></strong></span> - <span
														class="color-black list-capitalize"><c:out
															value="${searchResult.jobLocation}" /></span>
												</h5>
												<h5>
													<i class="fa fa-suitcase"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong>Experience</strong> </span> - <span
														class="color-black list-capitalize"> <c:out
															value="${searchResult.minExp} to ${searchResult.maxExp}"
															escapeXml="false">
														</c:out></span>
												</h5>
												<h5 style="line-height: 20px;text-align: justify;">
													<i class="fa fa-tags"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Key Skills</strong></span> - <span
														class="color-black list-capitalize"><c:out
															value="${searchResult.keywords}" escapeXml="false">
														</c:out></span>
												</h5>

												<h5 style="text-align: justify;">
													<i class="fa fa-file-text"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong>Job Description</strong></span>
													- <span class="color-black list-capitalize"
														style="line-height: 20px;"><c:out
															value="${searchResult.jobDescription}" escapeXml="false"></c:out></span>
												</h5>

												<div>
													<b><span class="color-white-mute"><i
														class="fa fa-calendar-o" style="color: #000;"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong>Expiry Date</strong></span> - <c:out
															value="${searchResult.eDate}" /></span> </b>
															<br><a
														href="#modal-email" data-toggle="modal"
														class="btn btn-theme btn-xs btn-info">Apply by E-mail</a>
													<c:choose>
														<c:when test="${searchResult.isVisiable=='0'}">
															<a class="btn btn-theme btn-xs btn-default">Already
																applied </a>
														</c:when>
														<c:otherwise>
															<a href="applied_jobs.html?id=${searchResult.id}"
																class="btn btn-theme btn-xs btn-default">Apply This
																Job</a>
														</c:otherwise>
													</c:choose>
													<a
														href="search_job_details.html?id=${searchResult.id}&&active=${searchResult.isVisiable}&&disable=${searchResult.isdisable}"
														class="btn btn-theme btn-xs btn-primary">More
														Details...</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<nav style="text-align: center;">
								<ul class="pagination pagination-theme  no-margin">
									<c:if test="${JobDescriptions.currentPage !=1}">
										<li><a aria-label="Previous"
											href="find_jobs.html?page=${JobDescriptions.currentPage - 1}">
												<span aria-hidden="true">&larr;</span>
										</a></li>
									</c:if>
									<li><c:forEach begin="${JobDescriptions.start}"
											end="${JobDescriptions.records}" var="i">
											<c:choose>
												<c:when test="${JobDescriptions.page == i}">
													<a href="find_jobs.html?page=${i}"
														style="color: #fff; background-color: #34495e">${i}</a>
												</c:when>
												<c:otherwise>
													<a href="find_jobs.html?page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach></li>
									<c:if
										test="${JobDescriptions.currentPage lt JobDescriptions.totalPages}">
										<li><a
											href="find_jobs.html?page=${JobDescriptions.currentPage + 1}"><span
												aria-hidden="true">&rarr;</span></a></li>
									</c:if>
								</ul>
							</nav>
						</c:if>
						<br />
					</div>
					<div class="col-md-3" >
						<div style="padding:15px;">						
						<div class="row hidden-xs">
							<div class="col-sm-6">
								<p>
									<strong class="color-black">Sort By:</strong>
								</p>
							</div>
						</div>

							<div class="result-filter box-list"  style="background-color: #fafafa;border:1px solid #e1e1e1;padding:10px;">

								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_2" data-toggle="collapse">Title <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>

								<c:if test="${!empty titleList}">
									<div class="collapse in" id="s_collapse_2">
										<div class="list-area">
											<c:forEach items="${titleList}" var="countCompany">
												<ul class="list-unstyled ">
													<li><a
														href="find_jobs.html?jobtitle=${countCompany.jobTitle}">${countCompany.jobTitle}
															(${countCompany.titleCount})</a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>
								<c:if test="${empty titleList}">
									<div class="collapse in" id="s_collapse_2">
										<div class="list-area">
											<c:forEach items="${JobDescriptions.list}" var="searchResult">
												<ul class="list-unstyled ">
													<li><a
														href="find_jobs.html?jobtitle=${searchResult.jobTitle}">${searchResult.jobTitle}</a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>

								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_3" data-toggle="collapse">Company <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i></a>
								</h5>
								<c:if test="${!empty countCompany}">

									<div class="collapse in" id="s_collapse_3">
										<div class="list-area">
											<c:forEach items="${countCompany}" var="countCompany">

												<ul class="list-unstyled ">
													<li><a
														href="find_jobs.html?companyId=${countCompany.companyId}">${countCompany.companyName}
															(${countCompany.companyCount})</a></li>
													<!-- <li><a href="#" data-toggle="modal"
												data-target="#myModal11">More ... </a></li> -->
												</ul>
											</c:forEach>
										</div>
									</div>

								</c:if>
								<c:if test="${empty countCompany}">

									<div class="collapse in" id="s_collapse_3">
										<div class="list-area">
											<c:forEach items="${JobDescriptions.list}" var="searchResult">
												<ul class="list-unstyled ">
													<li><a href="search_job.html?id=scube Technologies">${searchResult.companyName}</a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>

								<h5 class="font-bold  margin-b-20">
									<a href="#s_collapse_4" data-toggle="collapse"
										class="collapsed">Location <i
										class="fa ic-arrow-toogle fa-angle-right pull-right"></i>
									</a>
								</h5>
								<c:if test="${!empty locationList}">
									<div class="collapse in" id='s_collapse_4'>
										<div class="list-area">
											<c:forEach items="${locationList}" var="countCompany">
												<ul class="list-unstyled ">
													<li><a
														href="find_jobs.html?location=${countCompany.jobLocation}">${countCompany.jobLocation}
															(${countCompany.countlocation}) </a></li>
												</ul>
											</c:forEach>
										</div>
									</div>
								</c:if>

								<c:if test="${empty locationList}">
									<div class="collapse in" id='s_collapse_4'>
										<div class="list-area">
											<c:forEach items="${JobDescriptions.list}" var="searchResult">
												<ul class="list-unstyled ">
													<li><a
														href="find_jobs.html?location=${searchResult.jobLocation}">${searchResult.jobLocation}
													</a></li>

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
	

</body>
</html>