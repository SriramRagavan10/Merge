package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Employee;
import com.crud.repository.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "emp")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping(value = "/insert")
	public void post(@RequestBody Employee emp) {
		employeeRepository.save(emp);
	}

	@PostMapping(value = "/insertAll")
	public String postAll(@RequestBody List<Employee> employee) {
		employeeRepository.saveAll(employee);
		return "All Details Successfully Saved";
	}

	@GetMapping(value = "/getAll")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

}
