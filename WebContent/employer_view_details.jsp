<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<body>

	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="text-center">
					<h3 class="text-center no-margin">Employer Details</h3>
					<hr />
				</div>
				<c:if test="${!empty updateProfile}">
					<div class="resume-block">
						<div class="img-profile">
							<img src="image_employer_details.html?id=${updateProfile.id}"
								alt="">
						</div>
						<div class="desc">
							<h2>
								<c:out value="${updateProfile.firstName}"></c:out>
							</h2>
							<b>Contact Mail:</b>
							<c:out value="${updateProfile.emailAddress}"></c:out>
							<br /> <b>Contact Number :</b>
							<c:out value="${updateProfile.contactNumber}"></c:out>
							<br />
							
							<b>Mobile Number :</b>
							<c:out value="${updateProfile.mobileNumber}"></c:out>

							<h3 class="resume-sub-title">
								<span>Company Details</span>
							</h3>
							<b>Company Name:</b>
							<c:out value="${updateProfile.companyName}"></c:out>
							<br />
							<b> Company Website:</b>
							<c:out value="${updateProfile.webSite}"></c:out>
							<br />
							<b> Terms&Conditions:</b>
							<c:out value="${updateProfile.termsConditions}"></c:out>
							<br />
						</div>
					</div>
				</c:if>
				<div class="white-space-10"></div>
				<br/>
				<div>
					<a href="employer_details.html"> <input type="button"
						value="Back" class="btn btn-t-primary btn-theme" /></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>