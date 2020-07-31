package com.nks.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nks.entity.Employee;
import com.nks.service.EmployeeService;

@RestController
public class EmployeeTemplate {

	private final Logger logger  = Logger.getLogger(EmployeeTemplate.class);
	
	private final EmployeeService employeeService;
	
	public EmployeeTemplate(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
		
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		logger.info("Inside post method");
		try {
			employeeService.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		logger.info("Inside post method");
		try {
			employeeService.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@GetMapping("/employee")
	public List<Employee> getEmployee() {
		logger.info("Inside get method");
		List<Employee> emp = new ArrayList<Employee>();
		try {
			emp = employeeService.retrieve();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	@GetMapping("/employee/{id}")
	public Employee saveEmployee(@PathVariable Long id) {
		logger.info("Inside put method");
		Optional<Employee> employee = null;
		try {
			employee = employeeService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee.get();
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable  Long id) {
		logger.info("Inside delete method");
		employeeService.deleteEmployee(id);
		return id + " was deleted";
	}
}
