package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.entity.TayAo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TayAoRepository extends JpaRepository<TayAo, Long> {
    boolean existsByTen(String ten);
    boolean existsByTenAndIdNot(String ten, Long id);
}
