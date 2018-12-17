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
						<h3 class="text-center no-margin">Notifications</h3>
				        <hr />
							<!-- <h3 class="no-margin-top">
								<i class="fa fa-user"></i>Notifications
							</h3> -->
                        <div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${not empty Infomessage}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Info!</strong>
							<c:out value="${Infomessage}"></c:out>
						</div>
					</c:if>
				</div>
				</div>

						<ul id="myTab" class="nav nav-tabs flat-nav-tabs"
							style="width: 103%; margin-left: -14px">
							<li style="background-color:${notiAlters};"><a href="notifications_alerts.html"
								style="font-weight: bold;">Jobseeker </a></li>
							<li style="background-color:${empNotiAlters};"><a href="employer_notification_alert.html"
								style="font-weight: bold;">Employer </a></li>

						</ul>
                         <br/>
						<c:if test="${!empty notificationsList.list}">

								
                                <div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">

										<tr>
											<th style="text-align: center; color: #fff; width: 15%;">Jobseeker Name</th>
											<th style="text-align: center; color: #fff; width: 15%;">Email</th>
											<th style="text-align: center; color: #fff; width: 15%;">Remaining Days</th>
										</tr>

									</thead>
									<tbody>

										<c:forEach items="${notificationsList.list}"
											var="searchResult">
											<tr>
												<td class="td-border"><c:out value="${searchResult.firstName }"></c:out></td>
												<td class="td-border"><c:out value="${searchResult.emailAddress }"></c:out></td>
												<td class="td-border"><c:out value="${searchResult.totalDays}"></c:out>
													Days remaining for renewal</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
								</div>
                             <br/>



								<nav class="text-center">
									<ul class="pagination pagination-theme  no-margin">
										<c:if test="${notificationsList.currentPage !=1}">
											<li><a aria-label="Previous"
												href="notifications_alerts.html?page=${notificationsList.currentPage - 1}">
													<span aria-hidden="true">&larr;</span>
											</a></li>
										</c:if>
										<li><c:forEach begin="${notificationsList.start}"
												end="${notificationsList.records}" var="i">
												<c:choose>
													<c:when test="${notificationsList.page == i}">
														<a href="notifications_alerts.html?page=${i}"
															style="color: #fff; background-color: #34495e">${i}</a>
													</c:when>
													<c:otherwise>
														<a href="notifications_alerts.html?page=${i}">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach></li>
										<c:if
											test="${notificationsList.currentPage lt notificationsList.totalPages}">
											<li><a
												href="notifications_alerts.html?page=${notificationsList.currentPage + 1}"><span
													aria-hidden="true">&rarr;</span></a></li>
										</c:if>
									</ul>
								</nav>
							
						</c:if>
						<c:if test="${!empty employerNotificationList.list}">
							<div class="row">


								<c:if test="${not empty message}">
									<div class="error">${message}</div>
								</c:if>

								<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<thead style="background-color: #2a3f54">
										<tr>
											<th style="text-align: center; color: #fff; width: 15%;">Employer Name</th>
											<th style="text-align: center; color: #fff; width: 15%;">Company</th>
											<th style="text-align: center; color: #fff; width: 15%;">Remaining Days</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty employerNotificationList.list}">
											<c:forEach items="${employerNotificationList.list}"
												var="searchResult">
												<tr>
													<td class="td-border"><c:out value="${searchResult.firstName }"></c:out></td>
													<td class="td-border"><c:out value="${searchResult.companyName }"></c:out></td>
													<td class="td-border"><c:out value="${searchResult.totalDate }"></c:out>
														Days remaining for renewal</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								</div>
                                   <br/>

								<nav class="text-center">
									<ul class="pagination pagination-theme  no-margin">
										<c:if test="${employerNotificationList.currentPage !=1}">
											<li><a aria-label="Previous"
												href="employer_notification_alert.html?page=${employerNotificationList.currentPage - 1}">
													<span aria-hidden="true">&larr;</span>
											</a></li>
										</c:if>
										<li><c:forEach begin="${employerNotificationList.start}"
												end="${employerNotificationList.records}" var="i">
												<c:choose>
													<c:when test="${employerNotificationList.page == i}">
														<a href="employer_notification_alert.html?page=${i}"
															style="color: #fff; background-color: #34495e">${i}</a>
													</c:when>
													<c:otherwise>
														<a href="employer_notification_alert.html?page=${i}">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach></li>
										<c:if
											test="${employerNotificationList.currentPage lt employerNotificationList.totalPages}">
											<li><a
												href="employer_notification_alert.html?page=${employerNotificationList.currentPage + 1}"><span
													aria-hidden="true">&rarr;</span></a></li>
										</c:if>
									</ul>
								</nav>
							</div>
						</c:if>

					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->


</body>
</html>