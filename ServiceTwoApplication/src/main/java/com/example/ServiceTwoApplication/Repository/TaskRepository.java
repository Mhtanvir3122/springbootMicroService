package com.example.ServiceTwoApplication.Repository;


import com.example.ServiceTwoApplication.Model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    long countByAssignedUserIdAndStatusIn(Long assignedUserId, List<String> statuses);


    List<Task> findByNameContainingIgnoreCase( String name, Sort sort);

    @Query("SELECT  u.id " +
            "FROM User u " +
            "LEFT JOIN Task t ON u.id = t.assignedUser.id " +
            "GROUP BY u.username, u.id " +
            "HAVING COUNT(t.assignedUser) <= 4")
    List<Long> findUsersWithOneOrFewerTasks();




    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:assignedUserId IS NULL OR t.assignedUser.id = :assignedUserId) AND " +
            "(:createdBy IS NULL OR t.createdBy = :createdBy) ")
    List<Task> findFilteredTasks(
            @Param("status") String status,
            @Param("priority") String priority,
            @Param("assignedUserId") Long assignedUserId,
            @Param("createdBy") Long createdBy
    );


    @Query("SELECT t FROM Task t WHERE t.dueDate < :currentTime AND t.status != 'EXPIRED'")
    List<Task> findTasksByDueDateBeforeAndStatusNot(LocalDateTime currentTime, String status);
}