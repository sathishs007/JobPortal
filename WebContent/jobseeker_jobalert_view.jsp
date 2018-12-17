<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
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
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">My JobAlert</h3>
				<hr />
				<!-- Start Warning Message  -->

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

				<!-- End Warning Message  -->

				<div class="row">
					<form:form id="myForm" method="post" class="form-search-list"
						action="viewJobalerSearch.html" commandName="viewJobalerSearch">

						<div class="form-group col-md-9">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="Key Skill" />
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


				<c:if test="${!empty jobAlertBoList}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="jobAlertList" name="${jobAlertBoList}"
							requestURI="jobseeker_jobalert_view.html" pagesize="5"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" />


							<%-- 	<display:column property="keySkills" title="Key Skills" /> --%>

							<c:if test="${jobAlertList.status =='Active'}">
								<display:column url="jobseeker_jobalert_status.html"
									media="html" paramId="status" title="Key Skills">
									<a
										href="jobseeker_jobalert_status.html?status=${jobAlertList.status}&&id=${jobAlertList.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: green;"> <c:out
											value="${jobAlertList.keySkills }"></c:out></a>

								</display:column>
							</c:if>

							<c:if test="${jobAlertList.status =='De-Active'}">
								<display:column url="jobseeker_jobalert_status.html"
									media="html" paramId="status" title="Key Skills">
									<a
										href="jobseeker_jobalert_status.html?status=${jobAlertList.status}&&id=${jobAlertList.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: red;"> <c:out
											value="${jobAlertList.keySkills }"></c:out></a>

								</display:column>
							</c:if>
							<display:column property="preferredLocation" title="Location" />
							<display:column property="alertDate" title="Date" />
							<display:column url="jobseeker_jobalert_details.html"
								media="html" paramId="id" paramProperty="id" title="View">
								<a href="jobseeker_jobalert_details.html?id=${jobAlertList.id}">
									<i style="text-align: center;" class=" fa fa-search"></i>
								</a>
							</display:column>
							<display:column url="jobseeker_update_jobalert.html" media="html"
								paramId="id" paramProperty="id" title="Edit">
								<a href="jobseeker_update_jobalert.html?id=${jobAlertList.id}">
									<i style="text-align: center;" class="fa fa-pencil"></i>
								</a>
							</display:column>
							<display:column
								url="jobseeker_delete_jobalert.html?id=${jobAlertList.id}"
								media="html" paramId="id" paramProperty="id" title="Delete">
								<a href="jobseeker_delete_jobalert.html?id=${jobAlertList.id}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
						</display:table>
					</div>
				</c:if>

			</div>
			<!-- pagination -->
		</div>
	</div>

</body>
</html>