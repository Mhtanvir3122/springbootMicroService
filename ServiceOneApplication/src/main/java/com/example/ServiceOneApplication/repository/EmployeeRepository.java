package com.example.ServiceOneApplication.repository;

import com.example.ServiceOneApplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

