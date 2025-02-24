package com.example.AuthService.model.dto;

import com.example.AuthService.model.Role;
import com.example.AuthService.model.RouteConfig;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "route_config_child")
public class RouteConfigChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String section;
    private String icon;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> permissionRole= new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference // Prevent infinite recursion
    private RouteConfig parent;

}

