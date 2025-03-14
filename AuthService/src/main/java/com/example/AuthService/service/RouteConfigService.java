package com.example.AuthService.service;
import com.example.AuthService.model.Role;
import com.example.AuthService.model.RouteConfig;
import com.example.AuthService.model.dto.*;
import com.example.AuthService.repository.RoleRepository;
import com.example.AuthService.repository.RouteConfigChildRepository;
import com.example.AuthService.repository.RouteConfigRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteConfigService {

    private final RouteConfigRepository routeConfigRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RouteConfigService(RouteConfigRepository routeConfigRepository, RoleRepository roleRepository) {
        this.routeConfigRepository = routeConfigRepository;
        this.roleRepository = roleRepository;
    }

    public RouteConfig saveRouteConfig(RouteConfig routeConfig) {
        return routeConfigRepository.save(routeConfig);
    }


    public RouteConfig updateRouteConfig(Long id, RouteConfig routeConfig) {
        RouteConfig config = routeConfigRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        config.setIcon(routeConfig.getIcon());
        config.setLink(routeConfig.getLink());
        config.setSection(routeConfig.getSection());
        return routeConfigRepository.save(config);
    }

    public List<RouteConfig> getAllRouteConfigs() {
        return routeConfigRepository.findAll();
    }


//    public List<RouteConfigDTO> getAllRoutes() {
//        List<RouteConfig> routeConfigs = routeConfigRepository.findAll();
//        return routeConfigs.stream()
//                .map(route -> new RouteConfigDTO(
//                        route.getId(),
//                        route.getLink(),
//                        route.getIcon(),
//                        route.getSection(),
//                        route.getPermissionRole().stream()
//                                .map(role -> new RoleDTO(role.getId(), role.getName()))
//                                .collect(Collectors.toSet())))
//                .collect(Collectors.toList());
//    }

    public List<RouteConfigDTO> getAllRoutes() {
        return routeConfigRepository.findAll().stream()
                .map(route -> new RouteConfigDTO(
                        route.getId(),
                        route.getLink(),
                        route.getIcon(),
                        route.getSection(),
                        route.getPermissionRole().stream()
                                .map(role -> new RoleDTO(role.getId(), role.getName()))
                                .collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }



    @Transactional
    public RouteConfig assignRoles(Long id, List<Long> roleIds) {
        // Find existing parent entity
        RouteConfig existingParent = routeConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        // Fetch new roles based on role IDs from the request
        Set<Role> newRoles = new HashSet<>(roleRepository.findAllById(roleIds));

        if (newRoles.isEmpty()) {
            throw new RuntimeException("No valid roles found");
        }

        // Update only the permissionRole field
        existingParent.getPermissionRole().clear();
        existingParent.getPermissionRole().addAll(newRoles);

        // Save only the updated permissionRole without modifying children or other fields
        return routeConfigRepository.save(existingParent);
    }


    @Transactional
    public RouteConfig saveRouteConfigs(RouteConfig routeConfig) {
        if (routeConfig.getChildren() != null) {
            for (RouteConfigChild child : routeConfig.getChildren()) {
                child.setParent(routeConfig); // Ensure child has parent reference
            }
        }
        return routeConfigRepository.save(routeConfig);
    }


    @Autowired
    private RouteConfigChildRepository routeConfigChildRepository;

    @Transactional
    public RouteConfigChild addChildToParent(Long parentId, RouteConfigChild childRequest) {
        // Step 1: Find the parent by ID
        RouteConfig parent = routeConfigRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        // Step 2: Set the parent in the child
        childRequest.setParent(parent);

        // Step 3: Save the child
        return routeConfigChildRepository.save(childRequest);
    }

    @Transactional
    public RouteConfigChild updateChild(Long childId, RouteConfigChild childRequest) {
        RouteConfigChild existingChild = routeConfigChildRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        // Update fields
        existingChild.setLink(childRequest.getLink());
        existingChild.setSection(childRequest.getSection());
        existingChild.setIcon(childRequest.getIcon());

        return routeConfigChildRepository.save(existingChild);
    }


    @Transactional
    public void deleteChild(Long childId) {
        RouteConfigChild existingChild = routeConfigChildRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        routeConfigChildRepository.delete(existingChild);
    }



    @Transactional
    public void deleteParent(Long parentId) {
        RouteConfig existingChild = routeConfigRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        routeConfigRepository.delete(existingChild);
    }



    public RouteChildConfigDTO assignRolesChild(Long userId, List<Long> roleId) {
        RouteConfigChild  user = routeConfigChildRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roless = new HashSet<>(roleRepository.findAllById(roleId));

        if (roless.isEmpty()) {
            throw new RuntimeException("No valid roles found");
        }

        // Clear existing roles before assigning new ones
        user.getPermissionRole().clear();
        user.getPermissionRole().addAll(roless);

        routeConfigChildRepository.save(user);

        return new RouteChildConfigDTO(user); // Return DTO instead of User entity
    }


}

