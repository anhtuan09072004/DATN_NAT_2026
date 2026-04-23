package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.DiaChiClientRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;

import java.util.List;

public interface DiaChiClientService {
    List<DiaChiResponse> getMyAddresses(Long userId);

    DiaChiResponse create(DiaChiClientRequest request, Long userId);

    DiaChiResponse update(Long id, DiaChiClientRequest request, Long userId);

    void delete(Long id, Long userId);
}
