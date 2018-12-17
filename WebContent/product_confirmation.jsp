<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
	var open1 = false;
	var existUrl = null;
	$(document).ready(function() {
		$('#detailId').click(function() {
			$('#prodId').hide();
			$('#detId').show();
		});

		$('#backId').click(function() {
			$('#prodId').show();
			$('#detId').hide();
		});
	});
</script>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyjobKart</title>
</head>
<body>

	<div class="box-list">
		<div class="item">
			<div class="row">
				<!-- <h3 class="margin-top">My Profile</h3> -->
				<h3 class="text-center no-margin titleunderline">Payment
					Details</h3>
				<hr />
				<!-- Start Warning Message  -->

				<div id="prodId">
					<!-- End Warning Message  -->
					<c:if test="${!empty paymentList}">
						<div class="pi-responsive-table-sm">
							<table class="col-sm-6 mb-20"
								style="border: 1px solid; margin-left: 23%;">
								<thead>
									<tr>
										<th style="text-align: center; color: #000; width: 60%;">Product
											Type</th>
										<td class="tbl-td-odd "
											style="text-align: center; color: #000;">:</td>

										<td><c:out value="${paymentList.selectProduct}"></c:out>

											&nbsp;<a href="#modal-advanced" data-toggle="modal"><i
												class=" fa fa-search"></i> </a></td>


										<!-- <p style="margin-left: 75%;">
											<a href="#modal-advanced" data-toggle="modal">View
												Product Details </a>
										</p> -->
									</tr>
									<tr>
										<th style="text-align: center; color: #000; width: 25%;">Product
											Cost</th>
										<td class="tbl-td-odd "
											style="text-align: center; color: #000;">:</td>
										<td><c:out value="${paymentList.totalcost}"></c:out></td>
									</tr>
									<tr>
										<th style="text-align: center; color: #000; width: 25%;">Validity
											Month</th>
										<td class="tbl-td-odd "
											style="text-align: center; color: #000;">:</td>
										<td><c:out value="${paymentList.totalMonth}"></c:out></td>
									</tr>
									<tr>
										<th style="text-align: center; color: #000; width: 25%;">Payment
											Date</th>
										<td class="tbl-td-odd "
											style="text-align: center; color: #000;">:</td>
										<td><c:out value="${paymentList.paymentDate }"></c:out></td>
									</tr>
									<tr>
										<th style="text-align: center; color: #000; width: 25%;">Payment
											Mode</th>
										<td class="tbl-td-odd "
											style="text-align: center; color: #000;">:</td>
										<td><c:out value="${paymentList.paymentMode}"></c:out></td>
									</tr>
							</table>
						</div>
					</c:if>

					<div class="col-sm-12 mb-20"
						style="text-align: center; margin-top: 20px;">
						<a
							href="paypal_order.html?mode=paypal&orderAmount=${paymentList.totalcost}&empId=${paymentList.empId}&pMonth=${paymentList.totalMonth}&pType=${paymentList.selectProduct}"
							class="btn btn-theme  btn-primary ">Proceed to paypal</a> <a
							href=" payment_details.html?type=${paymentList.selectProduct}"
							class="btn btn-theme btn-default">Back </a>

						<%-- <button class="btn btn-theme btn-default" id="detailId"
							value="${paymentList.selectProduct}">View Details</button> --%>
					</div>
				</div>

				<div style="margin-bottom: 0px; display: none;" id="detId">
					<div class="block-section box-side-account">
						<div class="box-list" style="margin-left: -20px; padding: 5px">
							<div class="item">
								<div class="row">
									<c:if test="${!empty paymentList}">
										<div class="resume-block">
											<c:if test="${!empty paymentList}">
												<div class="desc">
													<h3 class="resume-sub-title">
														<span>Product Details</span>
													</h3>
													<h5>
														Product - <span class="color-white-mute"><c:out
																value="${paymentList.selectProduct}"></c:out></span>
													</h5>


													<h5>
														Product Services: <br /> <span class="color-white-mute">
															<c:forTokens var="token" items="${paymentList.services}"
																delims=",">
																<li class="hidden-xs" style="margin-left: 12%;"><c:out
																		value="${token}" /></li>
															</c:forTokens>
														</span>

													</h5>

													<h5>
														Created From - <span class="color-white-mute"><c:out
																value="${paymentList.created}"></c:out></span>
													</h5>

													<h3 class="resume-sub-title">
														<span>Cost Details</span>
													</h3>
													<ul>
														<h5>
															Total Cost - <span class="color-white-mute"><c:out
																	value="${paymentList.productPrice}"></c:out></span>
														</h5>
														<h5>
															Total Months- <span class="color-white-mute"><c:out
																	value="${paymentList.durationDate}"></c:out></span>
														</h5>

													</ul>
												</div>
											</c:if>

										</div>
									</c:if>
									<div class="col-sm-12 mb-20"
										style="text-align: center; margin-top: 20px;">
										<div id="backId" class="btn btn-theme btn-default">Back
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- pagination -->
		</div>
	</div>



	<div class="modal fade" id="modal-advanced">
		<div class="modal-dialog ">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Product Details</h4>
				</div>
				<div class="block-section box-side-accounts">
					<div class="box-list" style="padding: 20px;">
						<div class="item">
							<div class="row">
								<c:if test="${!empty paymentList}">
									<div class="resume-block">
										<c:if test="${!empty paymentList}">
											<div class="desc">
												<h3 class="resume-sub-title">
													<span>Product Details</span>
												</h3>
												<h5>
													Product - <span class="color-white-mute"><c:out
															value="${paymentList.selectProduct}"></c:out></span>
												</h5>


												<h5>
													Product Services: <br /> <span class="color-white-mute">
														<c:forTokens var="token" items="${paymentList.services}"
															delims=",">
															<li class="hidden-xs" style="margin-left: 12%;"><c:out
																	value="${token}" /></li>
														</c:forTokens>
													</span>

												</h5>

												<h5>
													Created From - <span class="color-white-mute"><c:out
															value="${paymentList.created}"></c:out></span>
												</h5>

												<h3 class="resume-sub-title">
													<span>Cost Details</span>
												</h3>
												<ul>
													<h5>
														Total Cost - <span class="color-white-mute"><c:out
																value="${paymentList.productPrice}"></c:out></span>
													</h5>
													<h5>
														Total Months- <span class="color-white-mute"><c:out
																value="${paymentList.durationDate}"></c:out></span>
													</h5>

												</ul>
											</div>
										</c:if>

									</div>
								</c:if>

							</div>
						</div>
					</div>
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