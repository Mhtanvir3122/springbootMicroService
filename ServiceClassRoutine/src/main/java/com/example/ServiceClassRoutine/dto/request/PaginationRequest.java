package com.example.ServiceClassRoutine.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class PaginationRequest {
    private Meta meta;
    private  Body body;

    @Data
    public static class Meta {
        private int page;
        private int limit;
        private List<Sort> sort;

        @Data
        public static class Sort {
            private String order;
            private String field;
        }
    }


    @Data
    public static class Body {
        private String searchKey;

    }

}
