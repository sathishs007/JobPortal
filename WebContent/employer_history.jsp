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
		<div class="box-list" style="width: 123%; margin-left: -30px">
			<div class="item">
				<div class="row">
					<div class="text-center">
						<h3 class="no-margin-top">
							<i class=" fa fa-pencil"></i>Registered Employer Details <i
								class="fa fa-user"></i>
						</h3>
						<form:form id="myForm" method="post" class="form-search-list"
							action="employeeNameSearch.html" commandName="employeeNameSearch">
							<div class="row">
								<div class=" col-md-3 ">

									<label class="color-white">Company Name</label>
									<form:input type="text" class="form-control "
										path="searchElement" placeholder="Type Name"></form:input>

									<button class="btn btn-theme btn-success btn-block"
										style="margin-top: -19%; margin-left: 105%; width: 50%;">
										<small>Search</small>
									</button>
								</div>
							</div>
						</form:form>

					</div>

					<!-- form login -->
					<div class="block-section bg-color line-bottom" id="sh-tabs">
						<div class="container">
							<div class="row">
								<div class="col-lg-9 ">


									<ul id="myTab" class="nav nav-tabs flat-nav-tabs"
										style="width: 103%; margin-left: -14px">
										<li><a href="employer_details.html">All</a></li>
										<li><a href="active_employer_details.html">Active </a></li>
										<li><a href="de_active_employer_details.html">In-Active
										</a></li>

									</ul>

									<!-- div id="myTabContent"
										class="tab-content flat-tab-content bg-color1">
										<div class="tab-pane fade in active" id="tab0"> -->
									<c:if test="${!empty registeredEmployer.list}">
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
																<th>Company</th>
																<th>Web Site</th>
																<th>Company Logo</th>
																<th>Registered Date</th>
																<th class="text-right">Action</th>
																<th class="text-right"></th>
																<th class="text-right"></th>
																<th class="text-right"></th>

															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredEmployer.list}">
																<c:forEach items="${registeredEmployer.list}"
																	var="searchResult">

																	<tr>

																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.companyName }"></c:out></td>

																		<td><c:out value="${searchResult.webSite }"></c:out></td>
																		<td><img
																			src="image_employer_details.html?id=${searchResult.id}"
																			class="img-rounded" alt="" width="50%" height="50%"></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>

																		<td class="text-right"><a
																			href="employer_view_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View </a></td>
																		<td class="text-right"><a
																			href="emp_profile_status.html?status=${searchResult.active },${searchResult.id}"
																			onclick="return confirm('Are you sure you want to continue?')"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a>&nbsp;&nbsp;&nbsp;<c:out
																				value="${searchResult.active }"></c:out></td>
																		<td class="text-right"><a
																			href="update_employer.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-pencil"></li></a></td>

																		<td class="text-right"><a
																			href="delete_employer.html?id=${searchResult.id}"
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
													<c:if test="${registeredEmployer.currentPage != 1}">
														<li><a aria-label="Previous"
															href="employer_details.html?page=${registeredEmployer.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredEmployer.start}"
															end="${registeredEmployer.records}" var="i">
															<c:choose>
																<c:when test="${registeredEmployer.page == i}">
																	<a href="employer_details.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="employer_details.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredEmployer.currentPage lt registeredEmployer.totalPages}">
														<li><a
															href="employer_details.html?page=${registeredEmployer.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>
										</div>
									</c:if>
									<!-- 	<div class="tab-pane fade" id="tab1"> -->
									<c:if test="${!empty registeredEmployers.list}">
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
																<th>Company</th>
																<th>Web Site</th>
																<th>Company Logo</th>
																<th>Registered Date</th>
																<th class="text-right">Action</th>
																<th class="text-right"></th>
																<th class="text-right"></th>
																<th class="text-right"></th>

															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredEmployers.list}">
																<c:forEach items="${registeredEmployers.list}"
																	var="searchResult">

																	<tr>

																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.companyName }"></c:out></td>

																		<td><c:out value="${searchResult.webSite }"></c:out></td>
																		<td><img
																			src="image_employer_details.html?id=${searchResult.id}"
																			class="img-rounded" alt="" width="50%" height="50%"></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>

																		<td class="text-right"><a
																			href="employer_view_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View </a></td>
																		<td class="text-right"><a href=""
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a> <c:out
																				value="${searchResult.active}"></c:out></td>
																		<td class="text-right"><a
																			href="update_employer.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-pencil"></li></a></td>
																		<td class="text-right"><a
																			href="delete_employer.html?id=${searchResult.id}"
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
													<c:if test="${registeredEmployers.currentPage !=1}">
														<li><a aria-label="Previous"
															href="active_employer_details.html?page=${registeredEmployers.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredEmployers.start}"
															end="${registeredEmployers.records}" var="i">
															<c:choose>
																<c:when test="${registeredEmployers.page == i}">
																	<a href="active_employer_details.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="active_employer_details.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredEmployers.currentPage lt registeredEmployers.totalPages}">
														<li><a
															href="active_employer_details.html?page=${registeredEmployers.currentPage + 1}"><span
																aria-hidden="true">&rarr;</span></a></li>
													</c:if>
												</ul>
											</nav>

										</div>

									</c:if>

									<!-- <div class="tab-pane fade" id="tab2"> -->
									<c:if test="${!empty registeredEmployer1.list}">
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
																<th>Company</th>
																<th>Web Site</th>
																<th>Company Logo</th>
																<th>Registered Date</th>
																<th class="text-right">Action</th>
																<th class="text-right"></th>
																<th class="text-right"></th>
																<th class="text-right"></th>

															</tr>
														</thead>
														<tbody>
															<c:if test="${!empty registeredEmployer1.list}">
																<c:forEach items="${registeredEmployer1.list}"
																	var="searchResult">

																	<tr>

																		<td><c:out value="${searchResult.firstName }"></c:out></td>
																		<td><c:out value="${searchResult.companyName }"></c:out></td>

																		<td><c:out value="${searchResult.webSite }"></c:out></td>
																		<td><img
																			src="image_employer_details.html?id=${searchResult.id}"
																			class="img-rounded" alt="" width="50%" height="50%"></td>
																		<td><c:out value="${searchResult.created }"></c:out></td>

																		<td class="text-right"><a
																			href="employer_view_details.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default">View </a></td>
																		<td class="text-right"><a href=""
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-thumbs-o-up"></li></a> <c:out
																				value="${searchResult.active}"></c:out></td>
																		<td class="text-right"><a
																			href="update_employer.html?id=${searchResult.id}"
																			class="btn btn-theme btn-xs btn-default"><li
																				class=" fa fa-pencil"></li></a></td>
																		<td class="text-right"><a
																			href="delete_employer.html?id=${searchResult.id}"
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
													<c:if test="${registeredEmployer1.currentPage != 1}">
														<li><a aria-label="Previous"
															href="de_active_employer_details.html?page=${registeredEmployer1.currentPage - 1}">
																<span aria-hidden="true">&larr;</span>
														</a></li>
													</c:if>
													<li><c:forEach begin="${registeredEmployer1.start}"
															end="${registeredEmployer1.records}" var="i">
															<c:choose>
																<c:when test="${registeredEmployer1.page == i}">
																	<a href="de_active_employer_details.html?page=${i}"
																		style="color: #fff; background-color: #34495e">${i}</a>
																</c:when>
																<c:otherwise>
																	<a href="de_active_employer_details.html?page=${i}">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach></li>
													<c:if
														test="${registeredEmployer1.currentPage lt registeredEmployer1.totalPages}">
														<li><a
															href="de_active_employer_details.html?page=${registeredEmployer1.currentPage + 1}"><span
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
				</div>
			</div>
		</div>
		<!-- form login -->
	</div>
	</div>
	</div>



	<!--end body-content -->
	<!-- main-footer -->
	<!-- end main-footer -->
	<!-- end wrapper page -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
</body>
</html>