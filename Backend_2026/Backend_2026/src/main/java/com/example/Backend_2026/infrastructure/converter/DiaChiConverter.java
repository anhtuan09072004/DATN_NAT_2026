package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.DiaChi;
import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.request.DiaChiClientRequest;
import com.example.Backend_2026.infrastructure.request.DiaChiRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;
import org.springframework.stereotype.Component;

@Component
public class DiaChiConverter {
    public DiaChiResponse toResponse(DiaChi entity) {
        return DiaChiResponse.builder()
                .id(entity.getId())
                .taiKhoanId(entity.getTaiKhoan().getId())
                .ten(entity.getTen())
                .soDienThoai(entity.getSoDienThoai())
                .diaChiCuThe(entity.getDiaChiCuThe())
                .phuongXa(entity.getPhuongXa())
                .quanHuyen(entity.getQuanHuyen())
                .tinhThanh(entity.getTinhThanhPho())
                .macDinh(entity.getDiaChiMacDinh())
                .build();
    }

    public void toEntity(DiaChi entity, DiaChiRequest request, TaiKhoan taiKhoan) {
        entity.setTaiKhoan(taiKhoan);
        entity.setTen(request.getTen());
        entity.setSoDienThoai(request.getSoDienThoai());
        entity.setDiaChiCuThe(request.getDiaChiCuThe());
        entity.setPhuongXa(request.getPhuongXa());
        entity.setQuanHuyen(request.getQuanHuyen());
        entity.setTinhThanhPho(request.getTinhThanh());
        entity.setDiaChiMacDinh(request.getMacDinh());
    }

    public void toEntityClient(DiaChi entity, DiaChiClientRequest request, TaiKhoan taiKhoan) {
        entity.setTen(request.getTen());
        entity.setSoDienThoai(request.getSoDienThoai());
        entity.setDiaChiCuThe(request.getDiaChiCuThe());
        entity.setPhuongXa(request.getPhuongXa());
        entity.setQuanHuyen(request.getQuanHuyen());
        entity.setTinhThanhPho(request.getTinhThanh());
        entity.setDiaChiMacDinh(request.getMacDinh() != null ? request.getMacDinh() : false);
        entity.setTaiKhoan(taiKhoan);
    }
}
