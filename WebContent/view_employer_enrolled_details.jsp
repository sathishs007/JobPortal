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
</head>
<style>
p {
	white-space: pre-line;
}
</style>
<body>
	<%-- <c:if test="${!empty enrolledList}">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				onclick="location.reload();" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if> --%>
	<!-- <div class="block-section box-side-account"> -->
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<c:if test="${!empty enrolledList}">
					<div class="resume-block">
						<div class="img-profile">
							<img
								src="imageEmployerLoginDisplay.html?id="
								>
						</div>
						<!-- <div class="col-sm-6"> -->
						<c:if test="${!empty enrolledList}">
							<div class="desc">
								<h3 class="resume-sub-title">
									<span>Product Details</span>
								</h3>
								<h5>
									Product - <span class="color-white-mute"><c:out
											value="${enrolledList.selectProduct}"></c:out></span>
								</h5>


								<h5>
									Product Type - <span class="color-white-mute"><c:out
											value="${enrolledList.productType}"></c:out></span>

								</h5>

								<h5>
									Valid From - <span class="color-white-mute"><c:out
											value="${enrolledList.created}"></c:out></span>
								</h5>
								<h5>
									End Date- <span class="color-white-mute"><c:out
											value="${enrolledList.endDate}"></c:out></span>
								</h5>

								<h3 class="resume-sub-title">
									<span>Payment Details</span>
								</h3>
								<ul>
									<h5>
										Total Cost - <span class="color-white-mute"><c:out
												value="${enrolledList.totalcost}"></c:out></span>
									</h5>
									<h5>
										Total Months- <span class="color-white-mute"><c:out
												value="${enrolledList.totalMonth}"></c:out></span>
									</h5>

								</ul>
							</div>
						</c:if>
					</div>
				</c:if>
			</div>
			<div class="box-list">
				<a href="employer_enrolled_details.html"> <input type="button"
					value="Back" class="btn btn-t-primary btn-theme" />
				</a>
			</div>
		</div>
	</div>
</body>
</html>