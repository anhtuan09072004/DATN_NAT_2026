package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "khuyen_mai_chi_tiet")
public class KhuyenMaiChiTiet extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private SanPhamChiTiet sanPhamChiTiet;
    @ManyToOne
    @JoinColumn(name = "khuyen_mai_id")
    private KhuyenMai khuyenMai;
    @Column(name = "gia_khuyen_mai")
    private BigDecimal giaKhuyenMai;
}
