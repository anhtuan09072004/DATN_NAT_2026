package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileRequest {
    private String ten;
    private String email;
    private String soDienThoai;
    private String gioiTinh;
    private LocalDate ngaySinh;
}
