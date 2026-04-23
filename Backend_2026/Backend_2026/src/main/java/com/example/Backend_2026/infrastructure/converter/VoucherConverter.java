package com.example.Backend_2026.infrastructure.converter;


import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.entity.Voucher;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.request.VoucherRequest;
import com.example.Backend_2026.infrastructure.response.MauSacResponse;
import com.example.Backend_2026.infrastructure.response.VoucherResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class VoucherConverter {
    public Voucher toEntity(VoucherRequest request) {
        Voucher ms = new Voucher();
        ms.setMa(request.getMa());
        ms.setTen(request.getTen());
        ms.setSoLuong(request.getSoLuong());
        ms.setPhanTramGiam(request.getPhanTramGiam());
        ms.setGiaTriToiDa(request.getGiaTriToiDa());
        ms.setGiaTriToiThieu(request.getGiaTriToiThieu());
        ms.setNgayBatDau(request.getNgayBatDau());
        ms.setNgayKetThuc(request.getNgayKetThuc());
        ms.setTrangThai(request.getTrangThai());
        return ms;
    }

    public VoucherResponse toResponse(Voucher entity) {
        return VoucherResponse.builder()
                .id(entity.getId())
                .ma(entity.getMa())
                .ten(entity.getTen())
                .soLuong(entity.getSoLuong())
                .phanTramGiam(entity.getPhanTramGiam())
                .giaTriToiDa(entity.getGiaTriToiDa())
                .giaTriToiThieu(entity.getGiaTriToiThieu())
                .ngayBatDau(entity.getNgayBatDau())
                .ngayKetThuc(entity.getNgayKetThuc())
                .trangThai(entity.getTrangThai())
                .taoLuc(entity.getTaoLuc())
                .capNhatLuc(entity.getCapNhatLuc())
                .build();
    }
}
