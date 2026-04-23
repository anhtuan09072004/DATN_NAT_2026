package com.example.Backend_2026.controller.client;

import com.example.Backend_2026.infrastructure.request.DiaChiClientRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;
import com.example.Backend_2026.service.DiaChiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/address")
@RequiredArgsConstructor
public class DiaChiClientController {

    private final DiaChiClientService diaChiClientService;

    // ================= GET =================
    @GetMapping("/my-addresses")
    public List<DiaChiResponse> getMy(@RequestParam Long userId) {
        return diaChiClientService.getMyAddresses(userId);
    }

    // ================= CREATE =================
    @PostMapping
    public DiaChiResponse create(@RequestParam Long userId,
                                 @RequestBody DiaChiClientRequest request) {
        return diaChiClientService.create(request, userId);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public DiaChiResponse update(@PathVariable Long id,
                                 @RequestParam Long userId,
                                 @RequestBody DiaChiClientRequest request) {
        return diaChiClientService.update(id, request, userId);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestParam Long userId) {
        diaChiClientService.delete(id, userId);
    }
}