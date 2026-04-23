package com.example.Backend_2026.controller.client;

import com.example.Backend_2026.infrastructure.request.TaiKhoanClientRequest;
import com.example.Backend_2026.infrastructure.response.TaiKhoanClientResponse;
import com.example.Backend_2026.service.TaiKhoanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/auth")
@CrossOrigin("*")
public class TaiKhoanClientController {
    @Autowired
    private TaiKhoanClientService service;

    @PostMapping("/login")
    public TaiKhoanClientResponse login(@RequestBody TaiKhoanClientRequest request) {
        return service.login(request);
    }
}
