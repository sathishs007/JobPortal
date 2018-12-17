package com.megatech.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.megatech.exception.JLogger;
import com.megatech.model.AddressInfo;
import com.megatech.model.AdminLoginBO;
import com.megatech.model.ChangePassword;
import com.megatech.model.CustomerBO;
import com.megatech.model.InvoiceCustomerBO;
import com.megatech.model.ResponseObject;
import com.megatech.model.SupplierBO;
import com.megatech.model.SupplierInvoiceBO;
import com.megatech.service.AdminService;
import com.megatech.utils.EncryptAndDecrypt;
import com.megatech.utils.MegatechResourceBundle;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

@Controller
public class AdminController {
	@Autowired
	AdminService service;

	String salt = "this is a simple clear salt";
	private static final JLogger LOGGER = JLogger
			.getLogger(AdminController.class);

	List<AdminLoginBO> profileList;

	List<AdminLoginBO> adminUserList;
	List<AddressInfo> addressInfoBOList;

	TreeSet<String> locations = new TreeSet<String>();

	AddressInfo addressInfo;
	List<CustomerBO> customerLists = new ArrayList<CustomerBO>();

	String autogenerateId;
	List<SupplierBO> supplierBOList;
	List<SupplierInvoiceBO> supplierInvoiceBOList;
	SupplierBO supplier;
	SupplierInvoiceBO supplierInvoiceBO;
	CustomerBO customer;
	BigDecimal total = new BigDecimal(0.00);
	List<SupplierInvoiceBO> supplierAllInvoice;
	String navigation;

	/**
	 * This method is used authenticate the admin user
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin_sign_in", method = RequestMethod.GET)
	public String login(Model model) {
		LOGGER.entry();
		try {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			model.addAttribute("adminLogin", adminLoginBO);
			model.addAttribute("myclinic", "myclinic");
		} catch (Exception e) {

		}

		LOGGER.exit();
		return "admin_sign_in";
	}

	/**
	 * This method is used authenticate the admin user
	 * 
	 * @param adminLoginBO
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin_sign_in", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute("adminLogin") AdminLoginBO adminLoginBO,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			if (result.hasErrors()) {
				return "admin_sign_in";
			}
			adminLoginBO = service.authenticate(adminLoginBO);

			if (adminLoginBO.getStatus()) {
				session.setAttribute("id", adminLoginBO.getId());
				session.setAttribute("emailId", adminLoginBO.getEmailAddress());
				session.setAttribute("userType", adminLoginBO.getUserType());

				if (null != session.getAttribute("id")) {
					session.setAttribute("admin", "admin");
					session.setAttribute("login", "login");

				}
				return "redirect:/admin_home.html";
			} else {
				model.addAttribute("message", MegatechResourceBundle
						.getValue("Validate.User.Authenticate"));
				return "admin_sign_in";
			}
		} catch (Exception jb) {

		}
		return null;
	}

	@RequestMapping(value = "/create_customer_invoice", method = RequestMethod.GET)
	public String createCustomerInvoice(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {

			allCustomer(model);
			model.addAttribute("inBO", new InvoiceCustomerBO());
		} catch (Exception e) {

		}

		LOGGER.exit();
		return "create_customer_invoice";
	}

	@RequestMapping(value = "/create_supplier_invoice", method = RequestMethod.GET)
	public String createSupplierInvoice(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {
			allSupplier(model);
			model.addAttribute("SupplierInvoice", new SupplierInvoiceBO());
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "create_supplier_invoice";
	}

	@RequestMapping(value = "/supplier_invoice", method = RequestMethod.GET)
	public String createSupplierInvoiceAll(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		try {
			for (SupplierInvoiceBO invoiceBO : supplierInvoiceBOList) {
				supplierInvoiceBO = invoiceBO;
			}
			supplierInvoiceBO.setTotal(total);
			boolean isStatus = service
					.createSupplierAllInvoice(supplierInvoiceBO);

			if (isStatus) {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierInvoiceBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Success"));
				return "redirect:/view_supplier_invoice.html";
			} else {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierInvoiceBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Failure"));
				return "create_supplier";
			}

		} catch (Exception e) {

		}
		LOGGER.exit();
		return "create_supplier_invoice";
	}

	@RequestMapping(value = "/update_supplier_invoice", method = RequestMethod.GET)
	public String updateSupplierInvoiceAll(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		String invoiceId = request.getParameter("id");
		long id = Long.parseLong(invoiceId);
		try {
			for (SupplierInvoiceBO invoiceBO : supplierInvoiceBOList) {
				supplierInvoiceBO = invoiceBO;
			}
			supplierInvoiceBO.setId(id);
			supplierInvoiceBO.setTotal(total);
			boolean isStatus = service
					.updateSupplierAllInvoice(supplierInvoiceBO);

			if (isStatus) {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierInvoiceBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Success"));
				return "redirect:/view_supplier_invoice.html";
			} else {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierInvoiceBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Failure"));
				return "create_supplier";
			}

		} catch (Exception e) {

		}
		LOGGER.exit();
		return "create_supplier_invoice";
	}

	@RequestMapping(value = "/create_supplier_invoice", method = RequestMethod.POST)
	public String createSupplierInvoice(
			@ModelAttribute("SupplierInvoice") @Valid SupplierInvoiceBO supplierInvoiceBO,
			BindingResult result, HttpServletRequest request, Model model) {
		LOGGER.entry();
		HttpSession session = request.getSession();
		long loginId = (Long) session.getAttribute("id");
		LOGGER.entry();
		try {

			if (result.hasErrors()) {

				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - "
							+ error.getDefaultMessage());
				}

				allSupplier(model);
				return "create_supplier_invoice";
			}
			supplierInvoiceBO.setIsDeleted(true);
			supplierInvoiceBO.setCreatedBy(loginId);
			supplierInvoiceBO.setModifiedBy(loginId);

			boolean isStatus = service.createSupplierInvoice(supplierInvoiceBO);
			if (isStatus) {
				SupplierInvoiceBO invoiceBO = service
						.retrieveSupplierInvoice(supplierInvoiceBO);
				supplierInvoiceBOList = invoiceBO.getAllSupplierInvoiceBOList();
				if (null != supplierInvoiceBOList
						&& 0 != supplierInvoiceBOList.size()) {

					for (SupplierInvoiceBO invoiceBO2 : supplierInvoiceBOList) {
						total = total.add(invoiceBO2.getInvoiceAmount());
					}

					model.addAttribute("TotalAmount", total);
					model.addAttribute("SupplierList", supplierInvoiceBOList);
				} else {
					model.addAttribute("message", "Retrieve Failure");
				}

				model.addAttribute("message", "SuuplierInvoice"
						+ MegatechResourceBundle.getValue("Validate.Success"));
				allSupplier(model);
				clear(model);
				return "create_supplier_invoice";
			} else {
				model.addAttribute("message", "SuuplierInvoice"
						+ MegatechResourceBundle.getValue("Validate.Failure"));
				return "view_supplier_invoice";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();

		return null;
	}

	@RequestMapping(value = "/edit_supplier_invoice_items", method = RequestMethod.GET)
	public String editSupplierInvoiceItems(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		navigation = null;
		String invoiceId = request.getParameter("id");
		long id = Long.parseLong(invoiceId);
		String path = request.getParameter("path");
		try {
			for (SupplierInvoiceBO invoiceBO : supplierInvoiceBOList) {
				if (id == invoiceBO.getId()) {
					allSupplier(model);
					model.addAttribute("EditSupplierItem", invoiceBO);
					supplierInvoiceBO = invoiceBO;
					if (path != null) {
						navigation = path;
					}
				} else {
					model.addAttribute("EditSupplierItem", supplierInvoiceBO);
				}
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "edit_supplier_invoice_items";
	}

	@RequestMapping(value = "/edit_supplier_invoice_items", method = RequestMethod.POST)
	public String editSupplierInvoiceItems(
			@Valid @ModelAttribute("EditSupplierItem") SupplierInvoiceBO supplierBO,
			BindingResult result, Model model) throws Exception {
		LOGGER.entry();
		SupplierInvoiceBO invoiceBO = null;
		total = new BigDecimal(0.00);
		try {

			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - "
							+ error.getDefaultMessage());
				}
				allSupplier(model);
				return "edit_supplier_invoice_items";
			}

			supplierBO.setId(supplierInvoiceBO.getId());
			supplierBO.setCreated(supplierInvoiceBO.getCreated());
			supplierBO.setIsDeleted(supplierInvoiceBO.getIsDeleted());
			supplierBO.setCreatedBy(supplierInvoiceBO.getCreatedBy());
			supplierBO.setModifiedBy(supplierInvoiceBO.getModifiedBy());

			boolean status = service.editSupplierInvoiceItems(supplierBO);

			if (status) {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Update.Success"));
				model.addAttribute("message", supplierBO.getResponse());
				allSupplier(model);
				model.addAttribute("SupplierInvoice", new SupplierInvoiceBO());

				for (SupplierInvoiceBO bo : supplierInvoiceBOList) {
					if (supplierInvoiceBO.getId() == bo.getId()) {
						invoiceBO = service.retrieveSupplierInvoice(bo);
					}
				}

				supplierInvoiceBOList = invoiceBO.getAllSupplierInvoiceBOList();
				if (null != supplierInvoiceBOList
						&& 0 != supplierInvoiceBOList.size()) {

					for (SupplierInvoiceBO invoiceBO2 : supplierInvoiceBOList) {
						total = total.add(invoiceBO2.getInvoiceAmount());
						if (supplierInvoiceBO.getId() == invoiceBO2.getId()) {
							supplierInvoiceBO = invoiceBO2;
						}
					}
					model.addAttribute("TotalAmount", total);
					model.addAttribute("SupplierList", supplierInvoiceBOList);
					if (navigation == null) {
						return "create_supplier_invoice";
					} else if (navigation.equalsIgnoreCase("edit")) {
						model.addAttribute("EditSupplier", supplierInvoiceBO);
						return "edit_supplier_invoice";
					}

				} else {
					model.addAttribute(
							"message",
							"Supplier"
									+ supplierBO.getSupplierName()
									+ MegatechResourceBundle
											.getValue("Validate.Update.Failure"));
					return "edit_supplier_invoice_items";
				}
			}
		} catch (Exception e) {

		}
		return "view_supplier";
	}

	@RequestMapping(value = "/delete_supplier_invoice_items", method = RequestMethod.GET)
	public String deleteSupplierInvoiceItems(Model model,
			HttpServletRequest request) {
		String deletedId = request.getParameter("id");
		long id = Long.parseLong(deletedId);
		SupplierInvoiceBO supplierBO = new SupplierInvoiceBO();
		SupplierInvoiceBO invoiceBO = null;
		total = new BigDecimal(0.00);
		HttpSession session = request.getSession();
		long loginId = (Long) session.getAttribute("id");
		try {
			supplierBO.setId(id);
			supplierBO.setModified(new Date());
			supplierBO.setIsDeleted(false);
			supplierBO.setModifiedBy(loginId);
			supplierBO = service.deleteSupplierInvoiceItems(supplierBO);
			if (null != supplierBO.getResponse()) {
				model.addAttribute("message", supplierBO.getResponse());
				allSupplier(model);
				model.addAttribute("SupplierInvoice", new SupplierInvoiceBO());

				for (SupplierInvoiceBO bo : supplierInvoiceBOList) {
					if (id == bo.getId()) {
						invoiceBO = service.retrieveSupplierInvoice(bo);
					}
				}

				supplierInvoiceBOList = invoiceBO.getAllSupplierInvoiceBOList();
				if (null != supplierInvoiceBOList
						&& 0 != supplierInvoiceBOList.size()) {

					for (SupplierInvoiceBO invoiceBO2 : supplierInvoiceBOList) {
						total = total.add(invoiceBO2.getInvoiceAmount());
					}

					model.addAttribute("TotalAmount", total);
					model.addAttribute("SupplierList", supplierInvoiceBOList);
					return "create_supplier_invoice";
				} else {
					model.addAttribute("message",
							MegatechResourceBundle.getValue("Validate.Delete"));

				}
			}
		} catch (Exception e) {

		}
		return null;

	}

	private void clear(Model model) {
		SupplierInvoiceBO supplierInvoiceBO = new SupplierInvoiceBO();
		supplierInvoiceBO.setInvoiceAmount(null);
		model.addAttribute("SupplierInvoice", supplierInvoiceBO);
	}

	@RequestMapping(value = "/create_customer_invoice", method = RequestMethod.POST)
	public String createCustomerInvoice(
			@Valid @ModelAttribute("inBO") InvoiceCustomerBO invoiceCustomerBO,
			BindingResult result, HttpServletRequest request, Model model) {
		LOGGER.entry();
		HttpSession session = request.getSession();
		long id = (Long) session.getAttribute("id");
		try {

			if (result.hasErrors()) {
				allCustomer(model);
				return "create_customer_invoice";
			}

			invoiceCustomerBO.setCreatedBy(id);
			invoiceCustomerBO.setModifiedBy(id);
			boolean isStatus = service.createCustomerInvoice(invoiceCustomerBO);
			if (isStatus) {
				model.addAttribute("message", "Customerinvoice"
						+ MegatechResourceBundle.getValue("Validate.Success"));
				return "redirect:/view_customer_invoice.html";
			} else {
				model.addAttribute("message", "Customerinvoice"
						+ MegatechResourceBundle.getValue("Validate.Failure"));
				return "view_customer_invoice";
			}

		} catch (Exception e) {

		}

		LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/view_customer_invoice", method = RequestMethod.GET)
	public String retrieveCustomerInvoice(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		try {

			List<InvoiceCustomerBO> invoiceCustomer = service
					.retrieveCustomerInvoice();
			if (null != invoiceCustomer && 0 != invoiceCustomer.size()) {
				model.addAttribute("invoiceCustomer", invoiceCustomer);
			} else {
				model.addAttribute("message", "reteive failer");
			}

		} catch (Exception e) {

		}
		LOGGER.exit();
		return "view_customer_invoice";
	}

	@RequestMapping(value = "/view_supplier_invoice", method = RequestMethod.GET)
	public String retrieveSupplierInvoice(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		try {

			supplierAllInvoice = service.retrieveAllSupplierInvoice();
			if (null != supplierAllInvoice && 0 != supplierAllInvoice.size()) {
				model.addAttribute("SupplierAllList", supplierAllInvoice);
			} else {
				model.addAttribute("message", "Retrieve Failure");
			}

		} catch (Exception e) {

		}
		LOGGER.exit();
		return "view_supplier_invoice";
	}

	@RequestMapping(value = "/edit_supplier_invoice", method = RequestMethod.GET)
	public String editSupplierInvoice(Model model, HttpServletRequest request) {
		LOGGER.entry();
		String invoiceId = request.getParameter("id");
		long id = Long.parseLong(invoiceId);
		SupplierInvoiceBO invoiceBO = null;
		total = new BigDecimal(0.00);
		try {
			for (SupplierInvoiceBO bo : supplierAllInvoice) {
				if (id == bo.getId()) {
					invoiceBO = service.retrieveSupplierInvoice(bo);
					supplierInvoiceBO = bo;
				}
			}

			supplierInvoiceBOList = invoiceBO.getAllSupplierInvoiceBOList();
			if (null != supplierInvoiceBOList
					&& 0 != supplierInvoiceBOList.size()) {

				for (SupplierInvoiceBO invoiceBO2 : supplierInvoiceBOList) {
					total = total.add(invoiceBO2.getInvoiceAmount());
				}
				allSupplier(model);
				model.addAttribute("TotalAmount", total);
				model.addAttribute("EditSupplier", supplierInvoiceBO);
				model.addAttribute("SupplierList", supplierInvoiceBOList);

			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "edit_supplier_invoice";
	}

	@RequestMapping(value = "/admin_forgot_password", method = RequestMethod.GET)
	public String adminForgotPassword(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {
			AdminLoginBO signIn = new AdminLoginBO();
			model.addAttribute("signIn", signIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit();
		return "admin_forgot_password";
	}

	@RequestMapping(value = "/admin_forgot_password", method = RequestMethod.POST)
	public String adminForgotPassword(
			@Valid @ModelAttribute("signIn") AdminLoginBO loginBO,
			BindingResult result, HttpServletRequest request, Model model)
			throws Exception {
		LOGGER.entry();
		try {
			boolean status = service.forgotPassword(loginBO);
			if (status) {
				model.addAttribute("message",
						"Please Check Your Email To Reset Your Old Password!");
			} else {
				model.addAttribute("message",
						"Your account does not exisit,please contact Administrator.");
			}
		} catch (Exception e) {

		}

		LOGGER.exit();
		return "admin_forgot_password";
	}

	@RequestMapping(value = "/admin_change_password", method = RequestMethod.GET)
	public String adminChangePassword(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {
			ChangePassword changePassword = new ChangePassword();
			model.addAttribute("changePassword", changePassword);
		} catch (Exception e) {

		}

		LOGGER.exit();
		return "admin_change_password";
	}

	@RequestMapping(value = "/admin_change_password", method = RequestMethod.POST)
	public String adminChangePassword(
			@Valid @ModelAttribute("changePassword") ChangePassword changePassword,
			BindingResult result, HttpServletRequest request, Model model)
			throws Exception {
		LOGGER.entry();

		try {

			if (!changePassword.getPassword().equalsIgnoreCase(
					changePassword.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				// model.addAttribute("message", "PassWord should be equal.");
				return "admin_change_password";
			}
			if (result.hasErrors()) {
				return "admin_change_password";
			}

			AdminLoginBO loginBO = new AdminLoginBO();

			if (request.getParameter("emailId") != null) {
				loginBO.setEmailAddress(request.getParameter("emailId"));
			}
			String passwordEnc = EncryptAndDecrypt.encrypt(
					changePassword.getPassword(), salt);
			loginBO.setPassword(passwordEnc);
			boolean status = service.changePassword(loginBO);

			if (status) {
				model.addAttribute("message",
						"password change has been successfuly updated.");
				model.addAttribute("signIn", changePassword);
				return ("redirect:/admin_sign_in.html");

			} else {
				model.addAttribute("message",
						"Your account does not exisit,please contact Administrator.");
			}

		} catch (Exception e) {

		}

		LOGGER.exit();
		return "admin_change_password";
	}

	private void allCustomer(Model model) throws IllegalAccessException,
			InvocationTargetException {
		List<CustomerBO> customerList = new ArrayList<CustomerBO>();
		TreeSet<String> customers = new TreeSet<String>();
		try {
			customerList = service.allCustomer();
			customerLists = customerList;
			for (CustomerBO name : customerList) {
				System.out.println(name.getCustomerName());
				customers.add(name.getCustomerName());
			}
			model.addAttribute("customerName", customers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void allSupplier(Model model) {
		TreeSet<String> suppliers = new TreeSet<String>();
		try {
			SupplierBO supplierBO = service.retrieveSuppliers();
			supplierBOList = supplierBO.getAllSupplierBOList();
			for (SupplierBO supplier : supplierBOList) {
				suppliers.add(supplier.getSupplierName());
			}
			model.addAttribute("supplierName", suppliers);
		} catch (Exception e) {

		}
	}

	@RequestMapping(value = "/get_customer", method = RequestMethod.GET)
	private @ResponseBody
	CustomerBO allCustomerDetails(Model model, @RequestParam String customerName)
			throws IllegalAccessException, InvocationTargetException {

		CustomerBO bo = new CustomerBO();
		System.out.println(customerName);
		for (CustomerBO name : customerLists) {
			if (customerName.equals(name.getCustomerName())) {
				bo = name;
			}
		}
		System.out.println("bo output:::" + bo.getCustomerId());
		return bo;
	}

	@RequestMapping(value = "/get_supplier", method = RequestMethod.GET)
	private @ResponseBody
	SupplierBO allsupplierDetails(Model model, @RequestParam String supplierName)
			throws IllegalAccessException, InvocationTargetException {

		SupplierBO bo = new SupplierBO();
		for (SupplierBO supplierBO : supplierBOList) {
			if (supplierName.equals(supplierBO.getSupplierName())) {
				bo = supplierBO;
			}
		}
		return bo;
	}

	@RequestMapping(value = "/create_supplier", method = RequestMethod.GET)
	private String createSupplier(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {
			SupplierBO supplierBO = new SupplierBO();
			autogenerateId = service.supplierIdAutoGenerated();
			supplierBO.setSupplierId(autogenerateId);
			model.addAttribute("supplier", supplierBO);
		} catch (Exception e) {

		}
		return "create_supplier";
	}

	@RequestMapping(value = "/create_supplier", method = RequestMethod.POST)
	public String createSupplier(
			@Valid @ModelAttribute("supplier") SupplierBO supplierBO,
			BindingResult result, HttpServletRequest request, Model model)
			throws Exception {
		LOGGER.entry();
		try {

			if (result.hasErrors()) {

				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - "
							+ error.getDefaultMessage());
				}

				return "create_supplier";
			}
			supplierBO.setSupplierId(autogenerateId);
			supplierBO.setIsDeleted(true);
			boolean status = service.createSupplier(supplierBO);

			if (status) {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Success"));
				return "redirect:/view_supplier.html";
			} else {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Failure"));
				return "create_supplier";
			}

		} catch (Exception e) {

		}
		LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/view_supplier", method = RequestMethod.GET)
	public String retrieveSupplier(Model model, HttpServletRequest request) {
		LOGGER.entry();
		int page = 1;
		String paging = request.getParameter("page");
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);

				ResponseObject<SupplierBO> reponseObject = supplierPagination(
						page, supplierBOList);

				model.addAttribute("ViewSupplier", reponseObject);

			} else {
				SupplierBO supplierBO = service.retrieveSuppliers();
				supplierBOList = supplierBO.getAllSupplierBOList();
				if (supplierBOList.size() != 0 && supplierBOList != null) {
					ResponseObject<SupplierBO> responseObject = supplierPagination(
							1, supplierBOList);
					model.addAttribute("ViewSupplier", responseObject);
				} else {
					model.addAttribute("message", MegatechResourceBundle
							.getValue("Validate.Retrieve"));
				}
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "view_supplier";
	}

	@RequestMapping(value = "/edit_supplier", method = RequestMethod.GET)
	public String editSupplier(Model model, HttpServletRequest request) {
		LOGGER.entry();
		String supplierId = request.getParameter("supplierId");
		try {
			for (SupplierBO supplierBO : supplierBOList) {
				if (supplierId.equalsIgnoreCase(supplierBO.getSupplierId())) {
					model.addAttribute("EditSupplier", supplierBO);
					supplier = supplierBO;
				}
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "edit_supplier";
	}

	@RequestMapping(value = "/edit_supplier", method = RequestMethod.POST)
	public String editTest(
			@Valid @ModelAttribute("EditSupplier") SupplierBO supplierBO,
			BindingResult result, Model model) throws Exception {
		LOGGER.entry();
		try {

			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - "
							+ error.getDefaultMessage());
				}
				return "edit_supplier";
			}

			supplierBO.setSupplierId(supplier.getSupplierId());
			supplierBO.setCreated(supplier.getCreated());
			supplierBO.setIsDeleted(supplier.getIsDeleted());
			supplierBO.setCreatedBy(supplier.getCreatedBy());
			supplierBO.setModifiedBy(supplier.getModifiedBy());

			boolean status = service.editSupplier(supplierBO);

			if (status) {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Update.Success"));
				return "redirect:/view_supplier.html";

			} else {
				model.addAttribute(
						"message",
						"Supplier"
								+ supplierBO.getSupplierName()
								+ MegatechResourceBundle
										.getValue("Validate.Update.Failure"));
				return "edit_supplier";
			}

		} catch (Exception e) {

		}
		return "view_supplier";
	}

	@RequestMapping(value = "/delete_supplier", method = RequestMethod.GET)
	public String deleteSupplier(Model model, HttpServletRequest request) {
		String deletedId = request.getParameter("supplierId");
		SupplierBO supplierBO = new SupplierBO();
		HttpSession session = request.getSession();
		long loginId = (Long) session.getAttribute("id");
		try {
			supplierBO.setSupplierId(deletedId);
			supplierBO.setModified(new Date());
			supplierBO.setIsDeleted(false);
			supplierBO.setModifiedBy(loginId);
			supplierBO = service.deleteSupplier(supplierBO);
			if (null != supplierBO.getResponse()) {
				model.addAttribute("message", supplierBO.getResponse());

			} else {
				model.addAttribute("message",
						MegatechResourceBundle.getValue("Validate.Delete"));

			}
		} catch (Exception e) {

		}
		return "redirect:/view_supplier.html";

	}

	@RequestMapping(value = "/create_customer", method = RequestMethod.GET)
	public String createCustomer(Model model, HttpServletRequest request) {
		LOGGER.entry();
		try {
			CustomerBO customerBO = new CustomerBO();
			autogenerateId = service.customerIdAutoGenerated();
			model.addAttribute("customer", customerBO);
		} catch (Exception e) {

		}

		LOGGER.exit();
		return "create_customer";
	}

	@RequestMapping(value = "/create_customer", method = RequestMethod.POST)
	public String createCustomer(
			@Valid @ModelAttribute("customer") CustomerBO customerBO,
			BindingResult result, HttpServletRequest request, Model model)
			throws Exception {
		LOGGER.entry();
		try {
			if (result.hasErrors()) {
				return "create_customer";
			}
			boolean isStatus=service.checkCustomerName(customerBO.getCustomerName());
			if(!isStatus){
				result.rejectValue("customerName", "Validate.User");
				return "create_customer";
			}
			customerBO.setCustomerId(autogenerateId);
			customerBO.setIsDeleted(true);
			boolean status = service.createCustomer(customerBO);
			if (status) {
				model.addAttribute("message", "Customer"
						+ MegatechResourceBundle.getValue("Validate.Success"));
				return "redirect:/view_customer.html";
			} else {
				model.addAttribute("message", "Customer"
						+ MegatechResourceBundle.getValue("Validate.Failure"));
				return "create_customer";
			}
		} catch (Exception e) {

		}

		LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/view_customer", method = RequestMethod.GET)
	public String retrieveCustomers(Model model, HttpServletRequest request) {
		LOGGER.entry();
		int page = 1;
		String paging = request.getParameter("page");
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);

				ResponseObject<CustomerBO> reponseObject = customerPagination(
						page, customerLists);

				model.addAttribute("ViewCustomer", reponseObject);

			} else {

				CustomerBO customerBO = service.retrieveCustomers();
				customerLists = customerBO.getAllCustomerBOList();
				if (customerLists.size() != 0 && customerLists != null) {
					ResponseObject<CustomerBO> responseObject = customerPagination(
							1, customerLists);
					model.addAttribute("ViewCustomer", responseObject);
				} else {
					model.addAttribute("message", MegatechResourceBundle
							.getValue("Validate.Retrieve"));
				}
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "view_customer";
	}

	@RequestMapping(value = "/edit_customer", method = RequestMethod.GET)
	public String editCustomer(Model model, HttpServletRequest request) {
		LOGGER.entry();
		String customerId = request.getParameter("customerId");
		try {
			for (CustomerBO customerBO : customerLists) {
				if (customerId.equalsIgnoreCase(customerBO.getCustomerId())) {
					model.addAttribute("EditCustomer", customerBO);
					customer = customerBO;
				}
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return "edit_customer";
	}

	@RequestMapping(value = "/edit_customer", method = RequestMethod.POST)
	public String editCustomer(
			@Valid @ModelAttribute("EditCustomer") CustomerBO customerBO,
			BindingResult result, Model model) throws Exception {
		LOGGER.entry();
		try {

			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - "
							+ error.getDefaultMessage());
				}
				return "edit_customer";
			}

			customerBO.setCustomerId(customer.getCustomerId());
			customerBO.setCreated(customer.getCreated());
			customerBO.setIsDeleted(customer.getIsDeleted());
			customerBO.setCreatedBy(customer.getCreatedBy());
			customerBO.setModifiedBy(customer.getModifiedBy());

			boolean status = service.editCustomer(customerBO);

			if (status) {
				model.addAttribute(
						"message",
						"Customer"
								+ customerBO.getCustomerName()
								+ MegatechResourceBundle
										.getValue("Validate.Update.Success"));
				return "redirect:/view_customer.html";

			} else {
				model.addAttribute(
						"message",
						"Customer"
								+ customerBO.getCustomerName()
								+ MegatechResourceBundle
										.getValue("Validate.Update.Failure"));
				return "edit_customer";
			}

		} catch (Exception e) {

		}
		return "view_customer";
	}

	@RequestMapping(value = "/delete_customer", method = RequestMethod.GET)
	public String deleteCustomer(Model model, HttpServletRequest request) {
		String deletedId = request.getParameter("customerId");
		CustomerBO customerBO = new CustomerBO();
		HttpSession session = request.getSession();
		long loginUserId = (Long) session.getAttribute("id");
		try {
			System.out.println("inside the COntroller");
			customerBO.setCustomerId(deletedId);
			customerBO.setModified(new Date());
			customerBO.setIsDeleted(false);
			customerBO.setModifiedBy(loginUserId);
			customerBO = service.deleteCustomer(customerBO);
			if (null != customerBO.getResponse()) {
				model.addAttribute("message", customerBO.getResponse());

			} else {
				model.addAttribute("message", "Customer"
						+ MegatechResourceBundle.getValue("Validate.Delete"));

			}
		} catch (Exception e) {

		}
		return "redirect:/view_customer.html";

	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private ResponseObject supplierPagination(int page,
			List<SupplierBO> dataLsit) {
		int recordsPerPage = 5;
		int noOfRecords = 0;
		int noOfPages = 0;
		int startingRecord = 1;
		int d;
		if (page > 1) {
			startingRecord = (startingRecord * recordsPerPage * page)
					- recordsPerPage;
		} else {
			startingRecord = 0;
		}
		ResponseObject<SupplierBO> ro = new ResponseObject<SupplierBO>();
		List<SupplierBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		List<SupplierBO> pageList = new ArrayList<SupplierBO>();
		if (null != list) {
			for (int i = startingRecord; i < list.size(); i++) {
				if (count < 5)
					pageList.add(list.get(i));
				count++;
			}
		}
		int a = page % 10;
		int b = page / 10;
		if (a == 1) {
			int c = a + b * 10;
			ro.setStart(c);
			if (noOfPages > c + 9) {
				ro.setRecords(c + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		if (a == 0) {
			int f = b - 1;
			d = f * 10 + 1;
			ro.setStart(d);

			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		} else {
			d = b * 10 + 1;
			ro.setStart(d);
			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		ro.setList(pageList);
		ro.setTotalRecords(noOfRecords);
		ro.setPage(page);
		return ro;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private ResponseObject customerPagination(int page,
			List<CustomerBO> dataLsit) {
		int recordsPerPage = 5;
		int noOfRecords = 0;
		int noOfPages = 0;
		int startingRecord = 1;
		int d;
		if (page > 1) {
			startingRecord = (startingRecord * recordsPerPage * page)
					- recordsPerPage;
		} else {
			startingRecord = 0;
		}
		ResponseObject<CustomerBO> ro = new ResponseObject<CustomerBO>();
		List<CustomerBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		List<CustomerBO> pageList = new ArrayList<CustomerBO>();
		if (null != list) {
			for (int i = startingRecord; i < list.size(); i++) {
				if (count < 5)
					pageList.add(list.get(i));
				count++;
			}
		}
		int a = page % 10;
		int b = page / 10;
		if (a == 1) {
			int c = a + b * 10;
			ro.setStart(c);
			if (noOfPages > c + 9) {
				ro.setRecords(c + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		if (a == 0) {
			int f = b - 1;
			d = f * 10 + 1;
			ro.setStart(d);

			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		} else {
			d = b * 10 + 1;
			ro.setStart(d);
			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		ro.setList(pageList);
		ro.setTotalRecords(noOfRecords);
		ro.setPage(page);
		return ro;
	}

	public String invoiceValid(MultipartFile uploadRecord)
			throws FileNotFoundException {
		String str = null;
		if (uploadRecord.getContentType().equals("image/jpeg")
				|| uploadRecord.getContentType().equals("application/pdf")) {

		} else {
			str = MegatechResourceBundle.getValue("Validate.Upload");

		}
		return str;
	}
@RequestMapping(value="/getcustomer",method=RequestMethod.GET)	
public @ResponseBody String checkCustomerName(@RequestParam String customerName){
	String status = null;
	boolean isStatus=service.checkCustomerName(customerName);
	if(isStatus){
		
	}else{
	status="User name all ready exist";
	}
	return status;
	
}
}