<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>HelloClinic</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/layerslider.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/fullwidth/skin.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/owl.carousel.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/color-blue.css">
<link rel="stylesheet" type="text/css"
	href="resources/tuner/css/styles.css">

<!-- Icons -->
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/theme.css" rel="stylesheet">
<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<br/>
	<div class="page" style="margin-left: 20px;">
		<div class="grid-col grid-col-3">
			<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		</div>
		<div class="grid-col grid-col-9" style="margin-top:10px;">
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		</div>
	</div>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	<script language="javascript" src="resources/js/highlight.js"></script>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#datepicker").datepicker();
		});
	</script>
	<script language="javascript" src="resources/js/highlight.js"></script>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.migrate.min.js"></script>
	<script type="text/javascript" src="resources/js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.isotope.min.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.fancybox.pack.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.fancybox-media.js"></script>
	<script type="text/javascript" src="resources/js/jquery.flot.js"></script>
	<script type="text/javascript" src="resources/js/jquery.flot.pie.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.flot.categories.js"></script>
	<script type="text/javascript" src="resources/js/greensock.js"></script>
	<script type="text/javascript"
		src="resources/js/layerslider.transitions.js"></script>
	<script type="text/javascript"
		src="resources/js/layerslider.kreaturamedia.jquery.js"></script>

	<!-- Superscrollorama -->
	<script type="text/javascript"
		src="resources/js/jquery.superscrollorama.js"></script>
	<script type="text/javascript" src="resources/js/TweenMax.min.js"></script>
	<script type="text/javascript" src="resources/js/TimelineMax.min.js"></script>
	<!--/ Superscrollorama -->

	<script type="text/javascript" src="resources/js/jquery.ui.core.min.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.ui.widget.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.ui.tabs.min.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery-ui-tabs-rotate.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.ui.accordion.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.tweet.js"></script>
	<!-- EASYPIECHART -->
	<script type="text/javascript"
		src="resources/js/jquery.easypiechart.js"></script>
	<!--/ EASYPIECHART -->
	<script type="text/javascript"
		src="resources/js/jquery.autocomplete.min.js"></script>
	<script type="text/javascript" src="resources/js/scripts.js"></script>
	<!--/ scripts -->
	<!-- tuner -->
	<script src="resources/tuner/js/scripts.js"></script>

	<div id="tuner" class="tuner">
		<label>Theme Colors</label>
		<ul class="colors">
			<li data-color="blue" class="color-blue active"><span></span></li>
			<li data-color="green" class="color-green"><span></span></li>
			<li data-color="pink" class="color-pink"><span></span></li>
			<li data-color="cyan" class="color-cyan"><span></span></li>
			<li data-color="orange" class="color-orange"><span></span></li>
			<li data-color="purple" class="color-purple"><span></span></li>
		</ul>
		<label>Layout Styles</label>
		<ul class="layouts">
			<li data-layout="boxed">Boxed</li>
			<li data-layout="widescreen" class="active">Widescreen</li>
		</ul>

	</div>
	<!--/ tuner -->
</body>
</html>