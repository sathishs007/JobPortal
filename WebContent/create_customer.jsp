
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


<script type="text/javascript">		
	$(document).ready(function() {
		$('#customerName').change(function() {
			
			$.ajax({
				type : "GET",
				url : 'getcustomer',
				data : 'customerName=' + $("#customerName").val(),				
				success : function(data) {	
				//	var obj = JSON.parse(data);
					var len = data.length;
					
					var location = data;
					alert(data);
					if(len!=0){
						alert("data");
						
						var val="user name";
						
					}
					
					
					
					
					
					
					
					
					
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
							<i class="fa fa-user"></i>&nbsp;&nbsp;Create Customer
						</button>
					</div>
					<section class="widget widget-appointment ">
						<form:form id="myForm" class="login-form clearfix"
							modelAttribute="customer">
							<label>Customer Name<font color="red">*</font></label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										path="customerName" id="customerName"
										placeholder="Customer Name" maxlength="20" />
									<i class="fa fa-user"></i>
									<form:errors path="customerName"  cssClass="error" />
								</div>
								<label class="menu-text2">Mobile Number<font color="red">*</font></label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control" path="mobileNumber"
										id="contactNumberInput" placeholder="Contact Number" maxlength="10" />
									<i class="fa fa-user"></i>
									<form:errors path="mobileNumber" cssClass="error" />
								</div>
							</div>
							<label>Company Name</label>
							<div class="vc_row-fluid ">
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="companyName" path="companyName"
										placeholder="Company Name" maxlength="20" />
									<i class="fa fa-mobile"></i>
									<form:errors path="companyName" cssClass="error" />
									
								</div>
								<label class="menu-text2">Office Number</label>
								<div class="vc_span6 row">
									<form:input type="text" class="form-control"
										id="contactNumberInput" path="officeNumber"
										placeholder="ContactNumber" maxlength="10" />
									<i class="fa fa-phone"></i>
									<form:errors path="officeNumber" cssClass="error" />
								</div>
							</div>
							<label>Contact E-Mail</label>
							<div class="vc_row-fluid ">
								<div class="vc_span12 row">
									<form:input type="text" class="form-control" id="emailAddress"
										path="emailAddress" placeholder="E-mail" />
									<i class="fa fa-inbox"></i>
									<form:errors path="emailAddress" cssClass="error" />
								</div>
							</div>
							<label>Address</label>
							<div class="vc_span12 row">
								<form:textarea path="address" />
								<t class="fa fa-list-ul"></t>
								<form:errors path="address" cssClass="error" />
							</div>
							<br />
							<br />
							<br />
							<div class="wpb_content_element grid-md grid-vdi">
								<button class="wpb_button wpb_btn-rounded wpb_btn-small ">Add</button>
								<a href="retrieve_lab_address.html"> <span
									class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
								</a>
								
								<c:if test="${not empty message}">
									<div style="color: #008fd5;">${message}</div>
								</c:if>
							</div>
						</form:form>
					</section>
					<!-- form login -->
				</div>
			</div>
		</div>
	</div>
</div>
</div>