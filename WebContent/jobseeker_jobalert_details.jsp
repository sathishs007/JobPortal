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
	<div class="box-list">
		<div class="item">
			<div class="row">

				<c:if test="${!empty jobalertDetail}">
					<div class="resume-block">
						<div class="desc">
							<h3 class="resume-sub-title">
								<span><i class="fa fa-briefcase"></i> Job Alert</span>
							</h3>

							<h4>
								Role - <span class="color-white-mute"
									style="text-transform: capitalize;"><c:out
										value="${jobalertDetail.role}"></c:out></span>
							</h4>
							<h4>
								Email - <span class="color-white-mute"><c:out
										value="${jobalertDetail.emailId}"></c:out></span>
							</h4>

							<h4>
								Preferred Industry - <span class="color-white-mute"
									style="text-transform: capitalize;"><c:out
										value="${jobalertDetail.preferredIndustry}"></c:out></span>
							</h4>

							<h4>
								Preferred Location - <span class="color-white-mute"
									style="text-transform: capitalize;"><c:out
										value="${jobalertDetail.preferredLocation}"></c:out></span>
							</h4>

							<h4>
								Job Type - <span class="color-white-mute"
									style="text-transform: capitalize;"><c:out
										value="${jobalertDetail.jobType}"></c:out></span>
							</h4>

							<h4>
								Experience - <span class="color-white-mute"><c:out
										value="${jobalertDetail.experienceInYear}"></c:out></span>
							</h4>

							<h4>
								Alert Name - <span class="color-white-mute"
									style="text-transform: capitalize;"><c:out
										value="${jobalertDetail.alertName}"></c:out></span>
							</h4>

							<h4>
								Expected Salary - <span class="color-white-mute"><c:out
										value="${jobalertDetail.salary}"></c:out></span>
							</h4>


						</div>
					</div>
				</c:if>

			</div>
<br/>
			<div>
				<a href="jobseeker_jobalert_view.html"> <input type="button"
					value="Back" class="btn btn-t-primary btn-theme" />
				</a>
			</div>
		</div>
	</div>
	
</body>
</html>