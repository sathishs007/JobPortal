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
				<!-- <h3 class="margin-top">My Profile</h3> -->
				<h3 class="text-center no-margin titleunderline">My Profile</h3>
				<hr />

				<div class="warning" style="width: 100%; text-align: left;">

					<c:if test="${not empty successmessage}">
						<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Success</strong>
							<c:out value="${successmessage}"></c:out>
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
						action="search.html" commandName="search">
						<div class="form-group col-md-9">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="Location Name" />
								<span class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</div>
						<div class=" col-md-3 ">
							<button id="btnSubmit"
								class="btn btn-theme btn-success btn-block">
								<small>Search</small>
							</button>
						</div>
					</form:form>
				</div>





				<c:if test="${!empty searchJobseeker}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="searchJobseeker" name="${searchJobseeker}"
							requestURI="jobseeker_profile_view.html" pagesize="10"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

							<display:column property="SNo" title="SNo" />

							<c:if test="${userType !='Jobseeker'}">
								<display:column property="firstName" title="Jobseeker Name" />

								<c:if test="${searchJobseeker.status =='Active'}">
									<display:column url="admin_jobseekers_active.html" media="html"
										paramId="id" title="Job Title">
										<a href="" style="color: green;"> <c:out
												value="${searchJobseeker.profiledescription }"></c:out></a>

									</display:column>
								</c:if>

								<c:if test="${searchJobseeker.status =='De-Active'}">
									<display:column url="admin_jobseekers_active.html" media="html"
										paramId="id" title="Job Title">
										<a href="" style="color: red;"> <c:out
												value="${searchJobseeker.profiledescription }"></c:out></a>

									</display:column>
								</c:if>
							</c:if>

							<c:if test="${userType=='Jobseeker'}">
								<c:if test="${searchJobseeker.status =='Active'}">
									<display:column url="admin_jobseekers_active.html" media="html"
										paramId="id" title="Job Title">
										<a
											href="jobseeker_profile_status.html?status=${searchJobseeker.status},${searchJobseeker.id}"
											onclick="return confirm('Are you sure you want to continue?')"
											style="color: green;"> <c:out
												value="${searchJobseeker.profiledescription }"></c:out></a>

									</display:column>
								</c:if>


								<c:if test="${searchJobseeker.status =='De-Active'}">
									<display:column url="admin_jobseekers_active.html" media="html"
										paramId="id" title="Job Title">
										<a
											href="jobseeker_profile_status.html?status=${searchJobseeker.status},${searchJobseeker.id}"
											onclick="return confirm('Are you sure you want to continue?')"
											style="color: red;"> <c:out
												value="${searchJobseeker.profiledescription }"></c:out></a>

									</display:column>
								</c:if>
							</c:if>

							<display:column property="keySkills" title="Key Skills"
								style="word-break:break-all;" />
							<display:column property="location" title="Location" />

							<c:if test="${userType=='Jobseeker'}">
								<display:column url="jobseeker_update_personal.html"
									media="html" paramId="id" paramProperty="id" title="Personal">
									<a
										href="jobseeker_update_personal.html?id=${searchJobseeker.id}">
										<i style="text-align: center;" class="fa fa-pencil"></i>
									</a>
								</display:column>

								<display:column url="jobseeker_update_educational.html"
									media="html" paramId="id" paramProperty="id" title="Education">
									<a
										href="jobseeker_update_educational.html?id=${searchJobseeker.id}">
										<i style="text-align: center;" class="fa fa-pencil"></i>
									</a>
								</display:column>

								<display:column url="jobseeker_update_experience.html"
									media="html" paramId="id" paramProperty="id"
									title="Professional">
									<a
										href="jobseeker_update_experience.html?id=${searchJobseeker.id}">
										<i style="text-align: center;" class="fa fa-pencil"></i>
									</a>
								</display:column>

								<display:column url="jobseeker_delete_profile.html" media="html"
									paramId="id" paramProperty="id" title="Delete">
									<a
										href="jobseeker_delete_profile.html?id=${searchJobseeker.id}"
										"
									onclick="return confirm('Are you sure you want to Delete?')"><i
										class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
								</display:column>

							</c:if>

							<display:column url="jobseeker_profile_details.html" media="html"
								paramId="id" paramProperty="id" title="View">
								<a
									href="jobseeker_profile_details.html?id=${searchJobseeker.id}">
									<i style="text-align: center;" class=" fa fa-search"></i>
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

	<!-- </div>
	</div> -->
</body>
</html>