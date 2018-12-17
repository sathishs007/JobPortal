<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Myjobkart|employerHome</title>
</head>
<body>
	<!-- <div class="body-content clearfix">
		<div class="bg-color"> -->
	<!-- <div class="container"> -->
	<!-- <div> -->
	<!-- <div class="col-sm-12 col-xs-12 dashborad-height"> -->
	<div class="box-list">
		<div class="item">


			<div class="row">

				<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty message}">
						<c:if test="${not empty message}">
								<div class="alert alert-success" role="alert">
									<button class="close" type="button" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Info</strong> ${message}
									</div>
							</c:if>
					</c:if>
				</div>
				<h3 class="text-center no-margin titleunderline">My DashBoard</h3>
				<hr />
				<div class="row">
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4>Registered Jobseeker</h4>
									<a href="admin_jobseekers.html"><img
										src="resources/theme/images/more.png"
										class="img-rounded pull-right"
										style="height: 5%; width: 5%; margin-top: -5%; margin-left: 20%"></a>
								</div>
							</div>
							<c:if test="${!empty RegisteredCount}">
								<div class="panel-body">

									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Registered
										Jobseeker:${RegisteredCount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Jobseeker:${ActiveCount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
										Jobseeker:${InactiveCount} <a href=""><img
											src="resources/theme/images/registration1.jpeg"
											class="img-rounded pull-right"
											style="height: 5%; width: 15%; margin-top: -16%; margin-bottom: 20px"></a>

									</label>

								</div>
							</c:if>

						</div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4>Registered Employer</h4>
									<a href="employer_details.html"><img
										src="resources/theme/images/more.png"
										class="img-rounded pull-right"
										style="height: 5%; width: 5%; margin-top: -5%; margin-bottom: 15px; margin-left: 20%"></a>
								</div>
							</div>
							<div class="panel-body">
								<c:if test="${!empty EmployerRegisteredCount}">
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Registered
										Employer:${EmployerRegisteredCount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Employer:${EmployerActiveCount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
										Employer:${EmployerInactiveCount} <a href=""><img
											src="resources/theme/images/employer.jpg"
											class="img-rounded pull-right"
											style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px"></a>


									</label>
								</c:if>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4>Total Applied Jobs</h4>
									<a href=""><img src="resources/theme/images/more.png"
										class="img-rounded pull-right"
										style="height: 5%; width: 5%; margin-top: -5%; margin-bottom: 10px; margin-left: 20%"></a>
								</div>
							</div>
							<c:if test="${!empty appliedjobcount}">
								<div class="panel-body">
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Applied
										Candidates:${appliedjobcount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Shortlisted Candidates: ${ShortlistCount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnShortlisted
										Candidates: ${UnShortlistCount } <a href=""><img
											src="resources/theme/images/applyjob.jpg"
											class="img-rounded pull-right"
											style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px"></a>

									</label>

								</div>
							</c:if>

						</div>
						<%-- <div class="panel panel-default">
											<div class="panel-heading">
												<div class="panel-title">
													<h4>
														<a>Applied Job</a> <a href=""><img
															src="resources/theme/images/more.png"
															class="img-rounded pull-right"
															style="height: 5%; width: 5%; margin-top: 3%; margin-bottom: 10px; margin-left: 20%"></a>
													</h4>
												</div>
											</div>
											<div class="panel-body">
												<c:if test="${!empty totalappliedcount}">
													<h6>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Applied
														Job:${totalappliedcount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
														Applied Job:${activeappliedcount} <br /> <br />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
														Application:${deactiveappliedcount} <a
															href="jobseeker_applied_jobs.html"><img
															src="resources/theme/images/post.jpeg"
															class="img-rounded pull-right"
															style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px"></a>


													</h6>
												</c:if>
											</div>

										</div> --%>
					</div>

					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4>Total Job Post</h4>
									<a href="admin_job_post.html"><img
										src="resources/theme/images/more.png"
										class="img-rounded pull-right"
										style="height: 5%; width: 5%; margin-top: -5%; margin-bottom: 10px; margin-left: 20%"></a>
								</div>
							</div>
							<div class="panel-body">
								<c:if test="${!empty jobpostcount}">
									<label class="my-body-font">

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Job Posts:
										${jobpostcount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
										Job Posts: ${ActiveJobPost }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
										Job Posts: ${InActiveJobPost } <a href=""><img
											src="resources/theme/images/jobs.png"
											class="img-rounded pull-right"
											style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px"></a>

									</label>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="row">
									<div class="col-md-6">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div class="panel-title">
													<h4>Save Job</h4>
													<a href=""><img src="resources/theme/images/more.png"
														class="img-rounded pull-right"
														style="height: 5%; width: 5%; margin-top: -5%; margin-bottom: 10px; margin-left: 20%"></a>
												</div>
											</div>
											<div class="pannel-body">
												<c:if test="${!empty  totalsavecount}">

													<h6>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Saved
														Job:${totalsavecount} <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
														Saved Job:${totalactivesavecount} <br /> <br />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
														Saved:${totaldeactivesavecount} <br /> <br />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Applied in Saved
														Jobs:${totalapplysavecount} <a
															href="jobseeker_saved_jobs.html"><img
															src="resources/theme/images/save.jpeg"
															class="img-rounded pull-right"
															style="height: 50px; width: 50px; margin-top: -15%; margin-bottom: 20px"></a>

													</h6>
												</c:if>
											</div>

										</div>
									</div> --%>
				<%-- <div class="col-md-6">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div class="panel-title">
													<h4>Total Applied Jobs</h4>
													<a href=""><img src="resources/theme/images/more.png"
														class="img-rounded pull-right"
														style="height: 5%; width: 5%; margin-top: -5%; margin-bottom: 10px; margin-left: 20%"></a>
												</div>
											</div>
											<c:if test="${!empty appliedjobcount}">
												<div class="panel-body">
													<h6>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Applied
														Candidates:${appliedjobcount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														Shortlisted Candidates: ${ShortlistCount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnShortlisted
														Candidates: ${UnShortlistCount } <a href=""><img
															src="resources/theme/images/applyjob.jpg"
															class="img-rounded pull-right"
															style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px"></a>

													</h6>

												</div>
											</c:if>

										</div>
									</div> --%>
			</div>
		</div>
	</div>

	<!-- </div>
			</div> -->
	<!-- </div>
	</div> -->

</body>
</html>