package com.imageuploader.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private String status;
    private String message;
    private Object data;

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(String status, String message, Object data) {
       this(status, message);
        this.data = data;
    }
}
