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
</head>
<style>
p {
	white-space: pre-line;
}
</style>
<body>
	
	<div class="container">
		<div class="col-sm-12">
			<div class="box-list">
				<div class="item">
					<div class="row">
						<c:if test="${!empty jobDetail}">
							<div class="resume-block">
								 <div class="img-profile">
									<img src="imageEmployerLoginDisplay.html?id=${jobDetail.id}" alt="">
								</div> 
								<div class="desc">
								
								<h2>
										<c:out value="${jobDetail.companyName} "></c:out>
									</h2>
								<h4>
										<c:out value="${jobDetail.jobTitle} "></c:out>
									</h4>
									
								<h3 class="resume-sub-title">
										<span><i class="fa fa-tags"></i> Job Description</span>
									</h3>
								
									
									<%-- <p class="richtext-word-wrap">
										<c:out value="${jobDetail.address}" escapeXml="false"></c:out>
									</p> --%>

								<ul>
									<li><h5> Function Area  <span class="color-white-mute">-<c:out
												value="${jobDetail.functionArea}"></c:out> </span>
											
										</h5>		
									 </li>
									 <li>
									 <h5>
									 Experience Years
									 <span class="color-white-mute">- <c:out
													value="${jobDetail.minExp}"></c:out>
											</span> 
											<span class="color-white-mute"> to  <c:out
													value="${jobDetail.maxExp}"></c:out>
											</span>
									 </h5>
									 
									 </li>
									 
									 <li>
									 <h5>
									 
										Salary Year <span class="color-white-mute"> -<c:out
												value="${jobDetail.minSalary}"></c:out>
												</span>
											<span class="color-white-mute"> to <c:out
												value="${jobDetail.maxSalary}"></c:out>
												</span>
									 
									 </h5>
									 
									 </li>
									 
									 <li>
									 <h5>
										Industry Type- <span class="color-white-mute"><c:out
												value="${jobDetail.industryType}"></c:out></span>
									</h5>
									 </li>
									 
									
								</ul>
									
									
									<h3 class="resume-sub-title">
										<span><i class="fa fa-mobile"></i> Contact Details</span>
									</h3>

									<h4>
										<c:out value="${jobDetail.contactNo}"></c:out>
										-
										<c:out value="${jobDetail.contactPerson}"></c:out>
									</h4>
									<h5>
										<c:out value="${jobDetail.companyName}"></c:out>
										<span class="color-white-mute"> - <c:out
												value="${jobDetail.jobLocation}"></c:out></span>
									</h5>
									<%-- <p class="color-white-mute richtext-word-wrap">
										<c:out value="${jobDetail.jobDescription}"  escapeXml="false"></c:out>
									</p> --%>
									<h3 class="resume-sub-title">
										<span>	<i class="fa fa-sign-in"></i>  Required Skills</span>
									</h3>
									
										<%-- <h5>
											Designation - <span class="color-white-mute"><c:out
													value="${jobDetail.jobTitle} "></c:out></span>
										</h5> --%>
										<h5>
										
											Key Skills- <span class="color-white-mute">
													
													<c:out
														value="${jobDetail.keywords}"    escapeXml="false" >
													</c:out>
													
												</span>
										</h5>
										<h5>
											Minimum Experience -<span class="color-white-mute"> <c:out
													value="${jobDetail.minExp}"></c:out>
											</span>
										</h5>
										<h5>
											Maximum Experience -<span class="color-white-mute"> <c:out
													value="${jobDetail.maxExp}"></c:out>
											</span>
										</h5>
										<h5>
											Minimum Salary -<span class="color-white-mute"> <c:out
													value="${jobDetail.minSalary}"></c:out>
											</span>
										</h5>
										<h5>
											Maximum Salary -<span class="color-white-mute"> <c:out
													value="${jobDetail.maxSalary}"></c:out>
											</span>
										</h5>
									
									<h3 class="resume-sub-title">
										<span><i class="fa fa-graduation-cap"></i> Qualification</span>
									</h3>
									<h5>
										UG Qualification -<span class="color-white-mute"> <c:out
												value="${jobDetail.ugQualification}"></c:out>
										</span>
									</h5>
									<h5>
										PG Qualification -<span class="color-white-mute"> <c:out
												value="${jobDetail.pgQualification}"></c:out>
										</span>
									</h5>

								</div>
							</div>
						</c:if>
					</div>
					<br/>
				<div >
								<a href="employer_job_view.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
								</a>
							</div>
					
				</div>
				

			</div>
		</div>
	</div>
</body>
</html>