package com.example.dimaBeauty.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dimaBeauty.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
