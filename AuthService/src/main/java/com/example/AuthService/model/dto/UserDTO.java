package com.example.AuthService.model.dto;

import com.example.AuthService.model.User;
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
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<RoleDTO> roles;  // Update to hold a list of roles


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles =
                        user.getRoles().stream()
                                .map(role -> new RoleDTO(role.getId(), role.getName()))
                                .collect(Collectors.toSet());
    }
}
