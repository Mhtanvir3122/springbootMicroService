package com.example.ServiceClassRoutine.service;


import com.example.ServiceClassRoutine.dto.response.CourseResponse;
import com.example.ServiceClassRoutine.model.Course;
import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.repository.CourseRepository;
import com.example.ServiceClassRoutine.repository.DepartmentRepository;
import com.example.ServiceClassRoutine.utils.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    private FileService fileService;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Course createCourse(String courseJson, MultipartFile file) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Course course = mapper.readValue(courseJson, Course.class);

        // Validate department
        if (course.getDepartment() != null && course.getDepartment().getId() != null) {
            Department dep = departmentRepository
                    .findById(course.getDepartment().getId())
                    .orElse(null);
            course.setDepartment(dep);
        }
        if (file != null && !file.isEmpty()) {
            String fileName = fileService.saveFile(file);
            course.setFileName(fileName);
        }


        // File upload (Optional)


        return courseRepository.save(course);
    }





//    public Course createCourse(Course course) {
//        // Validate department existence
//        if (course.getDepartment() != null) {
//            Optional<Department> department = departmentRepository.findById(course.getDepartment().getId());
//            department.ifPresent(course::setDepartment);
//        }
//
//        return courseRepository.save(course);
//    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllCourses();
    }


    public Page<Course> searchCourses(String searchKey, Pageable pageable) {
        if (searchKey != null && !searchKey.isEmpty()) {
            return courseRepository.findByNameContainingIgnoreCase(searchKey, pageable);
        }
        return courseRepository.findAll(pageable);
    }

}