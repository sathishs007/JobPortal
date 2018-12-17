<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>


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
				<h3 class="text-center no-margin titleunderline">View Employer
					Report</h3>
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
				<form:form id="myForm" method="post" class="form-search-list"
						action="companyNameSearch.html" commandName="companyNameSearch">
						<div class="row">
							<div class=" col-md-8 ">
								<form:input type="text" class="form-control "
									path="companyName" placeholder="Company Name"></form:input>
									</div>
                        <div class=" col-md-4 ">
								<button id="btnSubmit"
									class="btn btn-theme btn-success btn-block">
									<small>Search</small>
								</button>
							</div>
							</div>
					</form:form>

				

				<c:if test="${!empty companyList}">
					<div class="pi-responsive-table-sm">
						<c:if test="${!empty companyList}">
							<display:table id="data" name="${companyList}"
								requestURI="/admin_employer_history.html" pagesize="10"
								export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
								<display:column property="sNo" title="S.NO" />
								<display:column property="companyName" title="Company Name" />
							
							<display:column url="/employer_report_view.html" media="html"
									paramId="companiesId" paramProperty="companiesId" title="Emploer History">
									<a href="employer_report_view.html?id=${data.companiesId}"><li
											class=" fa fa-search"></li></a>
								</display:column>
							
							</display:table>
						</c:if>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>