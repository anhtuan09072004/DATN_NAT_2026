package com.example.Backend_2026.service;

import com.example.Backend_2026.entity.SanPham;
import com.example.Backend_2026.entity.SanPhamChiTiet;
import com.example.Backend_2026.infrastructure.request.QuanLyHoaDonRequest;
import com.example.Backend_2026.infrastructure.response.QuanLyHoaDonResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuanLyHoaDonService {
    Page<QuanLyHoaDonResponse> getAll(QuanLyHoaDonRequest request);

    QuanLyHoaDonResponse getDetail(Long id);

    void updateTrangThai(Long id, Integer trangThai);

    void huyHoaDon(Long id);
}
