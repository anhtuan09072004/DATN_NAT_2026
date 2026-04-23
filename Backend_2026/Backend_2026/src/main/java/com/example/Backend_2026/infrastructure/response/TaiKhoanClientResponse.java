package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaiKhoanClientResponse {
    private Long id;
    private String username;
    private String ten;
    private String email;
    private String soDienThoai;
}
