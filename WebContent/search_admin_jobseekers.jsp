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
							<i class="fa fa-user"></i>Registered Jobseekers
						</h3>
					</div>
					<div class="block-section bg-color line-bottom" id="sh-tabs">
						<div class="container">
							<div class="row">
								<div class="col-lg-9">
									<ul id="myTab" class="nav nav-tabs flat-nav-tabs"
										style="width: 103%; margin-left: -14px">
										<li><a href="admin_jobseekers.html">All</a></li>
										<li><a href="active_admin_jobseekers.html">Active </a></li>
										<li><a href="inactive_admin_jobseekers.html">In-Active
										</a></li>

									</ul>
									<!-- 	<div id="myTabContent"
										class="tab-content flat-tab-content bg-color1"> -->
									<!-- <div class="tab-pane fade in active" id="tab0"> -->
									<c:if test="${!empty registeredList.list}">
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
																<th>Email</th>
																<th class="text-right">Registered Date</th>
																<th class="text-right">Active</th>
																<th class="text-right">View</th>
																<th class="text-right">Actions</th>
																<th class="text-right"></th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredList.list}">
																<c:forEach items="${registeredList.list}"
																	var="searchResult">
																	<tr>
																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.emailAddress }"></c:out></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>
																		<td class="text-right"><a href="#"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a>&nbsp;&nbsp;&nbsp;<c:out
																				value="${searchResult.active }"></c:out></td>
																		<td class="text-right"><a
																			href="admin_jobseeker_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View</a></td>
																		<td class="text-right"><a
																			href="admin_update_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">Edit</a></td>
																		<td class="text-right"><a
																			href="admin_delete_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-trash"></li></a></td>
																	</tr>

																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>


											<nav class="text-center">
												<ul class="pagination pagination-theme  no-margin">
													<c:if test="${registeredList.currentPage !=1}">
														<li><a aria-label="Previous"
															href="admin_jobseekers.html?page=${registeredList.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredList.start}"
															end="${registeredList.records}" var="i">
															<c:choose>
																<c:when test="${registeredList.page == i}">
																	<a href="admin_jobseekers.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="admin_jobseekers.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredList.currentPage lt registeredList.totalPages}">
														<li><a
															href="admin_jobseekers.html?page=${registeredList.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>

										</div>

									</c:if>

									<!-- 	</div> -->
									<!-- <div class="tab-pane fade" id="tab1"> -->
									<c:if test="${!empty registeredLists.list}">
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
																<th>Email</th>
																<th class="text-right">Registered Date</th>
																<th class="text-right">Active</th>
																<th class="text-right">View</th>
																<th class="text-right">Actions</th>
																<th class="text-right"></th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredLists.list}">
																<c:forEach items="${registeredLists.list}"
																	var="searchResult">
																	<tr>
																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.emailAddress }"></c:out></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>
																		<td class="text-right"><a href="#"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a>&nbsp;&nbsp;&nbsp;<c:out
																				value="${searchResult.active }"></c:out></td>
																		<td class="text-right"><a
																			href="admin_jobseeker_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View</a></td>
																		<td class="text-right"><a
																			href="admin_update_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">Edit</a></td>
																		<td class="text-right"><a
																			href="admin_delete_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-trash"></li></a></td>
																	</tr>

																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>

											<nav class="text-center">
												<ul class="pagination pagination-theme  no-margin">
													<c:if test="${registeredList.currentPage !=1}">
														<li><a aria-label="Previous"
															href="active_admin_jobseekers.html?page=${registeredLists.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredLists.start}"
															end="${registeredLists.records}" var="i">
															<c:choose>
																<c:when test="${registeredLists.page == i}">
																	<a href="active_admin_jobseekers.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="active_admin_jobseekers.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredLists.currentPage lt registeredLists.totalPages}">
														<li><a
															href="active_admin_jobseekers.html?page=${registeredLists.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>

										</div>
									</c:if>
									<!-- </div>
									<div class="tab-pane fade" id="tab2"> -->
									<c:if test="${!empty registeredList1.list}">
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
																<th>Email</th>
																<th class="text-right">Registered Date</th>
																<th class="text-right">Active</th>
																<th class="text-right">View</th>
																<th class="text-right">Actions</th>
																<th class="text-right"></th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredList1.list}">
																<c:forEach items="${registeredList1.list}"
																	var="searchResult">
																	<tr>
																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.emailAddress }"></c:out></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>
																		<td class="text-right"><a href="#"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a>&nbsp;&nbsp;&nbsp;<c:out
																				value="${searchResult.active }"></c:out></td>

																		<td class="text-right"><a
																			href="admin_jobseeker_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View</a></td>

																		<td class="text-right"><a
																			href="admin_update_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">Edit</a></td>

																		<td class="text-right"><a
																			href="admin_delete_jobseeker.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-trash"></li></a></td>
																	</tr>

																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>



											</div>


											<nav class="text-center">
												<ul class="pagination pagination-theme  no-margin">
													<c:if test="${registeredList1.currentPage !=1}">
														<li><a aria-label="Previous"
															href="inactive_admin_jobseekers.html?page=${registeredList1.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredList1.start}"
															end="${registeredList1.records}" var="i">
															<c:choose>
																<c:when test="${registeredList1.page == i}">
																	<a href="inactive_admin_jobseekers.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="inactive_admin_jobseekers.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredList1.currentPage lt registeredList1.totalPages}">
														<li><a
															href="inactive_admin_jobseekers.html?page=${registeredList1.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>

											<div class="text-center">
												<div class="form-group no-margin">
													<a href="admin_jobseekers.html"> <input type="button"
														value="Cancel" class="btn btn-theme btn-default" /></a>
												</div>
											</div>


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
	<!-- </div>
	</div> -->

	<!--end body-content -->
	<!-- main-footer -->
	<!-- end main-footer -->
	<!-- end wrapper page -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
</body>
</html>