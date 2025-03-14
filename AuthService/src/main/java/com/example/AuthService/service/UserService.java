package com.example.AuthService.service;

import com.example.AuthService.model.Role;
import com.example.AuthService.model.User;
import com.example.AuthService.model.dto.RoleDTO;
import com.example.AuthService.model.dto.UserDTO;
import com.example.AuthService.model.dto.UserDTOMessage;
import com.example.AuthService.repository.RoleRepository;
import com.example.AuthService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role defaultRole = roleRepository.findByName("USER_ROLE").orElse(null);


        // Set default role to user
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        // Add default role to the user’s roles Set
        user.getRoles().add(defaultRole);

        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
    public String forgotPassword(String email, String newPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(existingUser);
            return "Password updated successfully!";
        }
        return "User not found!";
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().stream()
                                .map(role -> new RoleDTO(role.getId(), role.getName()))
                                .collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public List<UserDTO> searchUsers(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword);
    }


//    public User assignRoles(Long userId, List<Long> roleIds) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
//
//        if (roles.isEmpty()) {
//            throw new RuntimeException("No valid roles found");
//        }
//
//        user.getRoles().addAll(roles);
//        return userRepository.save(user);
//    }

    public UserDTO assignRoles(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));

        if (roles.isEmpty()) {
            throw new RuntimeException("No valid roles found");
        }

        // Clear existing roles before assigning new ones
        user.getRoles().clear();
        user.getRoles().addAll(roles);

        userRepository.save(user);

        return new UserDTO(user); // Return DTO instead of User entity
    }




    public User removeRoles(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> rolesToRemove = new HashSet<>(roleRepository.findAllById(roleIds));

        if (rolesToRemove.isEmpty()) {
            throw new RuntimeException("No valid roles found to remove");
        }

        user.getRoles().removeAll(rolesToRemove);
        return userRepository.save(user);
    }
    public Set<Role> getUserRoles(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRoles();
    }

    public Optional<UserDTOMessage> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTOMessage(user.getId(), user.getUsername(), user.getEmail()));
    }

}

