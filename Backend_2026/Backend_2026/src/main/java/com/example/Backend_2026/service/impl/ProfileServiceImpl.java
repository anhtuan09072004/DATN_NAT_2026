package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.converter.ProfileConverter;
import com.example.Backend_2026.infrastructure.request.ProfileRequest;
import com.example.Backend_2026.infrastructure.response.ProfileResponse;
import com.example.Backend_2026.repository.TaiKhoanRepository;
import com.example.Backend_2026.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final ProfileConverter converter;

    @Override
    public ProfileResponse getMyProfile(Long userId) {
        TaiKhoan user = taiKhoanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        return converter.toResponse(user);
    }

    @Override
    public ProfileResponse updateMyProfile(Long userId, ProfileRequest request) {
        TaiKhoan user = taiKhoanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        // 🔥 Check email trùng
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            taiKhoanRepository.findByEmail(request.getEmail())
                    .filter(u -> !u.getId().equals(user.getId()))
                    .ifPresent(u -> {
                        throw new RuntimeException("Email đã tồn tại");
                    });
        }

        // update data
        converter.updateEntity(user, request);

        return converter.toResponse(taiKhoanRepository.save(user));
    }
}