package com.example.filo.api.dto;

public class GroqRequest {
    private String message;

    public GroqRequest() {}

    public GroqRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
