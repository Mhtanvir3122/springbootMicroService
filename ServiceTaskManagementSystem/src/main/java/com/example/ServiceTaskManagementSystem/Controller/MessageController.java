package com.example.ServiceTaskManagementSystem.Controller;

import com.example.ServiceTaskManagementSystem.FignClient.UserClient;
import com.example.ServiceTaskManagementSystem.FignClient.UserDto;
import com.example.ServiceTaskManagementSystem.Model.Message;
import com.example.ServiceTaskManagementSystem.Repository.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/message")
public class MessageController {

    private final UserClient userClient;




    private final MessageRepository messageRepository;

    public MessageController(UserClient userClient, MessageRepository messageRepository) {
        this.userClient = userClient;
        this.messageRepository=messageRepository;
    }

    @GetMapping("/userbyid/{id}")
    public ResponseEntity<?> getEmployeeWithDepartment(@PathVariable Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto userDto = userClient.getUserById(message.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("userDto", userDto);

        return ResponseEntity.ok(response);
    }
}
