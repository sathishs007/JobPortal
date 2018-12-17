<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<body>
	<!-- wrapper page -->
	<!-- end main-header -->


	<!-- body-content -->
	<!-- <div class="container">
		<div class="col-sm-12"> -->
			<div class="box-list">
				<div class="item">
					<div class="row">
						<div class="text-center">
							<h3 class="text-center no-margin">Jobseeker Details</h3>
				         <hr />
						</div>

						<c:if test="${!empty viewJobseeker}">
							<div class="resume-block">
								<div class="img-profile">
									<img src="jobseeker_image.html?id=${viewJobseeker.id}" alt="">

								</div>
								<div class="desc">
									<h2>
										<c:out value="${viewJobseeker.firstName}"></c:out>
										<c:out value="${viewJobseeker.lastName}"></c:out>
									</h2>

									<b>Contact Mail:</b>
									<c:out value="${viewJobseeker.emailAddress}"></c:out>
									<br /><b> Contact Number:</b>
									<c:out value="${viewJobseeker.mobileNo}"></c:out>
									<br /> <b>Terms&Conditions:</b>
									<c:out value="${viewJobseeker.termsConditions}"></c:out>
									
								</div>
							</div>
							<div class="form-group">
							<br /> <a href="admin_jobseekers.html"> <input
										type="button" value="Back" class="btn btn-t-primary btn-theme" /></a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->
</body>
</html>