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
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<!-- <h3 class="margin-top">My Profile</h3> -->
				<h3 class="text-center no-margin titleunderline">Payment Details</h3>
				<hr />
				<!-- Start Warning Message  -->
				
					<div class="warning" style="width: 100%;text-align:left;">
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Success</strong>
									<c:out value="${sucessmessage}"></c:out>
								</div>
							</c:if>
							
							<c:if test="${not empty infomessage}">
								<div class="alert alert-info" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								 <strong>Info!</strong>
									<c:out value="${infomessage}"></c:out>
								</div>
							</c:if>
							</div>
					
					
						
					
				
				<!-- End Warning Message  -->
				<c:if test="${!empty JobDescription}">
					<div class="pi-responsive-table-sm">
						<table style="border: 1px solid;">
							<thead style="background-color: #2a3f54">
								<tr>
								<th style="text-align: center; color: #fff; width: 5%;">SNo
										</th>
									<th style="text-align: center; color: #fff; width: 25%;">Company
										Name</th>
									<th style="text-align: center; color: #fff; width: 22%;">
										Designation</th>
									<th style="text-align: center; color: #fff; width: 25%;">Email
									</th>
									<th style="text-align: center; color: #fff; width: 15%;">Status</th>
									<th style="text-align: center; color: #fff; width: 10%;">View</th>
									<th style="text-align: center; color: #fff; width: 10%;">Edit</th>
									<!-- <th style="text-align: center; color: #fff; width: 8%;">Delete</th> -->
								</tr>
							</thead>
							<tbody>
								<c:set var="rowCount" value="1"></c:set>
								<tr>
								<td class="td-border list-capitalize"><c:out
											value="${rowCount}"></c:out></td>
									<td class="td-border list-capitalize"><c:out
											value="${JobDescription.companyName }"></c:out></td>
									<td class="td-border list-capitalize"><c:out
											value="${JobDescription.currentDesignation }"></c:out></td>
									<td class="td-border"><c:out
											value="${JobDescription.emailId }"></c:out></td>
									<td class="td-border list-capitalize"><a
										href="profile_status.html?status=${JobDescription.status }"
										onclick="return confirm('Are you sure you want to continue?')"
										class="btn btn-theme btn-xs btn-default"><li
											class=" fa fa-thumbs-o-up"></li></a>&nbsp;&nbsp;&nbsp;<c:out
											value="${JobDescription.status }"></c:out></td>
									<td class="td-border"><a 
										href="employer_profile_details.html?id=${JobDescription.id}"
										class="btn btn-theme btn-xs btn-default" >
											<li class=" fa fa-search"></li>
									</a></td>
									<td class="td-border"><a
										href="employer_update_profile.html?id=${JobDescription.id}"
										class="btn btn-theme btn-xs btn-default"><li
											class=" fa fa-pencil"></li></a></td>
									<%-- <td class="td-border"><a
										href="employer_delete_profile.html?id=${JobDescription.id}"
										class="btn btn-theme btn-xs btn-default"><li
											class=" fa fa-trash"></li></a></td> --%>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			<!-- pagination -->
		</div>
	</div>
	<!-- </div> -->
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
	<!-- </div> -->
</body>
</html>