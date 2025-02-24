package com.example.AuthService.model.dto;

import com.example.AuthService.model.RouteConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteChildConfigDTO {

    private Long id;
    private String link;
    private String section;
    private String icon;
    private Set<RoleDTO> permissionRole;  // Update to hold a list of roles


    public RouteChildConfigDTO(RouteConfigChild config) {
        this.id = config.getId();
        this.icon = config.getIcon();
        this.link = config.getLink();
        this.section = config.getSection();
        this.permissionRole = config.getPermissionRole().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}
