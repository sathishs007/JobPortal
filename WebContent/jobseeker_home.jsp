<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="box-list">
	<div class="item">
		<div class="row">
			<h3 class="text-center margin-top titleunderline">My DashBoard</h3>
			<div class="warning" style="width: 100%; text-align: left;">
				<c:if test="${not empty message}">
					<div class="alert alert-success" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Success</strong>
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty ErrorMessage}">
					<div class="alert alert-info" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Info!</strong>
						<c:out value="${ErrorMessage}"></c:out>
					</div>
				</c:if>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								<h4>
									<a href="jobseeker_applied_jobs.html">Applied Job</a>
								</h4>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<c:if test="${!empty totalappliedcount}">
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Applied
										Job : ${totalappliedcount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Applied Job : ${activeappliedcount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
										Application : ${deactiveappliedcount}
									</label>
									<a href="jobseeker_applied_jobs.html"><img
										src="resources/theme/images/post.jpeg"
										class="img-rounded pull-right dashboard_icon"
										style="margin-top: 20px;"></a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								<h4><a href="jobseeker_saved_jobs.html">Save Job</a></h4>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<c:if test="${!empty  totalsavecount}">
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Saved
										Job : ${totalsavecount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Saved Job : ${totalactivesavecount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Applied
										Saved Job : ${totalapplysavecount} 
									</label>
									<a href="jobseeker_saved_jobs.html"><img
										src="resources/theme/images/save.jpeg"
										class="img-rounded pull-right dashboard_icon"
										style="margin-top: 20px;"></a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
		
				<div class="col-md-4 ">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								<h4><a href="jobseeker_profile_view.html">My Profile</a></h4>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<c:if test="${!empty totalprofilecount}">
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total
										Profile:${totalprofilecount}<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Profile :${profilecount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
										Profile:${inactiveprofilecount}
									</label>
									<a href="jobseeker_profile_view.html"><img
										src="imageLoginDisplay.html?id=${registerId}"
										class="img-rounded pull-right dashboard_icon"
										style="margin-top: 10px;"></a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 ">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								<h4><a href="jobseeker_view.html">Employer Views </a></h4>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<label class="my-body-font">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Employer Views
									 : ${viewJobseekerListSize} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Profile Views : ${viewTotalJobseeker}
									<br /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</label> <a href=""><img
									src="resources/theme/images/shortlist2.jpeg"
									class="img-rounded pull-right dashboard_icon"
									style="margin-top: 20px;"></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
