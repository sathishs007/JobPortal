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
<link rel="stylesheet" type="text/css" href="resources/theme/css/tables.css" />
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
	
	<link rel="stylesheet" type="text/css" href="resources/theme/css/tables.css" />

</head>
<body>
			<div class="box-list">
				<div class="item">
					<div class="row">
						<h3 class="text-center no-margin titleunderline">View Industry Entity</h3>
								<hr />
						
						<div class="warning" style="width: 100%;text-align:left;">
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
						
						<c:if test="${!empty industryEntityList}">
							<div class="pi-section-w pi-section-white piTooltips">
							
							<!-- Table Tags -->
							
							<display:table id="data" name="${industryEntityList}"
								requestURI="/admin_industry_view.html" pagesize="3" export="false"
								 class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders"> 
								
								<display:column property="companyName" title="Customer Name" />
								<display:column property="email" title="Email" />
								
								
								 <display:column url="admin_update_industry_entity.html" media="html"
									paramId="id" paramProperty="industryId" title="Edit">
									<a href="admin_update_industry_entity.html?industryId=${data.companiesId}"><i
									style="text-align:center;"	class=" fa fa-pencil"></i></a>
								</display:column>

								<display:column url="admin_delete_industry_entity.html" media="html"
									paramId="id" paramProperty="industryId" title="Delete">
									<a href="admin_delete_industry_entity.html?industryId=${data.companiesId}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									 class=" fa fa-trash"	class="btn btn-theme btn-xs btn-default"></i></a>
								</display:column> 

								
							</display:table>
							
							<%-- <table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>
										<th style="text-align:center; color: #fff; width: 5%;">SNo
												</th>
											<th style="text-align:center; color: #fff; width: 40%;">Company
												Name</th>
											<th style="text-align:center; color: #fff; width: 40%;">Email
												</th>
																				
											<th style="text-align:center; color: #fff; width: 25%;">Edit</th>
											<th style="text-align:center; color: #fff; width: 25%;">Delete</th>
										</tr>
									</thead>
									<tbody>
									
										<c:forEach items="${companyEntityList.list}" var="searchResult">
										<c:set var="numberOfRows" value="${numberOfRows+1}"/>
											<tr>
											<td class="td-border list-capitalize"><c:out value="${numberOfRows}" /></td>
												<td class="td-border list-capitalize"><c:out
														value="${searchResult.companyName}"></c:out></td>
												
												<td style="text-align: center; border-left: 1px solid;" class="td-border">
												
												<c:out	value="${searchResult.email}" ></c:out>
												
																					
												</td>
												
												
												
												 <td class="td-border"><a
												href="job_post_profile_status.html?status=${searchResult.status},${searchResult.id}"
												onclick="return confirm('Are you sure you want to continue?')"
												class="btn btn-theme btn-xs btn-default"><li
													class=" fa fa-thumbs-o-up list-capitalize"></li></a>&nbsp;&nbsp;&nbsp;<c:out
													value="${searchResult.status }"></c:out></td>
												
												<td class="td-border"><a
													href="employer_jobpost_details.html?id=${searchResult.id}"
												
													class="btn btn-theme btn-xs btn-default"><li
														class=" fa fa-search"></li></a></td>
											
														
												<td class="td-border"><a
														href="admin_update_company_entity.html?companiesId=${searchResult.companiesId}"
														class="btn btn-theme btn-xs btn-default"><li
															class=" fa fa-pencil"></li></a></td>
												</a></td>
												<td class="td-border"><a
													href="admin_delete_company_entity.html?companiesId=${searchResult.companiesId}"
													onclick="return confirm('Are you sure you want to Delete?')"
													class="btn btn-theme btn-xs btn-default">
														<li class=" fa fa-trash"></li>
												</a></td>
												 
												
												
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
								
							</div>

							<br />
							<!-- pagination -->
							
							<nav class="text-center">
								<ul class="pagination pagination-theme  no-margin">
									<c:if test="${companyEntityList.currentPage != 1}">
										<li><a aria-label="Previous"
											href="admin_company_view.html?page=${companyEntityList.currentPage - 1}">
												<span aria-hidden="true">&larr;</span>
										</a></li>
									</c:if>
									<li><c:forEach begin="${companyEntityList.start}"
											end="${companyEntityList.records}" var="i">
											<c:choose>
												<c:when test="${companyEntityList.page == i}">
													<a href="admin_company_view.html?page=${i}"
														style="color: #fff; background-color: #34495e">${i}</a>
												</c:when>
												<c:otherwise>
													<a href="admin_company_view.html?page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach></li>
									<c:if
										test="${companyEntityList.currentPage lt companyEntityList.totalPages}">
										<li><a
											href="admin_company_view.html?page=${companyEntityList.currentPage + 1}"><span
												aria-hidden="true">&rarr;</span></a></li>
									</c:if>
								</ul>
							</nav>
							 --%>
							
							
							
						</div>	
						</c:if> 
						
						
						<!-- pagination -->
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
	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content" style="width: 115%"></div>
		</div>
	</div>


</body>
</html>