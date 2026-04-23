package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.CoAo;
import com.example.Backend_2026.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Long> {

//    boolean existsByTen(String ten);
//    boolean existsByTenAndIdNot(String ten, Long id);

}
