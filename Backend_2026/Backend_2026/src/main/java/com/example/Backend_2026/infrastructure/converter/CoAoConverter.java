package com.example.Backend_2026.infrastructure.converter;


import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import org.springframework.stereotype.Component;

@Component
public class CoAoConverter {
    public CoAo toEntity(CoAoRequest request) {
        CoAo ms = new CoAo();
        ms.setTen(request.getTen());
        return ms;
    }

    public CoAoResponse toResponse(CoAo entity) {
        return CoAoResponse.builder()
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
