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

<style>
.nav>li>a:focus,.nav>li>a:hover {
	text-decoration: none;
	background-color: #34495e;
	color: white;
	border-radius: 4px 4px 0 0;
}
</style>
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
		</header>

		<!-- Start form search -->
		<div
			style="border-top: 0px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; background: #000;"
			class="parallax"></div>
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
									<div class=" col-md-4 clients" style="opacity: 30.6;width:28%"><a
										href="find_jobs.html?companyId=${searchResult.employerRegistion.id}"
										style="border: 1px solid #e1e1e1; padding: 3px;margin-left:10px;  background-color: white;"><img
										src="employerProfileImageDisplay.html?id=${searchResult.employerRegistion.id}"
										style="height:25px; width:50px;" alt=""></a>
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


								<a class="recruit-padding activet"
									href="view_recruiters.html?val=A">A</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=B">B</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=C">C</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=D">D</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=E">E</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=F">F</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=G">G</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=H">H</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=I">I</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=J">J</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=K">K</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=L">L</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=M">M</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=N">N</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=O">O</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=P">P</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=Q">Q</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=R">R</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=S">S</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=T">T</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=U">U</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=V">V</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=W">W</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=X">X</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=Y">Y</a> <a
									class="recruit-padding activet"
									href="view_recruiters.html?val=Z">Z</a>
							</div>


							<div id="myTabContent"
								class="tab-content flat-tab-content bg-color1">
								<div class="row">
									<c:if test="${not empty errorMessage}">
										<div class="alert alert-success" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>OOPS</strong>
											<c:out value="${errorMessage}"></c:out>
										</div>
									</c:if>
									<div class="col-md-4">
										<c:if test="${not empty companyList }">
											<ul style="list-style-type:none;color:#666;font-size: 12px;">
											
												<c:forEach items="${companyList}" var="company">
													<li><a
														href="find_jobs.html?companyId=${company.companyId}"><c:out value="${company.companyName}" /></a></li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
									<div class="col-md-4">
										<c:if test="${not empty companyListTwo }">
											<ul style="list-style-type:none;color:#666;font-size: 12px;">
											
												<c:forEach items="${companyListTwo}" var="company">
													<li><a
														href="find_jobs.html?companyId=${company.companyId}"><c:out value="${company.companyName}" /></a></li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
									<div class="col-md-4">
										<c:if test="${not empty companyListThree }">
											<ul style="list-style-type:none;color:#666;font-size: 12px;">
											
												<c:forEach items="${companyListThree}" var="company">
													<li><a
														href="find_jobs.html?companyId=${company.companyId}"><c:out value="${company.companyName}" /></a></li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-2 home-third">
					<div class="box-list" style="border: 1px solid #e1e1e1;">

						<div class="result-filter">
							<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
								Search Walk-in jobs</h5>
							<div>

								<p style="padding: 15px;">
									<i class="fa fa-check-square" aria-hidden="true"></i> Myjobkart
									Resume Score - Free Get your FREE resume feedback report and
									know the improvement areas in your resume within 30 seconds
								</p>


								<p style="padding: 15px;">
									<i class="fa fa-check-square" aria-hidden="true"></i> Reach out to
									more recruiters Become a Featured Applicant on Myjobkart and
									Increase your profile views by up to 3 times. Know More.
								</p>
								<p style="padding: 15px;">Call 1800-3010-5557 now!
									(Toll-Free)</p>


								<a href="find_jobs.html"
									class="btn btn-theme btn-success btn-block"><strong>Search
										Walk-in jobs</strong></a>



								<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
									View Recruiters</h5>

								<p style="padding: 15px;">
									<i class="fa fa-check-square" aria-hidden="true"></i> Connect to
									recruiters directly. More than 20000 Recruiters looking for
									candidates like you
								</p>


								<a href="view_recruiters.html"
									class="btn btn-theme btn-success btn-block"><strong>View
										Recruiters</strong></a>




								<h5 class="font-bold  margin-b-20" style="margin-left: 11px;">
									Job Alert</h5>


								<p style="padding: 15px;">
									<i class="fa fa-check-square" aria-hidden="true"></i> Get best
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
	</section>


	<!-- ============ COMPANIES END ============ -->

</body>
</html>