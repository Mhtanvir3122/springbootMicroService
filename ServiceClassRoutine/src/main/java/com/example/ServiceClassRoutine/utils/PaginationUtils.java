package com.example.ServiceClassRoutine.utils;
import com.example.ServiceClassRoutine.dto.request.PaginationRequest;
import com.example.ServiceClassRoutine.dto.response.ApiResponse;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationUtils {

    // 🔹 Convert CourseSearchRequest.Meta → ApiResponse.Meta
    public static ApiResponse.Meta convertMeta(PaginationRequest.Meta requestMeta) {
        ApiResponse.Meta meta = new ApiResponse.Meta();
        meta.setPage(requestMeta.getPage());
        meta.setLimit(requestMeta.getLimit());

        if (requestMeta.getSort() != null) {
            List<ApiResponse.Meta.Sort> sortList = requestMeta.getSort().stream().map(s -> {
                ApiResponse.Meta.Sort sortObj = new ApiResponse.Meta.Sort();
                sortObj.setField(s.getField());
                sortObj.setOrder(s.getOrder());
                return sortObj;
            }).collect(Collectors.toList());
            meta.setSort(sortList);
        }
        return meta;
    }

    // 🔹 Build Pageable object from Meta
    public static Pageable buildPageable(ApiResponse.Meta meta) {
        int page = meta.getPage();
        int limit = meta.getLimit();

        Sort sort = Sort.unsorted();
        if (meta.getSort() != null && !meta.getSort().isEmpty()) {
            var s = meta.getSort().get(0);
            sort = Sort.by(
                    s.getOrder().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                    s.getField()
            );
        }

        return PageRequest.of(page, limit, sort);
    }

    // 🔹 Build Meta info from Page result
    public static ApiResponse.Meta buildMeta(ApiResponse.Meta requestMeta, Page<?> pageData) {
        ApiResponse.Meta meta = new ApiResponse.Meta();

        int page = requestMeta.getPage();
        int limit = requestMeta.getLimit();

        meta.setPage(page);
        meta.setPrevPage(page > 0 ? page - 1 : 0);
        meta.setNextPage(page + 1);
        meta.setLimit(limit);
        meta.setTotalRecords(pageData.getTotalElements());
        meta.setResultCount(pageData.getContent().size());
        meta.setTotalPageCount(pageData.getTotalPages());

        if (requestMeta.getSort() != null) {
            List<ApiResponse.Meta.Sort> sortList = requestMeta.getSort().stream().map(s -> {
                ApiResponse.Meta.Sort sortObj = new ApiResponse.Meta.Sort();
                sortObj.setField(s.getField());
                sortObj.setOrder(s.getOrder());
                return sortObj;
            }).collect(Collectors.toList());
            meta.setSort(sortList);
        }

        return meta;
    }

    // 🔹 Build Final ApiResponse
    public static <T> ApiResponse<Object> buildResponse(Page<T> pageData, ApiResponse.Meta meta, String message) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(200);
        response.setMessage(message);
        response.setMeta(meta);
        response.setBody(pageData.getContent());
        return response;
    }

    // 🔹 One-liner shortcut: Handle Search and build full ResponseEntity<ApiResponse<?>>
    public static <T> ResponseEntity<ApiResponse<?>> handleSearch(
            Page<T> pageData,
            ApiResponse.Meta requestMeta,
            String successMessage
    ) {
        ApiResponse.Meta meta = buildMeta(requestMeta, pageData);
        ApiResponse<Object> response = buildResponse(pageData, meta, successMessage);
        return ResponseEntity.ok(response);
    }
}