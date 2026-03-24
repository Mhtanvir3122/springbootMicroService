package com.example.ServiceClassRoutine.controller;

import com.example.ServiceClassRoutine.dto.request.CourseRequest;
import com.example.ServiceClassRoutine.dto.request.departmentRequest;
import com.example.ServiceClassRoutine.dto.response.ApiResponse;
import com.example.ServiceClassRoutine.dto.response.CourseResponse;
import com.example.ServiceClassRoutine.model.Course;
import com.example.ServiceClassRoutine.model.Department;
import com.example.ServiceClassRoutine.service.CourseService;
import com.example.ServiceClassRoutine.utils.PaginationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

//    @PostMapping
//    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//        return ResponseEntity.ok(courseService.createCourse(course));
//    }
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestParam("course") String courseJson,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws Exception {

        Course course = courseService.createCourse(courseJson, file);

        return ResponseEntity.ok(course);
    }


    @GetMapping("/course/file/{fileName}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(
            @PathVariable String fileName) throws Exception {

        Path path = Paths.get("uploads").resolve(fileName);
        org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(path.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }




    @PostMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchCourses(@RequestBody CourseRequest request) {
        ApiResponse.Meta meta = PaginationUtils.convertMeta(request.getMeta());
        Pageable pageable = PaginationUtils.buildPageable(meta);
        Page<Course> coursePage = courseService.searchCourses(request.getBody().getSearchKey(), pageable);

        return PaginationUtils.handleSearch(coursePage, meta, "Courses fetched successfully");
    }



}
