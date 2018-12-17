<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

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

				<div class="text-center">
					<h3 class="text-center no-margin titleunderline">Registered
						Employer Details</h3>

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
				</div>
				<div class="row">
						<form:form id="myForm" method="post" class="form-search-list"
							action="employerSearch.html" commandName="employerSearch">

							<div class="form-group col-md-9">
								<div class="form-group has-feedback">
									<form:input type="text" class="form-control "
										path="searchElement" placeholder="Employer Name"/>
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
					
				<c:if test="${!empty registeredEmployer}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="data" name="${registeredEmployer}"
							requestURI="/employer_details.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sNo" title="SNo" />
							<c:if test="${data.active =='Active'}">
							<display:column url="emp_profile_Active.html" media="html"
								paramId="id" paramProperty="id" title="Employer" >
								<a	href="emp_profile_Active.html?status=${data.active},${data.id}" style="color:green;">
									<c:out value="${data.firstName }"></c:out></a>
									
							</display:column>
							</c:if>
							
							<c:if test="${data.active =='De-active'}">
							<display:column url="emp_profile_Active.html" media="html"
								paramId="id" paramProperty="id" title="Employer" >
								<a	href="emp_profile_Active.html?status=${data.active},${data.id}" style="color:red;">
									<c:out value="${data.firstName }"></c:out></a>
									
							</display:column>
							</c:if>
							
							<display:column url="employer_view_details.html" media="html"
								paramId="id" paramProperty="id" title="Company" >
								<a	href="employer_view_details.html?id=${data.id}">
									<c:out value="${data.companyName }"></c:out></a>
									
							</display:column>
							
							<%-- <display:column property="webSite" title="Website" /> --%>
							
							
							
							
							
							<display:column url="employer_job_view.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Job Post" >
								<a	href="employer_job_view.html?email=${data.emailAddress}">
									<i style="text-align: center;" class=" fa fa-search"></i></a>
									
							</display:column> 
							
							<display:column url="candidate_resume.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Saved" >
								<a	href="candidate_resume.html?email=${data.emailAddress}">
									<i style="text-align: center;" class=" fa fa-search"></i></a>
									
							</display:column>
							
							<display:column url="employer_applied_jobs.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Applied" >
								<a	href="employer_applied_jobs.html?email=${data.emailAddress}">
									<i style="text-align: center;" class=" fa fa-search"></i></a>
									
							</display:column> 
							
							<display:column url="short_list_candidate_view.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Shortlisted" >
								<a	href="short_list_candidate_view.html?email=${data.emailAddress}">
									<i style="text-align: center;" class=" fa fa-search"></i></a>
									
							</display:column>
							
							<display:column url="employer_renewal_details.html" media="html"
								paramId="id" paramProperty="emailAddress" title="Paid History" >
								<a	href="employer_renewal_details.html?email=${data.emailAddress}">
									<i style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							<display:column url="delete_employer.html" media="html"
								paramId="id" paramProperty="id" title="Delete">
								<a href="delete_employer.html?id=${data.id}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>

						</display:table>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	
</body>
</html>