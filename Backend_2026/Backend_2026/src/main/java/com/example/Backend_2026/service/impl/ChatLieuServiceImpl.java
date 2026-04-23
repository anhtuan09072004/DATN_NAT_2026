package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.ChatLieu;
import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.infrastructure.converter.ChatLieuConverter;
import com.example.Backend_2026.infrastructure.converter.KichCoConverter;
import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;
import com.example.Backend_2026.repository.ChatLieuRepository;
import com.example.Backend_2026.repository.KichCoRepository;
import com.example.Backend_2026.service.ChatLieuService;
import com.example.Backend_2026.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatLieuServiceImpl implements ChatLieuService {
    private final ChatLieuRepository repository;
    private final ChatLieuConverter converter;


    @Override
    public ChatLieuResponse create(ChatLieuRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new RuntimeException("Tên Chat lieu đã tồn tại");
        }

        ChatLieu entity = converter.toEntity(request);
        entity.setDaXoa(false);

        ChatLieu saved = repository.save(entity);

        return converter.toResponse(saved);
    }

    @Override
    public ChatLieuResponse update(Long id, ChatLieuRequest request) {
        ChatLieu entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chat lieu"));

        if (repository.existsByTenAndIdNot(request.getTen(), id)) {
            throw new RuntimeException("Tên chat lieu đã tồn tại");
        }

        entity.setTen(request.getTen());

        return converter.toResponse(repository.save(entity));
    }

    @Override
    public List<ChatLieuResponse> getAll() {
        return repository.findAll()
                .stream()
                .filter(ms -> Boolean.FALSE.equals(ms.getDaXoa()))
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public ChatLieuResponse getById(Long id) {
        ChatLieu entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chat lieu"));

        return converter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        ChatLieu entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chat lieu"));

        entity.setDaXoa(true);
        repository.save(entity);
    }
}
