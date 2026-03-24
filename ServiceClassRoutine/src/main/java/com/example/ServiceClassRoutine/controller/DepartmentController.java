package com.example.ServiceClassRoutine.controller;
import com.example.ServiceClassRoutine.dto.request.departmentRequest;
import com.example.ServiceClassRoutine.dto.response.ApiResponse;
import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.service.DepartmentService;
import com.example.ServiceClassRoutine.utils.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department saved = departmentService.createDepartment(department);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }


    @PostMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchDepartment(@RequestBody departmentRequest request) {
        ApiResponse.Meta meta = PaginationUtils.convertMeta(request.getMeta());
        Pageable pageable = PaginationUtils.buildPageable(meta);
        Page<Department> coursePage = departmentService.searchDepartment(request.getBody().getSearchKey(), request.getBody().getNameEn() ,pageable);
        return PaginationUtils.handleSearch(coursePage, meta, "Department fetched successfully");
    }
}
