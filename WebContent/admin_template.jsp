<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="not-ie no-js" lang="en">
<head>
<meta charset="utf-8">
<title>MyJobKart</title>
<!--Meta Property  -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="description"
	content="Register with myjobkart.com & receive jobs in your inbox, apply to jobs easily. Find the right Job by top employers from India & abroad. Create your Profile Now, Free!." />
<meta NAME="keywords"
	CONTENT="job vacancies, job search, job opportunities, jobs in India, job openings">
<link rel="canonical" href="http://myjobkart.com" />
<link rel="alternate" href="http://myjobkart.com" />
<!-- End Meta Property -->
<!-- <meta name="description"
	content="jobtrolley - Responsive HTML5 Template - 1.0"> -->
<meta name="author" content="http://themeforest.net/user/dan_fisher">

<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta http-equiv="REFRESH"
	content="<%=session.getMaxInactiveInterval()%>;" />
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic|Bitter:700'
	rel='stylesheet' type='text/css'>
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
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">


<!-- Head Libs -->
<script src="vendor/modernizr.js"></script>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="images/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="images/apple-touch-icon-144x144.png">
<link rel="stylesheet" href="resources/theme/css/contact-buttons.css">



<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

</head>

<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<c:if test="${userType eq 'admin'}">
		<!-- <header class="main-header">
			<div class="container">
				<div class="text-center logo hidden-sm hidden-xs">
					<a href=""><img
						src="resources/theme/images/logo_myjobkart.png" alt=""></a>
			</div>
				<div class="text-center logo  hidden-md hidden-lg">
					<a href=""><img
						src="resources/theme/images/logo_myjobkart.png" alt=""
						style="width: 70%"></a>
				</div>
			</div>
	</header> -->
	</c:if>

	<c:if test="${userType eq 'Jobseeker'}">
		<!-- <header class="main-header  mobile-nav hidden-md hidden-lg">
			<div class="container">
				<div class="text-center logo hidden-sm hidden-xs">
					<a href="home.html"><strong><span class="header-logo">MyJob</span>Kart.com</strong></a>
			</div>
				<div class="text-center logo  hidden-md hidden-lg">
					<a href="home.html"><strong style="color:white;"><span  class="header-logo">MyJob</span>Kart.com</strong></a>
				</div>
			</div>
	</header> -->
	</c:if>
	<c:if test="${userType eq 'Employer'}">
		<!--  <header class="main-header mobile-nav hidden-md hidden-lg">
			<div class="container">
				<div class="text-center logo hidden-sm hidden-xs">
					<a href="home.html"><img
						src="resources/theme/images/logo_myjobkart.png" alt=""></a>
			</div>
				<div class="text-center logo  hidden-md hidden-lg">
					<a href="home.html"><img
						src="resources/theme/images/logo_myjobkart.png" alt=""
						style="width: 70%"></a>
				</div>
			</div>
		</header>  -->
	</c:if>

	<div class="body-content clearfix">

		<div class="bg-color1">
			<div class="col-md-3 col-sm-4">
				<div class="container">
					<tiles:insertAttribute name="menu"></tiles:insertAttribute>
				</div>
			</div>

			<div class="col-md-9 col-sm-8">

				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>
	</div>

	<tiles:insertAttribute name="footer"></tiles:insertAttribute>

	<!-- <script src="resources/plugins/jquery.js"></script> -->


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

	<script type="text/javascript">
		$(document).ready(function() {
			$('.wpb_alert').on('click', function() {
				$(this).fadeOut();
			});
		});
	</script>
	<script src="resources/theme/js/jquery.contact-buttons.js"></script>
	<script src="resources/theme/js/demo.js"></script>




	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>


</body>
</html>