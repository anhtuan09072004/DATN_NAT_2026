package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.TaiKhoanClientRequest;
import com.example.Backend_2026.infrastructure.response.TaiKhoanClientResponse;

public interface TaiKhoanClientService {
    TaiKhoanClientResponse login(TaiKhoanClientRequest request);
}
