package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.converter.TaiKhoanClientConverter;
import com.example.Backend_2026.infrastructure.request.TaiKhoanClientRequest;
import com.example.Backend_2026.infrastructure.response.TaiKhoanClientResponse;
import com.example.Backend_2026.repository.TaiKhoanClientRepository;
import com.example.Backend_2026.service.TaiKhoanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanClientServiceImpl implements TaiKhoanClientService {
    @Autowired
    private TaiKhoanClientRepository repository;

    @Autowired
    private TaiKhoanClientConverter converter;

    @Override
    public TaiKhoanClientResponse login(TaiKhoanClientRequest request) {

        TaiKhoan tk = repository
                .findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(() -> new RuntimeException("Sai tài khoản hoặc mật khẩu"));

        return converter.toResponse(tk);
    }
}
