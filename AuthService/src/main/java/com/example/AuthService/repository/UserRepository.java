package com.example.AuthService.repository;
import com.example.AuthService.model.Role;
import com.example.AuthService.model.User;
import com.example.AuthService.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<UserDTO> findByUsernameContainingIgnoreCase(String username);

}

