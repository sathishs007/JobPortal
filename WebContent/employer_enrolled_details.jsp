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
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="text-center">
					<!-- <h3 class="margin-top">
								<i class="fa fa-user"></i>Enrollment Details
							</h3> -->
					<h3 class="text-center no-margin titleunderline">Enrollment
						Details</h3>
					<hr>
				</div>
				<div class="warning">
					<c:if test="${not empty message}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Info!</strong>
							<c:out value="${message}"></c:out>
						</div>
					</c:if>
				</div>
				<c:if test="${!empty enrolledList}">
					<div class="pi-responsive-table-sm">
						<display:table id="data" name="${enrolledList }"
							requestURI="/employer_enrolled_details.html" pagesize="5"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

							<display:column property="id" title="Enrolled Id" />
							<display:column property="selectProduct" title="Product Name" />
							<display:column property="productType" title="Product Type" />
							<display:column property="created" title="Valid From" />
							<display:column property="endDate" title="Valid End" />

							<display:column url="view_employer_enrolled_details.html"
								media="html" paramId="id" paramProperty="id" title="View">
								<a href="view_employer_enrolled_details.html?id=${data.id}"><i
									style="text-align: center;" class="fa fa-search"></i></a>
							</display:column>

							<display:column url="employer_delete_enrolled_details.html"
								media="html" paramId="id" paramProperty="id" title="Delete">
								<a href="employer_delete_enrolled_details.html?id=${data.id}"
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