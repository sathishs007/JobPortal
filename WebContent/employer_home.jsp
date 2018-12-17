<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>MyjobKart|employerHome</title>
</head>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<c:if test="${not empty sucessmessage}">
					<div class="alert alert-success fade in">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Success!</strong>
						<c:out value="${sucessmessage}"></c:out>
					</div>
				</c:if>
				<h3 class="text-center no-margin titleunderline">My DashBoard</h3>
				<hr />
				<div class="row">
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4><a href="employer_job_view.html">Total Job Post</a></h4>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<c:if test="${!empty jobpostcount}">
										<label class="my-body-font">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Job Posts:
											${jobpostcount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Active
											Job Posts: ${ActiveJobPost }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In-Active
											Job Posts: ${InActiveJobPost } <img
												src="resources/theme/images/jobs.png"
												class="img-rounded pull-right"
												style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px">
										</label>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-default">

							<div class="panel-heading">
								<div class="panel-title">
									<h4><a href="employer_applied_jobs.html">Applied Candidate Details</a></h4>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<c:if test="${!empty appliedjobcount}">
										<label class="my-body-font">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Applied
											Candidates: ${appliedjobcount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Short
											listed Candidates: ${ShortlistCount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnShortlisted
											Candidates: ${UnShortlistCount } <img
												src="resources/theme/images/applyjob.jpg"
												class="img-rounded pull-right"
												style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px">
										</label>
									</c:if>
								</div>
							</div>
						</div>
					</div>		
				
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4>Payment Valid ON</h4>
								</div>
							</div>
							<c:choose>
								<c:when test="${!empty enrolledList}">
									<c:forEach items="${enrolledList}" var="enrolleList">
										<div class="panel-body">
										<div>
											<label class="my-body-font">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Valid From: ${enrolleList.created }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Valid
												To: ${enrolleList.endDate } <br /> <br />
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											</label>
											 <img src="resources/theme/images/payment.jpeg"
													class="img-rounded pull-right"
													style="height:5%; width: 15%;">
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="panel-body">
										<label class="my-body-font">
											Valid From: 30days<br /> <br />Valid To: 30days <br /> <br />
											&nbsp;&nbsp;&nbsp;
										</label>
										<img
												src="resources/theme/images/payment.jpeg"
												class="img-rounded pull-right"
												style="height: 5%; width: 15%;">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<h4><a href="candidate_resume.html">Save Candidate Details</a></h4>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<c:if test="${!empty savejobcount}">
										<label class="my-body-font">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Saved Candidates:
											${savejobcount}<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selected
											Candidates: ${SelectedCount }<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnSelected
											Candidates: ${UnSelectedCount } <img
												src="resources/theme/images/save.jpeg"
												class="img-rounded pull-right"
												style="height: 5%; width: 15%; margin-top: -15%; margin-bottom: 20px">
										</label>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>