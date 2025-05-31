package com.example.ServiceTaskManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String  status;
    private String priority;

    @ManyToOne
    private User assignedUser;

    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
    private String createdBy;

    private String Description;

}



