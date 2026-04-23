package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoaDonRequest {
    private Long taiKhoanId;
    private Integer kieuHoaDon; // 0: tại quầy, 1: online
}
