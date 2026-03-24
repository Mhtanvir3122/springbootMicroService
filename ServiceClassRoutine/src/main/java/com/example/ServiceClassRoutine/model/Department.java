package com.example.ServiceClassRoutine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nameEn;

    @CreationTimestamp // ✅ Hibernate automatically sets creation time
    @Column(updatable = false)
    private LocalDateTime createdOn;
}
