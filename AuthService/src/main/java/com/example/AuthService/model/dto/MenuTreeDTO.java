package com.example.AuthService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeDTO {
    private Long id;
    private String name;
    private String title;
    private String type;
    private String iconClass;
    private String path;
    private String collapseId;
    private String badgeCount;
    private List<MenuTreeDTO> children = new ArrayList<>();
}
