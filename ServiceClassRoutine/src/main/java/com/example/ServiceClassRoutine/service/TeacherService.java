package com.example.ServiceClassRoutine.service;


import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.model.Teacher;
import com.example.ServiceClassRoutine.repository.DepartmentRepository;
import com.example.ServiceClassRoutine.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public Teacher createTeacher(Teacher teacher) {
        // Validate and fetch department
        if (teacher.getDepartment() != null) {
            Optional<Department> dept = departmentRepository.findById(teacher.getDepartment().getId());
            dept.ifPresent(teacher::setDepartment);
        }
        return teacherRepository.save(teacher);
    }
}