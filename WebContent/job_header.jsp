<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	<header class="main-header"> <nav
		class="navbar navbar-default main-navbar hidden-sm hidden-xs">
	<div class="container">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class=""><a href="find_jobs.html">Find a Job</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button">Pages <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="jobseeker_about_as.html">About Us</a></li>
						<li><a href="jobseeker_contact.html">Contact Us</a></li>
						<li><a href="jobseeker_blog.html">Articles &amp; Blog</a></li>
						<li><a href="jobseeker_terms_privacy.html">Terms &amp;
								Privacy</a></li>
						<!-- <li><a href="shortcode.jsp">Short Code</a></li> -->
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
					class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
						src="imageLoginDisplay.html?id=${registerId}" alt=""
						class="img-profile"> &nbsp; ${name} <b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="jobseeker_home.html"> My Home </a></li>
						<li><a href="my_notifications_jobseeker.html">
								Notifications <span class="badge ">5</span>
						</a></li>
						<li><a href="jobseeker_change_password_after_login.html">
								Change Password</a></li>

					</ul></li>
				<li class="link-btn"><a href="logout.html"><span
						class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
			</ul>
		</div>
	</div>
	</nav><!-- end main navbar --> <!-- mobile navbar -->
	<div class="container">
		<nav class="mobile-nav hidden-md hidden-lg">

		<ul class="nav navbar-nav nav-block-left">
			<li class="dropdown"><a href="#"
				class="link-profile dropdown-toggle" data-toggle="dropdown"> <img
					src="imageLoginDisplay.html?id=${registerId}" alt=""
					class="img-profile"> &nbsp; ${name} <b class="caret"></b></a>
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
				<li class="active"><a href="find_jobs.html">Find a Job</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button">Pages <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="jobseekers_about_as.html">About Us</a></li>
						<li><a href="jobseekers_contact.html">Contact Us</a></li>
						<li><a href="jobseekers_blog.html">Articles &amp; Blog</a></li>
						<li><a href="jobseeker_terms_privacy.html">Terms &amp;
								Privacy</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button">Features <span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="find_jobs.html">Job Details</a></li>
					</ul></li>
			</ul>
		</div>
		</nav>
	</div>
	</header>
</body>
</html>