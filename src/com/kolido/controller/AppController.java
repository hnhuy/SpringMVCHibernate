package com.kolido.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kolido.model.Employee;
import com.kolido.service.EmployeeService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployee(ModelMap model) {
		model.addAttribute("employees", employeeService.findAll());
		return "allemployees";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("edit", false);
		return "registration";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {
		if (result.hasErrors())
			return "registration";

//		if (!employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
//			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn",
//					new String[] { employee.getSsn() }, Locale.getDefault()));
//		}
		
		employeeService.saveEmployee(employee);
		model.addAttribute("success", "Employee " + employee.getName() + " registration successfully!");
		
		return "success";
	}
	
	@RequestMapping(value = "/edit-{ssn}-employee", method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model){
		model.addAttribute("employee", employeeService.findBySsn(ssn));
		model.addAttribute("edit", true);
		return "registration";
	}
	
	@RequestMapping(value = "/edit-{ssn}-employee", method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model){
		if (result.hasErrors())
			return "registration";
		
//		if (!employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
//			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn",
//					new String[] { employee.getSsn() }, Locale.getDefault()));
//		}
		
		employeeService.updateEmployee(employee);
		
		model.addAttribute("success", "Employee " + employee.getName() + " updated successfully!");
		
		return "success";
	}
	
	@RequestMapping(value = "delete-{ssn}-employee", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn){
		employeeService.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}

}


















