package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.DiaChi;
import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.converter.DiaChiConverter;
import com.example.Backend_2026.infrastructure.request.DiaChiClientRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;
import com.example.Backend_2026.repository.DiaChiRepository;
import com.example.Backend_2026.repository.TaiKhoanRepository;
import com.example.Backend_2026.service.DiaChiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaChiClientServiceImpl implements DiaChiClientService {
    private final DiaChiRepository diaChiRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final DiaChiConverter converter;
    @Override
    public List<DiaChiResponse> getMyAddresses(Long userId) {
        return diaChiRepository.findByTaiKhoanIdAndDaXoaFalse(userId)
                .stream()
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public DiaChiResponse create(DiaChiClientRequest request, Long userId) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        // 🔥 xử lý địa chỉ mặc định
        if (Boolean.TRUE.equals(request.getMacDinh())) {
            diaChiRepository.findByTaiKhoanIdAndDaXoaFalse(userId)
                    .forEach(dc -> dc.setDiaChiMacDinh(false));
        }

        DiaChi entity = new DiaChi();
        converter.toEntityClient(entity, request, taiKhoan);

        return converter.toResponse(diaChiRepository.save(entity));
    }

    @Override
    public DiaChiResponse update(Long id, DiaChiClientRequest request, Long userId) {
        DiaChi entity = diaChiRepository.findByIdAndDaXoaFalse(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        // 🔥 check quyền
        if (!entity.getTaiKhoan().getId().equals(userId)) {
            throw new RuntimeException("Không có quyền");
        }

        if (Boolean.TRUE.equals(request.getMacDinh())) {
            diaChiRepository.findByTaiKhoanIdAndDaXoaFalse(userId)
                    .forEach(dc -> dc.setDiaChiMacDinh(false));
        }

        converter.toEntityClient(entity, request, entity.getTaiKhoan());

        return converter.toResponse(diaChiRepository.save(entity));
    }

    @Override
    public void delete(Long id, Long userId) {
        DiaChi entity = diaChiRepository.findByIdAndDaXoaFalse(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        if (!entity.getTaiKhoan().getId().equals(userId)) {
            throw new RuntimeException("Không có quyền");
        }

        entity.setDaXoa(true);
        diaChiRepository.save(entity);
    }
}
