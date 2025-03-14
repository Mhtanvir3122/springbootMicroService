package com.example.ServiceTwoApplication.FignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "AuthService", url = "http://localhost:9003")
public interface UserClient {

    @GetMapping("/auth/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
