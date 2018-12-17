<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<title>MyjobKart</title>
</head>
<body>
	<!-- <div class="container">
		<div class="col-sm-12"> -->
			<div class="box-list">
				<div class="item">
					<div class="row">
						<!-- <h3 class="margin-top">Notifications</h3> -->
						<h3 class="text-center no-margin">Notifications</h3>
								<hr />
						<div class="alert alert-warning alert-dismissible fade in"
							role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<c:if test="${!empty registeredList.list}">
								<c:if test="${not empty message}">
									<div class="error">${message}</div>
								</c:if>
								<table>
									<thead>
										<tr>
											<th>Remaining Days</th>
										</tr>
									</thead>
									<tr>
										<c:forEach items="${registeredList.list}" var="searchResult">
											<tr>
												<td><c:out value="${searchResult.totalDays}"></c:out><label">
														Days remaining for renewal your profile.Check your Renewal
														Status </label></td>
											</tr>
										</c:forEach>
									</tr>
								</table>
							</c:if>
						</div>
					</div>
				</div>
				</div>
			<!-- </div>

		</div> -->
</body>
</html>