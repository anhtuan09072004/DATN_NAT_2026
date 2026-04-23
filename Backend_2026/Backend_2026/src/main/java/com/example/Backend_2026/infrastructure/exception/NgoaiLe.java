package com.example.Backend_2026.infrastructure.exception;


import com.example.Backend_2026.infrastructure.constant.Message;

public class NgoaiLe extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public NgoaiLe(){
    }
    public NgoaiLe(Message statusCode) {
        this.message = statusCode.getMessage();
    }

    public NgoaiLe(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
