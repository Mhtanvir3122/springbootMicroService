package com.example.ServiceClassRoutine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Routine {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private TimeSlot timeSlot;

    private String semester;
    private String classroom;
}
