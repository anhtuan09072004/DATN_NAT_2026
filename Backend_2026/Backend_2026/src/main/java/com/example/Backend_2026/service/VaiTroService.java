package com.example.Backend_2026.service;

import com.example.Backend_2026.entity.VaiTro;
import com.example.Backend_2026.infrastructure.request.VaiTroRequest;
import com.example.Backend_2026.infrastructure.response.VaiTroResponse;

import java.util.List;

public interface VaiTroService {
     VaiTroResponse create(VaiTroRequest request);
     VaiTroResponse update(Long id,VaiTroRequest request);
     List<VaiTroResponse> getAll();
     VaiTroResponse getById(Long id);
     void deleteById(Long id);
}
