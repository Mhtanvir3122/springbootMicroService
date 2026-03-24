package com.example.ServiceClassRoutine.controller;

import com.example.ServiceClassRoutine.ServiceClassRoutine;
import com.example.ServiceClassRoutine.model.Routine;
import com.example.ServiceClassRoutine.service.RoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
public class RoutineController {

    private final RoutineService serviceClassRoutine;

    public RoutineController(RoutineService serviceClassRoutine) {
        this.serviceClassRoutine = serviceClassRoutine;
    }

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
        return ResponseEntity.ok(serviceClassRoutine.createRoutine(routine));
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<Routine>> getBySemester(@PathVariable String semester) {
        return ResponseEntity.ok(serviceClassRoutine.getRoutinesBySemester(semester));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Routine>> getByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(serviceClassRoutine.getRoutinesByTeacher(teacherId));
    }

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines() {
        return ResponseEntity.ok(serviceClassRoutine.getAllRoutines());
    }

}