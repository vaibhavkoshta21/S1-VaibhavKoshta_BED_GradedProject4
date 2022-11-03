package com.greatlearning.EmployeeManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.EmployeeManagement.entity.Employee;
import com.greatlearning.EmployeeManagement.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
	
	@Autowired
	private EmployeeService employeeService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listEmployees(Model theModel) {
		

		// get Employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("Employees", theEmployees);
		return "list-Employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("Employee", theEmployee);

		return "Employee-form";
	}

	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {

		// get the Employee from the service
		Employee theEmployee = employeeService.findById(theId);


		// set Employee as a model attribute to pre-populate the form
		theModel.addAttribute("Employee", theEmployee);

		// send over to our form
		return "Employee-form";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("email") String email) {

		System.out.println(id);
		Employee theEmployee;
		if(id!=0)
		{
			theEmployee=employeeService.findById(id);
			theEmployee.setFirstname(firstname);
			theEmployee.setLastname(lastname);
			theEmployee.setEmail(email);
		}
		else
			theEmployee=new Employee(firstname, lastname, email);
		// save the Employee
		employeeService.save(theEmployee);


		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the Employee
		employeeService.deleteById(theId);

		// redirect to /employees/list
		return "redirect:/employees/list";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			Model theModel) {

		// check names, if both are empty then just give list of all Employees

		if (firstname.trim().isEmpty() && lastname.trim().isEmpty()) {
			return "redirect:/employees/list";
		}
		else {
			// else, search by first name and last name
			List<Employee> theEmployees =
					employeeService.searchBy(firstname, lastname);

			// add to the spring model
			theModel.addAttribute("Employees", theEmployees);

			// send to list-Employees
			return "list-Employees";
		}

	}
	
	@RequestMapping("/ascending")
	public String orderByAsc(Model theModel) {
		

		// get Employees from db
		List<Employee> theEmployees = employeeService.orderByAsc(Sort.by(Sort.Direction.ASC, "firstname"));

		// add to the spring model
		theModel.addAttribute("Employees", theEmployees);
		return "list-Employees";
	}
	
	@RequestMapping("/descending")
	public String orderByDesc(Model theModel) {
		

		// get Employees from db
		List<Employee> theEmployees = employeeService.orderByDesc(Sort.by(Sort.Direction.DESC, "firstname"));

		// add to the spring model
		theModel.addAttribute("Employees", theEmployees);
		return "list-Employees";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
	

}
