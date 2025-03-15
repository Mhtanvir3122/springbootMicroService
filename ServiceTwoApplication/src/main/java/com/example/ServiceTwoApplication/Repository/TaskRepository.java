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



        // Custom query to find agents with less than 5 active tasks (TODO, IN_PROGRESS)
        @Query("SELECT t.assignedUser.id FROM Task t " +
                "WHERE t.status IN :statuses " +
                "GROUP BY t.assignedUser.id " +
                "HAVING COUNT(t) < 5")
        List<Long> findUserIdsWithLessThanFiveActiveTasks(@Param("statuses") List<String> statuses);

    List<Task> findByAssignedUserContainingIgnoreCaseAndNameContainingIgnoreCase(String assignedUser, String name, Sort sort);

}