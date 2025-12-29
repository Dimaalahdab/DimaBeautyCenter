package com.example.dimaBeauty.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimaBeauty.Entity.Employee;
import com.example.dimaBeauty.Repository.EmployeeRepository;



@RestController // REQUIRED for Spring to handle requests
@CrossOrigin(origins = "*") 
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @PostMapping
    public Employee save(@RequestBody Employee e) {
        // Return the object so the frontend gets the new ID
        return employeeRepo.save(e);
    }
    
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee e) {
        return employeeRepo.findById(id).map(existing -> {
            existing.setName(e.getName());
            existing.setSpecialty(e.getSpecialty());
            return employeeRepo.save(existing);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeRepo.deleteById(id);
        return "Employee deleted!";
    }
}