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

<!--favicon-->
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


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>MyJobKart|Jobseeker|saveJobs</title>



</head>
<body>
	<!-- wrapper page -->
	<!-- end main-header -->



	<!-- body-content -->
	<div class="block-section box-side-account">
		<div class="box-list">
			<div class="item">
				<div class="row">

					<!--  <div class="box-list" style="margin-left: -25px ;padding: 10px">	 -->
					<!-- <div class="item">
                      <div class=" row">	 -->

					<h2>Jobseeker's History Details</h2>
				</div>

				<ul id="myTab" class="nav nav-tabs flat-nav-tabs" role="tablist">
					<li><a href="jobseeker_appliedjobs.html" role="tab">
							Applied Jobs</a></li>
					<li><a href="jobseeker_savedjobs.html" role="tab"> Saved
							Jobs</a></li>
					<li><a href="jobseeker_paymenthistory.html" role="tab">
							Payment History</a></li>
				</ul>

				<%-- <div class="box-list">

					<c:if test="${not empty message}">
						<div class="message red">${message}</div>
					</c:if>
					<c:if test="${!empty JobDescriptions.list}">
						<c:forEach items="${JobDescriptions.list}" var="searchResult">
							<div class="item"
								style="background-color: #fafafa; margin-top: 10px;">
								<div class="row">
									<div class="col-md-1 hidden-sm hidden-xs">
										<div class="img-item">
											<img
												src="appliedJobImagehistryDisplay.html?id=${searchResult.id }"
												alt="">
										</div>
									</div>
									<div class="col-md-11">
										<h3 class="no-margin-top ">
											<a href="search_job_details.html?id=${searchResult.id} "><c:out
													value="${searchResult.companyName}" /> <i
												class="fa fa-link color-white-mute font-1x"></i></a>
										</h3>
										<h5>
											<span class="color-black"><c:out
													value="${searchResult.jobTitle}" /></span> - <span
												class="color-white-mute"><c:out
													value="${searchResult.jobLocation}" /></span>
										</h5>
										<p class="text-truncate ">
											KeySkills-
											<c:out value="${searchResult.keywords}" />
										</p>
										JobDescription-
										<c:out value="${searchResult.jobDescription}" />
										<div>
											<span class="color-white-mute"><c:out
													value="${searchResult.created}" /></span> <a
												href="search_job_details.html?id=${searchResult.id}"
												class="btn btn-theme btn-xs btn-primary">More Details...</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div> --%>
				<%-- 	<c:if test="${!empty JobDescriptions.list}">
					<nav>
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${JobDescriptions.currentPage != 1}">
							<li><a aria-label="Previous"
								href="jobseeker_appliedmonth.html?page=${JobDescriptions.currentPage - 1}">
									<span aria-hidden="true">&larr;</span>
							</a></li></c:if>

							<li><c:forEach begin="${JobDescriptions.start}" end="${JobDescriptions.records}"
									var="i">
									<c:choose>
										<c:when test="${JobDescriptions.page==i}">
											<a
												href="jobseeker_appliedmonth.html?page=${i}"
												style="color: #fff;background-color:#34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="jobseeker_appliedmonth.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${JobDescriptions.currentPage lt JobDescriptions.totalPages}">
								<li ><a
									href="jobseeker_appliedmonth.html?page=${JobDescriptions.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if> --%>
				<%-- <div class="box-list">

					
					<c:if test="${!empty JobDescriptions1.list}">
						<c:forEach items="${JobDescriptions1.list}" var="searchResult1">
							<div class="item"
								style="background-color: #fafafa; margin-top: 10px;">
								<div class="row">
									<div class="col-md-1 hidden-sm hidden-xs">
										<div class="img-item">
											<img
												src="appliedJobImagehistryDisplay.html?id=${searchResult1.id }"
												alt="">
										</div>
									</div>
									<div class="col-md-11">
										<h3 class="no-margin-top ">
											<a href="search_job_details.html?id=${searchResult1.id} "><c:out
													value="${searchResult1.jobTitle}" /> <i
												class="fa fa-link color-white-mute font-1x"></i></a>
										</h3>
										<h5>
											<span class="color-black"><c:out
													value="${searchResult1.companyName}" /></span> - <span
												class="color-white-mute"><c:out
													value="${searchResult1.jobLocation}" /></span>
										</h5>
										<p class="text-truncate ">
											KeySkills-
											<c:out value="${searchResult1.keywords}" />
										</p>
										JobDescription-
										<c:out value="${searchResult1.jobDescription}" />
										<div>
											<span class="color-white-mute"><c:out
													value="${searchResult1.created}" /></span> <a
												href="search_job_details.html?id=${searchResult1.id}"
												class="btn btn-theme btn-xs btn-primary">More Details...</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div> --%>



				<%-- 	<c:if test="${!empty JobDescriptions1.list}">
					<nav>
						<ul class="pagination pagination-theme  no-margin">
						<c:if test="${JobDescriptions1.currentPage != 1}">
							<li><a aria-label="Previous"
								href="jobseeker_appliedcompany.html?page=${JobDescriptions1.currentPage - 1}">
									<span aria-hidden="true">&larr;</span>
							</a></li></c:if>

							<li><c:forEach begin="${JobDescriptions1.start }"
									end="${JobDescriptions1.records}" var="i">
									<c:choose>
										<c:when test="${JobDescriptions1.page==i}">
											<a
												href="jobseeker_appliedcompany.html?page=${i}"
												style="color: #fff;background-color:#34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="jobseeker_appliedcompany.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${JobDescriptions1.currentPage lt JobDescriptions1.totalPages}">
								<li class="active"><a
									href="jobseeker_appliedcompany.html?page=${JobDescriptions1.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if> --%>
				<<%-- div class="box-list"> <c:if test="${!empty
				JobDescriptions2.list}"> <c:forEach items="${JobDescriptions2.list}"
				var="searchResult2"> <div class="item" style="background-color:
				#fafafa; margin-top: 10px;"> <div class="row"> <div class="col-md-1
				hidden-sm hidden-xs"> <div class="img-item"> <img
				src="appliedJobImagehistryDisplay.html?id=${searchResult2.id }"
				alt=""> </div> </div> <div class="col-md-11"> <h3
				class="no-margin-top "> <a
				href="search_job_details.html?id=${searchResult2.id} "><c:out
				value="${searchResult2.jobTitle}" /> <i class="fa fa-link
				color-white-mute font-1x"></i></a> </h3> <h5> <span
				class="color-black"><c:out value="${searchResult2.companyName}"
				/></span> - <span class="color-white-mute"><c:out
				value="${searchResult2.jobLocation}" /></span> </h5> <p
				class="text-truncate "> KeySkills- <c:out
				value="${searchResult2.keywords}" /> </p> JobDescription- <c:out
				value="${searchResult2.jobDescription}" /> <div> <span
				class="color-white-mute"><c:out value="${searchResult2.created}"
				/></span> <a href="search_job_details.html?id=${searchResult2.id}"
				class="btn btn-theme btn-xs btn-primary">More Details...</a> </div>
				</div> </div> </div> </c:forEach> </c:if> </div> --%> <%-- <c:if
				test="${!empty JobDescriptions2.list}"> <nav> <ul class="pagination
				pagination-theme no-margin"> <c:if
				test="${JobDescriptions2.currentPage !=1}"> <li><a
				aria-label="Previous"
				href="jobseeker_appliedtitle.html?page=${JobDescriptions2.currentPage
				- 1}"> <span aria-hidden="true">&larr;</span> </a></li></c:if>

				<li><c:forEach begin="${JobDescriptions2.start}"
				end="${JobDescriptions2.records}" var="i"> <c:choose> <c:when
				test="${JobDescriptions2.page==i}"> <a
				href="jobseeker_appliedtitle.html?page=${i}" style="color:
				#fff;background-color:#34495e">${i}</a> </c:when> <c:otherwise> <a
				href="jobseeker_appliedtitle.html?page=${i}">${i}</a> </c:otherwise>
				</c:choose> </c:forEach></li> <c:if
				test="${JobDescriptions2.currentPage lt
				JobDescriptions2.totalPages}"> <li ><a
				href="jobseeker_appliedtitle.html?page=${JobDescriptions2.currentPage
				+ 1}"><span aria-hidden="true">&rarr;</span></a></li> </c:if> </ul>
				</nav> </c:if> --%> <br />
				<!-- form get alert -->
				<div class="get_alert">
					<h4>
						Get email updates for the latest <span class=" "> jobs in
							United States</span>
					</h4>
					<form>
						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label>My </label> <input class="form-control"
										placeholder="Enter Email">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="hidden-sm hidden-xs ">&nbsp;</label>
									<button class="btn btn-theme btn-success btn-block">Activate</button>
								</div>
							</div>
						</div>
						<small>You can cancel email alerts at any time.</small>
					</form>
				</div>


			</div>
		</div>




	</div>



	</div>




</body>
</html>