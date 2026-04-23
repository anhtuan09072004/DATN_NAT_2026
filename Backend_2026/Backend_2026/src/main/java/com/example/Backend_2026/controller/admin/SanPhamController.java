package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.SanPhamRequest;
import com.example.Backend_2026.service.CoAoService;
import com.example.Backend_2026.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/san-pham", produces = "application/json")
@RequiredArgsConstructor
public class SanPhamController {
    private final SanPhamService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SanPhamRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody SanPhamRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}
