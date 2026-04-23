package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.TayAo;
import com.example.Backend_2026.infrastructure.converter.CoAoConverter;
import com.example.Backend_2026.infrastructure.converter.TayAoConverter;
import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.TayAoRequest;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;
import com.example.Backend_2026.infrastructure.response.TayAoResponse;
import com.example.Backend_2026.repository.CoAoRepository;
import com.example.Backend_2026.repository.TayAoRepository;
import com.example.Backend_2026.service.CoAoService;
import com.example.Backend_2026.service.TayAoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TayAoServiceImpl implements TayAoService {
    private final TayAoRepository repository;
    private final TayAoConverter converter;


    @Override
    public TayAoResponse create(TayAoRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new RuntimeException("Tên tay ao đã tồn tại");
        }

        TayAo entity = converter.toEntity(request);
        entity.setDaXoa(false);

        TayAo saved = repository.save(entity);

        return converter.toResponse(saved);
    }

    @Override
    public TayAoResponse update(Long id, TayAoRequest request) {
        TayAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tay ao"));

        if (repository.existsByTenAndIdNot(request.getTen(), id)) {
            throw new RuntimeException("Tên tay ao sắc đã tồn tại");
        }

        entity.setTen(request.getTen());

        return converter.toResponse(repository.save(entity));
    }

    @Override
    public List<TayAoResponse> getAll() {
        return repository.findAll()
                .stream()
                .filter(ms -> Boolean.FALSE.equals(ms.getDaXoa()))
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public TayAoResponse getById(Long id) {
        TayAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tay ao "));

        return converter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        TayAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tay ao"));

        entity.setDaXoa(true);
        repository.save(entity);
    }
}
