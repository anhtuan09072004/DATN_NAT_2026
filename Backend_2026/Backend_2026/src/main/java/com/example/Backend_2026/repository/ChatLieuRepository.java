package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.ChatLieu;
import com.example.Backend_2026.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Long> {
    boolean existsByTen(String ten);
    boolean existsByTenAndIdNot(String ten, Long id);
}
