package com.greatlearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagement.entity.Employee;
import com.greatlearning.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		return employeeRepository.findById(theId).get();
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
		
	}

	@Override
	public List<Employee> searchBy(String firstname, String lastname) {
		List<Employee> employees = employeeRepository.findByFirstnameContainsAndLastnameContainsAllIgnoreCase(firstname, lastname);
		return employees;
	}

	@Override
	public List<Employee> orderByAsc(Sort by) {
		List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstname"));
		return employees;
	}
	
	@Override
	public List<Employee> orderByDesc(Sort by) {
		List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstname"));
		return employees;
	}
	
}
