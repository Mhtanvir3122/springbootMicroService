package com.example.ServiceClassRoutine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int creditHour;

    @ManyToOne
    private Department department;
}
