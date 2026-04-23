package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VaiTroResponse {
    private Long id;
    private String ten;
    private Boolean daXoa;

    private LocalDateTime taoLuc;
    private LocalDateTime capNhatLuc;

    private String taoBoi;
    private String capNhatBoi;
}
