package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.TaiKhoan;
import com.example.Backend_2026.entity.VaiTro;
import com.example.Backend_2026.infrastructure.converter.CoAoConverter;
import com.example.Backend_2026.infrastructure.converter.CustomerConverter;
import com.example.Backend_2026.infrastructure.request.CoAoRequest;
import com.example.Backend_2026.infrastructure.request.CreateCustomerRequest;
import com.example.Backend_2026.infrastructure.request.UpdateCustomerRequest;
import com.example.Backend_2026.infrastructure.response.CoAoResponse;
import com.example.Backend_2026.infrastructure.response.CustomerResponse;
import com.example.Backend_2026.repository.CoAoRepository;
import com.example.Backend_2026.repository.TaiKhoanRepository;
import com.example.Backend_2026.repository.VaiTroRepository;
import com.example.Backend_2026.service.CoAoService;
import com.example.Backend_2026.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final VaiTroRepository vaiTroRepository;
    private final CustomerConverter converter;

    private static final Long CUSTOMER_ROLE_ID = 2L; // chỉnh theo DB của bạn

    @Override
    public CustomerResponse create(CreateCustomerRequest request) {

        // 🔥 validate business
        if (taiKhoanRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }

        if (taiKhoanRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        VaiTro role = vaiTroRepository.findById(CUSTOMER_ROLE_ID)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy role"));

        TaiKhoan entity = converter.toEntity(request, role);

        return converter.toResponse(taiKhoanRepository.save(entity));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return taiKhoanRepository.findByVaiTro_IdAndDaXoa(CUSTOMER_ROLE_ID, false)
                .stream()
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse update(Long id, UpdateCustomerRequest request) {

        TaiKhoan entity = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        entity.setTen(request.getTen());
        entity.setEmail(request.getEmail());
        entity.setSoDienThoai(request.getSoDienThoai());
        entity.setGioiTinh(request.getGioiTinh());
        entity.setNgaySinh(request.getNgaySinh());

        return converter.toResponse(taiKhoanRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        TaiKhoan entity = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        entity.setDaXoa(true);
        taiKhoanRepository.save(entity);
    }
}
