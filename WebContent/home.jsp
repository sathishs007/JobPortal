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
<title>MyJobKart</title>
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">
<link rel="shortcut icon" href="resources/theme/images">
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
	<div id="loader">
		<i class="fa fa-cog fa-4x fa-spin"></i>
	</div>
	<div class="wrapper">
		<header class="main-header">
			<!-- ============ SLIDES START ============ -->
			<div id="slider" class="sl-slider-wrapper" style="max-height: 400px;">

				<div class="sl-slider">
					<div class="sl-slide" data-orientation="horizontal"
						data-slice1-rotation="-25" data-slice2-rotation="-25"
						data-slice1-scale="2" data-slice2-scale="2">
						<div class="sl-slide-inner">
							<div class="bg-img bg-img-1"></div>
							<div class="tint"></div>
							<div class="slide-content">
								<h2>Looking for a job?</h2>
								<h3>There&#39;s no better place to start</h3>
								<p>
									<a href="find_jobs.html" class="btn btn-lg btn-default">Find
										a job</a>
								</p>
							</div>
						</div>
					</div>

					<nav id="nav-arrows" class="nav-arrows">
						<span class="nav-arrow-prev">Previous</span> <span
							class="nav-arrow-next">Next</span>
					</nav>

					<nav id="nav-dots" class="nav-dots">
						<span class="nav-dot-current"></span> <span></span> <span></span>
						<span></span>
					</nav>
				</div>
			</div>
		</header>

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
					action="find_jobs.html" commandName="searchjob">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Advanced Job Search</h4>
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
									<form:input type="text" class="form-control " path="jobTitle"
										placeholder=" Job Title"></form:input>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Key Skills </label>
									<form:input type="text" class="form-control  " path="keywords"
										placeholder=" Key Skills"></form:input>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label> Salary </label>
									<form:input type="text" class="form-control  " path="maxSalary"
										placeholder=" Salary"></form:input>
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

	<!-- ============ COMPANIES START ============ -->

	<section id="">
		<div class="container bg-color2">
			<div class="row">
				<div class="col-md-2 home-first">
					<div class="box-list" style="border: 1px solid #e1e1e1;">
						<h5 class="font-bold margin-b-20"
							style="margin-left: 55px; margin-top: 26px;">Top Employers</h5>

						<div class="row">
							<c:forEach items="${JobDescriptions}" var="searchResult">
								<div class=" col-md-4 clients" style="opacity: 30.6; width: 28%">
									<a
										href="find_jobs.html?companyId=${searchResult.employerRegistion.id}"
										style="border: 1px solid #e1e1e1; padding: 3px; margin-left: 10px; background-color: white;"><img
										src="employerProfileImageDisplay.html?id=${searchResult.employerRegistion.id}"
										style="height: 25px; width: 50px;" alt=""></a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>

				<div class="col-md-7"
					style="margin-left: 10px; padding-right: 0px; padding-left: 0px;">
					<div class="block-section">
						<div class="container">

							<div
								style="background-color: #efefef; overflow: hidden; cursor: pointer;">

								<a class="allSector-padding activet" style="color: #333;"
									href="home.html">All Sector</a> <a
									class="allSector-padding activet" href="home.html?val=A">A</a>
								<a class="allSector-padding activet" href="home.html?val=B">B</a>
								<a class="allSector-padding activet" href="home.html?val=C">C</a>
								<a class="allSector-padding activet" href="home.html?val=D">D</a>
								<a class="allSector-padding activet" href="home.html?val=E">E</a>
								<a class="allSector-padding activet" href="home.html?val=F">F</a>
								<a class="allSector-padding activet" href="home.html?val=G">G</a>
								<a class="allSector-padding activet" href="home.html?val=H">H</a>
								<a class="allSector-padding activet" href="home.html?val=I">I</a>
								<a class="allSector-padding activet" href="home.html?val=J">J</a>
								<a class="allSector-padding activet" href="home.html?val=K">K</a>
								<a class="allSector-padding activet" href="home.html?val=L">L</a>
								<a class="allSector-padding activet" href="home.html?val=M">M</a>
								<a class="allSector-padding activet" href="home.html?val=N">N</a>
								<a class="allSector-padding activet" href="home.html?val=O">O</a>
								<a class="allSector-padding activet" href="home.html?val=P">P</a>
								<a class="allSector-padding activet" href="home.html?val=Q">Q</a>
								<a class="allSector-padding activet" href="home.html?val=R">R</a>
								<a class="allSector-padding activet" href="home.html?val=S">S</a>
								<a class="allSector-padding activet" href="home.html?val=T">T</a>
								<a class="allSector-padding activet" href="home.html?val=U">U</a>
								<a class="allSector-padding activet" href="home.html?val=V">V</a>
								<a class="allSector-padding activet" href="home.html?val=W">W</a>
								<a class="allSector-padding activet" href="home.html?val=X">X</a>
								<a class="allSector-padding activet" href="home.html?val=Y">Y</a>
								<a class="allSector-padding activet" href="home.html?val=Z">Z</a>
							</div>

							<div id="myTabContent"
								class="tab-content flat-tab-content bg-color1">
								<div class="tab-pane fade in active" id="tab0">
									<div class="row" id="modCompany">
										<div class="col-md-4">
											<c:set var="numOfRecords" value="15" />
											<c:forEach items="${companyListVal}" var="companyList">

												<strong style="color: #34495e"><c:out
														value="${companyList.key}" /></strong>
												<c:forEach items="${companyList.value}" var="companyName">

													<c:set var="numOfRows" value="${numOfRows+1}" />

													<li><a
														href="find_jobs.html?companyId=${companyName.companyID.companiesId}"><c:out
																value="${companyName.companyName}" /></a></li>
													<c:if test="${numOfRows == numOfRecords}">
														</ul>
										</div>
										<div class="col-md-4">
											<c:set var="numOfRows" value="0" />
											</c:if>
											<c:if test="${numOfRows != numOfRecords}">
												</ul>
											</c:if>
											</c:forEach>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>


				<div class="col-md-3 home-third">
					<div class="box-list" style="border: 1px solid #e1e1e1;">

						<a href="walkin_jobs.html"
							class="btn btn-theme btn-success btn-block"><strong>Current
								Walk-in's</strong></a>


						<p style="padding: 15px;">
							<i class="fa fa-check-square" aria-hidden="true"></i> Find the
							job walk-in from our clients and get your career.
						</p>

						<a href="feedback.html"
							class="btn btn-theme btn-success btn-block"><strong>Join
								as a recruitment Partner</strong></a>
						<p style="padding: 15px;">
							<i class="fa fa-check-square" aria-hidden="true"></i> We welcome
							recruiters to myjobkart, our recuritment platform shall help you
							to find the right candidates and fulfill your customer needs.</br> <i
								class="fa fa-mobile" aria-hidden="true"></i> 044-42824663
								<br/>
								<a href="feedback.html"
									class="btn btn-sm btn-pill btn-theme btn-line dark" style="float:right;padding: 0px 10px;">Go</a>
						</p>
						

						<a href="feedback.html"
							class="btn btn-theme btn-success btn-block"><strong>Become
								a client</strong></a>


						<p style="padding: 15px;">
							<i class="fa fa-check-square" aria-hidden="true"></i> Our platform is not just
							marketting the jobpost and jobseekers. We offers "One place
							recuriment program" which enable the recuriters to have
							interactive recruitment process with candidates.
							<a href="feedback.html"
									class="btn btn-sm btn-pill btn-theme btn-line dark" style="float:right;padding: 0px 10px;">Go</a>
						</p>

						<a href="create_jobalert.html"
							class="btn btn-theme btn-success btn-block"><strong>Myjobkart
								Referal Program</strong></a>


						<p style="padding: 15px;">
							<i class="fa fa-check-square" aria-hidden="true"></i> Get best
							matched jobs on your email. No registration needed
						</p>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- ============ COMPANIES END ============ -->

	<script type="text/javascript">
		function fun(id) {
			var html = '';

			$.ajax({
				type : "GET",
				url : 'information_technology',
				data : 'id=' + id,

				success : function(companyListVal) {
					$('#modCompany').hide();
					/* html= '<div class="row">'
						   +'<div class="col-md-4">'
					       +'<c:set var="numOfRecords" value="15" />'
					       +'<c:forEach items="'+companyListVal+'" var="companyList">'
					       
						   +'<strong style="color: #34495e">'
					       +'<c:out value="'+companyList.key+'" /></strong>'
								
						   +'<c:forEach items="'+companyList.value+'" var="companyName">'
							
						   +'<c:set var="numOfRows" value="${numOfRows+1}" />'

						   +'<li><a href="search_jobs.html?id=${companyName}"><c:out value="${companyName}" /></a></li>'
										
						   +'<c:if test="${numOfRows == numOfRecords}">'
						   +'</ul>'
					+'</div>'
					+'<div class="col-md-4">'
					+'<c:set var="numOfRows" value="0" />'
					+'</c:if>'
					+'<c:if test="${numOfRows != numOfRecords}">'
					+'</ul>'
					+'</c:if>'
					+'</c:forEach>'
					+'</c:forEach>'
					+'</div>'
					+'</div>'; */
					$('#ajaxCompany').html(html);
					var len = companyList.length;
					alert(len);
				},
			});
		}
	</script>
</body>
</html>