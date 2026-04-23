package com.example.Backend_2026.infrastructure.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuanLyHoaDonResponse {
    private Long id;
    private String ma;

    private BigDecimal tongTien;
    private BigDecimal tienGiam;
    private BigDecimal tienShip;

    private Integer trangThai;
    private Integer kieuHoaDon;

    private String tenKhachHang;
    private String sdt;

    private LocalDateTime ngayThanhToan;
    private String diaChi;

    private LocalDateTime taoLuc;

    private List<HoaDonChiTietResponse> chiTiet;
}
