<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			$('input[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>
<html>
<head>
<title>MyjobKart|Jobseeker|Applied Jobs</title>
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
	
	<div class="box-list">
		<div class="item">
			<div class="row">
				<!-- <h3 class="margin-top">Applied Jobs</h3> -->
				<h3 class="text-center no-margin titleunderline">Applied Jobs</h3>
				<hr />
				<center>
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
					</div>
				</center>

				
				
					<div class="row">
						<form:form id="myForm" method="post" class="form-search-list"
							action="appliedJobSearch.html" commandName="appliedJobSearch">

							<div class="form-group col-md-9">
								<div class="form-group has-feedback">
									<form:input type="text" class="form-control "
										path="searchElement" placeholder="Location Name"/>
									<span class="glyphicon glyphicon-search form-control-feedback"></span>
									<form:errors path="searchElement" cssClass="error" />
								</div>
							</div>				
							<%-- <div class="col-md-3">
								<label class="color-white">Company Name</label>
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="Location Name"></form:input>
							</div> --%>
							<div class=" col-md-3">
								<button id="btnSubmit" class="btn btn-theme btn-success btn-block"
									style="width: 50%;">
									<small>Search</small>
								</button>
							</div>
						</form:form>
												
					</div>
					<center>
					<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty errormessage}">
					<div class="alert alert-info" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Info!</strong>
						<c:out value="${errormessage}"></c:out>
					</div>
				</c:if> 
				</div>
				</center>	
					
					
					
				<c:if test="${!empty appliedJobs}">
				
			<c:if test="${userType=='Jobseeker'}">	
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedJobs" name="${appliedJobs}"
							requestURI="jobseeker_applied_jobs.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="jobTitle" title="Job Title" />
							<display:column property="jobLocation" title="Location" />
							<display:column property="companyName" title="Company" />
							<display:column property="appliedDate" title="Date" />
								<display:column url="search_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="search_job_details.html?id=${appliedJobs.jobId}">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							<c:if test="${userType=='Jobseeker'}"> 
							 <display:column url="jobseeker_delete_applied_job.html?"
								media="html" paramId="id" paramProperty="id"
								title="Delete">
								<a
									href="jobseeker_delete_applied_job.html?id=${appliedJobs.id}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
							
							</c:if>
							
						</display:table>
					</div>
				</c:if>
				
				
				<c:if test="${userType=='admin'}">
				
				
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedJobs" name="${appliedJobs}"
							requestURI="admin_jobseeker_applied_jobs.html" pagesize="1" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="jobTitle" title="Job Title" />
							<display:column property="jobLocation" title="Location" />
							<display:column property="companyName" title="Company" />
							<display:column property="appliedDate" title="Date" />
								<display:column url="search_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="search_job_details.html?id=${appliedJobs.jobId}">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
						</display:table>
					</div>
				
				
				
				
				
				
				</c:if>	
					
				</c:if>
					
					<div>
							
							<c:if test="${userType=='admin'}">	
							<a href="admin_jobseekers.html"> <input type="button"
									value="Back" class="btn btn-t-primary btn-theme" />
									</a>
									</c:if>
							</div>
					
					
			
				
				
				
				
				
				
			</div>
		</div>
	</div>
	
</body>
</html>