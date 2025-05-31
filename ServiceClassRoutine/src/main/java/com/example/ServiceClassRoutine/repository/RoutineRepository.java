package com.example.ServiceClassRoutine.repository;

import com.example.ServiceClassRoutine.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByTeacherId(Long teacherId);
    List<Routine> findBySemester(String semester);
}


