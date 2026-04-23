package com.example.Backend_2026.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopSanPhamResponse {
    private Long sanPhamChiTietId;
    private String tenSanPham;
    private Long soLuongBan;
}
