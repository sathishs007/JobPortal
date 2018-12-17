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
	<c:if test="${!empty bannerDetail}">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				onclick="location.reload();" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title">
				<c:out value="${bannerDetail.bannerName}"></c:out>
			</h4>
		</div>
	</c:if>
	<div class="container" style="margin-top: -80px;">
		<div class="col-sm-8">
			<div class="box-list-emp">
				<div class="item">
					<div class="row">

						<c:if test="${!empty bannerDetail}">

							<div class="resume-block">
							<h3 class="resume-sub-title">
										<span>Banner Details</span>
									</h3>
							

								<div class="desc">
									<h2>
										<c:out value="${bannerDetail.bannerName}"></c:out>
									</h2>
									<h4>
										Post Page- <span class="color-white-mute"><c:out
												value="${bannerDetail.postPage}"></c:out></span>
									</h4>

									<h4>
										Total Cost- <span class="color-white-mute"><c:out
												value="${bannerDetail.totalcost}"></c:out></span>
									</h4>
									<h4>
										Remaining Days - <span class="color-white-mute"><c:out
												value="${bannerDetail.totalDays}"></c:out></span>
									</h4>


								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>