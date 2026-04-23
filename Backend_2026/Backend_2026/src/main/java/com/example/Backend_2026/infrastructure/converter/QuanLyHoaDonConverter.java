package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.HoaDon;
import com.example.Backend_2026.entity.HoaDonChiTiet;
import com.example.Backend_2026.infrastructure.response.QuanLyHoaDonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuanLyHoaDonConverter {
    public QuanLyHoaDonResponse toResponse(HoaDon hoaDon, List<HoaDonChiTiet> list) {
        QuanLyHoaDonResponse res = new QuanLyHoaDonResponse();
        res.setId(hoaDon.getId());
        res.setMa(hoaDon.getMa());
        res.setTongTien(hoaDon.getTongTien());
        res.setTienGiam(hoaDon.getTienGiam());
        res.setTienShip(hoaDon.getTienShip());
        res.setTrangThai(hoaDon.getTrangThai());
        res.setKieuHoaDon(hoaDon.getKieuHoaDon());
        res.setTenKhachHang(hoaDon.getTenKhachHang());
        res.setSdt(hoaDon.getPhoneNumber() != null ? hoaDon.getPhoneNumber().toString() : "");
        res.setNgayThanhToan(hoaDon.getNgayThanhToan());
        res.setDiaChi(hoaDon.getDiaChi());
        res.setTaoLuc(hoaDon.getTaoLuc());

        // dùng lại converter cũ nếu có
        // res.setChiTiet(...)

        return res;
    }
}
