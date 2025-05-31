package com.example.ServiceClassRoutine.repository;

import com.example.ServiceClassRoutine.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
