package com.example.ServiceClassRoutine.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ApiResponse<T> {
    private long timestamp;
    private int status;
    private String message;
    private Object fieldErrors;
    private Object error;
    private Object header;
    private Meta meta;
    private T body;

    @Data
    public static class Meta {
        private int page;
        private int prevPage;
        private int nextPage;
        private int limit;
        private long totalRecords;
        private int resultCount;
        private int totalPageCount;
        private List<Sort> sort;

        @Data
        public static class Sort {
            private String order;
            private String field;
        }
    }
}
