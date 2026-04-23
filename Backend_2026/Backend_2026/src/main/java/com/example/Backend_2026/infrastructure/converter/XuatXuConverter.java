package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.ChatLieu;
import com.example.Backend_2026.entity.XuatXu;
import com.example.Backend_2026.infrastructure.request.ChatLieuRequest;
import com.example.Backend_2026.infrastructure.request.XuatXuRequest;
import com.example.Backend_2026.infrastructure.response.ChatLieuResponse;
import com.example.Backend_2026.infrastructure.response.XuatXuResponse;
import org.springframework.stereotype.Component;

@Component
public class XuatXuConverter {
    public XuatXu toEntity(XuatXuRequest request) {
        XuatXu xuatXu = new XuatXu();
        xuatXu.setTen(request.getTen());
        return xuatXu;
    }

    public XuatXuResponse toResponse(XuatXu xuatXu) {
        return XuatXuResponse.builder()
                .id(xuatXu.getId())
                .ten(xuatXu.getTen())
                .daXoa(xuatXu.getDaXoa())
                .taoLuc(xuatXu.getTaoLuc())
                .capNhatLuc(xuatXu.getCapNhatLuc())
                .taoBoi(xuatXu.getTaoBoi())
                .capNhatBoi(xuatXu.getCapNhatBoi())
                .build();
    }
}
