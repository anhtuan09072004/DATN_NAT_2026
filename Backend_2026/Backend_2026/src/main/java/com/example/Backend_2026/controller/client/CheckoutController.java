package com.example.Backend_2026.controller.client;

import com.example.Backend_2026.infrastructure.request.ThanhToanRequest;
import com.example.Backend_2026.infrastructure.response.HoaDonResponse;
import com.example.Backend_2026.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/checkout")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CheckoutController {
    private final CheckoutService checkoutService;

//     api checkout
    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody ThanhToanRequest request) {
        try {
            Long userId = request.getCustomerId(); // tạm thời lấy từ body

            HoaDonResponse response = checkoutService.checkout(userId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
