package com.dentalmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Getter
public class DefaultResponse {
    private final int http_status_code;
    private final String message;
    private final LocalDateTime current_datetime;
    private final boolean errors;
    private final Object data;


    public DefaultResponse(HttpStatus httpStatus, String message, boolean errors, Object data) {
        this.http_status_code = httpStatus.value();
        this.message = message;
        this.errors = errors;
        this.current_datetime = LocalDateTime.now();
        this.data = data;
    }

    public static ResponseEntity<?> buildDefaultResponse(HttpStatus status, String message, boolean errors, Object data) {
        DefaultResponse response = new DefaultResponse(status, message, errors, Collections.singletonList(data));
        return ResponseEntity.status(status).body(response);
    }

}
