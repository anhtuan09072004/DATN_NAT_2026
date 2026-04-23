package com.example.Backend_2026.repository;

import com.example.Backend_2026.entity.MauSac;
import com.example.Backend_2026.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    boolean existsByTen(String ten);
    boolean existsByTenAndIdNot(String ten, Long id);

    boolean existsByMa(String ma);
    boolean existsByMaAndIdNot(String ma, Long id);
    Optional<Voucher> findByMa(String ma);


}
