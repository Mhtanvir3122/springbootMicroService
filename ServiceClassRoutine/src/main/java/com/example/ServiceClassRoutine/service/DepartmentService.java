package com.example.ServiceClassRoutine.service;



import com.example.ServiceClassRoutine.model.Course;
import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Page<Department> searchDepartment(String searchKey, String nameEn, Pageable pageable) {
        // যদি কোনোটাই না দেওয়া হয়, সব ডেটা রিটার্ন করবে
        if ((searchKey == null || searchKey.isEmpty()) && (nameEn == null || nameEn.isEmpty())) {
            return departmentRepository.findAll(pageable);
        }
        return departmentRepository.searchDepartments(searchKey, nameEn, pageable);
    }
}
