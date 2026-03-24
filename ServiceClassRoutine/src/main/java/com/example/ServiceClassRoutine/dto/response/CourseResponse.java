package com.example.ServiceClassRoutine.dto.response;

import com.example.ServiceClassRoutine.model.Department;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
public class CourseResponse {


    private String name;



    private Department department;

}
