<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
	<!-- wrapper page -->
	<!-- end main-header -->


	<!-- body-content -->
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="text-center">
					<!-- <h3 class="margin-top">
								<i class="fa fa-user"></i>Renewal Details
							</h3> -->
					<h3 class="text-center no-margin">Renewal Details</h3>
					<hr>
				</div>
				<c:if test="${not empty message}">
					<div class="error">${message}</div>
				</c:if>

				<c:if test="${!empty employerRenewalDetails}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="data" name="${employerRenewalDetails}"
							requestURI="/employer_renewal_details.html" pagesize="5"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sNo" title="SNo" />
							<display:column property="firstName" title="Name" />
							<display:column property="emailAddress" title="Email" />
							<display:column property="totalDate" title="Renewal Date" />

							<display:column url="product_services.html" media="html"
								paramId="id" paramProperty="totalDate" title="Payment">
							<c:choose>
														<c:when test="${data.totalDate>'0'}">
															<center>
																<a class="btn btn-xs btn-theme btn-success"> Already
																	paid</a>
															</center>
														</c:when>
														<c:otherwise>
															<center>
																<a href="product_services.html"
																	class="btn btn-xs btn-theme btn-success">Pay Now </a>
															</center>
														</c:otherwise>
													</c:choose>
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