package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.TayAoRequest;
import com.example.Backend_2026.infrastructure.request.VoucherRequest;
import com.example.Backend_2026.service.TayAoService;
import com.example.Backend_2026.service.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/voucher", produces = "application/json")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VoucherRequest request) {
        return ResponseEntity.ok(service.cretae(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody VoucherRequest request) {
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
