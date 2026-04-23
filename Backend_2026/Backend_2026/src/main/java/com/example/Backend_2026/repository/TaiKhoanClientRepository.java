package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanClientRepository extends JpaRepository<TaiKhoan, Long> {
    Optional<TaiKhoan> findByUsernameAndPassword(String username, String password);

    Optional<TaiKhoan> findByUsername(String username);
}
