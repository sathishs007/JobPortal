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

<title>MyJobKart</title>

<!--favicon-->
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">

<!-- bootstrap -->
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Icons -->
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- lightbox -->
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">


<!-- Themes styles-->
<link href="resources/theme/css/theme.css" rel="stylesheet">
<!-- Your custom css -->
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>MyJobKart|Jobseeker|saveJobs</title>




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
				<!--  <h3 class="margin-top">Applied Job History</h3> -->
				<h3 class="text-center no-margin titleunderline">Applied Job History</h3>
				<hr />
				<center>
					<div class="warning" style="width: 100%; text-align: left;">
						<c:if test="${not empty message}">
							<div class="alert alert-info" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Info!</strong>
								<c:out value="${message}"></c:out>
							</div>
							<br />
						</c:if>
					</div>
				</center>
				<c:if test="${empty message}">

					<ul id="myTab" class="nav nav-tabs flat-nav-tabs" role="tablist">
						<li style="background-color:${clickmonth}"><a
							href="jobseeker_appliedmonth.html" role="tab">Last 6 Months</a></li>
						<li style="background-color:${clickcompany}"><a
							href="jobseeker_appliedcompany.html" role="tab">Job By
								Company</a></li>
						<li style="background-color:${clicktitle}"><a
							href="jobseeker_appliedtitle.html" role="tab">Job By JobTitle</a></li>
					</ul>
					<br>
<!-- Jobseeker Applied jobs in 6Month -->


	<c:if test="${!empty appliedJob6month}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedJob6month" name="${appliedJob6month}"
							requestURI="jobseeker_appliedmonth.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							 <display:column property="SNo" title="SNo" />  
							<display:column property="companyName" title="Company Name" style="word-break:break-all;"/>
							<display:column property="jobTitle" title="Job Tittle" style="word-break:break-all;" />
							<display:column property="keywords" title="Key Skills" style="word-break:break-all;" />
							 <display:column property="eDate" title="Applied Date" />
								 <display:column url="search_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="search_job_details.html?id=${appliedJob6month.id}">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							 
							
						</display:table>
					</div>
				</c:if>
						  

<!-- Jobseeker applied By Company  -->

<c:if test="${!empty appliedCompany}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedCompany" name="${appliedCompany}"
							requestURI="jobseeker_appliedcompany.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="SNo" title="SNo" />  
							<display:column property="companyName" title="Company Name" style="word-break:break-all;"/>
							<display:column property="jobTitle" title="Job Tittle" style="word-break:break-all;" />
							<display:column property="keywords" title="Key Skills"  style="word-break:break-all;" />
							<%-- <display:column property="jobDescription" title="Job Descriptions" /> --%>
							 <display:column property="eDate" title="Applied Date" />
								 <display:column url="search_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="search_job_details.html?id=${appliedCompany.id}">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							 
							
						</display:table>
					</div>
				</c:if>



<c:if test="${!empty appliedJobTitle}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedJobTitle" name="${appliedJobTitle}"
							requestURI="jobseeker_appliedtitle.html" pagesize="10" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="SNo" title="SNo" />  
							<display:column property="companyName" title="Company Name" style="word-break:break-all;"/>
							<display:column property="jobTitle" title="Job Tittle" style="word-break:break-all;"/>
							<display:column property="keywords" title="Key Skills" style="word-break:break-all;" />
							<%-- <display:column property="jobDescription" title="Job Descriptions" /> --%>
							 <display:column property="eDate" title="Applied Date" />
								 <display:column url="search_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="search_job_details.html?id=${appliedJobTitle.id}">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
						</display:table>
					</div>
				</c:if>
				</c:if>				
			</div>
		</div>
	</div>	
</body>
</html>