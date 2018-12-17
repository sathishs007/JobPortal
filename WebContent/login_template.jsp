<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="not-ie no-js" lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="REFRESH"
	content="<%= session.getMaxInactiveInterval()%>;" />
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
	content="MyJobKart - Responsive HTML5 Template - 1.0"> -->
<meta name="author" content="http://themeforest.net/user/dan_fisher">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic|Bitter:700'
	rel='stylesheet' type='text/css'>
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">
<link rel="stylesheet" href="resources/theme/css/contact-buttons.css">

<script src="resources/theme/js/jquery.contact-buttons.js"></script>
<script src="resources/theme/js/demo.js"></script>

<!-- Modernizr Plugin -->
<script src="resources/theme/js/modernizr.custom.79639.js"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/theme/js/jquery-1.11.2.min.js"></script>



<!-- Retina Plugin -->
<script src="resources/theme/js/retina.min.js"></script>

<!-- ScrollReveal Plugin -->
<script src="resources/theme/js/scrollReveal.min.js"></script>

<!-- Flex Menu Plugin -->
<script src="resources/theme/js/jquery.flexmenu.js"></script>

<!-- Slider Plugin -->
<script src="resources/theme/js/jquery.ba-cond.min.js"></script>
<script src="resources/theme/js/jquery.slitslider.js"></script>

<!-- Carousel Plugin -->
<script src="resources/theme/js/owl.carousel.min.js"></script>

<!-- Parallax Plugin -->
<script src="resources/theme/js/parallax.js"></script>

<!-- Counterup Plugin -->
<script src="resources/theme/js/jquery.counterup.min.js"></script>
<script src="resources/theme/js/waypoints.min.js"></script>

<!-- No UI Slider Plugin -->
<script src="resources/theme/js/jquery.nouislider.all.min.js"></script>

<!-- Bootstrap Wysiwyg Plugin -->
<script src="resources/theme/js/bootstrap-wysiwyg.js"></script>
<script src="resources/theme/js/jquery.hotkeys.js"></script>

<!-- Flickr Plugin -->
<script src="resources/theme/js/jflickrfeed.min.js"></script>

<!-- Fancybox Plugin -->
<script src="resources/theme/js/fancybox.pack.js"></script>

<!-- Magic Form Processing -->
<script src="resources/theme/js/magic.js"></script>

<!-- jQuery Settings -->
<script src="resources/theme/js/settings.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<!--header logo start  -->
	<header class="main-header"> <!-- <div class="container">
				<div class="text-center logo hidden-sm hidden-xs">
					<a href="index.html"><img
						src="resources/theme/images/logo_myjobkart.png" alt=""></a>
			</div>
				<div class="text-center logo  hidden-md hidden-lg">
					<a href="index.html"><img
						src="resources/theme/images/logo_myjobkart.png" alt=""
						style="width: 70%"></a>
				</div>
			</div> -->

	




	</header>
	<!--header logo end  -->
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	<script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="resources/theme/js/theme.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<script src="resources/theme/js/map-detail.js"></script>
	<script type="text/javascript"
		src="resources/theme/script/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.wpb_alert').on('click', function() {
			$(this).fadeOut();
		});
	});
	</script>

	<script src="resources/theme/js/jquery.contact-buttons.js"></script>
	<script src="resources/theme/js/demo.js"></script>

</body>
</html>