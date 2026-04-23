package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.SanPhamRequest;
import com.example.Backend_2026.infrastructure.response.SanPhamResponse;

import java.util.List;

public interface SanPhamService {
    SanPhamResponse create(SanPhamRequest request);

    SanPhamResponse getById(Long id);

    List<SanPhamResponse> getAll();

    SanPhamResponse update(Long id, SanPhamRequest request);

    void delete(Long id);

    List<SanPhamResponse> getTop8();
}
