package com.example.Backend_2026.controller.admin;
import com.example.Backend_2026.infrastructure.request.HinhAnhRequest;
import com.example.Backend_2026.infrastructure.request.MauSacRequest;
import com.example.Backend_2026.service.HinhAnhService;
import com.example.Backend_2026.service.MauSacService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/hinh-anh", produces = "application/json")
@RequiredArgsConstructor
public class HinhAnhController {
    private final HinhAnhService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody HinhAnhRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/san-pham-chi-tiet/{id}")
    public ResponseEntity<?> getBySPCT(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBySanPhamChiTiet(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("spctId") Long spctId,
            @RequestParam(value = "isMain", defaultValue = "false") Boolean isMain
    ) {
        try {
            return ResponseEntity.ok(service.upload(file, spctId, isMain));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload file thất bại");
        }
    }
}
