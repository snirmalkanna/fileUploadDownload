package com.nks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nks.entity.Employee;
import com.nks.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Employee save(Employee employee) {
		employee.setRecord_activity(1);
		employeeRepository.save(employee);
		return employee;
	}
	
	public Employee update(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	public Optional<Employee> findById(Long id) {
		Optional<Employee> emp =employeeRepository.findById(id);
		return emp;
	}
	
	public List<Employee> retrieve() {
		List<Employee> list = new ArrayList<Employee>();
		list = employeeRepository.findAll();
		System.out.println(list.size());
		return list;
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
}
