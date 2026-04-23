package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.entity.VaiTro;
import com.example.Backend_2026.infrastructure.converter.VaitroConverter;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.request.VaiTroRequest;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import com.example.Backend_2026.infrastructure.response.VaiTroResponse;
import com.example.Backend_2026.repository.VaiTroRepository;
import com.example.Backend_2026.service.VaiTroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VaiTroServiceImpl implements VaiTroService {
    private final VaiTroRepository repository;
    private final VaitroConverter converter;

    @Override
    public VaiTroResponse create(VaiTroRequest request) {

        if (repository.existsByTen(request.getTen())) {
            throw new RuntimeException("Tên chuc vu đã tồn tại");
        }

        VaiTro entity = converter.toEntity(request);
        entity.setDaXoa(false);

        VaiTro saved = repository.save(entity);

        return converter.toResponse(saved);
    }

    @Override
    public VaiTroResponse update(Long id, VaiTroRequest request) {

        VaiTro entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuc vu"));

        if (repository.existsByTenAndIdNot(request.getTen(), id)) {
            throw new RuntimeException("Tên chuc vu đã tồn tại");
        }
        entity.setTen(request.getTen());

        return converter.toResponse(repository.save(entity));
    }

    @Override
    public List<VaiTroResponse> getAll() {
        return repository.findAll()
                .stream()
                .filter(ms -> Boolean.FALSE.equals(ms.getDaXoa()))
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public VaiTroResponse getById(Long id) {
        VaiTro entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuc vu"));

        return converter.toResponse(entity);
    }

    @Override
    public void deleteById(Long id) {
        VaiTro entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chucvu"));

        entity.setDaXoa(true);
        repository.save(entity);
    }
}
