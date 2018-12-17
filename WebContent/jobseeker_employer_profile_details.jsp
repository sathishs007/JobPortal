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
					
								<c:if test="${!empty employerDetail}">
									<div class="resume-block">
										<div class="img-profile">
											<img
												src="showimage.html?id=${employerDetail.id}"
												alt="">
										</div>
										<div class="desc">
											<h2>
												<c:out value="${employerDetail.firstName}"></c:out>
											</h2>
											<h4>
												Primary Email- <span class="color-white-mute"><c:out
														value="${employerDetail.emailId}"></c:out></span>
											</h4>
											<h4>
												Secondary Email - <span class="color-white-mute"><c:out
														value="${employerDetail.secondaryEmail}"></c:out></span>
											</h4>
											

											<h3 class="resume-sub-title">
												<span>Contact Details</span>
											</h3>

											<h4>
												<c:out value="${employerDetail.contactNo}"></c:out>
												-
												<c:out value="${employerDetail.mobileNo}"></c:out>
											</h4>
											<h5>
												<c:out value="${employerDetail.companyName}"></c:out>
												<span class="color-white-mute"> - <c:out
														value="${employerDetail.city}"></c:out></span>
											</h5>
											<p class="color-white-mute">
												<c:out value="${employerDetail.companyWebsite}"></c:out>
											</p>
											<h5>Address :</h5>
											<ul>
												<li><c:out value="${employerDetail.addressLine1}"></c:out>
												<c:out
														value="${employerDetail.pinCode}"></c:out></li>

												<li><c:out value="${employerDetail.city}"></c:out></li>
												<li><c:out value="${employerDetail.state}"></c:out>
												<c:out
														value="${employerDetail.country}"></c:out></li>

											</ul>
											<h3 class="resume-sub-title">
												<span>Roles Responsibilities</span>
											</h3>
											<ul>
												<h5>
													Roles - <span class="color-white-mute"><c:out
															value="${employerDetail.currentDesignation}"></c:out></span>
												</h5>
												<h5>
													Hiring Purpose -<span class="color-white-mute"> <c:out
															value="${employerDetail.hiringPurpose}"></c:out></span>
												</h5>
											</ul>
											<h3 class="resume-sub-title">
												<span>Company Profile</span>
											</h3>
											<p class="richtext-word-wrap" style="text-align: justify;">
												<c:out value="${employerDetail.companyProfile}" escapeXml="false"></c:out>
											</p>
										</div>
									</div>
									<br/>
									<div >
								<a href="jobseeker_view.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
								</a>
							</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		
</body>
</html>