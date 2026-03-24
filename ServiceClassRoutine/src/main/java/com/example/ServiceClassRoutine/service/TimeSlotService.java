package com.example.ServiceClassRoutine.service;


import com.example.ServiceClassRoutine.model.TimeSlot;
import com.example.ServiceClassRoutine.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public TimeSlot createTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

}

