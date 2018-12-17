
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$('#supplierName').change(function() {
			alert("hi");
			$.ajax({
				type : "GET",
				url : 'get_supplier',
				data : 'supplierName=' + $("#supplierName").val(),
				success : function(bo) {
					alert("hi");

					var nameInput = bo.supplierId;
					alert("hi" + nameInput);
					$("#supplierIdInput").val(bo.supplierId);
					$("#emailInput").val(bo.emailAddress);
					$("#supplierCompanyNameInput").val(bo.companyName);
					$("#mobileNumberInput").val(bo.mobileNumber);
					$("#addressInput").val(bo.address);
				},
			});
		});
	});
</script>


<main class="page-content">
<div class="grid-row ">
	<div class="grid-col grid-col-9 bg-color2 bg1-width">
		<div class="vc_row-fluid ">
			<div class="vc_span12">
				<div class="grid-mid">
					<div class="row">
						<button type="submit"
							class="wpb_button wpb_btn-rounded wpb_btn-small grid-vid1 "
							disabled="true" value="Submit">
							<i class="fa fa-user"></i>&nbsp;&nbsp;Add Customer Invoice
						</button>
					</div>
					<section class="widget widget-appointment ">
						<form:form id="myForm" class="login-form clearfix"
							enctype="multipart/form-data" modelAttribute="SupplierInvoice"
							method="POST">
							<c:if test="${not empty message }">
								<c:out value="${message}"></c:out>
							</c:if>
							<label>Supplier Name<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:select path="supplierName" id="supplierName">
										<form:option value="">Select</form:option>
										<form:options items="${supplierName}" />
									</form:select>
									<i class="fa fa-user"></i>
									<form:errors path="supplierName" cssClass="error" />
								</div>
								<label class="menu-text2">Supplier ID<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control" path="supplierId"
										id="supplierIdInput" placeholder="Supplier ID" />
									<i class="fa fa-user"></i>
								</div>
							</div>

							<label>Company Name<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										path="supplierCompanyName" id="supplierCompanyNameInput"
										placeholder="Mobile Number" />
									<i class="fa fa-user"></i>
								</div>
								<label class="menu-text2">Mobile Number<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										path="mobileNumber" id="mobileNumberInput"
										placeholder="Mobile Number" />
									<i class="fa fa-mobile"></i>
								</div>
							</div>

							<label>Email<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span12 row">
									<form:input type="text" class="form-control" path="email"
										id="emailInput" placeholder="Email" />
									<i class="fa fa-inbox"></i>
								</div>
							</div>

							<label>Address<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span12 row">
									<form:textarea path="address" id="addressInput" />
									<t class="fa fa-list-ul"></t>
								</div>
							</div>


							<label>Invoice Date<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control" path="invoiceDate"
										id="datepicker" placeholder="Invoice Date" />
									<i class="fa fa-calendar"></i>
									<form:errors path="invoiceDate" cssClass="error" />
								</div>
								<label class="menu-text2">Quantity<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control" path="quantity"
										id="quantityInput" placeholder="Quantity" />
									<i class="fa fa-user"></i>
								</div>
							</div>


							<label>Description<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span12 row">
									<form:textarea path="description" />
									<t class="fa fa-list-ul"></t>
									<form:errors path="" cssClass="error" />
								</div>
							</div>

							<label>Invoice Amount<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="invoiceAmountInput" path="invoiceAmount"
										placeholder="invoiceAmount" />
									<i class="fa fa-dollar"></i>
									<form:errors path="invoiceAmount" cssClass="error" />
								</div>
								<label class="menu-text2">Invoice Number<font
									color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="invoiceNumberInput" path="invoiceNumber"
										placeholder="invoiceNumber" />
									<i class="fa fa-inbox"></i>
									<form:errors path="invoiceNumber" cssClass="error" />
								</div>
							</div>
							<div class="wpb_content_element grid-md grid-vdi">
								<button class="wpb_button wpb_btn-rounded wpb_btn-small ">Add
									Items</button>
								<a href="retrieve_lab_address.html"> <span
									class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
								</a>

							</div>
						</form:form>
						<%-- <c:if test="${not empty message}">
							<div class="message red">${message}</div>
						</c:if> --%>
						<c:if test="${!empty SupplierList}">
							<div class="datagrid">
								<table>
									<thead>
										<tr>
											<th><center>Invoice ID</center></th>
											<th><center>Invoice Number</center></th>
											<th><center>Description</center></th>
											<th><center>Quantity</center></th>
											<th><center>Price</center></th>
											<th><center>Edit</center></th>
											<th><center>Delete</center></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${SupplierList}" var="searchResult">
											<tr>
												<td><c:out value="${searchResult.id }"></c:out></td>
												<td><c:out value="${searchResult.invoiceNumber }"></c:out></td>
												<td><c:out value="${searchResult.description }"></c:out></td>
												<td><c:out value="${searchResult.quantity }"></c:out></td>
												<td><c:out value="${searchResult.invoiceAmount }"></c:out></td>
												<td class="text-right"><center>
														<a
															href="edit_supplier_invoice_items.html?id=${searchResult.id}"
															class="btn btn-theme btn-xs btn-default">Edit</a>
													</center></td>
												<td class="text-right"><center>
														<a
															href="delete_supplier_invoice_items.html?id=${searchResult.id}"
															class="btn btn-theme btn-xs btn-default">Delete</a>
													</center></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty TotalAmount}">
								<div class="message red">Total Amount=${TotalAmount}</div>
							</c:if>
							<div class="wpb_content_element grid-md grid-vdi">
								<a href="supplier_invoice.html"> <span
									class="wpb_button wpb_btn-rounded wpb_btn-small ">Add
										Invoice</span>
								</a>
							</div>
						</c:if>
					</section>
					<!-- form login -->
				</div>
			</div>
		</div>
	</div>
</div>