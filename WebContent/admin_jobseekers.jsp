<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/tables.css" />
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

</head>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Registered
					Jobseekers</h3>
				<hr />

				<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty message}">
						<div class="alert alert-success" role="alert">
							<button class="close" type="button" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Success</strong> ${message}
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
						action="jobseekerSearch.html" commandName="jobseekerSearch">

						<div class="form-group col-md-9">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="First Name" />
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


				<c:if test="${!empty registeredList}">
					<div class="pi-section-w pi-section-white piTooltips">

						<display:table id="data" name="${registeredList}"
							requestURI="/admin_jobseekers.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

							<display:column property="SNo" title="S.No" />

							<c:if test="${data.active =='Active'}">
								<display:column url="admin_jobseekers_active.html" media="html"
									paramId="id" paramProperty="firstName" title="Name">
									<a
										href="admin_jobseekers_active.html?status=${data.status},${data.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: green;"> <c:out value="${data.firstName }"></c:out></a>

								</display:column>
							</c:if>

							<c:if test="${data.active =='De-active'}">
								<display:column url="admin_jobseekers_active.html" media="html"
									paramId="id" paramProperty="firstName" title="Name">
									<a
										href="admin_jobseekers_active.html?status=${data.status},${data.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: red;"> <c:out value="${data.firstName }"></c:out></a>

								</display:column>
							</c:if>


							<%-- <display:column property="firstName" title="Name" /> --%>
							<display:column property="emailAddress" title="Email" />
							<display:column property="mobileNo" title="Mobile" />

							<c:if test="${userType !='Jobseeker'}">
								<c:if test="${data.active =='Active'}">
									<display:column url="jobseeker_profile_view.html" media="html"
										paramId="email" paramProperty="emailAddress" title="Profile">
										<a
											href="jobseeker_profile_view.html?email=${data.emailAddress}">
											<i style="text-align: center;" class=" fa fa-search"></i>
										</a>
									</display:column>
								</c:if>

								<c:if test="${data.active !='Active'}">

									<display:column url="jobseeker_profile_view.html" media="html"
										paramId="email" paramProperty="emailAddress" title="Profile">
										<a
											href="jobseeker_profile_view.html?email=${data.emailAddress}">
											<i style="text-align: center;" class=" fa fa-search"></i>
										</a>
									</display:column>
								</c:if>
							</c:if>

							<display:column url="admin_jobseeker_saved_jobs.html"
								media="html" paramId="id" paramProperty="firstName"
								title="Saved Jobs">
								<a href="admin_jobseeker_saved_jobs.html?RegisterId=${data.id}">
									<i style="text-align: center;" class=" fa fa-search"></i>
								</a>
							</display:column>

							<display:column url="admin_jobseeker_applied_jobs.html"
								media="html" paramId="id" paramProperty="firstName"
								title="Applied Jobs">
								<a
									href="admin_jobseeker_applied_jobs.html?RegisterId=${data.id}">
									<i style="text-align: center;" class=" fa fa-search"></i>
								</a>

							</display:column>



							<%-- <display:column url="admin_jobseeker_details.html" media="html"
								paramId="id" paramProperty="emailAddress" title="View">
								<a href="admin_jobseeker_details.html?id=${data.id}"><i
									style="text-align: center;" class="fa fa-search"></i></a>
							</display:column> --%>

							<c:if test="${userType !='Jobseeker'}">
								<c:if test="${data.active =='Active'}">

									<display:column media="html" paramId="email" paramProperty="id"
										title="Activity">
										<a
											href="jobseeker_report_view.html?email=${data.emailAddress}">
											<i style="text-align: center;" class=" fa fa-search"></i>
										</a>
									</display:column>
								</c:if>

								<c:if test="${data.active !='Active'}">

									<display:column url="jobseeker_report_view.html" media="html"
										paramId="email" paramProperty="id" title="Jobseekers Activity">
										<i style="text-align: center;" class=" fa fa-search"></i>
									</display:column>
								</c:if>



							</c:if>

							<display:column url="admin_delete_jobseeker.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Delete">
								<a href="admin_delete_jobseeker.html?id=${data.id}"
									onclick="return confirm('Are you sure you want to continue?')"><i
									style="text-align: center;" class="fa fa-trash"></i></a>
							</display:column>



						</display:table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>