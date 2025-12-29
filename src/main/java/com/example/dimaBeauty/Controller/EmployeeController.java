package com.example.dimaBeauty.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.dimaBeauty.Entity.Employee;
import com.example.dimaBeauty.Repository.EmployeeRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("employees")
public class EmployeeController {
	 @Autowired
	    EmployeeRepository employeeRepo;

	    @GetMapping
	    public List<Employee> getAll() {
	        return employeeRepo.findAll();
	    }

	    @PostMapping
	    public String save(@RequestBody Employee e) {
	        employeeRepo.save(e);
	        return "Worker saved!";
	    }
	    
	    @PutMapping("{id}")
	    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee e) {
	        Employee existing = employeeRepo.findById(id).get();

	        existing.setName(e.getName());
	        existing.setSpecialty(e.getSpecialty());

	        return employeeRepo.save(existing);
	    }
	    @DeleteMapping("{id}")
	    public String delete(@PathVariable Long id) {
	        employeeRepo.deleteById(id);
	        return "Employee deleted!";
	    }
}


