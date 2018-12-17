<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MyjobKart</title>
</head>
<body>
	<link rel="apple-touch-icon"
		href="resources/theme/images/apple-touch-icon.png">
		<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
			type="image/x-icon">

			<!-- bootstrap -->
			<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
				rel="stylesheet">

				<!-- Icons -->
				<link
					href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
					rel="stylesheet">

					<!-- lightbox -->
					<link href="resources/plugins/magnific-popup/magnific-popup.css"
						rel="stylesheet">


						<!-- Themes styles-->
						<link href="resources/theme/css/theme.css" rel="stylesheet">
							<!-- Your custom css -->
							<link href="resources/theme/css/theme-custom.css"
								rel="stylesheet">
								<!-- wrapper page -->
								<div class="wrapper">
									<!-- main-header -->
									<header class="main-header"> <!-- main navbar --> <nav
										class="navbar navbar-default main-navbar hidden-sm hidden-xs">
									<div class="container">
										<div class="collapse navbar-collapse"
											id="bs-example-navbar-collapse-1">

											<ul class="nav navbar-nav">
												<li class=""><a href="find_jobs.html"><normal>Find
														a Job</normal></a></li>
												<li class=""><a href="find_resumes.html"><normal>Find
														Resumes</normal></a></li>
												<li class=""><a href="jobseekers_services.html"><normal>Post
														a Job</normal></a></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Pages <span class="caret"></span></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="about_as.html">About Us</a></li>
														<li><a href="contact.html">Contact Us</a></li>
														<li><a href="blog.jsp">Articles &amp; Blog</a></li>
														<li><a href="terms_privacy.jsp">Terms &amp;
																Privacy</a></li>
														<!--  <li><a href="shortcode.jsp">Short Code</a></li> -->
													</ul></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Features <span class="caret"></span></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="find_jobs.html">Job Details</a></li>
														<li><a href="find_resumes.html">Resume Details</a></li>
														<li><a href="jobseekers_services.html">Post a Job</a></li>
													</ul></li>
											</ul>
											<ul class="nav navbar-nav navbar-right">

												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button"><span
														class="btn btn-theme btn-pill btn-xs btn-line">Login
													</span></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
														<li><a href="employer_sign_in.html">Employer</a></li>
													</ul></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button"><span
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
											class="btn-nav-toogle first"> <span class="bars"></span>
											Menu
										</a>
										<div class="mobile-nav-block">
											<h4>Navigation</h4>
											<a href="#" class="btn-nav-toogle"> <span
												class="barsclose"></span> Close
											</a>

											<ul class="nav navbar-nav">
												<li class="active"><a href="find_jobs.html"><normal>Find
														a Job</normal></a></li>
												<li class=""><a href="find_resumes.html"><normal>Find
														Resumes</normal></a></li>
												<li class=""><a href="jobseekers_services.html"><normal>Post
														a Job</normal></a></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Login<b class="caret"></b>
												</a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="jobseeker_sign_in.html">Job Seeker</a></li>
														<li><a href="employer_sign_in.html">Employer</a></li>
													</ul></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Register<b class="caret"></b></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="jobseeker_sign_up.html">Job Seeker</a></li>
														<li><a href="employer_sign_up.html">Employer</a></li>
													</ul></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Pages <span class="caret"></span></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="about_as.html">About Us</a></li>
														<li><a href="contact.html">Contact Us</a></li>
														<li><a href="blog.jsp">Articles &amp; Blog</a></li>
														<li><a href="terms_privacy.jsp">Terms &amp;
																Privacy</a></li>
														<!--  <li><a href="shortcode.jsp">Short Code</a></li> -->
													</ul></li>
												<li class="dropdown"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button">Features <span class="caret"></span></a>
													<ul class="dropdown-menu" role="menu">
														<li><a href="find_jobs.html">Job Details</a></li>
														<li><a href="find_resumes.html">Resume Details</a></li>
														<li><a href="jobseekers_services.html">Post a Job</a></li>
													</ul></li>
											</ul>
										</div>
										</nav>
									</div>
									<!-- mobile navbar -->


									<div class="container">
										<div class="text-center logo no-margin-bottom">
											<a href="index.html"><img
												src="resources/theme/images/logo_myjobkart.png" alt=""></a>
										</div>
										<h3 class="color-white text-center ">Articles &amp; Blog</h3>
										<div class="white-space-50"></div>
									</div>
									</header>
									<!-- end main-header -->


									<!-- body-content -->
									<div class="body-content clearfix">

										<!-- blog block -->
										<div class="bg-color1 block-section">
											<div class="container">
												<div class="row">
													<div class="col-md-10 col-md-offset-1">

														<!-- blog list -->
														<div class="blog-list">

															<!-- blog item -->
															<div class="blog-item">
																<h2 class="blog-title">
																	<a href="blog_single_post.html">Considerations for
																		Your Next Move <br /> <small>(How It Can
																			Affect Your Career)</small>
																	</a>
																</h2>
																<div class="blog-meta">
																	February 27, 2015 by <a href="#">William Frierson</a>
																</div>
																<div class="blog-img">
																	<img src="./resources/theme/images/home-bg.jpg" alt=""
																		class="img-full "> <small>University law
																			school graduate on graduation day. Photo courtesy of
																			Pixabay.</small>
																</div>
																<p>While it is true that it is still pretty early in
																	the school year for most college students, those finals
																	and the last days of the school year are just around
																	the corner. It is easy to plan out how you will address
																	the class requirements, but sometimes the other aspects
																	of life are missed or not given as much attention and
																	you may find yourself “throwing it together” when
																	faced with projects at the end of the year.</p>
																<p class="blog-links">
																	<a href="blog_single_post.html"
																		class="btn btn-theme btn-line soft ">Continue
																		Reading &nbsp; &rarr;</a>
																</p>
															</div>
															<!-- end blog item -->

															<!-- blog item -->
															<div class="blog-item">
																<h2 class="blog-title">
																	<a href="blog_single_post.html">5 Key Tips to
																		Starting a Career in Higher Education</a>
																</h2>
																<div class="blog-meta">
																	February 27, 2015 by <a href="#">William Frierson</a>
																</div>
																<p>Looking into the future with a career in higher
																	education is a way for you to give back to the world
																	with a sense of satisfaction. Before you begin moving
																	forward with a plan of action to seek a career in
																	higher education it is essential to review a few tips
																	prior to enrolling in the college or university of your
																	choice.</p>
																<p class="blog-links">
																	<a href="blog_single_post.html"
																		class="btn btn-theme btn-line soft ">Continue
																		Reading &nbsp; &rarr;</a>
																</p>
															</div>
															<!-- end blog item -->

															<!-- blog item -->
															<div class="blog-item">
																<h2 class="blog-title">
																	<a href="blog_single_post.html">Career Paths for
																		Law Students:<br /> <small> Where You Can Go</small>
																	</a>
																</h2>
																<div class="blog-meta">
																	February 27, 2015 by <a href="#">William Frierson</a>
																</div>
																<div class="row">
																	<div class="col-md-6">
																		<img src="./resources/theme/images/home-bg.jpg" alt=""
																			class="img-full ">
																			<div class="white-space-20 hidden-lg hidden-md"></div>
																	</div>
																	<div class="col-md-6">
																		<p>No doubt, you labored admirably to acquire your
																			law degree.</p>
																		<p>Having graduated from law school, you may have
																			decided that practicing law is the one and only
																			career option. The good news is that your law degree
																			can be put to good use securing some incredibly,
																			diverse careers that are outside of the law. Given
																			today’s economy and the competition within the
																			legal industry,</p>
																	</div>
																</div>
																<p class="blog-links">
																	<a href="blog_single_post.html"
																		class="btn btn-theme btn-line soft ">Continue
																		Reading &nbsp; &rarr;</a>
																</p>
															</div>
															<!-- end blog item -->


														</div>
														<!-- end blog list -->

														<!-- pager -->
														<nav>
														<ul class="pager">
															<li class="previous"><a href="#"
																class="btn-theme btn-lg"><span aria-hidden="true">&larr;</span>
																	Older</a></li>
															<li class="next"><a href="#"
																class="btn-theme btn-lg">Newer <span
																	aria-hidden="true">&rarr;</span></a></li>
														</ul>
														</nav>
														<!-- end pager -->


													</div>
												</div>
											</div>
										</div>
										<!-- end blog block -->
									</div>
									<!--end body-content -->
									<%@include file="footer.jsp"%>
									<script src="resources/plugins/jquery.js"></script>
									<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
									<!-- jQuery Bootstrap -->
									<script
										src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
									<!-- Lightbox -->
									<script
										src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
									<!-- Theme JS -->
									<script src="resources/theme/js/theme.js"></script>

									<!-- maps -->
									<script
										src="http://maps.googleapis.com/maps/api/js?sensor=false"
										type="text/javascript"></script>
									<script src="resources/plugins/gmap3.min.js"></script>
									<!-- maps single marker -->
									<script src="resources/theme/js/map-detail.js"></script>
</body>
</html>