package com.example.ServiceClassRoutine.repository;

import com.example.ServiceClassRoutine.dto.response.CourseResponse;
import com.example.ServiceClassRoutine.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByNameContainingIgnoreCase(
            String name,Pageable pageable);



    @Query("SELECT new com.example.ServiceClassRoutine.dto.response.CourseResponse(c.name ,c.department) FROM Course c")

    List<CourseResponse> findAllCourses();



}
