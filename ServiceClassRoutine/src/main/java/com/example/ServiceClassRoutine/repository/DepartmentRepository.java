package com.example.ServiceClassRoutine.repository;


import com.example.ServiceClassRoutine.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
