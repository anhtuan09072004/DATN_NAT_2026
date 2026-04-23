package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.entity.TayAo;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.request.TayAoRequest;
import com.example.Backend_2026.infrastructure.response.KichCoResponse;
import com.example.Backend_2026.infrastructure.response.TayAoResponse;
import org.springframework.stereotype.Component;

@Component
public class TayAoConverter {
    public TayAo toEntity(TayAoRequest request) {
        TayAo tayAo = new TayAo();
        tayAo.setTen(request.getTen());
        return tayAo;
    }

    public TayAoResponse toResponse(TayAo tayAo) {
        return TayAoResponse.builder()
                .id(tayAo.getId())
                .ten(tayAo.getTen())
                .daXoa(tayAo.getDaXoa())
                .taoLuc(tayAo.getTaoLuc())
                .capNhatLuc(tayAo.getCapNhatLuc())
                .taoBoi(tayAo.getTaoBoi())
                .capNhatBoi(tayAo.getCapNhatBoi())
                .build();
    }
}
