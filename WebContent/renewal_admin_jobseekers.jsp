<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
	<!-- <div class="container">
		<div class="col-sm-12"> -->
			<div class="box-list">
				<div class="item">
					<div class="row">
						<div class="text-center">
							<!-- <h3 class="margin-top">
								<i class="fa fa-user"></i>Renewal Jobseekers
							</h3>renewal_admin_jobseekers-->
 <h3 class="text-center no-margin">Renewal Jobseekers</h3>
				         <hr />
						</div>
						<c:if test="${!empty registeredList.list}">
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>
							<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>
											<th style="text-align: center; color: #fff; width: 15%;">Name</th>
											<th style="text-align: center; color: #fff; width: 15%;">Email</th>
											<th style="text-align: center; color: #fff; width: 15%;">Renewal
												Date</th>
											<th style="text-align: center; color: #fff; width: 15%;">Payment
												Now</th>

										</tr>
									</thead>
									<tbody>

										<c:forEach items="${registeredList.list}" var="searchResult">
											<tr>
												<td class="td-border"><c:out
														value="${searchResult.firstName }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.emailAddress }"></c:out></td>
												<td class="td-border"><c:out
														value="${searchResult.totalDays}"></c:out><label">
														Days remaining for renewal.</label></td>
												<td class="td-border"><c:choose>
														<c:when test="${searchResult.totalDays>'0'}">
															<center>
																<a class="btn btn-xs btn-theme btn-success"> Already
																	paid</a>
															</center>
														</c:when>
														<c:otherwise>
															<center>
																<a
																	<%-- href="product_services.html?id=${searchResult.id}" --%>
															class="btn btn-xs btn-theme btn-success">Not
																	Paid </a>
															</center>
														</c:otherwise>
													</c:choose>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
					</div>
					<br />

					<nav class="text-center">
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${registeredList.currentPage !=1}">
								<li><a aria-label="Previous"
									href="renewal_admin_jobseekers.html?page=${registeredList.currentPage - 1}">
										<span aria-hidden="true">&larr;</span>
								</a></li>
							</c:if>
							<li><c:forEach begin="${registeredList.start}"
									end="${registeredList.records}" var="i">
									<c:choose>
										<c:when test="${registeredList.page == i}">
											<a href="renewal_admin_jobseekers.html?page=${i}"
												style="color: #fff; background-color: #34495e">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="renewal_admin_jobseekers.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${registeredList.currentPage lt registeredList.totalPages}">
								<li><a
									href="renewal_admin_jobseekers.html?page=${registeredList.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>

				</div>





			</div>
		<!-- </div>
	</div>
 -->
</body>
</html>