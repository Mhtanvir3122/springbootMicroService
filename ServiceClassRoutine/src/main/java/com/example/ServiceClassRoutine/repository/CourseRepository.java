package com.example.ServiceClassRoutine.repository;

import com.example.ServiceClassRoutine.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
