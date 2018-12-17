<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>

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
				<h3 class="text-center no-margin titleunderline">Contacts</h3>
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
						action="contactSearch.html" commandName="contactSearch">

						<div class="form-group col-md-9">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control "
									path="searchElement" placeholder="Name" />
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
				<c:if test="${!empty contactList}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="contact" name="${contactList}"
							requestURI="view_contact.html" pagesize="5" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" />
							<display:column property="name" title="Name" />
							<display:column property="email" title="Email" />
							<display:column property="phoneno" title="Phone Number" />
							<display:column property="contactCreatedDate" title="Created" />
							
							<display:column url="view_contact_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="view_contact_details.html?id=${contact.id}"><i
									style="text-align: center;" class="fa fa-search"></i></a>
							</display:column>
							
							 <display:column url="view_contact_delete.html"
								media="html" paramId="id" paramProperty="id"
								title="Delete">
								<a
									href="view_contact_delete.html?id=${contact.id}"
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