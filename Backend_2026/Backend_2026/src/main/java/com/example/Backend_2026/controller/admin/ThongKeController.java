package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.ThongKeRequest;
import com.example.Backend_2026.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thong-ke")
@RequiredArgsConstructor
public class ThongKeController {
    private final ThongKeService service;

    // ===== hôm nay =====
    @GetMapping("/today")
    public Object homNay() {
        return service.homNay();
    }

    // ===== theo ngày =====
    @PostMapping("/range")
    public Object theoKhoang(@RequestBody ThongKeRequest request) {
        return service.theoKhoang(request);
    }

    // ===== top bán chạy =====
    @GetMapping("/top-ban-chay")
    public Object topBanChay() {
        return service.top5BanChay();
    }

    // ===== top sắp hết =====
    @GetMapping("/top-sap-het")
    public Object topSapHet() {
        return service.top5SapHet();
    }
}
