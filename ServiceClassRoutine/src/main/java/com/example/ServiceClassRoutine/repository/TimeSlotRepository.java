package com.example.ServiceClassRoutine.repository;


import com.example.ServiceClassRoutine.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}

