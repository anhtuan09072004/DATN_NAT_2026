//package com.example.Backend_2026.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
//
//@Entity
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "hoa_don_chi_tiet")
//public class HoaDonChiTiet {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "so_luong")
//    private Integer soLuong;
//    private BigDecimal gia;
//
//    @ManyToOne
//    @JoinColumn(name = "hoa_don_id")
//    private HoaDon hoaDon;
//
//    @ManyToOne
//    @JoinColumn(name = "san_pham_chi_tiet_id")
//    private SanPhamChiTiet sanPhamChiTiet;
//}


package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private SanPhamChiTiet chiTietSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia")
    private BigDecimal gia;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "phan_tram_giam")
    private Float phanTramGiam; // Discount percentage at the time of adding (nullable)

    @Column(name = "gia_tri_giam")
    private BigDecimal giaTriGiam;

}
