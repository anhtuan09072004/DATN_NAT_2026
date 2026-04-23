package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProfileResponse {
    private Long id;
    private String username;
    private String ten;
    private String email;
    private String soDienThoai;
    private String avatar;
    private String gioiTinh;
    private LocalDate ngaySinh;
}
