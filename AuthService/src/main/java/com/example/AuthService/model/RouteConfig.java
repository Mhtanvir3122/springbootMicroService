package com.example.AuthService.model;

import com.example.AuthService.model.dto.RouteConfigChild;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routeconfigs")

public class RouteConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String section;
    private String icon;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "routeconfig_roles",
            joinColumns = @JoinColumn(name = "routeconfig_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> permissionRole;



    @JsonManagedReference // Prevent infinite recursion
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RouteConfigChild> children = new HashSet<>();

}
