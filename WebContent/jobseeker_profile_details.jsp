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

<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">
</head>
<body>
	
	<div class="box-list">
		<div class="item">
			<div class="row">

				<c:if test="${!empty jobseekerDetail}">
				
					<div class="resume-block">
						<div class="img-profile">
							<img src="showimage.html?id=${jobseekerDetail.id}">
						</div>
						<c:if test="${userType=='Employer'}">
						<div class="row">
				<div class="form-group">
								
								<div class="row clearfix">
									<div class="col-xs-2" style="width: 4.667%;padding-left: 85%;padding-right: 30px">
						
						<c:choose>
									<c:when test="${SaveResumeStatus==false}">

										<p>
											<a onclick="return confirm('This Resume is Already Saved')"
												class="btn btn-theme btn-t-primary btn-block-xs" title='Already Saved' style="color:red;"><i class="fa fa-floppy-o" aria-hidden="true"></i></a>
												
												
										</p>
									</c:when>

									<c:otherwise>
										<p>
											<a href="save_resume.html?id=${jobseekerDetail.id}"
												class="btn btn-theme btn-t-primary btn-block-xs" title='Save Resume'><i class="fa fa-floppy-o" aria-hidden="true"></i>
												</a>
										</p>
									</c:otherwise>
								</c:choose>
						
						</div>
						
						<div class="col-xs-2" style="width: 4.667%;">
						
								<c:choose>
									<c:when test="${jobseekerDetail.resumeVisibility==true}">
										<p>
											
												<a href="resumeDisplay.html?id=${jobseekerDetail.id}" class="btn btn-theme btn-t-primary btn-block-xs" title='Download This Resume' >
												<i class="fa fa-download" aria-hidden="true"></i></a>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											<a href="#" class="btn btn-theme btn-t-primary btn-block-xs" title='Resume Not Uploaded' style="color:red;">
												<i class="fa fa-download" aria-hidden="true"></i></a>
										</p>
									</c:otherwise>
								</c:choose>
						
						</div>
						</div>
						</div>
				</div>
				</c:if>
						
						
						<div class="desc list-capitalize">

							<h2>
								<c:out value="${jobseekerDetail.firstName}"></c:out>
							</h2>
							<h4>
							<h4 style="text-align: justify;">
								<c:out value="${jobseekerDetail.profiledescription}"></c:out>
							</h4>
							<p><i class="fa fa-envelope-o" aria-hidden="true"></i>
								<c:out value="${jobseekerDetail.emailId}"></c:out>
								</br>
								<i class="fa fa-mobile" aria-hidden="true"></i>
								<c:out value="${jobseekerDetail.phone}"></c:out>

								<%-- <c:out value="${jobseekerDetail.location}"></c:out> --%>
							</p>
							
							<table style="width: 50%;">
								<thead>
																	
									 <h3 class="resume-sub-title">
								<span><i class="fa fa-tags"></i>
									Key Skills </span>
							</h3>
									
									
									
									
									<p class="richtext-word-wrap color-black" style="text-align:justify;">
									<c:out value="${jobseekerDetail.keySkills}"
												escapeXml="false"></c:out></td>
									
									</p>
									
									<h3 class="resume-sub-title">
								<span><i class="fa fa-book"></i>
									Profile Descriptions </span>
							</h3>
									<p class="richtext-word-wrap color-black" style="text-align:justify;">
								
									<c:out value="${jobseekerDetail.profileDescriptions}"
												escapeXml="false"></c:out></td>
									
									</p>
								</thead>
								<tbody>
								</tbody>
							</table>


							<%-- <c:if test="${!empty jobseekerDetail.jobProfileList}"> -



							<h3 class="resume-sub-title">
							
									<span><i class="list-capitalize">Work Experience </i></span>
								</h3>
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

											<c:forEach items="${jobseekerDetail.jobEductionProfileList}"
												var="searchResult">
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
							 --%>
							
							
							
							
							
							
							
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
							<h3 class="resume-sub-title list-capitalize" aria-hidden="true">
								<span><i class="fa fa-graduation-cap"></i>
								Education</span>
							</h3>

							<c:if test="${!empty educationalDetail}">
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

											<c:forEach items="${educationalDetail}" var="searchResult">
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
							<h3 class="resume-sub-title list-capitalize" aria-hidden="true">
								<span><i class="fa fa-info-circle"></i>
								Additional Information</span>
							</h3>
							
							
							
							<h5><i class="fa fa-map-marker"></i> Address :</h5>
					<ul>
										<li><c:out value="${jobseekerDetail.address}"></c:out>
										<li><c:out value="${jobseekerDetail.location}"></c:out></li>
										
									</ul>
<%-- 
							<table style="width: 50%;">
								<thead>

									<tr>
										<td><h5><i class="fa fa-map-marker"></i>
										Address </h5></td>
										<td>: <c:out value="${jobseekerDetail.address}"
												escapeXml="false"></c:out>- <c:out
												value="${jobseekerDetail.location}"></c:out>
										</td>


									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							 --%>
							
							
							
							<c:if test="${jobseekerDetail.resumeVisibility==true}">
							
								<h3 class="resume-sub-title list-capitalize" aria-hidden="true">
								<span><i class="fa fa-file"></i>
								Candidate Resume</span>
							</h3>
							<a href="resumeDisplay.html?id=${jobseekerDetail.id}" class="btn btn-t-primary btn-theme"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;
								Click To DownLoad Resume</a>


							</c:if>

						</div>
					</div>
  <br />
						<div >

						<button id="btnCancel" onclick="history.go(-1);" type="button"
							class="btn btn-t-primary btn-theme">Back</button>
							

						<!-- <a href="jobseeker_profile_view.html"> <input type="button"
							value="Back" class="btn btn-t-primary btn-theme" />
						</a> -->
					</div>
       
				</c:if>
			</div>
		</div>
	</div>
	

</body>
</html>