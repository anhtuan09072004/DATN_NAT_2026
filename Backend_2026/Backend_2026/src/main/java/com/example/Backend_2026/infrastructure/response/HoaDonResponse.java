package com.example.Backend_2026.infrastructure.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonResponse {
    private Long id;
    private BigDecimal tongTien;
    private BigDecimal tienGiam;
    private BigDecimal tienShip;
    private Integer trangThai;
    private String tenKhachHang;
    private String sdt;
    private Integer kieuHoaDon;
    private LocalDateTime taoLuc;
    private String maHoaDon;

    private List<HoaDonChiTietResponse> hoaDonChiTiets;
}
