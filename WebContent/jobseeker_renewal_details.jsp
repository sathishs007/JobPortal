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
								<i class="fa fa-user"></i>Renewal Details
							</h3> -->
							<h3 class="text-center no-margin">Renewal Details</h3>
								<hr />
						</div>

						<c:if test="${!empty jobseekerRenewalList.list}">
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>
							<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>
											<th style="text-align: center; color: #fff; width: 10%;">Name</th>
											<th style="text-align: center; color: #fff; width: 10%;">Email</th>
											<th style="text-align: center; color: #fff; width: 15%;">Renewal
												Date</th>
											<th style="text-align: center; color: #fff; width: 10%;">Payment</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${jobseekerRenewalList.list}"
											var="searchResult">
											<tr>
												<td class="td-border"><c:out
														value="${searchResult.firstName }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.emailAddress }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.totalDays}"></c:out><label>
														Days remaining for renewal.</label></td>
												<td class="td-border"><c:choose>
														<c:when test="${searchResult.totalDays>'0'}">
															<center>
																<a class="btn btn-xs btn-theme btn-success">Already
																	paid</a>
															</center>
														</c:when>
														<c:otherwise>
															<center>
																<a href="product_services.html?id=${searchResult.id}"
																	class="btn btn-xs btn-theme btn-success">Pay Now </a>
															</center>

														</c:otherwise>
													</c:choose>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<br />
							<nav class="text-center">
								<ul class="pagination pagination-theme  no-margin">
									<c:if test="${jobseekerRenewalList.currentPage !=1}">
										<li><a aria-label="Previous"
											href="jobseeker_renewal_details.html?page=${jobseekerRenewalList.currentPage - 1}">
												<span aria-hidden="true">&larr;</span>
										</a></li>
									</c:if>
									<li><c:forEach begin="${jobseekerRenewalList.start}"
											end="${jobseekerRenewalList.records}" var="i">
											<c:choose>
												<c:when test="${jobseekerRenewalList.page == i}">
													<a href="jobseeker_renewal_details.html?page=${i}"
														style="color: #fff; background-color: #34495e">${i}</a>
												</c:when>
												<c:otherwise>
													<a href="jobseeker_renewal_details.html?page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach></li>
									<c:if
										test="${jobseekerRenewalList.currentPage lt jobseekerRenewalList.totalPages}">
										<li><a
											href="jobseeker_renewal_details.html?page=${jobseekerRenewalList.currentPage + 1}"><span
												aria-hidden="true">&rarr;</span></a></li>
									</c:if>
								</ul>
							</nav>
						</c:if>
					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->

	<!--end body-content -->
	<!-- main-footer -->
	<!-- end main-footer -->
	<!-- end wrapper page -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
</body>
</html>