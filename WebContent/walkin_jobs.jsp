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
		<div class="bg-color2">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<br />
						<c:if test="${empty JobDescription.list}">
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

						<c:if test="${!empty JobDescription.list}">
						<div class="box-list" style="margin-top: 0%; margin-left: 52px;">
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>

							<c:forEach items="${JobDescription.list}" var="searchResult">
								<div class="item"
									style="background-color: #fafafa; margin-top: 10px;">
									<div class="row">
										<div class="col-md-1 hidden-sm hidden-xs">
											<div class="img-item">
												<img src="jobImageDisplay.html?id=${searchResult.id}" alt="">
											</div>
										</div>
										<div class="col-md-11">
											<h3 class="no-margin-top list-capitalize">
												
													<a
													href="walkin_search_details.html?id=${searchResult.id}"><c:out
														value="${searchResult.jobTitle}" /></a> 
											</h3>
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
											<h5>
												<i class="fa fa-tags"></i> <span
														class=" color-white-mute list-capitalize" style="font-size: 14px;"><strong>Key Skills</strong></span> - <span
													class="color-black list-capitalize" style="line-height: 20px;"><c:out
														value="${searchResult.keywords}" escapeXml="false">
													</c:out></span>
											</h5>

											<h5>
												<i class="fa fa-file-text"></i> <span
														class="color-white-mute list-capitalize" style="font-size: 14px;"><strong>Job Description</strong></span>
												- <span class="color-black list-capitalize"
													style="line-height: 20px;"><c:out
														value="${searchResult.jobDescription}" escapeXml="false"></c:out></span>
											</h5>
											
											<div>
													<a
														href="walkin_search_details.html?id=${searchResult.id}"
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
									<c:if test="${JobDescription.currentPage !=1}">
										<li><a aria-label="Previous"
											href="walkin_jobs.html?page=${JobDescription.currentPage - 1}">
												<span aria-hidden="true">&larr;</span>
										</a></li>
									</c:if>
									<li><c:forEach begin="${JobDescription.start}"
											end="${JobDescription.records}" var="i">
											<c:choose>
												<c:when test="${JobDescription.page == i}">
													<a href="walkin_jobs.html?page=${i}"
														style="color: #fff; background-color: #34495e">${i}</a>
												</c:when>
												<c:otherwise>
													<a href="walkin_jobs.html?page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach></li>
									<c:if
										test="${JobDescription.currentPage lt JobDescription.totalPages}">
										<li><a
											href="walkin_jobs.html?page=${JobDescription.currentPage + 1}"><span
												aria-hidden="true">&rarr;</span></a></li>
									</c:if>
								</ul>
							</nav>
						
					</c:if>
						<br />
					</div>
					<div class="col-md-3" >
						<div style="padding:15px;">						
						<div class="box-list" style="border: 1px solid #e1e1e1;">

						<div class="result-filter">
							<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
								Search Walk-in jobs</h5>
							<div>

								<p style="padding: 15px;">
									<i class="fa fa-square-o" aria-hidden="true"></i> Myjobkart
									Resume Score - Free Get your FREE resume feedback report and
									know the improvement areas in your resume within 30 seconds
								</p>


								<p style="padding: 15px;">
									<i class="fa fa-square-o" aria-hidden="true"></i> Reach out to
									more recruiters Become a Featured Applicant on Myjobkart and
									Increase your profile views by up to 3 times. Know More.
								</p>
								<p style="padding: 15px;">Call 1800-3010-5557 now!
									(Toll-Free)</p>


								<a href="walkin_jobs.html"
									class="btn btn-theme btn-success btn-block"><strong>Search
										Walk-in jobs</strong></a>



								<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
									View Recruiters</h5>

								<p style="padding: 15px;">
									<i class="fa fa-square-o" aria-hidden="true"></i> Connect to
									recruiters directly. More than 20000 Recruiters looking for
									candidates like you
								</p>


								<a href="find_jobs.html"
									class="btn btn-theme btn-success btn-block"><strong>View
										Recruiters</strong></a>




								<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
									Job Alert</h5>


								<p style="padding: 15px;">
									<i class="fa fa-square-o" aria-hidden="true"></i> Get best
									matched jobs on your email. No registration needed
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

</body>
</html>