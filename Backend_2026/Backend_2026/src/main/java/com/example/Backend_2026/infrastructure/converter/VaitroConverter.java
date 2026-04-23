package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.entity.VaiTro;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.request.VaiTroRequest;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import com.example.Backend_2026.infrastructure.response.VaiTroResponse;
import org.springframework.stereotype.Component;

@Component
public class VaitroConverter {
    public VaiTro toEntity(VaiTroRequest request) {
        VaiTro ms = new VaiTro();
        ms.setTen(request.getTen());
        return ms;
    }

    public VaiTroResponse toResponse(VaiTro entity) {
        return VaiTroResponse.builder()
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
