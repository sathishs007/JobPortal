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
<title>MyjobKart|Employer|home</title>

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
				<h3 class="text-center no-margin titleunderline">Short List
					Candidates</h3>
				<hr />

				<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty sucessmessage}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Success!</strong>
							<c:out value="${sucessmessage}"></c:out>
						</div>
					</c:if>

					<c:if test="${not empty Infomessage}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Info!</strong>
							<c:out value="${Infomessage}"></c:out>
						</div>
					</c:if>
				</div>
				
				
					<div class="row">
						<form:form id="myForm" method="post" class="form-search-list"
							action="shortlistSearch.html" commandName="shortlistSearch">

							<div class="form-group col-md-9">
								<div class="form-group has-feedback">
									<form:input type="text" class="form-control "
										path="searchElement" placeholder="Job Title"/>
									<span class="glyphicon glyphicon-search form-control-feedback"></span>
									<form:errors path="searchElement" cssClass="error" />
								</div>
							</div>				
							<div class=" col-md-3">
								<button id="btnSubmit" class="btn btn-theme btn-success btn-block"
									style="width: 50%;">
									<small>Search</small>
								</button>
							</div>
						</form:form>
					</div>
				
				
				

				<c:if test="${!empty shortListCandidate}">
					<div class="pi-section-w pi-section-white piTooltips">


						<display:table id="data" name="${shortListCandidate}"
							requestURI="/admin_jobseeker_profiles.html" pagesize="10"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">


							<%-- <display:column property="SNo" title="SNO" /> --%>
							<display:column property="jobTitle" title="Job Title" />
							<display:column property="keySkills" title="Key Skills" />
							<display:column property="firstName" title="First Name" />
							<display:column property="emailId" title="Email" />

							<display:column url="short_list_candidate.html" media="html"
								paramId="id" paramProperty="emailId" title="View">
								<a href="jobseeker_profile_details.html?id=${data.profileId}">
									<i class=" fa fa-search"
									class="btn btn-theme btn-xs btn-default"></i>
								</a>

							</display:column>


							<display:column url="short_list_candidate_delete.html"
								media="html" paramId="id" paramProperty="emailId" title="Delete">
								<a
									href="short_list_candidate_delete.html?id=${data.shortlistId}"
									onclick="return confirm('Are you sure you want to continue?')">
									<i class=" fa fa-trash"
									class="btn btn-theme btn-xs btn-default"></i>
								</a>
							</display:column>

						</display:table>

					</div>
				</c:if>
				<div>
					<button id="btnCancel" onclick="history.go(-1);" type="button"
						class="btn btn-t-primary btn-theme">Back</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>