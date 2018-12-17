
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
		<div class="box-list"
			style="width: 124%; margin-left: -25px; padding: 10px">
			<div class="item">
				<div class="row">

					<!--  <div class="box-list" style="margin-left: -25px ;padding: 10px">	 -->
					<!-- <div class="item">
                      <div class=" row">	 -->

					<h2>Payment History</h2>
				</div>

				<ul id="myTab" class="nav nav-tabs flat-nav-tabs" role="tablist">
					<li><a href="jobseeker_renewal_alert.html" role="tab">Free
							MemberShip </a></li>
					<li><a href="current_month_enrolled_details.html" role="tab">This
							Month</a></li>
					<li><a href="last_month_enrolled_details.html" role="tab">Last
							Month</a></li>
				</ul>
				<div class="box-list">

					<c:if test="${not empty message}">
						<div class="message red">${message}</div>
					</c:if>
					<c:if test="${!empty  registeredList.list}">
						<c:forEach items="${ registeredList.list}" var="searchResult">
							<div class="item"
								style="background-color: #fafafa; margin-top: 10px;">
								<div class="row">
									<div class="col-md-1 hidden-sm hidden-xs">
										<%-- <div class="img-item">
											<img
												src="appliedJobImagehistryDisplay.html?id=${searchResult.id }"
												alt="">
										</div> --%>
									</div>
									<div class="col-md-11">
										<h3 class="no-margin-top ">
											<c:out value="${searchResult.firstName}" />
											<i class="fa fa-link color-white-mute font-1x"></i>
										</h3>
										<h5>
											<span class="color-black"><c:out
													value="${searchResult.emailAddress}" /></span>
										</h5>
										<div>
											<span class="color-white-mute">Remaining Days-<c:out
													value="${searchResult.totalDays}" /></span>
											<c:choose>
												<c:when test="${searchResult.totalDays>'0'}">

													<a class="btn btn-xs btn-theme btn-success"
														style="margin-left: 10px">All ready paid</a>

												</c:when>
												<c:otherwise>

													<a href="product_services.html?id=${searchResult.id}"
														class="btn btn-xs btn-theme btn-success">Pay </a>


												</c:otherwise>
											</c:choose>

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<c:if test="${!empty  registeredList.list}">
					<nav>
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${ registeredList.currentPage != 1}">
								<li><a aria-label="Previous"
									href="jobseeker_renewal_alert?page=${ registeredList.currentPage - 1}">
										<span aria-hidden="true">&larr;</span>
								</a></li>
							</c:if>

							<li><c:forEach begin="${ registeredList.start}"
									end="${ registeredList.records}" var="i">
									<c:choose>
										<c:when test="${ registeredList.page==i}">
											<a href="jobseeker_renewal_alert.html?page=${i}"
												style="color: #fff; background-color: #34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="jobseeker_renewal_alert.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${ registeredList.currentPage lt  registeredList.totalPages}">
								<li><a
									href="jobseeker_renewal_alert.html?page=${JobDescriptions.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>

				<div class="box-list">
					<c:if test="${!empty  paymentEnrolledList.list}">
						<c:forEach items="${ paymentEnrolledList.list}" var="searchResult">
							<div class="item"
								style="background-color: #fafafa; margin-top: 10px;">
								<div class="row">
									<div class="col-md-11">
										<h3>
											Product - <span class="color-white-mute"><c:out
													value="${searchResult.selectProduct}"></c:out></span>
										</h3>


										<h5>
											Product Type - <span class="color-white-mute"><c:out
													value="${searchResult.productType}"></c:out></span>

										</h5>

										<h5>
											Valid From - <span class="color-white-mute"><c:out
													value="${searchResult.created}"></c:out></span>
										</h5>
										<h5>
											End Date- <span class="color-white-mute"><c:out
													value="${searchResult.endDate}"></c:out></span>
										</h5>

										<h3 class="resume-sub-title">
											<span>Payment Details</span>
										</h3>
										<ul>
											<h5>
												Total Cost - <span class="color-white-mute"><c:out
														value="${searchResult.totalcost}"></c:out></span>
											</h5>
											<h5>
												Total Months- <span class="color-white-mute"><c:out
														value="${searchResult.totalMonth}"></c:out></span>
											</h5>

										</ul>

									</div>
								</div>
							</div>
				</div>
				</c:forEach>
				</c:if>
				<c:if test="${!empty  paymentEnrolledList.list}">
					<nav>
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${ paymentEnrolledList.currentPage != 1}">
								<li><a aria-label="Previous"
									href="current_month_enrolled_details.html?page=${ paymentEnrolledList.currentPage - 1}">
										<span aria-hidden="true">&larr;</span>
								</a></li>
							</c:if>

							<li><c:forEach begin="${ paymentEnrolledList.start}"
									end="${ paymentEnrolledList.records}" var="i">
									<c:choose>
										<c:when test="${ paymentEnrolledList.page==i}">
											<a href="current_month_enrolled_details.html?page=${i}"
												style="color: #fff; background-color: #34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="current_month_enrolled_details.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${ paymentEnrolledList.currentPage lt  paymentEnrolledList.totalPages}">
								<li><a
									href="current_month_enrolled_details.html?page=${paymentEnrolledList.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>
			</div>

			<div class="box-list">
				<c:if test="${!empty  lastMonthPaymentList.list}">
					<c:forEach items="${ lastMonthPaymentList.list}" var="searchResult">
						<div class="item"
							style="background-color: #fafafa; margin-top: 10px;">
							<div class="row">
								<div class="col-md-11">
									<h3>
										Product - <span class="color-white-mute"><c:out
												value="${searchResult.selectProduct}"></c:out></span>
									</h3>


									<h5>
										Product Type - <span class="color-white-mute"><c:out
												value="${searchResult.productType}"></c:out></span>

									</h5>

									<h5>
										Valid From - <span class="color-white-mute"><c:out
												value="${searchResult.created}"></c:out></span>
									</h5>
									<h5>
										End Date- <span class="color-white-mute"><c:out
												value="${searchResult.endDate}"></c:out></span>
									</h5>

									<h3 class="resume-sub-title">
										<span>Payment Details</span>
									</h3>
									<ul>
										<h5>
											Total Cost - <span class="color-white-mute"><c:out
													value="${searchResult.totalcost}"></c:out></span>
										</h5>
										<h5>
											Total Months- <span class="color-white-mute"><c:out
													value="${searchResult.totalMonth}"></c:out></span>
										</h5>

									</ul>

								</div>
							</div>
						</div>
			</div>
			</c:forEach>
			</c:if>
			<c:if test="${!empty  lastMonthPaymentList.list}">
				<nav>
					<ul class="pagination pagination-theme  no-margin">
						<c:if test="${ lastMonthPaymentList.currentPage != 1}">
							<li><a aria-label="Previous"
								href="last_month_enrolled_details.html?page=${ lastMonthPaymentList.currentPage - 1}">
									<span aria-hidden="true">&larr;</span>
							</a></li>
						</c:if>

						<li><c:forEach begin="${ lastMonthPaymentList.start}"
								end="${ lastMonthPaymentList.records}" var="i">
								<c:choose>
									<c:when test="${ lastMonthPaymentList.page==i}">
										<a href="last_month_enrolled_details.html?page=${i}"
											style="color: #fff; background-color: #34495e">${i}</a>
									</c:when>
									<c:otherwise>
										<a href="last_month_enrolled_details.html?page=${i}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach></li>
						<c:if
							test="${ lastMonthPaymentList.currentPage lt  lastMonthPaymentList.totalPages}">
							<li><a
								href="last_month_enrolled_details.html?page=${lastMonthPaymentList.currentPage + 1}"><span
									aria-hidden="true">&rarr;</span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
	</div>
	</div>
</body>
</html>



