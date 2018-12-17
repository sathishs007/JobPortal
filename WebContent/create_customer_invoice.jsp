
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>		
	$(document).ready(function() {
		$('#customerName').change(function() {
			alert("hi");
			$.ajax({
				type : "GET",
				url : 'get_customer',
				data : 'customerName=' + $("#customerName").val(),				
success : function(bo) {							
					alert("hi");
					
					var nameInput=bo.customerId;
					alert("hi"+nameInput);
					$("#customerIdInput").val(bo.customerId);
					$("#emailInput").val(bo.emailAddress);
					$("#companyNameInput").val(bo.companyName);
					$("#contactNumberInput").val(bo.mobileNumber);
					$("#addressInput").val(bo.address);
					
					
					//list of value used this function
					/* var html = '<option value="">State</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].name + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';

					$('#usStates').html(html); */
					
					
					
					
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
							modelAttribute="inBO" method="POST">
							<c:if test="${not empty message }">
							<c:out value="${message}"></c:out>
							</c:if>
							<label>Lab Name<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									
									
										<form:select path="customerName" id="customerName">
										<form:option value="">Select</form:option>
										<form:options items="${customerName}" />
										</form:select>
									<i class="fa fa-user"></i>
									
									<form:errors path="customerName" cssClass="error" />
								</div>
								<label class="menu-text2">CustomerID<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control" path="customerId"
										id="customerIdInput" placeholder="CustomerId"/>
									<i class="fa fa-user"></i>
									<form:errors path="customerId" cssClass="error" />
								</div>
							</div>
							<label>CompanyName<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span12 row">
									<form:input type="text" class="form-control"
										id="companyNameInput" path="companyName"
										placeholder="CompanyName" />
									<i class="fa fa-inbox"></i>
									<form:errors path="companyName" cssClass="error" />
								</div>
							</div> 
							<label>ContactNo<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="contactNumberInput" path="contactNo"
										placeholder="ContactNumber"  />
									<i class="fa fa-mobile"></i>
									<form:errors path="contactNo" cssClass="error" />
								</div>
								<label class="menu-text2">Email-ID<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="emailInput" path="email"
										placeholder="ContactNumber"  />
									<i class="fa fa-phone"></i>
									<form:errors path="email" cssClass="error" />
								</div>
							</div>
							
							<lable>Address<font color="red">*</font></lable>
								<div class="vc_span12 row" >
									<form:textarea path="address" id="addressInput" />
									<t class="fa fa-list-ul"></t>
									<form:errors path="address" cssClass="error" />
								</div>
								<br/>	<br/>	<br/>
							<label>Description</label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:textarea type="text" class="form-control"
										id="contactNumberInput" path="description"
										placeholder="Description"  />
									<t class="fa fa-list-ul"></t>
									<form:errors path="description" cssClass="error" />
								</div>
								<label class="menu-text2" style="margin-top:1%">Amount<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="amountInput" path="amount"
										placeholder="Ampunt" maxlength="5" />
									<i class="fa fa-phone"></i>
									<form:errors path="amount" cssClass="error" />
								</div>
							</div>
							<div class="wpb_content_element grid-md grid-vdi">
								<button class="wpb_button wpb_btn-rounded wpb_btn-small ">Add</button>
								<a href="retrieve_lab_address.html">
									<span 
										class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
								</a>
								
							</div>
						</form:form>
					</section>
					<!-- form login -->
				</div>
			</div>
		</div>
	</div>
</div>
