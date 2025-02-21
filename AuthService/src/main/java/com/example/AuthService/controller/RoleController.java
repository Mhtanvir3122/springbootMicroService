package com.example.AuthService.controller;

import com.example.AuthService.model.Role;
import com.example.AuthService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Role>> searchEmployees(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");

        List<Role> employees;
        if (keyword == null || keyword.trim().isEmpty()) {
            employees = roleService.getAllRoles(); // Return all employees
        } else {
            employees = roleService.searchRole(keyword);
        }
        return ResponseEntity.ok(employees);
    }
}

