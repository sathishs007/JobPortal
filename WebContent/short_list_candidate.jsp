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
</head>

<body>

	<div class="box-list">
		<div class="item">
			<div class="row">

				<c:if test="${!empty candidate}">

					<div class="resume-block">
						<div class="img-profile">

							<img src="imageLoginDisplay?id=${candidate.profileId}" />
						</div>

						<div class="desc">
							<h2>
								<c:out value="${candidate.firstName}"></c:out>
							</h2>
							<h4>
								<c:out value="${candidate.jobTitle}"></c:out>
							</h4>
							<p>
								<c:out value="${candidate.companyName}"></c:out>

								<c:out value="${candidate.preferredLocation}"></c:out>
							</p>
							<h3 class="resume-sub-title">
								<span><i class="fa fa-graduation-cap"></i> Education</span>
							</h3>
							<c:if test="${!empty educationDetails}">
								<div class="pi-responsive-table-sm">
									<table style="border: 1px solid;">
										<thead style="background-color: #2a3f54">
											<tr>
												<th style="text-align: center; color: #fff; width: 10%;">Degree
												</th>
												<th style="text-align: center; color: #fff; width: 20%;">Department
												</th>
												<th style="text-align: center; color: #fff; width: 20%;">College</th>
												<th style="text-align: center; color: #fff; width: 15%;">Year
													Of passing</th>
												<th style="text-align: center; color: #fff; width: 15%;">Grade</th>
												<th style="text-align: center; color: #fff; width: 10%;">Percentage</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${educationDetails}" var="searchResult">
												<tr>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.degree}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.department}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.college}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.yearOfPassing}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.grade}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.percentage}"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:if>

							<h3 class="resume-sub-title">
								<span><i class="fa fa-suitcase"></i> Work Experience</span>
							</h3>
							<div class="warning" style="width: 100%; text-align: left;">
								<c:if test="${empty experienceDetails}">
									<div class="alert alert-info" role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<strong>Info!</strong>
										<c:out value="${Infomessage}"></c:out>

									</div>
								</c:if>
							</div>
							<c:if test="${!empty experienceDetails}">
								<div class="pi-responsive-table-sm">
									<table style="border: 1px solid;">
										<thead style="background-color: #2a3f54">
											<tr>
												<th style="text-align: center; color: #fff; width: 10%;">Company
													Name</th>
												<th style="text-align: center; color: #fff; width: 10%;">Company
													Type</th>
												<th style="text-align: center; color: #fff; width: 10%;">Designation</th>
												<th style="text-align: center; color: #fff; width: 10%;">Salary</th>
												<th style="text-align: center; color: #fff; width: 5%;">Start
													Date</th>
												<th style="text-align: center; color: #fff; width: 5%;">End
													Date</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${experienceDetails}" var="searchResult">
												<tr>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.companyName}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.companyType}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.designation}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.lastSalary}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.startDate}"></c:out></td>
													<td class="td-border list-capitalize"><c:out
															value="${searchResult.endDate}"></c:out></td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:if>


							<h3 class="resume-sub-title">
								<span><i class="fa fa-sign-in"></i> Roles
									Responsibilities</span>
							</h3>
							<%-- <h5>Resume Title :</h5>
										<p class="richtext-word-wrap">
											<c:out value="${candidate.resumeTitle}" escapeXml="false"></c:out>

										</p> --%>
							<h5>
								<b>Key Skills :</b>
								<c:out value="${candidate.keySkills}" escapeXml="false">.</c:out>
							</h5>



						</div>
					</div>
					<br/>
					<c:choose>
						<c:when test="${userType=='admin'}">
							<div>
								<a href="short_listed_candiadte_history.html"> <input
									type="button" value="Back" class="btn btn-t-primary btn-theme" />
								</a>
							</div>

							<br />
						</c:when>
						<c:otherwise>
							<div>

								<button id="btnCancel" onclick="history.go(-1);" type="button"
									class="btn btn-t-primary btn-theme">Back</button>


							</div>

							<br />
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
		</div>
	</div>
</html>