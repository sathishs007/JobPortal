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

<title>MyjobKart</title>

<!--favicon-->

</head>
<style>
p {
	white-space: pre-line;
}
</style>
<body>
	<c:if test="${!empty paymentDetail}">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				onclick="location.reload();" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title">
				<c:out value="${paymentDetail.name}"></c:out>
			</h4>
			<%-- <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title">
				<c:out value="${bannerDetail.bannerName}"></c:out>
			</h4>
		</div> --%>
	</c:if>
	<div class="block-section box-side-account">
		<div class="box-list" style="margin-left: -20px; padding: 5px">
			<div class="item">
				<div class="row">
					<div class="container">

						<div class="row">

							<div class="col-sm-6">
								<c:if test="${!empty paymentDetail}">

									<div class="col-sm-6">
										<c:if test="${!empty paymentDetail}">
											<div class="resume-block">
												<div class="img-profile">
													<img src="jobseeker_image.html?id=${paymentDetail.payId}"
														alt="">

												</div>
												<div class="desc">
													<h2>
														<c:out value="${paymentDetail.name}"></c:out>
													</h2>

													<h4>
														Product Name- <span class="color-white-mute"><c:out
																value="${paymentDetail.selectProduct}"></c:out></span>
													</h4>
													<h4>
														Total Cost - <span class="color-white-mute"><c:out
																value="${paymentDetail.totalcost}"></c:out></span>
													</h4>

													<h4>
														Valid From- <span class="color-white-mute"><c:out
																value="${paymentDetail.validFrom}"></c:out></span>
													</h4>

													<h4>
														Valid End- <span class="color-white-mute"><c:out
																value="${paymentDetail.endDate}"></c:out></span>
													</h4>

												</div>
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
	</div>
	</div>
</body>
</html>