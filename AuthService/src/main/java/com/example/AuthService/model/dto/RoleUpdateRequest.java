package com.example.AuthService.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleUpdateRequest {
    private String name;
    private List<Long> menuIds;
}