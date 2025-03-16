package com.example.ServiceTwoApplication.Service;

import com.example.ServiceTwoApplication.FignClient.UserClient;
import com.example.ServiceTwoApplication.FignClient.UserDto;
import com.example.ServiceTwoApplication.Model.Task;
import com.example.ServiceTwoApplication.Model.User;
import com.example.ServiceTwoApplication.Repository.TaskRepository;
import com.example.ServiceTwoApplication.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserClient userFeignClient; // To fetch the agent (User) via Feign Client

    public String assignTask(Long taskId, Long agentId) {
        // Fetch the agent (User) using Feign Client
        UserDto agent = userFeignClient.getUserById(agentId);
        if (agent == null) {
            return "Agent not found.";
        }

        User assignedUser = new User();
        assignedUser.setId(agentId);  // Set the user ID (you could fetch the full User object here)

        // Fetch the active task count for the agent
        long activeTaskCount = taskRepository.countByAssignedUserIdAndStatusIn(agentId, List.of("TODO", "IN_PROGRESS"));

        if (activeTaskCount > 4) {

            List<Long> users = taskRepository.findUsersWithOneOrFewerTasks();
            Long firstUser = users.get(0);

            User assignedUser2 = new User();
            assignedUser2.setId(firstUser);


            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Task not found"));

            // Assign the task to the agent (set the assignedUser object)

            task.setAssignedUser(assignedUser2);
            taskRepository.save(task);

            return "Task assigned successfully";


        }

        // Fetch the task to assign it to the agent
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Assign the task to the agent (set the assignedUser object)
        task.setAssignedUser(assignedUser);
        taskRepository.save(task);

        return "Task assigned successfully";
    }

    private String reassignTask(Long agentId) {
        // Handle task reassignment logic here (you can implement this part)


        return "Agent has exceeded the task limit. Tasks reassigned.";
    }


    // CREATE: Create a new task
    public Task createTask(String createdBy, LocalDateTime createdDate, String priority, String taskName, String status,   String description
) {
        Task task = new Task();

        task.setCreatedBy(createdBy);
        task.setCreatedDate(LocalDateTime.now());
        task.setPriority(priority);
        task.setName(taskName);
        task.setStatus(status);
        task.setDescription(description);

        return taskRepository.save(task);
    }



    public List<Task> getAllUsers() {
        Sort sort = Sort.by(
                Sort.Order.asc("priority"),  // Sort tasks with HIGH priority first
                Sort.Order.asc("createdDate")  // Sort tasks by the newest created first
        );
        return taskRepository.findAll(sort);
    }

    public List<Task> searchUsers(String keyword) {

        // Sort tasks: HIGH priority first, then by newest created date
        Sort sort = Sort.by(
                Sort.Order.asc("priority"),  // HIGH priority first
                Sort.Order.asc("createdDate") // Newest tasks first
        );
        return taskRepository.findByNameContainingIgnoreCase(keyword  ,sort);
    }


    public Task updateTask(Long id, Task roleDetails) {
        Task role = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDetails.getName());
        role.setDescription(roleDetails.getDescription());
        role.setPriority(roleDetails.getPriority());
        role.setCreatedDate(LocalDateTime.now());
        return taskRepository.save(role);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }




    private final UserClient userClient;

    public TaskService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<User> getAllAgents() {
        ResponseEntity<List<User>> response = userClient.getAllAgents();
        return response.getBody();
    }


    public List<Long> getUsersWithOneOrFewerTasks() {
        return taskRepository.findUsersWithOneOrFewerTasks();
    }
}
