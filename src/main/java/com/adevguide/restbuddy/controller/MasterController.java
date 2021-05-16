package com.adevguide.restbuddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adevguide.restbuddy.dao.EmployeeJpaRepository;
import com.adevguide.restbuddy.entity.EmployeeEntity;

/**
 * @author PraBhu
 *
 */
@RestController
public class MasterController {

	@Autowired
	private EmployeeJpaRepository employeeRepository;

	@GetMapping("/")
	public void selfHIt() {

	}

	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployee() {
		List<EmployeeEntity> list = employeeRepository.findAll();
		return list;
	}

	@GetMapping("employee/{id}")
	public Optional<EmployeeEntity> getEmployee(@PathVariable Long id) {
		return employeeRepository.findById(id);
	}

}
