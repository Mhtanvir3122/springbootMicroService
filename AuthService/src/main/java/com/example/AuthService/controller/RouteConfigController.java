package com.example.AuthService.controller;

import com.example.AuthService.model.RouteConfig;
import com.example.AuthService.service.RouteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
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
}

