<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
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
								<i class="fa fa-user"></i>Employer View History
							</h3> -->
							<h3 class="text-center no-margin titleunderline">Employer View History</h3>
								<hr />
						</div>
						<center><div class="warning" style="width: 100%;text-align:left;">
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
							</div></center>
					
						<c:if test="${ empty Infomessage}">
						<ul id="myTab" class="nav nav-tabs flat-nav-tabs"
							style="width: 103%; margin-left: -14px">
							<li style="background-color:${viewhistory}"><a href="jobseeker_view.html">All </a></li>
							<li style="background-color:${viewday}"><a href="jobseeker_view_day.html">Last 10 days</a></li>
							<li style="background-color:${view20day}"><a href="jobseeker_view_day20.html">Last 20 days </a></li>
							<li style="background-color:${view30day}"><a href="jobseeker_view_day30.html">Last 30 days </a></li>
						</ul>
						<br />
						  
						  
						  
						  
						  <center><div class="warning" style="width: 100%;text-align:left;">
							<c:if test="${not empty days}">
								<div class="alert alert-info" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								 <strong>Info!</strong>
									<c:out value="${days}"></c:out>
								</div>
							</c:if>
							</div></center>
						</c:if>  
						  
						  
						  
						  
				<c:if test="${!empty appliedJobs}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="appliedJobs" name="${appliedJobs}"
							requestURI="jobseeker_view.html" pagesize="2" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="name" title="Company Name"/>
							<display:column property="empName" title="Employer Name" />
							<display:column property="viewDate" title="Date" />
							<display:column property="days" title="Total Views" />
							<%-- <display:column property="appliedDate" title="Date" /> --%>
								<display:column url="jobseeker_applied_job_details.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="jobseeker_employee_view_total.html?id=${appliedJobs.id }">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							 
							
						</display:table>
					</div>
				</c:if>
						  
						  
						  <c:if test="${!empty jobseekerViewDay}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="jobseekerViewDay" name="${jobseekerViewDay}"
							requestURI="jobseeker_view_day.html" pagesize="2" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="name" title="Company Name"/>
							<display:column property="empName" title="Employer Name" />
							<display:column property="viewDate" title="Date" />
							<display:column property="days" title="Total Views" />
							<%-- <display:column property="appliedDate" title="Date" /> --%>
								<display:column url="jobseeker_employee_view_total.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="jobseeker_employee_view_total.html?id=${jobseekerViewDay.id }">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
							
							 
							
						</display:table>
					</div>
				</c:if>
				
				
				
				<c:if test="${!empty jobseeker20Days}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="jobseeker20Days" name="${jobseeker20Days}"
							requestURI="jobseeker_view_day20.html" pagesize="2" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="name" title="Company Name"/>
							<display:column property="empName" title="Employer Name" />
							<display:column property="viewDate" title="Date" />
							<display:column property="days" title="Total Views" />
							<display:column url="jobseeker_employee_view_total.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="jobseeker_employee_view_total.html?id=${jobseeker20Days.id }">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
						</display:table>
					</div>
				</c:if>
				
				
				
				
				
				
				<c:if test="${!empty jobseeker30Days}">
				<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="jobseeker30Days" name="${jobseeker30Days}"
							requestURI="jobseeker_view_day30.html" pagesize="2" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" /> 
							<display:column property="name" title="Company Name"/>
							<display:column property="empName" title="Employer Name" />
							<display:column property="viewDate" title="Date" />
							<display:column property="days" title="Total Views" />
							<display:column url="jobseeker_employee_view_total.html"
								media="html" paramId="id" paramProperty="id"
								title="View">
								<a
									href="jobseeker_employee_view_total.html?id=${jobseeker30Days.id }">
									<i
									style="text-align: center;" class=" fa fa-search"></i></a>
							</display:column>
						</display:table>
					</div>
				</c:if>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
						  
					
						<%-- <c:if test="${!empty appliedJobs.list}">
							<div class="error">${message}</div>
						</c:if>
						<c:if test="${!empty appliedJobs.list}">
							<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>
											<th style="text-align: center; color: #fff; width: 15%;padding:10px;line-height: 1em;">Company</th>
											<th style="text-align: center; color: #fff; width: 10%;padding:10px;line-height: 1em;">Employer</th>
											<th style="text-align: center; color: #fff; width: 10%;padding:10px;line-height: 1em;">Total
												View</th>
											<th  class="th-border" style="text-align: center; color: #fff; width: 10%;padding:10px;line-height: 1em;">View</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${appliedJobs.list}" var="searchResult">
											<tr>
												<td class="td-border"><c:out
														value="${searchResult.name }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.empName }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.days }"></c:out></td>
												<td class="td-border"><a
													href="jobseeker_employee_view_total.html?id=${searchResult.id }"
													 data-target="#myModal1"
													class="btn btn-theme btn-xs btn-default"><li class=" fa fa-search"></li></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if> --%>
						
					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->
	<div class="modal fade" id="myModal1">
		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>
	</div>
</body>
</html>