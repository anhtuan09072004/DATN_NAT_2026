package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "khuyen_mai")
public class KhuyenMai extends PrimaryEnity {
    @Column(name = "ten")
    private String ten;
    @Column(name = "ma")
    private String ma;
    @Column(name = "gia_tri")
    private Float gia_tri;
    @Column(name = "trang_thai")
    private Integer trang_thai;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime startDate;
    @Column(name = "ngay_ket_thuc")
    private LocalDateTime endDate;
}
