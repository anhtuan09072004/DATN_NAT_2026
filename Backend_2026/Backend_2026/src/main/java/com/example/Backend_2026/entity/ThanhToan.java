package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.BaseEntity;
import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "thanh_toan")
public class ThanhToan extends PrimaryEnity{
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;

    @Column(name = "phuong_thuc")
    private Integer phuongThuc;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "ma_giao_dich")
    private String maGiaoDich;

    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
