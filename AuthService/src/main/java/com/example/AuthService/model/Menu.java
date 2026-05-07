package com.example.AuthService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;
    private String type;
    private String iconClass;
    private String path;
    private String collapseId;
    private String badgeCount;





    // Parent side
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "menu-parent")
    private Menu parent;

    // Children side
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "menu-parent")
    private List<Menu> children;


    // Role relation
    @ManyToMany(mappedBy = "menus")
    @JsonBackReference(value = "role-menu")
    private List<Role> roles;
}
