package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.DiaChiRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;

import java.util.List;

public interface DiaChiService {
    List<DiaChiResponse> getByTaiKhoan(Long taiKhoanId);

    DiaChiResponse create(DiaChiRequest request);

    DiaChiResponse update(Long id, DiaChiRequest request);

    void delete(Long id);
}
