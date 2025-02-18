package com.example.ServiceOneApplication.controller;
import com.example.ServiceOneApplication.model.Employee;
import com.example.ServiceOneApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getList")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/save")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Employee deleteEmployee(@PathVariable Long id) {
        return   employeeService.deleteEmployee(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");

        List<Employee> employees;
        if (keyword == null || keyword.trim().isEmpty()) {
            employees = employeeService.getAllEmployees(); // Return all employees
        } else {
            employees = employeeService.searchEmployees(keyword);
        }
        return ResponseEntity.ok(employees);
    }



}

