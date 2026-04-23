package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ThanhToanRequest;
import com.example.Backend_2026.infrastructure.response.HoaDonResponse;

public interface CheckoutService {
    HoaDonResponse checkout(Long userId, ThanhToanRequest request);
}
