package com.example.ServiceTwoApplication.dto;

import com.example.ServiceTwoApplication.Model.Task;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String createdBy;
    private String Description;


    // Constructor, getters, and setters
}
