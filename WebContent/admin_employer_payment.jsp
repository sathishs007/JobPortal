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
	<div class="block-section box-side-account">
		<div class="box-list" style="width: 123.5%; margin-left: -30px">
			<div class="item">
				<div class="row">
					<div class="text-center">
						<h3 class="no-margin-top">
							<i class="fa fa-user"></i>Employeer Payment
						</h3>
						<%-- 	<form:form id="myForm" method="post" class="form-search-list"
							action="jobSeekerNameSearch.html" commandName="jobSeekerNameSearch">
							<div class="row">
								<div class=" col-md-3 ">
									
										<label class="color-white">Company Name</label>
										<form:input type="text" class="form-control "
											path="searchElement" placeholder="Type Name"></form:input>
									
									<button class="btn btn-theme btn-success btn-block" style="margin-top: -19%;margin-left:105%;width: 50%">
										<small>Search</small>
									</button>
								</div>
							</div>
						</form:form>  --%>
					</div>
					<div class="block-section bg-color line-bottom" id="sh-tabs">
						<div class="container">
							<div class="row">
								<div class="col-lg-9">
									<!-- <ul id="myTab" class="nav nav-tabs flat-nav-tabs"
										style="width: 103%; margin-left: -14px">
										<li><a href="admin_jobseekers.html">All</a></li>
										<li><a href="active_admin_jobseekers.html">Active </a></li>
										<li><a href="inactive_admin_jobseekers.html">In-Active
										</a></li>

									</ul> -->
									<!-- 	<div id="myTabContent"
										class="tab-content flat-tab-content bg-color1"> -->
									<!-- <div class="tab-pane fade in active" id="tab0"> -->
									<c:if test="${!empty employerPaymentList.list}">
										<div class="row">

											<div class="box-list">
												<c:if test="${not empty message}">
													<div class="message red">${message}</div>
												</c:if>
												<div class="datagrid">
													<table>
														<thead>
															<tr>
																<th>Name</th>
																<th>ProductName</th>
																<th>Total cost</th>
																<th>Valid From</th>
																<th>Valid End</th>
																<th class="text-right">View</th>
																<!-- <th class="text-right">Actions</th>
																<th class="text-right"></th>  -->
															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty employerPaymentList.list}">
																<c:forEach items="${employerPaymentList.list}"
																	var="searchResult">
																	<tr>
																		<td><c:out value="${searchResult.name}"></c:out></td>
																		<td><c:out value="${searchResult.selectProduct }"></c:out></td>
																		<td><c:out value="${searchResult.totalcost }"></c:out></td>
																		<td><c:out value="${searchResult.validFrom }"></c:out></td>
																		<td><c:out value="${searchResult.endDate }"></c:out></td>
																		<td class="text-right"><a
																			href="employeer_payment_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"
																			data-toggle="modal" data-target="#myModal">View</a></td>
																	</tr>

																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>


											<nav class="text-center">
												<ul class="pagination pagination-theme  no-margin">
													<c:if test="${employerPaymentList.currentPage !=1}">
														<li><a aria-label="Previous"
															href="admin_employer_payment.html?page=${employerPaymentList.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${employerPaymentList.start}"
															end="${employerPaymentList.records}" var="i">
															<c:choose>
																<c:when test="${employerPaymentList.page == i}">
																	<a href="admin_employer_payment.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="admin_employer_payment.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredList.currentPage lt employerPaymentList.totalPages}">
														<li><a
															href="admin_employer_payment.html?page=${employerPaymentList.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>

										</div>

									</c:if>

								</div>
							</div>
						</div>
					</div>
					<!-- form login -->
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="myModal">
		<div class="modal-dialog ">
			<div class="modal-content" style="width: 115%"></div>
		</div>
	</div>

</body>
</html>