package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;
import com.example.Backend_2026.repository.KichCoRepository;
import org.springframework.stereotype.Component;

@Component
public class KichCoConverter {
    public KichCo toEntity(KichCoRequest request) {
        KichCo kichCo = new KichCo();
        kichCo.setTen(request.getTen());
        return kichCo;
    }

    public KichCoResponse toResponse(KichCo kichCo) {
        return KichCoResponse.builder()
                .id(kichCo.getId())
                .ten(kichCo.getTen())
                .daXoa(kichCo.getDaXoa())
                .taoLuc(kichCo.getTaoLuc())
                .capNhatLuc(kichCo.getCapNhatLuc())
                .taoBoi(kichCo.getTaoBoi())
                .capNhatBoi(kichCo.getCapNhatBoi())
                .build();
    }
}
