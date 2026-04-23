package com.example.Backend_2026.infrastructure.converter;

import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.request.ProfileRequest;
import com.example.Backend_2026.infrastructure.response.ProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter {
    public ProfileResponse toResponse(TaiKhoan tk) {
        return ProfileResponse.builder()
                .id(tk.getId())
                .username(tk.getUsername())
                .ten(tk.getTen())
                .email(tk.getEmail())
                .soDienThoai(tk.getSoDienThoai())
                .avatar(tk.getAvatar())
                .gioiTinh(tk.getGioiTinh())
                .ngaySinh(tk.getNgaySinh())
                .build();
    }

    public void updateEntity(TaiKhoan tk, ProfileRequest req) {
        tk.setTen(req.getTen());
        tk.setEmail(req.getEmail());
        tk.setSoDienThoai(req.getSoDienThoai());
        tk.setGioiTinh(req.getGioiTinh());
        tk.setNgaySinh(req.getNgaySinh());
    }
}
