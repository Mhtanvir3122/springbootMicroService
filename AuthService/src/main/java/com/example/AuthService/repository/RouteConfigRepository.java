package com.example.AuthService.repository;

import com.example.AuthService.model.RouteConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteConfigRepository extends JpaRepository<RouteConfig, Long> {
    public List<RouteConfig> findBySectionContainingIgnoreCase(String keyword);

}

