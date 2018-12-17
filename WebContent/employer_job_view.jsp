<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyjobKart|Jobseeker|home</title>

<style>
span.pagebanner {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	width: 100%;
	margin-top: 10px;
	display: block;
	border-bottom: none;
}

span.pagelinks {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 6px 4px 6px 4px;
	width: 100%;
	display: block;
	border-top: none;
	margin-bottom: -5px;
}
</style>

<link rel="stylesheet" type="text/css"
	href="resources/theme/css/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/tables.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/theme-custom.css" />

</head>
<body>
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<!-- <h3 class="margin-top">My Jobs</h3> -->
				<h3 class="text-center no-margin titleunderline">My Jobs</h3>
				<hr />

				<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty sucessmessage}">
						<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Success</strong>
							<c:out value="${sucessmessage}"></c:out>
						</div>
					</c:if>

					<c:if test="${not empty infomessage}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Info!</strong>
							<c:out value="${infomessage}"></c:out>
						</div>
					</c:if>
				</div>

				<div class="row">
					<form:form id="myForm" method="post" class="form-search-list"
						action="viewJobpostsearch.html" commandName="viewJobpostsearch">

						<div class="form-group col-md-9">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="Job Title" />
								<span class="glyphicon glyphicon-search form-control-feedback"></span>
								<form:errors path="searchElement" cssClass="error" />
							</div>
						</div>
						<div class=" col-md-3">
							<button id="btnSubmit"
								class="btn btn-theme btn-success btn-block" style="width: 50%;">
								<small>Search</small>
							</button>
						</div>
					</form:form>
				</div>
				<c:if test="${!empty JobDescription}">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${JobDescription}"
								requestURI="/employer_job_view.html" pagesize="10"
								export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
								<display:column property="sNo" title="SNo" />
								<%-- <display:column property="companyName" title="Company Name" /> --%>

								
									<c:if test="${data.status =='Active'}">
										<display:column url="profile_status.html" media="html"
											paramId="id" title="Company Name">
											<a
												href="job_post_profile_status.html?status=${data.status},${data.id}"
												onclick="return confirm('Are you sure you want to continue?')"
												style="color: green;"> <c:out
													value="${data.companyName }"></c:out></a>

										</display:column>
										</c:if>
										
										<c:if test="${data.status =='De-Active'}">
											<display:column url="profile_status.html" media="html"
												paramId="id" title="Company Name">
												<a
													href="job_post_profile_status.html?status=${data.status},${data.id}"
													onclick="return confirm('Are you sure you want to continue?')"
													style="color: red;"> <c:out
														value="${data.companyName }"></c:out></a>

											</display:column>
										</c:if>
									
									<display:column property="jobTitle" title="Job Title" />
									<display:column property="jobLocation" title="Job Location" />
                              <c:if test="${userType=='Employer'}">
									<display:column url="employer_clone_jobpost.html" media="html"
										paramId="id" paramProperty="id" title="Clone">
										<a href="employer_clone_jobpost.html?id=${data.id}"><i
											style="text_align: center;"
											class="btn btn-theme btn-xs btn-default">Clone</i></a>
									</display:column>

									<display:column url="employer_update_jobposting.html"
										media="html" paramId="id" paramProperty="id" title="Edit">
										<a href="employer_update_jobposting.html?id=${data.id}"><i
											style="text-align: center;" class="fa fa-pencil"></i></a>
									</display:column>
								</c:if>
								
								<c:if test="${userType=='Employer'}">

									<display:column url="employer_delete_jobposting.html"
										media="html" paramId="id" paramProperty="id" title="Delete">
										<a href="employer_delete_jobposting.html?id=${data.id}"
											onclick="return confirm('Are you sure you want to Delete?')"><i
											class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
									</display:column>

								</c:if>
								<display:column url="employer_jobpost_details.html" media="html"
									paramId="id" paramProperty="id" title="View">
									<a href="search_job_details.html?id=${data.id}"><i
										style="text-align: center;" class="fa fa-search"></i></a>
								</display:column>

							</display:table>

						</div>
					</div>

				</c:if>
				<!-- pagination -->

				<div>

					<c:if test="${userType=='admin'}">
						<a href="employer_details.html"> <input type="button"
							value="Back" class="btn btn-t-primary btn-theme" />
						</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>