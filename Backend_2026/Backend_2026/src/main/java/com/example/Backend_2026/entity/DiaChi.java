package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi")
public class DiaChi extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    @JsonIgnore
    private TaiKhoan taiKhoan;
    @Nationalized
    @Column(name = "ten")
    private String ten;
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;
    @Nationalized
    @Column(name = "dia_chi_cu_the")
    private String diaChiCuThe;
    @Nationalized
    @Column(name = "phuong_xa")
    private String phuongXa;
    @Nationalized
    @Column(name = "quan_huyen")
    private String quanHuyen;
    @Nationalized
    @Column(name = "tinh_thanh")
    private String tinhThanhPho;
    @Column(name = "mac_dinh")
    private Boolean diaChiMacDinh;
}
