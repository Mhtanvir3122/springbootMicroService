package com.example.ServiceTwoApplication.Repository;


import com.example.ServiceTwoApplication.Model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}