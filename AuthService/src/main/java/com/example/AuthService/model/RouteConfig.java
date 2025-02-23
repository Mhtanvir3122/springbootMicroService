package com.example.AuthService.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RouteConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;
    private String section;
    private String icon;

    @ElementCollection
    private List<String> permissionRole;

    // Constructors, getters, and setters

    public RouteConfig() {
    }

    public RouteConfig(String link, String section, String icon, List<String> permissionRole) {
        this.link = link;
        this.section = section;
        this.icon = icon;
        this.permissionRole = permissionRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getPermissionRole() {
        return permissionRole;
    }

    public void setPermissionRole(List<String> permissionRole) {
        this.permissionRole = permissionRole;
    }
}
