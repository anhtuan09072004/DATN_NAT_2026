package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ThongKeRequest;
import com.example.Backend_2026.infrastructure.response.ThongKeResponse;
import com.example.Backend_2026.infrastructure.response.TonKhoResponse;
import com.example.Backend_2026.infrastructure.response.TopSanPhamResponse;

import java.util.List;

public interface ThongKeService {
    ThongKeResponse homNay();

    ThongKeResponse theoKhoang(ThongKeRequest request);

    List<TopSanPhamResponse> top5BanChay();

    List<TonKhoResponse> top5SapHet();
}
