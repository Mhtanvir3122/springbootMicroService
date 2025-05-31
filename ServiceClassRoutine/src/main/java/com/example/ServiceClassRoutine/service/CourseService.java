package com.example.ServiceClassRoutine.service;


import com.example.ServiceClassRoutine.model.Course;
import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.repository.CourseRepository;
import com.example.ServiceClassRoutine.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Course createCourse(Course course) {
        // Validate department existence
        if (course.getDepartment() != null) {
            Optional<Department> department = departmentRepository.findById(course.getDepartment().getId());
            department.ifPresent(course::setDepartment);
        }

        return courseRepository.save(course);
    }
}