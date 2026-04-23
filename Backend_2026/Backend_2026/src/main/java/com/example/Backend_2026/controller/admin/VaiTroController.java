package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.infrastructure.request.VaiTroRequest;
import com.example.Backend_2026.service.MauSacService;
import com.example.Backend_2026.service.VaiTroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vai-tro", produces = "application/json")
@RequiredArgsConstructor
public class VaiTroController {
    private final VaiTroService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VaiTroRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody VaiTroRequest request) {
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
        service.deleteById(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}
