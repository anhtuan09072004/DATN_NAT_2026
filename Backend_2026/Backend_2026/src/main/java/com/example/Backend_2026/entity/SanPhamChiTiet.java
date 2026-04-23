package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chi_tiet_san_Pham")
public class SanPhamChiTiet extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "kich_co_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private KichCo kichCo;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private MauSac mauSac;

    @Column(name = "gia")
    private BigDecimal gia;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ma")
    private String ma;

    @OneToMany(mappedBy = "sanPhamChiTiet")
    private List<HinhAnh> hinhAnh;

}
