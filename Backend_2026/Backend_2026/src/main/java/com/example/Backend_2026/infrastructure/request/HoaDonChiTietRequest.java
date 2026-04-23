package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoaDonChiTietRequest {
    private Long hoaDonId;
    private Long chiTietSanPhamId;
    private Integer soLuong;
}
