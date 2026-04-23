package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    boolean existsByTen(String ten);
    boolean existsByTenAndIdNot(String ten, Long id);

    List<SanPham> findTop8ByDaXoaFalseOrderByTaoLucDesc();

}
