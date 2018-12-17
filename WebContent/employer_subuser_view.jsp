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
				<h3 class="text-center no-margin titleunderline">Subuser
					Profile</h3>
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
				<!-- End Warning Message  -->
				<c:if test="${!empty subUserList}">
					<div class="pi-responsive-table-sm">
						<display:table id="data" name="${subUserList}"
							requestURI="/subuser_profile_view.html" pagesize="5"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sNo" title="SNo" />
							<%-- <display:column property="firstName" title="First Name" /> --%>



							<c:if test="${data.status =='Active'}">
								<display:column url="subuser_profile_status.html" media="html"
									paramId="id" title="First Name">
									<a
										href="subuser_profile_status.html?status=${data.status},${data.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: green;"> <c:out value="${data.firstName }"></c:out></a>

								</display:column>
							</c:if>

							<c:if test="${data.status =='De-Active'}">
								<display:column url="subuser_profile_status.html" media="html"
									paramId="id" title="First Name">
									<a
										href="subuser_profile_status.html?status=${data.status},${data.id}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: red;"> <c:out value="${data.firstName }"></c:out></a>

								</display:column>
							</c:if>





							<display:column property="emailAddress" title="Email Address" />
							<display:column property="contactNumber" title="Contact Number" />

							<%-- <display:column url="subuser_profile_status.html" media="html"
								paramId="id" paramProperty="id" title="Status">
								<a
									href="subuser_profile_status.html?status=${data.status},${data.id}"
									onclick="return confirm('Are you sure want to continue?')"
									style="text-align: center;" class="fa fa-thumbs-o-up">
								<c:out value="${data.status}"></c:out></a>
							</display:column> --%>

							<display:column url="subuser_update_profile.html" media="html"
								paramId="id" paramProperty="id" title="Edit">
								<a href="subuser_update_profile.html?id=${data.id}"><i
									style="text-align: center;" class="fa fa-pencil"></i></a>
							</display:column>

							<display:column url="subuser_delete_profile.html" media="html"
								paramId="id" paramProperty="id" title="Delete">
								<a href="subuser_delete_profile.html?id=${data.id}"
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

