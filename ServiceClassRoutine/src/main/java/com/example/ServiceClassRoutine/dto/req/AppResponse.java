package com.example.ServiceClassRoutine.dto.req;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author imtiaz
 * @project core-service
 * @created Sun, Oct 30, 2022
 */

public class AppResponse<T> {

    @Getter
    private Date timestamp;

    @Getter
    private Integer status;

    @Getter
    private String message;

    @Getter
    private Map<String, String> fieldErrors;
    @Getter
    private String error;



    @Getter
    private MetaModel meta;

    @Getter
    private T body;

    private AppResponse() {
    }

    private AppResponse(Integer status) {
        this.status = status;
        this.timestamp = new Date();
    }

    public static AppResponse status(Integer status) {
        return new AppResponse(status);
    }

    public static AppResponse status(HttpStatus status) {
        return new AppResponse(status.value());
    }



    public AppResponse meta(MetaModel meta) {
        this.meta = meta;
        return this;
    }

    public AppResponse body(T data) {
        this.body = data;
        return this;
    }

    public AppResponse message(String message) {
        this.message = message;
        return this;
    }

    public AppResponse fieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }

    public AppResponse error(String error) {
        this.error = error;
        return this;
    }

    public AppResponse error(Exception ex) {
        this.error = ex.getMessage();
        this.message = (null != getMessage()) ? getMessage() : (null != ex.getCause()) ? ex.getCause().getMessage() : ex.getMessage();
        return this;
    }

    public AppResponse error(Exception ex, String defaultMessage) {
        String errorMessage = Optional.ofNullable(ex.getCause())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());

        this.error = errorMessage;

        return this;
    }



}
