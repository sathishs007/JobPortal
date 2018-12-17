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
<title>MyjobKart|Jobseeker|home</title>
</head>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<!-- <h3 class="margin-top">My Jobs</h3> -->
				<h3 class="text-center no-margin titleunderline">View Company
					Entity</h3>
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

				<c:if test="${!empty companyEntityList}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="data" name="${companyEntityList}"
							requestURI="/admin_company_view.html" pagesize="5" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="companyName" title="Customer Name" />
							<display:column property="email" title="Email" />
							<display:column url="admin_update_company_entity.html"
								media="html" paramId="id" paramProperty="companiesId"
								title="Edit">
								<a
									href="admin_update_company_entity.html?companiesId=${data.companiesId}"><i
									style="text-align: center;" class=" fa fa-pencil"></i></a>
							</display:column>
							<display:column url="admin_delete_company_entity.html"
								media="html" paramId="id" paramProperty="companiesId"
								title="Delete">
								<a
									href="admin_delete_company_entity.html?companiesId=${data.companiesId}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
							<td class="td-border"><a
											href="subuser_delete_profile.html?id=${subuser.id}"
											onclick="return confirm('Are you sure you want to Delete?')"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-trash"></li></a></td>
						</display:table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>