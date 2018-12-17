<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

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
	content="MyJobKart - Responsive HTML5 Template - 1.0"> -->
<meta name="author" content="http://themeforest.net/user/dan_fisher">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta http-equiv="REFRESH"
	content="<%= session.getMaxInactiveInterval()%>;" />
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic|Bitter:700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.wpb_alert').on('click', function() {
			$(this).fadeOut();
		});
	});
	</script>
</body>
</html>