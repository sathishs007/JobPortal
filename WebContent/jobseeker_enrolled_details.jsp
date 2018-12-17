<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

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
								<i class="fa fa-user"></i>Enrollment Details
							</h3> -->
							<h3 class="text-center no-margin">Enrollment Details</h3>
								<hr />
						</div>

						<c:if test="${!empty enrolledJobseekerList.list}">

							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>
							<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54;">
										<tr>
											<th style="text-align: center; color: #fff; width: 10%;">Enrolled
												Id</th>
											<th style="text-align: center; color: #fff; width: 10%;">Product
												Name</th>
											<th style="text-align: center; color: #fff; width: 10%;">Product
												Type</th>

											<th style="text-align: center; color: #fff; width: 15%;">Valid
												From</th>
											<th style="text-align: center; color: #fff; width: 15%;">Valid
												End</th>
											<th style="text-align: center; color: #fff; width: 10%;">View</th>
											<th style="text-align: center; color: #fff; width: 10%;">Delete</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${enrolledJobseekerList.list}"
											var="searchResult">
											<tr>
												<td class="td-border"><c:out
														value="${searchResult.id }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.selectProduct }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.productType }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.created }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.endDate }"></c:out></td>
												<td class="td-border"><a
													href="view_jobseeker_enrolled_details.html?id=${searchResult.id}"
													data-toggle="modal" data-target="#myModal"
													class="btn btn-theme btn-xs btn-default"><li
														class=" fa fa-search"></li></a></td>
												<td class="td-border"><a
													href="jobseeker_delete_enrolled_details.html?id=${searchResult.id}"
													class="btn btn-theme btn-xs btn-default"><li
														class=" fa fa-trash"></li></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<br />

							<nav class="text-center">
								<ul class="pagination pagination-theme  no-margin">
									<c:if test="${!empty enrolledJobseekerList.list}">
										<c:if test="${enrolledJobseekerList.currentPage !=1}">
											<li><a aria-label="Previous"
												href="jobseeker_enrolled_details?page=${enrolledJobseekerList.currentPage - 1}">
													<span aria-hidden="true">&larr;</span>
											</a></li>
										</c:if>
										<li><c:forEach begin="${enrolledJobseekerList.start}"
												end="${enrolledJobseekerList.records}" var="i">
												<c:choose>
													<c:when test="${enrolledJobseekerList.page == i}">
														<a href="jobseeker_enrolled_details?page=${i}"
															style="color: #fff; background-color: #34495e">${i}</a>
													</c:when>
													<c:otherwise>
														<a href="jobseeker_enrolled_details?page=${i}">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach></li>
										<c:if
											test="${enrolledJobseekerList.currentPage lt enrolledJobseekerList.totalPages}">
											<li><a
												href="jobseeker_enrolled_details?page=${enrolledJobseekerList.currentPage + 1}"><span
													aria-hidden="true">&rarr;</span></a></li>
										</c:if>
									</c:if>
								</ul>
							</nav>
						</c:if>
					</div>
				</div>
			</div>
		<!-- </div> -->
		<div class="modal fade" id="myModal">

			<div class="modal-dialog ">
				<div class="modal-content" style="width: 115%"></div>
			</div>
		</div>
	<!-- </div> -->
</body>

</html>