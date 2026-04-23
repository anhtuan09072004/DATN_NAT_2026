package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.ChatLieu;
import com.example.Backend_2026.entity.ThuongHieu;
import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.ThuongHieuRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.ThuongHieuResponse;
import org.springframework.stereotype.Component;

@Component
public class ThuongHieuConverter {
    public ThuongHieu toEntity(ThuongHieuRequest request) {
        ThuongHieu thuongHieu = new ThuongHieu();
        thuongHieu.setTen(request.getTen());
        return thuongHieu;
    }

    public ThuongHieuResponse toResponse(ThuongHieu thuongHieu) {
        return ThuongHieuResponse.builder()
                .id(thuongHieu.getId())
                .ten(thuongHieu.getTen())
                .daXoa(thuongHieu.getDaXoa())
                .taoLuc(thuongHieu.getTaoLuc())
                .capNhatLuc(thuongHieu.getCapNhatLuc())
                .taoBoi(thuongHieu.getTaoBoi())
                .capNhatBoi(thuongHieu.getCapNhatBoi())
                .build();
    }
}
