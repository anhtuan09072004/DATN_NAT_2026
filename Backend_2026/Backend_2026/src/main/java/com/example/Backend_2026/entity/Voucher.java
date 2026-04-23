package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "voucher")
public class Voucher extends PrimaryEnity {
    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "phan_tram_giam")
    private BigDecimal  phanTramGiam;

    @Column(name = "gia_tri_toi_da")
    private BigDecimal giaTriToiDa;

    @Column(name = "gia_tri_toi_thieu")
    private BigDecimal giaTriToiThieu;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
