package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.ChatLieu;
import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;
import org.springframework.stereotype.Component;

@Component
public class ChatLieuConverter {
    public ChatLieu toEntity(ChatLieuRequest request) {
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setTen(request.getTen());
        return chatLieu;
    }

    public ChatLieuResponse toResponse(ChatLieu chatLieu) {
        return ChatLieuResponse.builder()
                .id(chatLieu.getId())
                .ten(chatLieu.getTen())
                .daXoa(chatLieu.getDaXoa())
                .taoLuc(chatLieu.getTaoLuc())
                .capNhatLuc(chatLieu.getCapNhatLuc())
                .taoBoi(chatLieu.getTaoBoi())
                .capNhatBoi(chatLieu.getCapNhatBoi())
                .build();
    }
}
