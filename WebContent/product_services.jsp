<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
	<div class="wrapper">
		<div class="body-content clearfix">
			<div class="block-section bg-color1 line-bottom">
				<div class="container ">
					<div class="text-center"
						style="border: 1px solid #e1e1e1; width: 90%; padding: 50px; margin: 0% 5% 0% 5%;box-shadow: 0 2px 8px #a5a5a5;">
						<div class="text-center">
							<h2>Our Products & service offerings</h2>
							<div class="white-space-30"></div>
						</div>

						<div class="warning" style="width: 100%; text-align: left;">


							<c:if test="${not empty infomessage}">
								<div class="alert alert-info" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Info!</strong>
									<!--  <label> Employer Purchase Any one
									Product</label> -->
									<c:out value="${infomessage}"></c:out>
								</div>
							</c:if>
						</div>



						<div class="row plan-nospace">
							<c:forEach items="${productList}" var="searchResult">
								<div class=" col-md-4 plan-item">
									<!-- plan box -->
									<div class="plan">
										<div class="plan-title">
											<h3 style="text-transform: capitalize;">
												<c:out value="${searchResult.productType}"></c:out>
											</h3>

											<p>
												Valid For
												<c:out value="${searchResult.durationDate}"></c:out>
												Months
											</p>
										</div>
										<div class="plan-price">
											<ul>
												<c:forTokens var="token" items="${searchResult.services}"
													delims=",">
													<p
														style="text-align: justify; padding: 5px; margin-left: -25px;">
														<i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;
														<c:out value="${token}" />
													</p>

													<%-- <li style="text-align: justify; padding: 8px;">	<c:out value="${token}"/></li> --%>
												</c:forTokens>
											</ul>
											<a
												href="payment_details.html?type=${searchResult.productType} && productId=${searchResult.productId}">
												<input type="button" value="Purchase"
												class="btn btn-t-primary btn-theme btn-lg btn-square btn-block" />
											</a>
										</div>
									</div>
									<!-- plan box -->
								</div>
							</c:forEach>
						</div>

						<div class="row plan-nospace">
							<p class="planAlignText">CV views/CV Download/Click to View
								phone No.</p>
							<p class="planAlignText">Hot Vacancy credits should be
								consumed within 30 Days from the date of activation/purchase.</p>
							<p class="planAlignText">Please note that the amounts are
								inclusive of all taxes as applicable.</p>


							<p class="text-center">
								<span class="span-line">Learn More</span>
							</p>
						</div>
					</div>
				</div>
			</div>

			
				<div class="container text-center">
					<strong class="text-uppercase">support</strong>
					<h2>Any questions?</h2>
					<div class="white-space-20"></div>
					<a href="employer_contact.html"
						class="btn btn-t-primary  btn-theme">Contact Us</a>
				</div>
			</br>
		</div>
	</div>
</body>
</html>