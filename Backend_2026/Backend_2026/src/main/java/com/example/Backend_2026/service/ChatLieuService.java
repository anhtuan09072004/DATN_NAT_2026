package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;

import java.util.List;

public interface ChatLieuService {
    ChatLieuResponse create(ChatLieuRequest request);
    ChatLieuResponse update(Long id , ChatLieuRequest request);
    List<ChatLieuResponse> getAll();
    ChatLieuResponse getById(Long id);
    void delete(Long id);
}
