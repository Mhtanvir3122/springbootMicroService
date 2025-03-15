package com.example.ServiceTwoApplication.dto;


import com.example.ServiceTwoApplication.Model.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> searchTasks(TaskSearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction(); // Default: `AND` condition

            if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
                predicates.getExpressions().add(
                        criteriaBuilder.like(root.get("name"), "%" + searchRequest.getName() + "%")
                );
            }

            if (searchRequest.getStatus() != null && !searchRequest.getStatus().isEmpty()) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("status"), searchRequest.getStatus())
                );
            }

            if (searchRequest.getPriority() != null && !searchRequest.getPriority().isEmpty()) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("priority"), searchRequest.getPriority())
                );
            }

            if (searchRequest.getAssignedUserId() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("assignedUser").get("id"), searchRequest.getAssignedUserId())
                );
            }

            if (searchRequest.getCreatedBy() != null && !searchRequest.getCreatedBy().isEmpty()) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("createdBy"), searchRequest.getCreatedBy())
                );
            }

            if (searchRequest.getStartDate() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), searchRequest.getStartDate())
                );
            }

            if (searchRequest.getEndDate() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), searchRequest.getEndDate())
                );
            }

            if (searchRequest.getDueStartDate() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), searchRequest.getDueStartDate())
                );
            }

            if (searchRequest.getDueEndDate() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), searchRequest.getDueEndDate())
                );
            }

            return predicates;
        };
    }
}
