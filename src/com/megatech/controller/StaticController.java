package com.megatech.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class StaticController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("welcome", "welcome");
		return "index";
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("welcome", "welcome");
		return "index";
	}
	@RequestMapping(value = "/doctor_search", method = RequestMethod.GET)
	public String doctorSearch(Model model) {
		model.addAttribute("doctor_search", "doctor_search");
		return "doctor_search";
	}
	@RequestMapping(value = "/find_donor", method = RequestMethod.GET)
	public ModelAndView findDonor(Model model) {
		model.addAttribute("find_donor", "find_donor");
		return new ModelAndView("find_donor");
	} 
	@RequestMapping(value = "/admin_home", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		return new ModelAndView("admin_home");
	}
	
	
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ModelAndView service( Model model) {
		model.addAttribute("services", "services");
		return new ModelAndView("services");
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ModelAndView contacts(Model model) {
		model.addAttribute("contacts", "contacts");
		return new ModelAndView("contacts");
	}
	
	@RequestMapping(value = "/staff-page", method = RequestMethod.GET)
	public ModelAndView searchStaff() {
		return new ModelAndView("staff-page");
	}
}
