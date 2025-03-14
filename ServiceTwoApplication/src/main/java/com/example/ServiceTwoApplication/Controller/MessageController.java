package com.example.ServiceTwoApplication.Controller;

import com.example.ServiceTwoApplication.FignClient.UserClient;
import com.example.ServiceTwoApplication.FignClient.UserDto;
import com.example.ServiceTwoApplication.Model.Message;
import com.example.ServiceTwoApplication.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
