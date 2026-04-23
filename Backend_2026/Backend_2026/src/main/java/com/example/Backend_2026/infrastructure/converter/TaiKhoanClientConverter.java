package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.response.TaiKhoanClientResponse;
import org.springframework.stereotype.Component;

@Component
public class TaiKhoanClientConverter {
    public TaiKhoanClientResponse toResponse(TaiKhoan tk) {
        return TaiKhoanClientResponse.builder()
                .id(tk.getId())
                .username(tk.getUsername())
                .ten(tk.getTen())
                .email(tk.getEmail())
                .soDienThoai(tk.getSoDienThoai())
                .build();
    }
}
