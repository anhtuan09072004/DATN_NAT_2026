package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.entity.KichCo;
import com.example.Backend_2026.infrastructure.request.KichCoRequest;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.service.KichCoService;
import com.example.Backend_2026.service.MauSacService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/kich-co", produces = "application/json")
@RequiredArgsConstructor
public class KichCoController {
    private final KichCoService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody KichCoRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody KichCoRequest request) {
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
