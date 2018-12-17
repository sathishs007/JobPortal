<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Myjobkart</title>
</head>
<body>
	<div class="body-content clearfix">
		<div class="bg-color1 block-section line-bottom">
			<div class="container">
				<h2 class="text-center">
					We're here to help in any way we can.<br /> <small> Please
						submit your request below and we'll get back to you shortly.</small>
				</h2>
				<div class="white-space-20"></div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form>
							<div class="form-group">
								<label>Email</label> <input type="email" class="form-control"
									value="your@email.com" readonly="readonly">
							</div>
							<div class="form-group">
								<label>Subject</label> <input type="text" class="form-control">
							</div>
							<div class="form-group">
								<label>Message</label>
								<textarea class="form-control" rows="6"></textarea>
							</div>
							<div class="form-group text-center">
								<div class="white-space-10"></div>
								<button type="submit"
									class="btn btn-theme btn-lg btn-long btn-t-primary btn-pill">Send</button>
							</div>
						</form>
						<div class="white-space-10"></div>
						<p class="text-center">
							<span class="span-line">OR</span>
						</p>
						<div class="row">
							<div class="col-md-6">
								<div class="box-ic-center ">
									<i class="fa fa-phone ic-box"></i>
									<h4>Phone</h4>
									<p>044 42824663</p>
								</div>

							</div>
							<div class="col-md-6">
								<div class="box-ic-center ">
									<i class="fa fa-skype ic-box"></i>
									<h4>Skype</h4>
									<p>info.myjobkart@gmail.com </p>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bg-color2 block-section-xs line-bottom">
			<div class="container text-center">
				<a href="#map-toogle" id="btn-map-toogle" data-toggle="collapse">Check
					Our Office</a>
			</div>
		</div>
		<div class="collapse" id="map-toogle">
			<div class=" bg-color2" id="map-area">
				<div class="container">
					<div class="marker-description">
						<div class="inner">
							<h4 class="no-margin-top">Office Location</h4>
							Central Jakarta No 1234, Jakarta, Indonesia
						</div>
					</div>
				</div>
				<div class="map-area-detail">
					<div class="box-map-detail" id="map-detail-job"
						data-lat="48.856318" data-lng="2.351866"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>