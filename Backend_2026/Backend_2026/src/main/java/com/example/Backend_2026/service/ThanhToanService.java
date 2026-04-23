package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.ThanhToanRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.HoaDonResponse;

import java.util.List;

public interface ThanhToanService {
    HoaDonResponse thanhToan(ThanhToanRequest request);

}
