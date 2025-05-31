package com.example.ServiceTaskManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String name;
    private String status;
    private String priority;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;

    private String createdBy;
    private String Description;


    // Constructor, getters, and setters
}
