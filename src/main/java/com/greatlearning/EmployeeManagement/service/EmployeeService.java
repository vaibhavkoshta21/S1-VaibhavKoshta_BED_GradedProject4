package com.greatlearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.greatlearning.EmployeeManagement.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public List<Employee> searchBy(String firstname , String lastname);

	public List<Employee> orderByAsc(Sort by);
	
	public List<Employee> orderByDesc(Sort by);


}
