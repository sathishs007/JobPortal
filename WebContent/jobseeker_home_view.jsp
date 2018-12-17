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
	<div class="wrapper">
		<header class="main-header ">
			<%-- <nav class="navbar navbar-default main-navbar hidden-sm hidden-xs">
				<div class="container">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class=""><a href="find_jobs.html"><normal>Find
									a Job</normal></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button">Pages <span
									class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="jobseeker_about_as.html">About Us</a></li>
									<li><a href="jobseeker_contact.html">Contact Us</a></li>
									<li><a href="jobseeker_blog.html">Articles & Blog</a></li>
									
									<li><a href="jobseeker_terms_privacy.html">Terms & Privacy</a></li>
									
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button">Features <span
									class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="find_jobs.html">Job Details</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#"
								class="link-profile dropdown-toggle" data-toggle="dropdown">
									<img src="imageLoginDisplay.html?id=${registerId}" alt=""
									class="img-profile"> &nbsp; ${name} <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="jobseeker_home.html"> My Home </a></li>
									<li><a href="my_notifications_jobseeker.html">
											Notifications <span class="badge ">1</span>
									</a></li>
									<li><a href="jobseeker_change_password_after_login.html">
											Change Password</a></li>

								</ul></li>
							<li class="link-btn"><a href="logout.html"><span
									class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
						</ul>
					</div>
				</div>
			</nav> --%>
			<%-- <div class="container">
				<nav class="mobile-nav hidden-md hidden-lg">
					<ul class="nav navbar-nav nav-block-left">
						<li class="dropdown"><a href="#"
							class="link-profile dropdown-toggle" data-toggle="dropdown">
								<img src="imageLoginDisplay.html?id=${registerId}" alt=""
								class="img-profile"> &nbsp; ${name} <b class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="jobseeker_home.html"> My Home </a></li>
								<li><a href="my_notifications_jobseeker.html">
										Notifications <span class="badge ">5</span>
								</a></li>
								<li><a href="jobseeker_change_password_after_login.html">
										Change Password</a></li>
								<li><a href="logout.html"> Logout</a></li>
							</ul></li>
					</ul>

					<a href="#" class="btn-nav-toogle first"> <span class="bars"></span>
						Menu
					</a>
					<div class="mobile-nav-block">
						<h4>Navigation</h4>
						<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
							Close
						</a>

						<ul class="nav navbar-nav">
							<li class=""><a href="find_jobs.html">Find a Jobs</a></li>
							

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button">Pages <span
									class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="jobseeker_about_as.html">About Us</a></li>
									<li><a href="jobseeker_contact.html">Contact Us</a></li>
									<li><a href="jobseeker_blog.html">Articles & Blog</a></li>
									<li><a href="jobseeker_terms_privacy.html">Terms & Privacy</a></li>
											
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button">Features <span
									class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="find_jobs.html">Find Jobs</a></li>

								</ul></li>
						</ul>
					</div>
				</nav>
			</div> --%>
			<div class="hero-header">

				<div class="inner-hero-header">
					<div class="container">
						<div class="text-center logo">
							<a href="jobseeker_home_view.html"><img
								src="resources/theme/images/logo_myjobkart.png" alt=""></a>
						</div>

						<div class="relative">
							<i class="fa fa-globe ic-fade-globe"></i>
							<!-- form search -->
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>
							<form:form id="myForm" method="post" class="form-search-home"
								commandName="searchjob">

								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Name</label>


											<form:input type="text" class="form-control input-lg"
												path="companyName" placeholder="Company Name  "></form:input>
											<form:errors path="companyName" cssClass="error" />
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Location</label>
											<form:input type="text" class="form-control  input-lg"
												path="searchElement" placeholder=" Job Location"></form:input>

										</div>
									</div>
									<div class="col-md-4">
										<label>Experience</label>
										<div class="form-group">

											<form:select class="form-control  input-lg" path="minExp">
												<form:option value="">Select Experience</form:option>
												<form:option value="0">0-1 Year</form:option>
												<form:option value="1">1 Year</form:option>
												<form:option value="2">2 Years</form:option>
											</form:select>

										</div>
									</div>
								</div>
								<div class="form-group">
									<button
										class="btn btn-t-primary btn-lg btn-theme btn-pill btn-block">Find
										a Jobs</button>
								</div>
								<div class="text-center">
									<a href="#modal-advanced" data-toggle="modal">Advanced Job
										Search</a>
								</div>
							</form:form>
							<!-- end form search -->
						</div>
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
												<label>Key words </label>
												<form:input type="text" class="form-control  "
													path="keywords" placeholder=" Key words"></form:input>
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
			<!-- box simple static -->
			<div class="block-section bg-color1">
				<div class="container">
					<div class="row text-center">
						<div class="col-md-4">
							<c:if test="${!empty registrationCount}">
								<h3 class="font-2x ">${registrationCount}</h3>
								<h4 class="color-text">Registered Member</h4>
							</c:if>
						</div>
						<div class="col-md-4">
							<c:if test="${!empty jobPostCount}">
								<h3 class="font-2x ">${jobPostCount}</h3>
								<h4 class="color-text">Joblist Posted</h4>
							</c:if>
						</div>
						<div class="col-md-4">
							<c:if test="${!empty companyCount}">
								<h3 class="font-2x ">${companyCount}</h3>
								<h4 class="color-text">Awesome Company</h4>
							</c:if>
						</div>
					</div>
				</div>
				<hr />
				<div class="container text-center">
					<h2>Featured Company</h2>
				</div>
				<div class="bg-color2 block-section line-bottom">

					<marquee>

						<div class="container text-center clients " style="width: 100%;">
							<%-- 	<c:if test="${!empty bannerList}">
								<c:forEach items="${bannerList}" var="searchResult">
									<a href="#"><img
										src="bannerImage.html?id=${searchResult.bannerId}" alt=""></a>
 --%>
							<a href="#"><img src="./resources/theme/images/patner/2.png"
								alt=""></a> <a href="#"><img
								src="./resources/theme/images/patner/3.png" alt=""></a> <a
								href="#"><img src="./resources/theme/images/patner/4.png"
								alt=""></a> <a href="#"><img
								src="./resources/theme/images/patner/5.png" alt=""></a> <a
								href="#"><img src="./resources/theme/images/patner/4.png"
								alt=""></a> <a href="#"><img
								src="./resources/theme/images/patner/5.png" alt=""></a>

						</div>
					</marquee>
				</div>
				<hr />
				<div class="bg-color2 block-section line-bottom">
					<div class="container text-center">
						<h2>Featured Profiles</h2>

						<div class="white-space-30"></div>
						<c:if test="${!empty searchJobseeker}">

							<c:forEach items="${searchJobseeker}" var="searchResult">

								<div class=" col-md-2">
									<div class="job-listing-box">
										<figure class="job-listing-img"
											>
											<a href=""><img style="width: 100px; height: 100px; padding: 10px; vertical-align: middle; margin-left: 20px;"
												src="imageDisplay.html?id=${searchResult.id}" alt=""></a>
										</figure>
										<div class="job-listing-body">
											<h5 class="name">
												<a href="find_resumes_details.html?id=${searchResult.id}"><b><c:out
														value="${searchResult.profiledescription }"></c:out></b></a>
											</h5>
										</div>
										<footer class="job-listing-footer">
											<h6>
												<c:out value="${searchResult.firstName }"></c:out>
											</h6>

										</footer>
									</div>
								</div>


							</c:forEach>
						</c:if>
					</div>
				</div>
				<br />
				<div class="bg-color21 block-section line-bottom">
					<div class="container text-center">
						<h2>Featured Jobs</h2>
						<div class="white-space-30"></div>
						<c:if test="${!empty JobDescription}">
							<c:forEach items="${JobDescription}" var="searchResult">
								<div class="col-md-2">
									<div class="box-testimonial">
										<p class="desc">
											<strong><a
												href="search_job_details.html?id=${searchResult.id}"><c:out
														value="${searchResult.companyName }"></c:out></a></strong><br /> <strong>
												<c:out value="${searchResult.jobTitle }"></c:out>
											</strong><br />
											<c:out value="${searchResult.contactNo }"></c:out>
											<br />
											<c:out value="${searchResult.contactPerson }"></c:out>
										</p>
										<img src="jobImageDisplay.html?id=${searchResult.id}" alt="">
									</div>
								</div>

							</c:forEach>
						</c:if>

					</div>

				</div>
				<br />

				<div class="block-section bg-color2 line-bottom" id="sh-tabs">
					<div class="container">
						<div class="row">
							<div class="col-lg-12 ">
								<div class="container text-center">
									<h2>Featured Category</h2>
								</div>
								<ul id="myTab" class="nav nav-tabs flat-nav-tabs" role="tablist">
									<li class="active"><a href="#tab0" role="tab"
										data-toggle="tab">Job By Experience</a></li>
									<li><a href="#tab1" role="tab" data-toggle="tab">Job
											By Industry</a></li>
									<li><a href="#tab2" role="tab" data-toggle="tab">Job
											By Skills</a></li>
									<li><a href="#tab3" role="tab" data-toggle="tab">Job
											By Location</a></li>

								</ul>

								<div id="myTabContent"
									class="tab-content flat-tab-content bg-color1">
									<div class="tab-pane fade in active" id="tab0">
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Experience in
														IT/Software</strong>
													<li><a href="find_jobs.html">0-Years</a></li>
													<li><a href="find_jobs.html">1-Years</a></li>
													<li><a href="find_jobs.html">2-Years</a></li>
													<li><a href="find_jobs.html">3-Years</a></li>
													<li><a href="find_jobs.html">4-Years</a></li>
													<li><a href="find_jobs.html">5-Years</a></li>

												</ul>
											</div>

											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Experience in
														Banking Domain</strong>
													<li><a href="find_jobs.html">0-Years</a></li>
													<li><a href="find_jobs.html">1-Years</a></li>
													<li><a href="find_jobs.html">2-Years</a></li>
													<li><a href="find_jobs.html">3-Years</a></li>
													<li><a href="find_jobs.html">4-Years</a></li>
													<li><a href="find_jobs.html">5-Years</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Experience in
														Networking </strong>
													<li><a href="find_jobs.html">0-Years</a></li>
													<li><a href="find_jobs.html">1-Years</a></li>
													<li><a href="find_jobs.html">2-Years</a></li>
													<li><a href="find_jobs.html">3-Years</a></li>
													<li><a href="find_jobs.html">4-Years</a></li>
													<li><a href="find_jobs.html">5-Years</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Experience in
														Physician Domain</strong>
													<li><a href="find_jobs.html?exp=0,1,physician">0-Years</a></li>
													<li><a href="find_jobs.html?exp=1,2,physician">1-Years</a></li>
													<li><a href="find_jobs.html?exp=2,3,physician">2-Years</a></li>
													<li><a href="find_jobs.html?exp=3,4,physician">3-Years</a></li>
													<li><a href="find_jobs.html?exp=4,5,physician">4-Years</a></li>
													<li><a href="find_jobs.html?exp=5,12,physician">5-Years</a></li>

												</ul>
											</div>
										</div>
									</div>
									<div class="tab-pane fade" id="tab1">
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Information
														Technology</strong>
													<li><a href="find_jobs.html?id=ibm">IBM</a></li>
													<li><a href="find_jobs.html?id=tcs">TCS</a></li>
													<li><a href="find_jobs.html?id=cts">CTS</a></li>
													<li><a href="find_jobs.html?id=wipro">Wipro</a></li>
													<li><a href="find_jobs.html?id=ebintel">Ebintel</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Telecom/ Internet</strong>
													<li><a
														href="find_jobs.html?id=adtran networks india private limited">Adtran
															Networks India Private Limited</a></li>
													<li><a href="find_jobs.html?id=aircel">AIRCEL</a></li>
													<li><a href="find_jobs.html?id=aricent">ARICENT</a></li>
													<li><a href="find_jobs.html?id=eci telecom">ECI
															TELECOM</a></li>
													<li><a href="find_jobs.html?id=lg soft india">LG
															Soft India</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Product Software</strong>
													<li><a href="find_jobs.html?id=3d plm">3D PLM</a></li>
													<li><a href="find_jobs.html?id=akmai">Akamai</a></li>
													<li><a href="find_jobs.html?id=emc2">EMC2</a></li>
													<li><a
														href="find_jobs.html?id=fis global commercial services">FIS
															Global Commercial Services</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Manufacturing</strong>
													<li><a href="find_jobs.html?id=anand automotive">Anand
															Automotive</a></li>
													<li><a href="find_jobs.html?id=atlas copco">Atlas
															Copco</a></li>
													<li><a href="find_jobs.html?id=claas india">CLAAS
															INDIA</a></li>
													<li><a href="find_jobs.html?id=daimler india">Daimler
															India</a></li>
												</ul>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Realestate/Constr</strong>
													<li><a href="find_jobs.html?id=abil group">ABIL
															Group</a></li>
													<li><a
														href="find_jobs.html?id=amarprkash developers pvt ltd">AMARPRAKASH
															DEVELOPERS PVT LTD</a></li>
													<li><a href="find_jobs.html?id=desai const pvt ltd">Desai
															Const Pvt Ltd</a></li>
													<li><a
														href="find_jobs.html?id=imperia structures limited">Imperia
															Structures Limited</a></li>
													<li><a href="find_jobs.html?id=vascon engineers ltd">Vascon
															Engineers Ltd.</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Healthcare/Pharma</strong>
													<li><a
														href="find_jobs.html?id=alembic pharmaceuticals ltd">Alembic
															Pharmaceuticals Ltd.</a></li>
													<li><a href="find_jobs.html?id=apotex">Apotex</a></li>
													<li><a href="find_jobs.html?id=baxter">Baxter</a></li>
													<li><a href="find_jobs.html?id=columbia asia">Columbia
															Asia</a></li>
													<li><a href="find_jobs.html?id=micro labs">MICRO
															LABS</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Finance/Insurance</strong>
													<li><a
														href="find_jobs.html?id=apollo munich health ins">Apollo
															Munich Health Ins.</a></li>
													<li><a href="find_jobs.html?id=bajaj capital ltd">Bajaj
															Capital Ltd.</a></li>
													<li><a href="find_jobs.html?id=muthoot finance">Muthoot
															Finance</a></li>
													<li><a href="find_jobs.html?id=sbi life insurance">SBI
															Life Insurance</a></li>
													<li><a href="find_jobs.html?id=tata capital">Tata
															Capital</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Education/ Media</strong>
													<li><a href="find_jobs.html?id=fiitjee">FIITJEE</a></li>
													<li><a href="find_jobs.html?id=frankfinn institute">Frankfinn
															Institute</a></li>
													<li><a
														href="find_jobs.html?id=lovely professional university">Lovely
															professional University</a></li>
													<li><a href="find_jobs.html?id=time inc.india">Time
															Inc. India</a></li>
												</ul>
											</div>


										</div>
									</div>
									<div class="tab-pane fade" id="tab2">
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Developing Skills</strong>
													<li><a href="find_jobs.html?skills=java">Java</a></li>
													<li><a href="find_jobs.html?skills=c">C</a></li>
													<li><a href="find_jobs.html?skills=c++">C++</a></li>
													<li><a href="find_jobs.html?skills=.net">.Net</a></li>
													<li><a
														href="find_jobs.html?skills=android developer">Android
															Developer</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Fream Work Skills</strong>
													<li><a href="find_jobs.html?skills=jsf">JSF</a></li>
													<li><a href="find_jobs.html?skills=spring">Spring</a></li>
													<li><a href="find_jobs.html?skills=hibernet">Hibernet</a></li>
													<li><a href="find_jobs.html?skills=php">PHP</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Design Skills</strong>
													<li><a href="find_jobs.html?skills=autocad">AutoCAD</a></li>
													<li><a href="find_jobs.html?skills=pcb board design">PCB
															Board Design </a></li>
													<li><a href="find_jobs.html?skills=catia">CATIA</a></li>
													<li><a href="find_jobs.html?skills=pro-e">pro-e</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Network Skills</strong>
													<li><a href="find_jobs.html?skills=network engineer">Network
															Engineer</a></li>
													<li><a href="find_jobs.html?skills=system admin">System
															Admin </a></li>
													<li><a
														href="find_jobs.html?skills=computer and networking security">
															Computer and Networking Security</a></li>
													<li><a
														href="find_jobs.html?skills=digital communications">Digital
															Communications</a></li>


												</ul>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Banking Skills</strong>
													<li><a href="find_jobs.html?skills=accounts dep">Accounts
															Dep</a></li>
													<li><a href="find_jobs.html?skills=finance dep">Finance
															Dep</a></li>
													<li><a href="find_jobs.html?skills=salaes manager">Salaes
															Manager</a></li>
													<li><a href="find_jobs.html?skills=cleark">Cleark</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Data Base Skills </strong>
													<li><a href="find_jobs.html?skills=oracle">Oracle</a></li>
													<li><a href="find_jobs.html?skills=mysql">Mysql</a></li>
													<li><a href="find_jobs.html?skills=sybase">SYbase</a></li>
													<li><a href="find_jobs.html?skills=fox pro">Fox
															Pro</a></li>

												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">Physician Skills </strong>
													<li><a href="find_jobs.html?skills=md">MD-(Doctor
															of Medicine)</a></li>
													<li><a href="find_jobs.html?skills=ms">MS
															-(Master of Surgery)</a></li>
													<li><a href="find_jobs.html?skills=dm">DM
															-(Doctor of Medicine)</a></li>
													<li><a href="find_jobs.html?skills=mch">MCh
															-(Master of Chirurgical)</a></li>

												</ul>
											</div>

										</div>
									</div>
									<div class="tab-pane fade" id="tab3">
										<div class="row">
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">JOB in India</strong>
													<li><a href="find_jobs.html?location=bangalore">Bangalore</a></li>
													<li><a href="find_jobs.html?location=chennai">Chennai</a></li>
													<li><a href="find_jobs.html?location=delhi">Delhi</a></li>
													<li><a href="find_jobs.html?location=hyderabad">Hyderabad</a></li>
													<li><a href="find_jobs.html?location=mysore">Mysore</a></li>
													<li><a href="find_jobs.html?location=trichy">Trichy</a></li>
													<li><a href="find_jobs.html?location=coimbatore">Coimbatore</a></li>
													<li><a href="find_jobs.html?location=pune">Pune</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">JOB in UK</strong>
													<li><a href="find_jobs.html?location=bath"> Bath</a></li>
													<li><a href="find_jobs.html?location=birmingham">Birmingham</a></li>
													<li><a href="find_jobs.html?location=bradford">Bradford</a></li>
													<li><a href="find_jobs.html?location=canterbury">Canterbury</a></li>
													<li><a href="find_jobs.html?location=manchester">Manchester</a></li>
													<li><a href="find_jobs.html?location=nottingham">Nottingham</a></li>
													<li><a href="find_jobs.html?location=lisburn">Lisburn</a></li>
													<li><a href="find_jobs.html?location=newry">Newry</a></li>
												</ul>
											</div>

											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">JOB in Nigeria</strong>
													<li><a href="find_jobs.html?location=anambra">
															Anambra</a></li>
													<li><a href="find_jobs.html?location=adamawa">Adamawa</a></li>
													<li><a href="find_jobs.html?location=edo">Edo</a></li>
													<li><a href="find_jobs.html?location=delta">Delta</a></li>
													<li><a href="find_jobs.html?location=kogi">Kogi</a></li>
													<li><a href="find_jobs.html?location=jigawa">Jigawa</a></li>
													<li><a href="find_jobs.html?location=sokoto">Sokoto</a></li>
													<li><a href="find_jobs.html?location=zamfara">Zamfara</a></li>
												</ul>
											</div>
											<div class="col-md-3">
												<ul title="">
													<strong style="color: #34495e">JOB in Australia</strong>
													<li><a href="find_jobs.html?location=sydney">
															Sydney</a></li>
													<li><a href="find_jobs.html?location=kingston">Kingston</a></li>
													<li><a href="find_jobs.html?location=melbourne">Melbourne</a></li>
													<li><a href="find_jobs.html?location=perth">Perth</a></li>
													<li><a href="find_jobs.html?location=adelaide">Adelaide</a></li>
													<li><a href="find_jobs.html?location=canberra">Canberra</a></li>
													<li><a href="find_jobs.html?location=hobart">Hobart</a></li>
													<li><a href="find_jobs.html?location=darwin">Darwin</a></li>
												</ul>
											</div>

										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<!-- jQuery Bootstrap -->
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<!-- Lightbox -->
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