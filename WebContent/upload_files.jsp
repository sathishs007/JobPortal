<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>

	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid1 = true;
			$('input[type="file"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid1 = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			if (isValid1 == false)
				e.preventDefault();

		});
	});


	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid2 = true;
			$('select[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid2 = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			if (isValid2 == false)
				e.preventDefault();

		});
	});
</script>

<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'csv'];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("uploadFiles").value = '';
				alert("Only '.csv' is formats are allowed.");
			}
		}
	</script>
<html>
<body>
	<section class="smallSection">
		<div class="box-list">
			<div class="item">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-xs-12 col-lg-12"
						style="border: 0px solid #fff;">
						<br>
						<div style="margin-left: 0.5%; margin-bottom: -10px">
							<h3 class="text-center no-margin titleunderline">Upload
								Entity</h3>

						</div>
						<br />

						<div class="warning">

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
						<form:form id="productCreation" modelAttribute="uploadFile"
							enctype="multipart/form-data">

							<div class="form-group">
								<div class="row clearfix">
									<label>${companyCount}</label>
									<div class="col-xs-6">
										<label>Entity</label>
										<form:select name="entityType" type="text"
											class="form-control" path="entityType" id="selectEntity"
											placeholder="entityType">
											<form:option value="">Select Entity</form:option>
											<form:option value="Company">Company Name</form:option>
											<form:option value="Industry">Industry</form:option>
										</form:select>
										<form:errors path="entityType" cssClass="message red"></form:errors>
										<p id="errorproducttype" class="red"></p>
									</div>
									<div class="col-xs-6">
										<label>Upload Files</label> <input type="file"
											name="uploadCompany" path="uploadCompany"
											onchange="fileCheck(this)" id="uploadFiles" size="50"
											placeholder="RESUME" />
									</div>
								</div>
							</div>
							<div>
								<button type="submit" id="btnSubmit"
									class="btn btn-primary textalign">Submit</button>
								<a href="admin_home.html"> <input value="Cancel"
									class="btn btn-primary textalign" type="button"></a>
							</div>
							<br>
							<br>
						</form:form>
						<div class="form-group">
							<div class="row clearfix">
								<c:if test="${not empty viewTable}">
									<div class="col-xs-6">
										<div class="pi-responsive-table-sm">
											<label> New Entity</label>
											<table style="border: 1px solid;">
												<thead style="background-color: #2a3f54">
													<tr>
														<th style="text-align: center; color: #fff; width: 50%;">Entity
															Name</th>
														<th style="text-align: center; color: #fff; width: 50%;">Email
															Address</th>

													</tr>
												</thead>
												<tbody>
													<c:choose>
														<c:when test="${!empty newCompanyList}">
															<c:forEach items="${newCompanyList}" var="searchResult">
																<tr>
																	<td class="td-border list-capitalize"><c:out
																			value="${searchResult.companyName}" /></td>
																	<td class="td-border list-capitalize"><c:out
																			value="${searchResult.email }"></c:out></td>
																</tr>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr>
																<td class="td-border list-capitalize">No Record
																	Found</td>
																<td class="td-border list-capitalize">No Record
																	Found</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
										</div>
									</div>
									<div class="col-xs-6">
										<div class="pi-responsive-table-sm">
											<label> Existing Entity / Not UpDated Record</label>
											<table style="border: 1px solid;">
												<thead style="background-color: #2a3f54">
													<tr>
														<th style="text-align: center; color: #fff; width: 50%;">Entity
															Name</th>
														<th style="text-align: center; color: #fff; width: 50%;">Email
															Address</th>
													</tr>
												</thead>
												<tbody>
													<c:choose>
														<c:when test="${!empty existingCompanyList}">
															<c:forEach items="${existingCompanyList}"
																var="searchResult">
																<tr>
																	<td class="td-border list-capitalize"
																		style="width: 50%;"><c:out
																			value="${searchResult.companyName}" /></td>
																	<td class="td-border list-capitalize"
																		style="width: 50%;"><c:out
																			value="${searchResult.email }"></c:out></td>
																</tr>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr>
																<td class="td-border list-capitalize"
																	style="width: 50%;">No Record Found</td>
																<td class="td-border list-capitalize"
																	style="width: 50%;">Record Found</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
