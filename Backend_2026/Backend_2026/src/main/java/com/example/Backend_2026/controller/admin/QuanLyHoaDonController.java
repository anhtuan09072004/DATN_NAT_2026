package com.example.Backend_2026.controller.admin;


import com.example.Backend_2026.infrastructure.request.QuanLyHoaDonRequest;
import com.example.Backend_2026.infrastructure.response.QuanLyHoaDonResponse;
import com.example.Backend_2026.service.QuanLyHoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-hoa-don")
@RequiredArgsConstructor
public class QuanLyHoaDonController {
    private final QuanLyHoaDonService service;

    @PostMapping("/search")
    public Page<QuanLyHoaDonResponse> search(@RequestBody QuanLyHoaDonRequest request) {
        return service.getAll(request);
    }

    @GetMapping("/{id}")
    public QuanLyHoaDonResponse detail(@PathVariable Long id) {
        return service.getDetail(id);
    }

    @PutMapping("/{id}/trang-thai")
    public void updateTrangThai(@PathVariable Long id,
                                @RequestParam Integer trangThai) {
        service.updateTrangThai(id, trangThai);
    }

    @DeleteMapping("/{id}")
    public void huy(@PathVariable Long id) {
        service.huyHoaDon(id);
    }
}
