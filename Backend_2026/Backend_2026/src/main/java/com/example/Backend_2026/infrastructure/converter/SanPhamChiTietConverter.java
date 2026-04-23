package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.*;
import com.example.Backend_2026.infrastructure.request.SanPhamChiTietRequest;
import com.example.Backend_2026.infrastructure.request.SanPhamRequest;
import com.example.Backend_2026.infrastructure.response.HinhAnhResponse;
import com.example.Backend_2026.infrastructure.response.SanPhamChiTietResponse;
import com.example.Backend_2026.infrastructure.response.SanPhamResponse;
import com.example.Backend_2026.infrastructure.response.SimpleResponse;
import com.example.Backend_2026.repository.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanPhamChiTietConverter {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;


    public SanPhamChiTiet toEntity(SanPhamChiTietRequest request) {
        SanPhamChiTiet spct = new SanPhamChiTiet();

        SanPham sp = new SanPham();
        sp.setId(request.getSanPhamId());
        spct.setSanPham(sp);

        KichCo kc  = new KichCo();
        kc.setId(request.getKichCoId());
        spct.setKichCo(kc);

        MauSac ms = new MauSac();
        ms.setId(request.getMauSacId());
        spct.setMauSac(ms);

        spct.setGia(request.getGia());
        spct.setSoLuong(request.getSoLuong());
        spct.setMa(request.getMa());

        return spct;
    }

    public SanPhamChiTietResponse toResponse(SanPhamChiTiet entity) {
        List<HinhAnhResponse> images = hinhAnhRepository
                .findBySanPhamChiTietIdAndDaXoaFalse(entity.getId())
                .stream()
                .map(img -> HinhAnhResponse.builder()
                        .id(img.getId())
                        .ten(img.getTen())
                        .build())
                .toList();
        return SanPhamChiTietResponse.builder()
                .id(entity.getId())
                .sanPham(entity.getSanPham() != null
                        ? SimpleResponse.builder()
                        .id(entity.getSanPham().getId())
                        .ten(entity.getSanPham().getTen()) // có thể null nhưng không crash
                        .build()
                        : null)

                .kichCo(entity.getKichCo() != null
                        ? SimpleResponse.builder()
                        .id(entity.getKichCo().getId())
                        .ten(entity.getKichCo().getTen())
                        .build()
                        : null)

                .mauSac(entity.getMauSac() != null
                        ? SimpleResponse.builder()
                        .id(entity.getMauSac().getId())
                        .ten(entity.getMauSac().getTen())
                        .build()
                        : null)

                .gia(entity.getGia())
                .soLuong(entity.getSoLuong())
                .ma(entity.getMa())
                .hinhAnhs(images)
                .daXoa(entity.getDaXoa())
                .taoLuc(entity.getTaoLuc())
                .capNhatLuc(entity.getCapNhatLuc())
                .taoBoi(entity.getTaoBoi())
                .capNhatBoi(entity.getCapNhatBoi())
                .build();
    }
}
