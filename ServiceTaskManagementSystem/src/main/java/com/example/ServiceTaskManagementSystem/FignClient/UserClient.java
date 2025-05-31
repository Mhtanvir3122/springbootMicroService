package com.example.ServiceTaskManagementSystem.FignClient;

import com.example.ServiceTaskManagementSystem.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "AuthService", url = "http://localhost:9003")
public interface UserClient {

    @GetMapping("/auth/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/auth/agents")
    ResponseEntity<List<User>> getAllAgents();
}
