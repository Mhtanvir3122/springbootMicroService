package com.example.ServiceTwoApplication.dto;

import lombok.Getter;

@Getter
public class StatusCountDTO {


    private String status;
    private Long count;

    public StatusCountDTO(String status, Long count) {
        this.status = status;
        this.count = count;
    }


}
