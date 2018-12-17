<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
<style>
span.pagebanner {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	width: 100%;
	margin-top: 10px;
	display: block;
	border-bottom: none;
}

span.pagelinks {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 6px 4px 6px 4px;
	width: 100%;
	display: block;
	border-top: none;
	margin-bottom: -5px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/tables.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/theme-custom.css" />

</head>

<body>

	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="text-center">
					<h3 class="margin-top titleunderline">View Products</h3>
					<div class="warning" style="width: 100%; text-align: left;">
						<c:if test="${not empty message}">
							<div class="alert alert-info" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Info!</strong> ${message}
							</div>
						</c:if>

					</div>

				</div>


				<c:if test="${!empty productList}">
					<div class="pi-section-w pi-section-white piTooltips">
						<display:table id="productList" name="${productList}"
							requestURI="viewProduct.html" pagesize="5" export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
							<display:column property="sno" title="SNo" />
							<c:if test="${productList.active =='Active'}">
								<display:column url="product_activation.html" media="html"
									paramId="status" title="Product Type ">
									<a
										href="product_activation.html?status=${productList.active},${productList.productId}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: green;"> <c:out
											value="${productList.productType }"></c:out></a>

								</display:column>
							</c:if>

							<c:if test="${productList.active =='De-Active'}">
								<display:column url="product_activation.html" media="html"
									paramId="status" title="Action">
									<a
										href="product_activation.html?status=${productList.active},${productList.productId}"
										onclick="return confirm('Are you sure you want to continue?')"
										style="color: red;"> <c:out
											value="${productList.productType }"></c:out></a>

								</display:column>
							</c:if>
							<display:column property="services" title="Services" />
							<display:column property="registerDate" title="Created Date" />
							<display:column url="productUpdate.html" media="html"
								paramId="id" paramProperty="id" title="Edit">
								<a href="productUpdate.html?id=${productList.productId}"> <i
									style="text-align: center;" class="fa fa-pencil"></i></a>
							</display:column>

							<display:column
								url="deleteProduct.html?id=${searchResult.productId}"
								media="html" paramId="id" paramProperty="id" title="Delete">
								<a href="deleteProduct.html?id=${productList.productId}"
									onclick="return confirm('Are you sure you want to Delete?')"><i
									class=" fa fa-trash" class="btn btn-theme btn-xs btn-default"></i></a>
							</display:column>
						</display:table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>