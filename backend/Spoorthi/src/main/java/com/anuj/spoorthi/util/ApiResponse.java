package com.anuj.spoorthi.util;

public class ApiResponse<T> {
    private T data;
    private String error;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
