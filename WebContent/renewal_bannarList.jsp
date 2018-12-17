<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyjobKart|Jobseeker|home</title>

<!--favicon-->

</head>
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
						<h3 class="text-center no-margin">BannerList Details</h3>
				            <hr />
							<!-- <h3 class="margin-top">
								<i class=" fa fa-pencil"></i>BannerList Details <i
									class="fa fa-user"></i>
							</h3> -->
						</div>
						<c:if test="${not empty message}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_confirm">
									<h1>Success</h1>
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
							<br/>
						<c:if test="${!empty bannerPostingList.list}">
							<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>

											<th style="text-align: center; color: #fff; width: 15%;">Banner Name</th>
											<th style="text-align: center; color: #fff; width: 15%;">Post Page</th>
											<th style="text-align: center; color: #fff; width: 15%;">Total Cost</th>
											<th style="text-align: center; color: #fff; width: 15%;">Remaining Days</th>
											<th style="text-align: center; color: #fff; width: 15%;">Payment</th>
											<!-- <th style="text-align: center; color: #fff; width: 15%;">Edit</th> -->
											<!-- <th style="text-align: center; color: #fff; width: 15%;">View</th> -->
											<th style="text-align: center; color: #fff; width: 15%;">Delete</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty bannerPostingList.list}">
											<c:forEach items="${bannerPostingList.list}"
												var="searchResult">

												<tr>
													<td class="td-border"><c:out
															value="${searchResult.bannerName }"></c:out></td>
													<td class="td-border"><c:out
															value="${searchResult.postPage }"></c:out></td>
													<td class="td-border"><c:out
															value="${searchResult.totalcost }"></c:out></td>
													<td class="td-border"><c:out
															value="${searchResult.totalDays }"></c:out> <label">Days
															to go for renewal.</label></td>
													<td class="td-border"><c:choose>
															<c:when test="${searchResult.totalDays>'0'}">
																<center>
																	<a class="btn btn-xs btn-theme btn-success">All
																		ready paid</a>
																</center>
															</c:when>
															<c:otherwise>
																<center>
																	<a href="banner_posting.html"
																		class="btn btn-xs btn-theme btn-success">Pay</a>
																</center>
															</c:otherwise>
														</c:choose></td>
													<%-- <td class="td-border"><a
														href="banner_update.html?id=${searchResult.bannerId}"
														data-toggle="modal" data-target="#myModal1"
														class="btn btn-theme btn-xs btn-default"><li
															class=" fa fa-pencil"></li></a></td> --%>
													<%-- <td class="td-border"><a
														href="banner_details.html?id=${searchResult.bannerId}"
														data-toggle="modal" data-target="#myModal"
														class="btn btn-theme btn-xs btn-default">
															<li class=" fa fa-search"></li>
													</a></td> --%>
													<td class="td-border"><a
														href="delete_bannar.html?id=${searchResult.bannerId}"
														class="btn btn-theme btn-xs btn-default">
															<li class=" fa fa-trash"></li>
													</a></td>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>

							<br />
							<nav class="text-center">
								<ul class="pagination pagination-theme  no-margin">
									<c:if test="${bannerPostingList.currentPage != 1}">
										<li><a aria-label="Previous"
											href="renewal_bannarList.html?page=${bannerPostingList.currentPage - 1}">
												<span aria-hidden="true">&larr;</span>
										</a></li>
									</c:if>
									<li><c:forEach begin="${bannerPostingList.start}"
											end="${bannerPostingList.records}" var="i">
											<c:choose>
												<c:when test="${bannerPostingList.page == i}">
													<a href="renewal_bannarList.html?page=${i}"
														style="color: #fff; background-color: #34495e">${i}</a>
												</c:when>
												<c:otherwise>
													<a href="renewal_bannarList.html?page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach></li>
									<c:if
										test="${bannerPostingList.currentPage lt bannerPostingList.totalPages}">
										<li><a
											href="renewal_bannarList.html?page=${bannerPostingList.currentPage + 1}"><span
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