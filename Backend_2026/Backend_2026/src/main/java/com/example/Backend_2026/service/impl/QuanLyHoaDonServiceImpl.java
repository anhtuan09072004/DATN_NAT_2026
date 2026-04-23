package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.HoaDon;
import com.example.Backend_2026.infrastructure.converter.QuanLyHoaDonConverter;
import com.example.Backend_2026.infrastructure.request.QuanLyHoaDonRequest;
import com.example.Backend_2026.infrastructure.response.QuanLyHoaDonResponse;
import com.example.Backend_2026.repository.QuanLyHoaDonRepository;
import com.example.Backend_2026.service.QuanLyHoaDonService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class QuanLyHoaDonServiceImpl implements QuanLyHoaDonService {
    private final QuanLyHoaDonRepository repo;
    private final QuanLyHoaDonConverter converter;

    @Override
    public Page<QuanLyHoaDonResponse> getAll(QuanLyHoaDonRequest request) {

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize()
        );

        return repo.findAll((root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();

                    if (request.getMa() != null) {
                        predicates.add(cb.like(root.get("ma"), "%" + request.getMa() + "%"));
                    }

                    if (request.getTrangThai() != null) {
                        predicates.add(cb.equal(root.get("trangThai"), request.getTrangThai()));
                    }

                    if (request.getKieuHoaDon() != null) {
                        predicates.add(cb.equal(root.get("kieuHoaDon"), request.getKieuHoaDon()));
                    }

                    if (request.getTuNgay() != null && request.getDenNgay() != null) {
                        predicates.add(cb.between(root.get("taoLuc"),
                                request.getTuNgay(), request.getDenNgay()));
                    }

                    return cb.and(predicates.toArray(new Predicate[0]));
                }, pageable) // 🔥 phải truyền pageable vào đây
                .map(hd -> converter.toResponse(hd, new ArrayList<>())); // 🔥 dùng map của Page
    }

    @Override
    public QuanLyHoaDonResponse getDetail(Long id) {
        HoaDon hd = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

        return converter.toResponse(hd, new ArrayList<>());
    }

    @Override
    public void updateTrangThai(Long id, Integer trangThai) {
        HoaDon hd = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        // 🔥 VALIDATE
        if (hd.getTrangThai() == 5) {
            throw new RuntimeException("Hóa đơn đã hoàn thành, không thể sửa");
        }

        hd.setTrangThai(trangThai);
        repo.save(hd);
    }

    @Override
    public void huyHoaDon(Long id) {
        HoaDon hd = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        if (hd.getTrangThai() == 5) {
            throw new RuntimeException("Không thể hủy hóa đơn đã thanh toán");
        }

        hd.setTrangThai(-1); // hủy
        repo.save(hd);
    }
}
