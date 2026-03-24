package com.example.ServiceClassRoutine.dto.request;

import lombok.Data;

@Data
public class departmentRequest extends PaginationRequest {
   @Data
    public static class Body extends PaginationRequest.Body {
        private String nameEn;
    }

    private Body body;
}
