package com.example.Backend_2026.controller.client;
import com.example.Backend_2026.infrastructure.response.SanPhamResponse;
import com.example.Backend_2026.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client/san-pham")
@RequiredArgsConstructor
@CrossOrigin("*") // cho React gọi
public class SanPhamClientController {
    private final SanPhamService sanPhamService;

    // 🔥 API lấy top 8 sản phẩm
    @GetMapping("/top8")
    public List<SanPhamResponse> getTop8() {
        return sanPhamService.getTop8();
    }
}
