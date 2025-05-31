package com.example.ServiceTaskManagementSystem.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskSearchRequest {
    private String name;
    private String status;
    private String priority;
    private Long assignedUserId;
    private LocalDateTime startDate; // Filter for tasks created after this date
    private LocalDateTime endDate;   // Filter for tasks created before this date
    private LocalDateTime dueStartDate; // Filter for tasks due after this date
    private LocalDateTime dueEndDate;   // Filter for tasks due before this date
    private String createdBy;
}
