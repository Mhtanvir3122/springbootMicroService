package com.example.AuthService.repository;

import com.example.AuthService.model.dto.RouteConfigChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteConfigChildRepository extends JpaRepository<RouteConfigChild, Long> {
}

