package com.example.ServiceTwoApplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TaskFilterRequest {
    private String status;
    private String priority;
    private Long assignedUserId;
    private Long createdBy;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}