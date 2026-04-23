package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface VaiTroRepository extends JpaRepository<VaiTro,Long> {
    boolean existsByTen(String ten);
    boolean existsByTenAndIdNot(String ten, Long id);
    Optional<VaiTro> findByTen(String ten);
}
