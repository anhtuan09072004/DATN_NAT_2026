package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.TayAoRequest;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;
import com.example.Backend_2026.infrastructure.response.TayAoResponse;

import java.util.List;

public interface TayAoService {
    TayAoResponse create(TayAoRequest request);
    TayAoResponse update(Long id , TayAoRequest request);
    List<TayAoResponse> getAll();
    TayAoResponse getById(Long id);
    void delete(Long id);
}
