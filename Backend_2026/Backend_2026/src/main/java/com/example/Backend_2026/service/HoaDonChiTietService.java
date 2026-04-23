package com.example.Backend_2026.service;

import com.example.Backend_2026.entity.HoaDonChiTiet;
import com.example.Backend_2026.entity.SanPhamChiTiet;

import java.util.List;

public interface HoaDonChiTietService {
    HoaDonChiTiet addProduct(Long hoaDonId, Long spctId, Integer soLuong);

    void decreaseQuantity(Long id);

    void removeItem(Long id);

    List<HoaDonChiTiet> getByHoaDon(Long hoaDonId);

    void increaseQuantity(Long id);

    void updateQuantity(Long id, Integer soLuong);

    void clearCart(Long hoaDonId);

//    SanPhamChiTiet save(SanPhamChiTiet ctsp);
}

