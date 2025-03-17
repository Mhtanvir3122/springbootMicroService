package com.example.ServiceTwoApplication.Controller;

import com.example.ServiceTwoApplication.Model.Task;
import com.example.ServiceTwoApplication.Model.User;
import com.example.ServiceTwoApplication.Repository.TaskRepository;
import com.example.ServiceTwoApplication.Service.TaskService;
import com.example.ServiceTwoApplication.dto.StatusCountDTO;
import com.example.ServiceTwoApplication.dto.TaskDTO;
import com.example.ServiceTwoApplication.dto.TaskFilterRequest;
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

    @Autowired
    private TaskRepository taskRepository;

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
                    taskDto.getStatus(),
                    taskDto.getDescription(),
                    taskDto.getDueDate()
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


    @PutMapping("/task-update/{id}")
    public Task updateRole(@PathVariable Long id, @RequestBody Task role) {
        return taskService.updateTask(id, role);
    }

    @DeleteMapping("/task-delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/agents")
    public ResponseEntity<List<User>> getAgents() {
        List<User> agents = taskService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/users-with-one-or-fewer-tasks")
    public List<Long> getUsersWithOneOrFewerTasks() {
        return taskService.getUsersWithOneOrFewerTasks();
    }



    @PostMapping("/filter")
    public List<Task> searchTasks(@RequestBody TaskFilterRequest filterRequest) {
        return taskService.getFilteredTasks(
                filterRequest.getStatus(),
                filterRequest.getPriority(),
                filterRequest.getAssignedUserId(),
                filterRequest.getCreatedBy()

        );
    }


    @GetMapping("/status-count")
    public ResponseEntity<List<StatusCountDTO>> getStatusWiseCount() {
        List<StatusCountDTO> countList = taskRepository.getStatusWiseTaskCount();
        return ResponseEntity.ok(countList);
    }
}

