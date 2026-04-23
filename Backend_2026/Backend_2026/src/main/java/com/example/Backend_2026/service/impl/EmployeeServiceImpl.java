package com.example.Backend_2026.service.impl;


import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.entity.VaiTro;
import com.example.Backend_2026.infrastructure.converter.EmployeeConverter;
import com.example.Backend_2026.infrastructure.request.CreateEmployeeRequest;
import com.example.Backend_2026.infrastructure.response.EmployeeResponse;
import com.example.Backend_2026.repository.TaiKhoanRepository;
import com.example.Backend_2026.repository.VaiTroRepository;
import com.example.Backend_2026.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final VaiTroRepository vaiTroRepository;
    private final EmployeeConverter converter;

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {

        if (taiKhoanRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }

        VaiTro role = vaiTroRepository.findByTen("ADMIN")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy role ADMIN"));

        TaiKhoan entity = converter.toEntity(request, role);

        return converter.toResponse(taiKhoanRepository.save(entity));
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return taiKhoanRepository.findAll()
                .stream()
                .filter(t -> t.getVaiTro().getTen().equals("ADMIN") && !t.getDaXoa())
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TaiKhoan entity = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        entity.setDaXoa(true);
        taiKhoanRepository.save(entity);
    }

    @Override
    public EmployeeResponse update(Long id, CreateEmployeeRequest request) {

        TaiKhoan entity = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        // 🔥 cập nhật thông tin
        entity.setTen(request.getTen());
        entity.setEmail(request.getEmail());
        entity.setSoDienThoai(request.getSoDienThoai());
        entity.setGioiTinh(request.getGioiTinh());

        // ❌ KHÔNG cho sửa username (tránh lỗi hệ thống)
        // entity.setUsername(request.getUsername());

        // 🔥 xử lý password (QUAN TRỌNG)
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            entity.setPassword(request.getPassword());
        }

        return converter.toResponse(taiKhoanRepository.save(entity));
    }
}
