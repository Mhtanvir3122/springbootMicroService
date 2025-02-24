package com.example.AuthService.controller;

import com.example.AuthService.model.RouteConfig;
import com.example.AuthService.model.dto.RouteChildConfigDTO;
import com.example.AuthService.model.dto.RouteConfigChild;
import com.example.AuthService.model.dto.RouteConfigDTO;
import com.example.AuthService.service.RouteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/route-configs")
public class RouteConfigController {

    private final RouteConfigService routeConfigService;

    @Autowired
    public RouteConfigController(RouteConfigService routeConfigService) {
        this.routeConfigService = routeConfigService;
    }

    // Save Route Configuration
    @PostMapping
    public ResponseEntity<RouteConfig> createRouteConfig(@RequestBody RouteConfig routeConfig) {
        RouteConfig savedRouteConfig = routeConfigService.saveRouteConfig(routeConfig);
        return ResponseEntity.ok(savedRouteConfig);
    }

    // Get all Route Configurations
    @GetMapping
    public ResponseEntity<List<RouteConfig>> getAllRouteConfigs() {
        List<RouteConfig> routeConfigs = routeConfigService.getAllRouteConfigs();
        return ResponseEntity.ok(routeConfigs);
    }

    @GetMapping("/list")
    public List<RouteConfigDTO> getAllUsers() {
        return routeConfigService.getAllRoutes();
    }

    @PostMapping("/{userId}/assign-roles-route")
    public ResponseEntity<RouteConfig> assignRoles(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(routeConfigService.assignRoles(userId, roleIds));
    }

    @PostMapping("/{userId}/assign-roles-route-child")
    public ResponseEntity<RouteChildConfigDTO> assignRolesChild(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(routeConfigService.assignRolesChild(userId, roleIds));
    }

    @PutMapping("update/{id}")
    public RouteConfig updateRole(@PathVariable Long id, @RequestBody RouteConfig config) {
        return routeConfigService.updateRouteConfig(id, config);
    }



    @PostMapping("/{parentId}/add-child")
    public ResponseEntity<RouteConfigChild> addChild(
            @PathVariable Long parentId,
            @RequestBody RouteConfigChild childRequest) {

        RouteConfigChild savedChild = routeConfigService.addChildToParent(parentId, childRequest);
        return ResponseEntity.ok(savedChild);
    }



    @PutMapping("/child/{childId}")
    public ResponseEntity<RouteConfigChild> updateChild(
            @PathVariable Long childId,
            @RequestBody RouteConfigChild childRequest) {

        RouteConfigChild updatedChild = routeConfigService.updateChild(childId, childRequest);
        return ResponseEntity.ok(updatedChild);
    }

    @DeleteMapping("/child/{childId}")
    public ResponseEntity<String> deleteChild(@PathVariable Long childId) {
        routeConfigService.deleteChild(childId);
        return ResponseEntity.ok("Child deleted successfully!");
    }


    @DeleteMapping("/parent/{childId}")
    public ResponseEntity<String> deleteParent(@PathVariable Long childId) {
        routeConfigService.deleteParent(childId);
        return ResponseEntity.ok("Child deleted successfully!");
    }
}

