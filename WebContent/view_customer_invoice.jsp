<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true" %>
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
							<i class="fa fa-user"></i>&nbsp;&nbsp;Invoice Customer Details
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
								<c:if test="${!empty invoiceCustomer}">
									<div class="datagrid">
								 <display:table id="data" name="invoiceCustomer" requestURI="/view_customer_invoice.html" pagesize="2" export="true" style="table">
													

														
														<display:column property="id" title="InvoiceID" />
														<display:column property="description" title="Description" />
														<display:column property="amount" title="Amount" />
														
													</display:table>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
</main>



