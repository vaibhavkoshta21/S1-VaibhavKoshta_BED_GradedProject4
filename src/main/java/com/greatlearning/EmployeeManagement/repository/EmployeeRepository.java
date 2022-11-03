package com.greatlearning.EmployeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.EmployeeManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByFirstnameContainsAndLastnameContainsAllIgnoreCase(String firstname,String lastname);

}
