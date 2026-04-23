package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.HinhAnh;
import com.example.Backend_2026.entity.SanPham;
import com.example.Backend_2026.entity.SanPhamChiTiet;
import com.example.Backend_2026.entity.TayAo;
import com.example.Backend_2026.infrastructure.request.HinhAnhRequest;
import com.example.Backend_2026.infrastructure.request.TayAoRequest;
import com.example.Backend_2026.infrastructure.response.HinhAnhResponse;
import com.example.Backend_2026.infrastructure.response.SimpleResponse;
import com.example.Backend_2026.infrastructure.response.TayAoResponse;
import org.springframework.stereotype.Component;

@Component
public class HinhAnhConverter {

    public HinhAnh toEntity(HinhAnhRequest request) {
        HinhAnh ha = new HinhAnh();
        ha.setTen(request.getTen());

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setId(request.getSanPhamChiTietId());
        ha.setSanPhamChiTiet(spct);

        return ha;
    }

    public HinhAnhResponse toResponse(HinhAnh entity) {
        return HinhAnhResponse.builder()
                .id(entity.getId())
                .ten(entity.getTen())
                .sanPhamChiTiet(SimpleResponse.builder()
                        .id(entity.getSanPhamChiTiet().getId())
                        .build())
                .daXoa(entity.getDaXoa())
                .taoLuc(entity.getTaoLuc())
                .capNhatLuc(entity.getCapNhatLuc())
                .build();
    }
}
