package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatLieuResponse {

    private Long id;
    private String ten;
    private Boolean daXoa;

    private LocalDateTime taoLuc;
    private LocalDateTime capNhatLuc;

    private String taoBoi;
    private String capNhatBoi;
}
