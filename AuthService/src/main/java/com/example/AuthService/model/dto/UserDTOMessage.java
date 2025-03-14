package com.example.AuthService.model.dto;

import com.example.AuthService.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOMessage {
    private Long id;
    private String username;
    private String email;


    public UserDTOMessage(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();

    }


}

