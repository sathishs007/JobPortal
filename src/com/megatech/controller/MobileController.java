package com.megatech.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.megatech.model.AdminLoginBO;
import com.megatech.model.CustomerBO;
import com.megatech.model.ServiceBO;
import com.megatech.service.AdminService;
import com.megatech.utils.MegatechResourceBundle;
@Controller
public class MobileController {
	@Autowired
	AdminService service;
	
	@RequestMapping(value = "log_in" 
			, method = RequestMethod.GET)
	public @ResponseBody AdminLoginBO login(@RequestParam String userName,@RequestParam String password
			
			) {
		AdminLoginBO adminLoginBO =new AdminLoginBO();
		try {
			adminLoginBO.setEmailAddress(userName);
			adminLoginBO.setPassword(password);
				adminLoginBO = service.authenticate(adminLoginBO);

			if (adminLoginBO.getStatus()) {
				return adminLoginBO;
			} else {
				adminLoginBO=null;
				return adminLoginBO;
			}
		} catch (Exception jb) {

		}
		return null;
	}
	
	
	@RequestMapping(value = "all_customer" 
			, method = RequestMethod.GET)
	public @ResponseBody List<CustomerBO> allCustomer(
			
			) {
		
		try {
			List<CustomerBO>customerList=new ArrayList<CustomerBO>();
			CustomerBO customerBO = service.retrieveCustomers();

			if (null!=customerBO.getAllCustomerBOList()&&0!=customerBO.getAllCustomerBOList().size()) {
				
				customerList=customerBO.getAllCustomerBOList();
				return customerList;
			} else {
				customerList=null;
				return customerList;
			}
		} catch (Exception jb) {

		}
		return null;
	}
	
	@RequestMapping(value = "add_service" 
			, method = RequestMethod.GET)
	public @ResponseBody String addService(@RequestParam int id,@RequestParam String customerId,
			@RequestParam String customerName,@RequestParam String jobNo,@RequestParam String technician,@RequestParam String location,
			@RequestParam Date dateOfService,@RequestParam String description,@RequestParam String refNo,@RequestParam String specialRemarks,@RequestParam String fileupload 
			
			) {
		
		try {
			ServiceBO bo=new ServiceBO();
			bo.setServiceId(id);
			CustomerBO customerBO=new CustomerBO();
			customerBO.setCustomerId(customerId);
			bo.setCustomerBO(customerBO);
			bo.setCustomerName(customerName);
			bo.setJobNo(jobNo);
			bo.setTechnician(technician);
			bo.setLocation(location);
			bo.setDateOfServics(dateOfService);
			bo.setDescription(description);
			bo.setRefNo(refNo);
			bo.setSpecialRemarks(specialRemarks);
			if(!fileupload.isEmpty()||fileupload!=null){
			byte[] imageByteArray = decodeImage(fileupload);
			bo.setFileupload(imageByteArray);
			}
		boolean isStatus = service.addService(bo);
		if(isStatus){
			return "service is successfully created";
		}else{
			return "service is created failure";
		}

			
		} catch (Exception jb) {

		}
		return null;
	}
	
	@RequestMapping(value = "all_service" 
			, method = RequestMethod.GET)
	public @ResponseBody List<ServiceBO> reteriveService(
			
			) {
		
		try {
			List<ServiceBO>serviceList=new ArrayList<ServiceBO>();
			ServiceBO serviceBO = service.retrieveService();

			if (null!=serviceBO.getAllServiceBOList()&&0!=serviceBO.getAllServiceBOList().size()) {
				
				serviceList=serviceBO.getAllServiceBOList();
				return serviceList;
			} else {
				serviceList=null;
				return serviceList;
			}
		} catch (Exception jb) {

		}
		return null;
	}
	
	@RequestMapping(value = "search_service" 
			, method = RequestMethod.GET)
	public @ResponseBody List<ServiceBO> searchService(@RequestParam String searchName
			
			) {
		
		try {
			List<ServiceBO>serviceList=new ArrayList<ServiceBO>();
			ServiceBO serviceBO = service.searchService(searchName);

			if (null!=serviceBO.getAllServiceBOList()&&0!=serviceBO.getAllServiceBOList().size()) {
				
				serviceList=serviceBO.getAllServiceBOList();
				return serviceList;
			} else {
				serviceList=null;
				return serviceList;
			}
		} catch (Exception jb) {

		}
		return null;
	}
	
	@RequestMapping(value = "search_report" 
			, method = RequestMethod.GET)
	public @ResponseBody List<ServiceBO> searchReport(@RequestParam String searchName
			
			) {
		
		try {
			List<ServiceBO>serviceList=new ArrayList<ServiceBO>();
			ServiceBO serviceBO = service.searchReport(searchName);

			if (null!=serviceBO.getAllServiceBOList()&&0!=serviceBO.getAllServiceBOList().size()) {
				
				serviceList=serviceBO.getAllServiceBOList();
				return serviceList;
			} else {
				serviceList=null;
				return serviceList;
			}
		} catch (Exception jb) {
jb.printStackTrace();
		}
		return null;
	}
	 
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
	
	
}
