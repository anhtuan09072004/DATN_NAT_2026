package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
    List<HinhAnh> findBySanPhamChiTietIdAndDaXoaFalse(Long sanPhamChiTietId);

    Optional<HinhAnh> findByIdAndDaXoaFalse(Long id);

    void deleteBySanPhamChiTietId(Long sanPhamChiTietId);
}
