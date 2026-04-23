package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> findByTaiKhoanId(Long taiKhoanId);
}
