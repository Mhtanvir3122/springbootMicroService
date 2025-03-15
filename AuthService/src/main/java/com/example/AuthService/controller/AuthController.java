package com.example.AuthService.controller;

import com.example.AuthService.model.Role;
import com.example.AuthService.model.User;
import com.example.AuthService.model.dto.UserDTO;
import com.example.AuthService.model.dto.UserDTOMessage;
import com.example.AuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        boolean isAuthenticated = userService.authenticate(loginData.get("username"), loginData.get("password"));
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        String response = userService.forgotPassword(email, newPassword);
        if (response.equals("Password updated successfully!")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }
    @GetMapping("/list")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping("/search")
    public ResponseEntity<List<UserDTO>> searchEmployees(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");

        List<UserDTO> users;
        if (keyword == null || keyword.trim().isEmpty()) {
            users = userService.getAllUsers(); // Return all employees
        } else {
            users = userService.searchUsers(keyword);
        }
        return ResponseEntity.ok(users);
    }




    @PostMapping("/{userId}/assign-roles")
    public ResponseEntity<UserDTO> assignRoles(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(userService.assignRoles(userId, roleIds));
    }

    @PostMapping("/{userId}/remove-roles")
    public ResponseEntity<User> removeRoles(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(userService.removeRoles(userId, roleIds));
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<Set<Role>> getUserRoles(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserRoles(userId));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTOMessage> getUserById(@PathVariable Long id) {
        Optional<UserDTOMessage> userDTO = userService.getUserById(id);
        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/agents")
    public ResponseEntity<List<User>> getAllAgents() {
        List<User> agents = userService.getAllAgents();
        return ResponseEntity.ok(agents);
    }


}
