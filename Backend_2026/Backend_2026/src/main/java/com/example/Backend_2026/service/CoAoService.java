package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;

import java.util.List;

public interface CoAoService {
    CoAoResponse create(CoAoRequest request);
    CoAoResponse update(Long id , CoAoRequest request);
    List<CoAoResponse> getAll();
    CoAoResponse getById(Long id);
    void delete(Long id);
}
