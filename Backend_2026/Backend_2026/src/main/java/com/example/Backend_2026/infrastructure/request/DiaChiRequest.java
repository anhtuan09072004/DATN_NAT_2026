package com.example.Backend_2026.infrastructure.request;

import lombok.Data;

@Data
public class DiaChiRequest {
    private Long taiKhoanId;
    private String ten;
    private String soDienThoai;
    private String diaChiCuThe;
    private String phuongXa;
    private String quanHuyen;
    private String tinhThanh;
    private Boolean macDinh;
}
