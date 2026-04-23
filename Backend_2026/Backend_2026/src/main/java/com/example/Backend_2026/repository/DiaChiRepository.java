package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaChiRepository extends JpaRepository<DiaChi, Long> {
    List<DiaChi> findByTaiKhoanIdAndDaXoaFalse(Long taiKhoanId);

    Optional<DiaChi> findByIdAndDaXoaFalse(Long id);
}
