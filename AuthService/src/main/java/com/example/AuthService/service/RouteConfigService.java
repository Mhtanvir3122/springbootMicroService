package com.example.AuthService.service;

import com.example.AuthService.model.RouteConfig;
import com.example.AuthService.repository.RouteConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteConfigService {

    private final RouteConfigRepository routeConfigRepository;

    @Autowired
    public RouteConfigService(RouteConfigRepository routeConfigRepository) {
        this.routeConfigRepository = routeConfigRepository;
    }

    public RouteConfig saveRouteConfig(RouteConfig routeConfig) {
        return routeConfigRepository.save(routeConfig);
    }

    public List<RouteConfig> getAllRouteConfigs() {
        return routeConfigRepository.findAll();
    }
}

