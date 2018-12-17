<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>



	<!-- job seeker header start -->
	<c:if test="${userType eq 'Employer'}">


		<header class="main-header"> <!-- main navbar --> <nav
			class="navbar navbar-default main-navbar hidden-sm hidden-xs">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li class=""><a href="home.html"><strong><span
								style="" class="header-logo"><i class="fa fa-check"
									style="font-size: 17px;"></i></span>MyJobKart.com</strong></a></li>
				</ul>
				<ul class="nav navbar-nav header-user">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_resumes.html"><strong>Find
								Resumes</strong></a></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- <li><a href="employer_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="employer_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<!--  <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" ><strong>Features</strong> <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
						<li><a href="find_resumes.html">Resume Details</a></li>
						<li><a href="find_jobs.html">Job Details</a></li>
						<li><a href="product_services.html">Post a Job</a></li>

					</ul></li>
                </li> -->
				</ul>
				<!--Employeer logout start  -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
							src="imageEmployerLoginDisplay.html?id=${employerRegister}"
							alt="" class="img-profile">&nbsp;<b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_home.html"> My Home </a></li>
							<li><a href="my_notifications.html"> Notifications <span
									class="badge ">1</span></a></li>
							<li><a href="employer_change_password_after_login.html">
									Change Password</a></li>

						</ul></li>
					<li class="link-btn"><a href="logout.html"><span
							class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
				</ul>
				<!--Employeer logout End  -->
			</div>
		</div>
		</nav><!-- end main navbar --> <!-- mobile navbar -->
		<div class="container">
			<nav class="mobile-nav hidden-md hidden-lg"> <a href="#"
				class="btn-nav-toogle first"> <span class="bars"></span> Menu
			</a>


			<ul class="nav navbar-nav nav-block-left">
				<li class="dropdown"><a href="#"
					class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
						src="imageEmployerLoginDisplay.html?id=${employerRegister}"
						alt="" class="img-profile">&nbsp;<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="employer_home.html"> My Home </a></li>
						<li><a href="my_notifications.html"> Notifications <span
								class="badge ">1</span></a></li>
						<li><a href="employer_change_password_after_login.html">
								Change Password</a></li>
						<li><a href="logout.html"> Logout</a></li>
					</ul></li>
			</ul>
			<div class="mobile-nav-block">
				<h4>Navigation</h4>
				<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
					Close
				</a>

				<ul class="nav navbar-nav">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_resumes.html"><strong>Find
								Resumes</strong></a></li>
								
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- <li><a href="employer_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="employer_terms_privacy.html">Terms &amp;
									Privacy</a></li>
									<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Features</strong>
							<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="find_resumes.html">Resume Details</a></li>
							<li><a href="find_jobs.html">Job Details</a></li>
						</ul></li>
				</ul>
			</div>
			</nav>
		</div>
		<!-- mobile navbar --> </header>
	</c:if>
	<!-- job seeker header end -->

	<!--Employeer header start  -->
	<c:if test="${userType eq 'Jobseeker'}">
		<header class="main-header"> <!-- main navbar --> <nav
			class="navbar navbar-default main-navbar hidden-sm hidden-xs">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li class=""><a href="home.html"><strong><span
								style="" class="header-logo"><i class="fa fa-check"
									style="font-size: 17px;"></i></span>MyJobKart.com</strong></a></li>
				</ul>
				<ul class="nav navbar-nav header-user">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class="text-center"><a href="find_jobs.html?search=true"><strong>Find
								a Job</strong></a></li>
								
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="jobseeker_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- <li><a href="jobseeker_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="jobseeker_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<!-- <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" ><strong>Features</strong> <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                   <li><a href="find_jobs.html">Job Details</a></li>
                  </ul>
                </li> -->
				</ul>
				<c:if test="${!empty jobseekerProfileId}">
				
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
							src="showimage.html?id=${jobseekerProfileId}" alt=""
							class="img-profile"> &nbsp;<b class="caret"></b></a>
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
				</c:if>
				
				<c:if test="${empty jobseekerProfileId}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
							src="imageLoginDisplay.html?id=${registerId}" alt=""
							class="img-profile"> &nbsp;<b class="caret"></b></a>
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
				</c:if>
				
				
				
			</div>
		</div>
		</nav><!-- end main navbar --> <!-- mobile navbar -->
		<div class="container">
			<nav class="mobile-nav hidden-md hidden-lg"> <a href="#"
				class="btn-nav-toogle first"> <span class="bars"></span> Menu
			</a>

			<ul class="nav navbar-nav nav-block-left">
				<li class="dropdown"><a href="#"
					class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
						src="imageLoginDisplay.html?id=${registerId}" alt=""
						class="img-profile"> &nbsp;<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="jobseeker_home.html"> My Home </a></li>
						<li><a href="my_notifications_jobseeker.html">
								Notifications <span class="badge ">1</span>
						</a></li>
						<li><a href="jobseeker_change_password_after_login.html">
								Change Password</a></li>
						<li><a href="logout.html"> Logout</a></li>
					</ul></li>
			</ul>

			<div class="mobile-nav-block">
				<h4>Navigation</h4>
				<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
					Close
				</a>

				<ul class="nav navbar-nav">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_jobs.html"><strong>Find
								a Job</strong></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="jobseeker_about_as.html">About Us</a></li>
							<li><a href="jobseeker_contact.html">Contact Us</a></li>
							<!-- 	<li><a href="jobseeker_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="jobseeker_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Features</strong>
							<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="find_jobs.html">Job Details</a></li>
						</ul></li>
				</ul>
			</div>
			</nav>
		</div>
		<!-- mobile navbar --> </header>
	</c:if>
	<!--Employeer header end  -->

	<!--main page header  start-->
	<c:if test="${empty userType}">
		<header class="main-header "> <!-- main navbar --> <nav
			class="navbar navbar-default main-navbar hidden-sm hidden-xs">
		<div class="container ">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div></div>

				<ul class="nav navbar-nav ">
					<li class="header-tab"><a href="home.html"><strong><span
								style="" class="header-logo"><i class="fa fa-check"
									style="font-size: 17px;"></i></span>MyJobKart.com</strong></a></li>
									<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_jobs.html"><strong>Find
								a Job</strong></a></li>
					<li class=""><a href="find_resumes.html"><strong>Find
								Resumes</strong></a></li>
					<!-- <li  class=""><a href="job_post_1.html"><strong>Post a Job</strong></a></li> -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- 	<li><a href="employer_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="employer_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<!-- <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >Features <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="">Find a Job</a></li>
                    <li><a href="">Job Details</a></li>
                    <li><a href="">Find Resumes</a></li>
                    <li><a href="">Resume Details</a></li>
                    <li><a href="">Post a Job</a></li>
                    <li><a href="">Company Profile</a></li>
                  </ul>
                </li> -->

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><span
							class="btn btn-theme btn-pill btn-xs btn-line">Login </span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
							<li><a href="employer_sign_in.html">Employer</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><span
							class="btn btn-theme btn-pill btn-xs btn-line">Register</span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="jobseeker_sign_up.html">Job Seeker</a></li>
							<li><a href="employer_sign_up.html">Employer</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		</nav><!-- end main navbar --> <!-- mobile navbar -->
		<div class="container">
			<nav class="mobile-nav hidden-md hidden-lg"> <a href="#"
				class="btn-nav-toogle first"> <span class="bars"></span> Menu
			</a>
			<div class="mobile-nav-block">
				<h4>Navigation</h4>
				<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
					Close
				</a>

				<ul class="nav navbar-nav">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_jobs.html"><strong>Find
								a Job</strong></a></li>
					<li class=""><a href="find_resumes.html"><strong>Find
								Resumes</strong></a></li>
					<li><a href="login.html"><strong>Login</strong></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
							<li><a href="employer_sign_in.html">Employer</a></li>
						</ul></li>

					<li><a href="register.html"><strong>Register</strong></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- 	<li><a href="employer_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="employer_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
					<!-- <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >Features <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="job_list.html">Find a Job</a></li>
                    <li><a href="job_details.html">Job Details</a></li>
                    <li><a href="resume_list.html">Find Resumes</a></li>
                    <li><a href="resume_details.html">Resume Details</a></li>
                    <li><a href="job_post_2.html">Post a Job</a></li>
                    <li><a href="company_page.html">Company Profile</a></li>
                  </ul>
                </li> -->
				</ul>
			</div>
			</nav>
		</div>
		<!-- mobile navbar --> </header>
	</c:if>
	<!--main page header end  -->



	<!--admin page header  start-->
	<c:if test="${userType eq 'admin'}">
		<header class="main-header"> <!-- main navbar --> <nav
			class="navbar navbar-default main-navbar hidden-sm hidden-xs">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">

				</ul>
				<ul class="nav navbar-nav">
					<li class="header-tab"><a href="home.html"><strong><span
								style="border-radius: 14px; background: #fff; color: #34495e; padding: 5px 2px 2px 6px; margin-right: 2px;"
								class="header-logo"><i class="fa fa-check"
									style="font-size: 17px;"></i> </span>MyJobKart.com</strong></a></li>
									<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="find_jobs.html"><strong>Find
								a Job</strong></a></li>
					<li class=""><a href="find_resumes.html"><strong>Find
								Resumes</strong></a></li>
								
								<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employer_about_as.html">About Us</a></li>
							<li><a href="employer_contact.html">Contact Us</a></li>
							<!-- <li><a href="employer_blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="employer_terms_privacy.html">Terms &amp;
									Privacy</a></li>
							<li><a href="product_services.html">Buy Online</a></li>
							<li><a href="employer_contact.html">Request Call</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
							src="resources/theme/images/company-logo/1.jpg" alt=""
							class="img-profile"> &nbsp;<b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="admin_home.html"> My Home </a></li>
							<li><a href="notifications_alerts.html"> Notifications <span
									class="badge "></span></a></li>
							<li><a href=""> Change Password</a></li>

						</ul></li>
					<li class="link-btn"><a href="logout.html"><span
							class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
				</ul>
			</div>
		</div>
		</nav><!-- end main navbar --> <!-- mobile navbar -->
		<div class="container">
			<nav class="mobile-nav hidden-md hidden-lg"> <a href="#"
				class="btn-nav-toogle first"> <span class="bars"></span> Menu
			</a>

			<ul class="nav navbar-nav nav-block-left">
				<li class="dropdown"><a href="#"
					class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
						src="resources/theme/images/company-logo/1.jpg" alt=""
						class="img-profile"> &nbsp;<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="admin_home.html"> My Home </a></li>
						<li><a href="notifications_alerts.html"> Notifications <span
								class="badge "></span>
						</a></li>
						<li><a href=""> Change Password</a></li>
						<li><a href="logout.html"> Logout</a></li>
					</ul></li>
			</ul>
			<div class="mobile-nav-block">
				<h4>Navigation</h4>
				<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
					Close
				</a>

				<ul class="nav navbar-nav">
				<li class=""><a href="view_recruiters".html><strong>Companies</strong></a></li>
					<li class=""><a href="job_list.html"><strong>Find
								a Job</strong></a></li>
					<li class=""><a href="resume_list.html"><strong>Find
								Resumes</strong></a></li>
					<li class=""><a href="job_post_1.html"><strong>Post
								a Job</strong></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><strong>Services</strong> <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="about.html">About Us</a></li>
							<li><a href="contact.html">Contact Us</a></li>
							<!-- 	<li><a href="blog.html">Articles &amp; Blog</a></li> -->
							<li><a href="terms_privacy.html">Terms &amp; Privacy</a></li>
							<li><a href="error_404.html">Error 404</a></li>
							<li><a href="shortcode.html">Short Code</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button">Features <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="job_list.html">Find a Job</a></li>
							<li><a href="job_details.html">Job Details</a></li>
							<li><a href="resume_list.html">Find Resumes</a></li>
							<li><a href="resume_details.html">Resume Details</a></li>
							<li><a href="job_post_2.html">Post a Job</a></li>
							<li><a href="company_page.html">Company Profile</a></li>
						</ul></li>
				</ul>
			</div>
			</nav>
		</div>
		<!-- mobile navbar --> </header>
	</c:if>
	<!--admin page header end  -->


</body>
</html>