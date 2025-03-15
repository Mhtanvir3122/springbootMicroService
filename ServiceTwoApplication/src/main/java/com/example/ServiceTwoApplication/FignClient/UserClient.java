package com.example.ServiceTwoApplication.FignClient;

import com.example.ServiceTwoApplication.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "AuthService", url = "http://localhost:9003")
public interface UserClient {

    @GetMapping("/auth/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/auth/users/agents")
    ResponseEntity<List<User>> getAllAgents();
}
