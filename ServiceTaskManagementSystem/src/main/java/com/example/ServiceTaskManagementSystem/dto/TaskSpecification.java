package com.example.ServiceTaskManagementSystem.dto;
import com.example.ServiceTaskManagementSystem.Model.Task;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskSpecification {

    public static Specification<Task> filterTasks(String status, String priority,
                                                  Long assignedUserId, Long createdBy,
                                                  Date createdDateFrom, Date createdDateTo) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (priority != null) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), priority));
            }

            if (assignedUserId != null) {
                predicates.add(criteriaBuilder.equal(root.get("assignedUserId"), assignedUserId));
            }

            if (createdBy != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy"), createdBy));
            }

            if (createdDateFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), createdDateFrom));
            }

            if (createdDateTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), createdDateTo));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
