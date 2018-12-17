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
<title>Myjobkart|Admin|Jobpost</title>

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
							<h3 class="text-center no-margin titleunderline">Renewal Employer Details</h3>
				            <hr />
						</div>
						
							<div class="warning" style="width: 100%; text-align: left;">
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
				
				<c:if test="${!empty renewalEmployer}">
					<div class="pi-section-w pi-section-white piTooltips">

						<display:table id="data" name="${renewalEmployer}"
							requestURI="/renewal_admin_employeers.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							
							<display:column property="sNo" title="SNO" />
							<display:column property="firstName" title="Name" />
							<display:column property="companyName" title="Company" />
							<display:column property="webSite" title="Website" />
							<display:column property="totalDate" title="Renewal Date" />
							<display:column  media="html"
								paramId="id" paramProperty="emailAddress" title="Days remaining for renewal">
								<c:choose>
														<c:when test="${data.totalDate>'0'}">
															<center>
																<a class="btn btn-xs btn-theme btn-success"> Already
																	paid</a>
															</center>
														</c:when>
														<c:otherwise>
															<center>
																<a class="btn btn-xs btn-theme btn-success">Not Paid</a>
															</center>
														</c:otherwise>
													</c:choose>
							</display:column>
							</display:table>
					</div>
				</c:if>
			</div>
		</div>
</body>
</html>