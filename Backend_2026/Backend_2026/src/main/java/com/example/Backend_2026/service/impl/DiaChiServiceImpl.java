package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.DiaChi;
import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.infrastructure.converter.DiaChiConverter;
import com.example.Backend_2026.infrastructure.request.DiaChiRequest;
import com.example.Backend_2026.infrastructure.response.DiaChiResponse;
import com.example.Backend_2026.repository.DiaChiRepository;
import com.example.Backend_2026.repository.TaiKhoanRepository;
import com.example.Backend_2026.service.DiaChiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaChiServiceImpl implements DiaChiService {

    private final DiaChiRepository diaChiRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final DiaChiConverter converter;

    @Override
    public List<DiaChiResponse> getByTaiKhoan(Long taiKhoanId) {
        return diaChiRepository.findByTaiKhoanIdAndDaXoaFalse(taiKhoanId)
                .stream()
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public DiaChiResponse create(DiaChiRequest request) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(request.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        DiaChi entity = new DiaChi();
        converter.toEntity(entity, request, taiKhoan);

        return converter.toResponse(diaChiRepository.save(entity));
    }

    @Override
    public DiaChiResponse update(Long id, DiaChiRequest request) {
        DiaChi entity = diaChiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        TaiKhoan taiKhoan = taiKhoanRepository.findById(request.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        converter.toEntity(entity, request, taiKhoan);

        return converter.toResponse(diaChiRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        DiaChi entity = diaChiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        entity.setDaXoa(true);
        diaChiRepository.save(entity);
    }
}
