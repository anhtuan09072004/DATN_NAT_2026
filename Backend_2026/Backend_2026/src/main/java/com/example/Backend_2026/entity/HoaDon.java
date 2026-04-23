package com.example.Backend_2026.entity;

import com.example.Backend_2026.emuns.TrangThaiHoaDon;
import com.example.Backend_2026.emuns.HinhThucMua;
import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "hoa_don")
public class HoaDon extends PrimaryEnity {
    @Column(name = "ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa", "diaChi", "password", "vaiTro"})
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa", "diaChi", "password", "vaiTro"})
    private TaiKhoan khachHang;

    @ManyToOne
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "tien_giam")
    private BigDecimal tienGiam;

    @Column(name = "tien_ship")
    private BigDecimal tienShip;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "kieu_hoa_don")
    private Integer kieuHoaDon;

    @Nationalized
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;


    @Column(name = "sdt", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 20)
    private String email;

    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;


    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"hoaDon"})
    private List<HoaDonChiTiet> hoaDonChiTiets;

}