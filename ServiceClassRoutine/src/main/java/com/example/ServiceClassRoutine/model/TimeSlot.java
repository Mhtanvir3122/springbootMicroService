package com.example.ServiceClassRoutine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class TimeSlot {
    @Id
    @GeneratedValue
    private Long id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
}
