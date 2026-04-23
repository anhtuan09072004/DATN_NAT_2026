package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.*;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.request.SanPhamRequest;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import com.example.Backend_2026.infrastructure.response.SanPhamResponse;
import com.example.Backend_2026.infrastructure.response.SimpleResponse;
import org.springframework.stereotype.Component;

@Component
public class SanPhamConverter {
    public SanPham toEntity(SanPhamRequest request) {
        SanPham sp = new SanPham();
        sp.setTen(request.getTen());
        sp.setMa(request.getMa());
        sp.setMoTa(request.getMoTa());
        ChatLieu cl = new ChatLieu();
        cl.setId(request.getChatLieuId());
        sp.setChatLieu(cl);
        ThuongHieu th = new ThuongHieu();
        th.setId(request.getThuongHieuId());
        sp.setThuongHieu(th);
        XuatXu xx = new XuatXu();
        xx.setId(request.getXuatXuId());
        sp.setXuatXu(xx);
        CoAo co = new CoAo();
        co.setId(request.getCoAoId());
        sp.setCoAo(co);
        TayAo tay = new TayAo();
        tay.setId(request.getTayAoId());
        sp.setTayAo(tay);
        return sp;
    }

    public SanPhamResponse toResponse(SanPham entity) {
        return SanPhamResponse.builder()
                .id(entity.getId())
                .ten(entity.getTen())
                .ma(entity.getMa())
                .moTa(entity.getMoTa())

                .chatLieu(SimpleResponse.builder()
                        .id(entity.getChatLieu().getId())
                        .ten(entity.getChatLieu().getTen())
                        .build())

                .thuongHieu(SimpleResponse.builder()
                        .id(entity.getThuongHieu().getId())
                        .ten(entity.getThuongHieu().getTen())
                        .build())

                .xuatXu(SimpleResponse.builder()
                        .id(entity.getXuatXu().getId())
                        .ten(entity.getXuatXu().getTen())
                        .build())

                .coAo(SimpleResponse.builder()
                        .id(entity.getCoAo().getId())
                        .ten(entity.getCoAo().getTen())
                        .build())

                .tayAo(SimpleResponse.builder()
                        .id(entity.getTayAo().getId())
                        .ten(entity.getTayAo().getTen())
                        .build())

                .daXoa(entity.getDaXoa())
                .taoLuc(entity.getTaoLuc())
                .capNhatLuc(entity.getCapNhatLuc())
                .taoBoi(entity.getTaoBoi())
                .capNhatBoi(entity.getCapNhatBoi())
                .build();
    }
}
