package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuanLyHoaDonRequest {
    private String ma;
    private Integer trangThai;
    private Integer kieuHoaDon;

    private LocalDateTime tuNgay;
    private LocalDateTime denNgay;

    private String tenKhachHang;
    private String sdt;

    private Integer page = 0;
    private Integer size = 10;
}
