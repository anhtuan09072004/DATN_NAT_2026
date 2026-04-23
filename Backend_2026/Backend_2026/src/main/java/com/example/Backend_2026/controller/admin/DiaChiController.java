package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.DiaChiRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;
import com.example.Backend_2026.service.DiaChiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dia-chi")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DiaChiController {
    private final DiaChiService diaChiService;

    @GetMapping("/tai-khoan/{id}")
    public List<DiaChiResponse> getByTaiKhoan(@PathVariable Long id) {
        return diaChiService.getByTaiKhoan(id);
    }

    @PostMapping
    public DiaChiResponse create(@RequestBody DiaChiRequest request) {
        return diaChiService.create(request);
    }

    @PutMapping("/{id}")
    public DiaChiResponse update(@PathVariable Long id,
                                 @RequestBody DiaChiRequest request) {
        return diaChiService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        diaChiService.delete(id);
    }
}
