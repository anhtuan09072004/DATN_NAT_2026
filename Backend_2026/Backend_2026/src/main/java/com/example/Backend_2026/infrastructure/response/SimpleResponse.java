package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleResponse {
    private Long id;
    private String ten;
}
