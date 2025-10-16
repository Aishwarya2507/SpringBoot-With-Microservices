package com.SpringBoot.api_category_service.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic API Response Wrapper
 *
 * @param <T> the type of data being returned
 */

@Data
@NoArgsConstructor

public class ApiResponse<T> {
    private boolean success;  // Indicates success/failure of API
    private String message;   // Message or description
    private T data;           // Actual response payload (Product, List<Product>, etc.)


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}


