
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
				<!-- box item details -->
				<c:if test="${!empty candidateview}">
					<div class="resume-block">
						<div class="img-profile" style="top: -40px;">
							<img src="showjobseekerimage.html?id=${candidateview.id}" alt="">
						</div>
						<div class="desc">
							<h2>
								<c:out value="${candidateview.firstName}"></c:out>
							</h2>
							<h3 class="resume-sub-title">
								<span><i class="fa fa-graduation-cap"></i> Education</span>
							</h3>
							<c:if test="${!empty educationList}">
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
											<c:forEach items="${educationList}" var="searchResult">
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
								<span><i class="fa fa-suitcase" aria-hidden="true"></i>
									Work Experience</span>
							</h3>
							<div class="warning" style="width: 100%; text-align: left;">
								<c:if test="${empty professionalList}">
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
							<c:if test="${!empty professionalList}">
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
											<c:forEach items="${professionalList}" var="searchResult">
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
								<span><i class="fa fa-info-circle" aria-hidden="true"></i>
									Additional Information</span>
							</h3>
							<h4>
								<i class="fa fa-sign-in"></i> SKILLS:
							</h4>
							<ul>
								<li>
									<p class="richtext-word-wrap">
										<c:out value="${candidateview.keySkills}" escapeXml="false"></c:out>
									</p>
								</li>
							</ul>
							<c:if test="${candidateview.resumeVisibility==true}">
								<h4>
									<i class="fa fa-file"></i> Candidate Resume
								</h4>
								<ul>
									<li><a
										href="candidateresumeDisplay.html?id=${candidateview.id}">Click
											To DownLoad Resume</a></li>
								</ul>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
			<!-- end box item details -->
			<br/>
			<div>
				<a href="candidate_resume.html"> <input type="button"
					value="Back" class="btn btn-t-primary btn-theme" />
				</a>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>