package com.example.Backend_2026.infrastructure.converter;


import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import org.springframework.stereotype.Component;

@Component
public class MauSacConverter {
    public MauSac toEntity(MauSacRequest request) {
        MauSac ms = new MauSac();
        ms.setTen(request.getTen());
        return ms;
    }

    public MauSacResponse toResponse(MauSac entity) {
        return MauSacResponse.builder()
                .id(entity.getId())
                .ten(entity.getTen())
                .daXoa(entity.getDaXoa())
                .taoLuc(entity.getTaoLuc())
                .capNhatLuc(entity.getCapNhatLuc())
                .taoBoi(entity.getTaoBoi())
                .capNhatBoi(entity.getCapNhatBoi())
                .build();
    }
}
