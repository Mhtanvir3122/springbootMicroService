package com.example.ServiceTwoApplication.FignClient;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Set<RoleDTO> roles;
    // Update to hold a list of roles
    public enum RoleDTO {
        ADMIN, AGENT
    }
}
