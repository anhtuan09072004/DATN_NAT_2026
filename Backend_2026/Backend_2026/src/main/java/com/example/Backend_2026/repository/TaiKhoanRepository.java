package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {

    Optional<TaiKhoan> findByUsername(String username);

    Optional<TaiKhoan> findByEmail(String email);

    List<TaiKhoan> findByVaiTro_IdAndDaXoa(Long roleId, Boolean daXoa);

}
