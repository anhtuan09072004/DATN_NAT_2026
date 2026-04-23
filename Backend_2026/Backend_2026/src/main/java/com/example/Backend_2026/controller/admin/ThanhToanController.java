package com.example.Backend_2026.controller.admin;


import com.example.Backend_2026.infrastructure.request.ThanhToanRequest;
import com.example.Backend_2026.infrastructure.response.HoaDonResponse;
import com.example.Backend_2026.service.ThanhToanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thanh-toan")
@RequiredArgsConstructor
public class ThanhToanController {
    private final ThanhToanService service;

    @PostMapping
    public HoaDonResponse thanhToan(@RequestBody ThanhToanRequest request) {
        return service.thanhToan(request);
    }
}
