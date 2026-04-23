package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.HoaDon;
import com.example.Backend_2026.entity.HoaDonChiTiet;
import com.example.Backend_2026.infrastructure.response.HoaDonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ThanhToanConverter {
    private final HoaDonConverter hoaDonConverter;

    public HoaDonResponse toResponse(HoaDon hd, List<HoaDonChiTiet> list) {
        return hoaDonConverter.toResponse(hd, list);
    }
}
