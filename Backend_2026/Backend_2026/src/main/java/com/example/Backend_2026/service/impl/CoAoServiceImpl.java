package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.infrastructure.converter.CoAoConverter;
import com.example.Backend_2026.infrastructure.converter.KichCoConverter;
import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;
import com.example.Backend_2026.repository.CoAoRepository;
import com.example.Backend_2026.repository.KichCoRepository;
import com.example.Backend_2026.service.CoAoService;
import com.example.Backend_2026.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoAoServiceImpl implements CoAoService {
    private final CoAoRepository repository;
    private final CoAoConverter converter;


    @Override
    public CoAoResponse create(CoAoRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new RuntimeException("Tên co ao đã tồn tại");
        }

        CoAo entity = converter.toEntity(request);
        entity.setDaXoa(false);

        CoAo saved = repository.save(entity);

        return converter.toResponse(saved);
    }

    @Override
    public CoAoResponse update(Long id, CoAoRequest request) {
        CoAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy co ao"));

        if (repository.existsByTenAndIdNot(request.getTen(), id)) {
            throw new RuntimeException("Tên co ao sắc đã tồn tại");
        }

        entity.setTen(request.getTen());

        return converter.toResponse(repository.save(entity));
    }

    @Override
    public List<CoAoResponse> getAll() {
        return repository.findAll()
                .stream()
                .filter(ms -> Boolean.FALSE.equals(ms.getDaXoa()))
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public CoAoResponse getById(Long id) {
        CoAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy co ao "));

        return converter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        CoAo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy co ao"));

        entity.setDaXoa(true);
        repository.save(entity);
    }
}
