<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
   <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="resources/theme/js/min.js"></script>
      <script src="resources/theme/js/min_ui.js"></script>

<html>
<head>
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
<script>
	
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid2 = true;
			var count=0;
			$('select[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid2 = false;
					count++;
					if(count == 1){
					$(this).css({
						"border" : "1px solid red",
					});
				}
				} 
			});
			if (isValid2 == false)
				e.preventDefault();
		});
	});
</script>
			<div class="box-list">
				<div class="item">
					<div class="row">
						<h3 class="text-center no-margin titleunderline">Saved Candidate Profile</h3>
                         <hr>
                         
						<div class="warning" style="width: 100%;text-align:left;">
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Success!</strong>
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
						 <div class="warning">
							<c:if test="${not empty message}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_info">
									<h1>Warning</h1>
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
						</div> 
						<!-- Industry Type= Company -->
						
				<div class="row">
					<form:form id="myForm" method="post" class="form-search-list"
						action="saveResumeSearch.html" commandName="saveResumeSearch">

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
						<c:if test="${!empty saveresume}">
						
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="saveresume" name="${saveresume}"
							requestURI="candidate_resume.html" pagesize="5" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" />  
							<display:column property="profiledescription" title="Job Title" />
							<display:column property="firstName" title="Name" />
							<display:column property="expYearandMonth" title="Experience" /> 
							<display:column property="dateandYear" title="Date" />
							<c:if test="${userType=='Employer'}">
								<display:column url="candidate_resume.html"
								media="html" paramId="id" paramProperty="id"
								title="ShortList">
								<a
									href="candidate_resume.html?id=${saveresume.profileId}">
									<i
									style="text-align: center;" class="btn btn-theme btn-xs btn-default">ShortList</i></a>
							</display:column>
							</c:if>
							
							
							<display:column url="candidate_view.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="jobseeker_profile_details.html?id=${saveresume.profileId}" >
									<i
									class=" fa fa-search" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
							 <display:column url="delete_shortlist_candidate.html"
								media="html" paramId="id" paramProperty="id"
								title="Delete">
								<a
									href="delete_shortlist_candidate.html?id=${saveresume.id}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
						</display:table>
					</div>
						</c:if>
						<div>
				<button id="btnCancel" onclick="history.go(-1);" type="button"
					class="btn btn-t-primary btn-theme">Back</button>

			</div>
					</div>
					<!-- pagination -->
				</div>
			</div>
	
</body>
</html>