package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiaChiResponse {
    private Long id;
    private Long taiKhoanId;
    private String ten;
    private String soDienThoai;
    private String diaChiCuThe;
    private String phuongXa;
    private String quanHuyen;
    private String tinhThanh;
    private Boolean macDinh;
}
