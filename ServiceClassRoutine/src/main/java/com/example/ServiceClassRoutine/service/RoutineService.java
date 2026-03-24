package com.example.ServiceClassRoutine.service;

import com.example.ServiceClassRoutine.model.Routine;
import com.example.ServiceClassRoutine.repository.RoutineRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoutineService {
    private final RoutineRepository routineRepository;

    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Routine createRoutine(Routine routine) {
        // Add validation logic to check for time-slot clashes, etc.
        return routineRepository.save(routine);
    }

    public List<Routine> getRoutinesBySemester(String semester) {
        return routineRepository.findBySemester(semester);
    }

    public List<Routine> getRoutinesByTeacher(Long teacherId) {
        return routineRepository.findByTeacherId(teacherId);
    }

    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

}
