package com.example.Backend_2026.infrastructure.response;

import lombok.*;

@Data
public class ApiErrorResponse {
    private boolean success;
    private String message;
}
