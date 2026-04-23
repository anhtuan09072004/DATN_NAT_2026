package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaChiClientRequest {
    private String ten; // tên người nhận
    private String soDienThoai;
    private String diaChiCuThe;
    private String phuongXa;
    private String quanHuyen;
    private String tinhThanh;
    private Boolean macDinh;
}
