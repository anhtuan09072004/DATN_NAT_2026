package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.GioHangChiTiet;
import com.example.Backend_2026.infrastructure.response.GioHangChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    List<GioHangChiTiet> findByGioHangId(Long gioHangId);

    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(
            Long gioHangId,
            Long sanPhamChiTietId
    );
}
