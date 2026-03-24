package com.example.ServiceClassRoutine.repository;


import com.example.ServiceClassRoutine.model.Course;
import com.example.ServiceClassRoutine.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("""
        SELECT d FROM Department d
        WHERE (:searchKey IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :searchKey, '%')) 
           OR LOWER(d.nameEn) LIKE LOWER(CONCAT('%', :searchKey, '%')))
          AND (:nameEn IS NULL OR LOWER(d.nameEn) LIKE LOWER(CONCAT('%', :nameEn, '%')))
    """)
    Page<Department> searchDepartments(@Param("searchKey") String searchKey,
                                       @Param("nameEn") String nameEn,
                                       Pageable pageable);
}
