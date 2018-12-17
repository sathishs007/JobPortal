<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- page title -->
<main class="page-content">
<div class="grid-row">
	<div class="grid-col grid-col-9 bg-color2 bg1-width">
		<div class="vc_row-fluid ">
			<div class="vc_span12">
				<div class="grid-mid">
					<div class="row">

						<button type="submit"
							class="wpb_button wpb_btn-rounded wpb_btn-small  grid-vid1"
							disabled="true" value="Submit">
							<i class="fa fa-user"></i>&nbsp;&nbsp;Supplier Details
						</button>
					</div>
					<br />
					<div class="row">
						<div class="vc_row-fluid">
							<div class="vc_span12">
								<!-- form login -->
								<c:if test="${not empty message}">
									<div class="message red">${message}</div>
								</c:if>
								<c:if test="${!empty ViewSupplier.list}">
									<div class="datagrid">
										<table>
											<thead>
												<tr>
													<th><center>Supplier ID</center></th>
													<th><center>Supplier Name</center></th>
													<th><center>Company Name</center></th>
													<th><center>Email Address</center></th>
													<th><center>Office Number</center></th>
													<th><center>Mobile Number</center></th>
													<th><center>Edit</center></th>
													<th><center>Delete</center></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ViewSupplier.list}" var="searchResult">
													<tr>
														<td><c:out value="${searchResult.supplierId }"></c:out></td>
														<td><c:out value="${searchResult.supplierName }"></c:out></td>
														<td><c:out value="${searchResult.companyName }"></c:out></td>
														<td><c:out value="${searchResult.emailAddress }"></c:out></td>
														<td><c:out value="${searchResult.officeNumber }"></c:out></td>
														<td><c:out value="${searchResult.mobileNumber }"></c:out></td>
														<td class="text-right"><center>
																<a
																	href="edit_supplier.html?supplierId=${searchResult.supplierId}"
																	class="btn btn-theme btn-xs btn-default">Edit</a>
															</center></td>
														<td class="text-right"><center>
																<a
																	href="delete_supplier.html?supplierId=${searchResult.supplierId}"
																	class="btn btn-theme btn-xs btn-default"> <i
																	class=" fa fa-trash-o"></i></a>
															</center></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${!empty ViewSupplier.list}">
					<nav class="text-center">
						<ul class="pagination pagination-theme  no-margin">
							<c:if test="${ViewSupplier.currentPage != 1}">
								<li><a aria-label="Previous"
									href="view_supplier.html?page=${ViewSupplier.currentPage - 1}">
										<span aria-hidden="true">&larr;</span>
								</a></li>
							</c:if>
							<li><c:forEach begin="${ViewSupplier.start}"
									end="${ViewSupplier.records}" var="i">
									<c:choose>
										<c:when test="${ViewSupplier.page == i}">
											<li class="active"><a
												href="view_supplier.html?page=${i}" style="color: #fff;">${i}</a></li>
										</c:when>
										<c:otherwise>
											<a href="view_supplier.html?page=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach></li>
							<c:if
								test="${ViewSupplier.currentPage lt ViewSupplier.totalPages}">
								<li><a
									href="view_supplier.html?page=${ViewSupplier.currentPage + 1}"><span
										aria-hidden="true">&rarr;</span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>
				<!-- form login -->
			</div>
		</div>
	</div>
</div>
</main>



