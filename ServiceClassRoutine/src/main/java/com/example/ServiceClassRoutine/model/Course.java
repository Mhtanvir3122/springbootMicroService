package com.example.ServiceClassRoutine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int creditHour;
    private String fileName; // any file


    @ManyToOne
    private Department department;

    @CreationTimestamp // ✅ Hibernate automatically sets creation time
    @Column(updatable = false)
    private LocalDateTime createdOn;
}
