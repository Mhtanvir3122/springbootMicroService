package com.example.ServiceTwoApplication.Controller;

import com.example.ServiceTwoApplication.Model.Task;
import com.example.ServiceTwoApplication.Service.TaskService;
import com.example.ServiceTwoApplication.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/assign")
    public ResponseEntity<String> assignTask(@RequestParam Long taskId, @RequestParam Long agentId) {
        String response = taskService.assignTask(taskId, agentId);
        return ResponseEntity.ok(response); // Return a 200 OK response with the message
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDto) {
        try {
            Task createdTask = taskService.createTask(
                    taskDto.getCreatedBy(),
                    taskDto.getCreatedDate(),
                    taskDto.getPriority(),
                    taskDto.getName(),
                    taskDto.getStatus()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> searchEmployees(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");

        List<Task> users;
        if (keyword == null || keyword.trim().isEmpty()) {
            users = taskService.getAllUsers(); // Return all employees
        } else {
            users = taskService.searchUsers(keyword);
        }
        return ResponseEntity.ok(users);
    }

}

