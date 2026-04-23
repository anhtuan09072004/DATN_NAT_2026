package com.example.Backend_2026.infrastructure.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MauSacResponse {

    private Long id;
    private String ten;
    private Boolean daXoa;

    private LocalDateTime taoLuc;
    private LocalDateTime capNhatLuc;

    private String taoBoi;
    private String capNhatBoi;
}
