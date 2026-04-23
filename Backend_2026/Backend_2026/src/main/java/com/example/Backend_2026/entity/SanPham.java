package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "san_pham")
public class SanPham extends PrimaryEnity {
    @Nationalized
    @Column(name = "ten")
    private String ten;

    @Column(name = "ma")
    private String ma;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    @JsonIgnoreProperties(value  = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "xuat_xu_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private XuatXu xuatXu;


    @ManyToOne
    @JoinColumn(name = "co_ao_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private CoAo coAo;

    @ManyToOne
    @JoinColumn(name = "tay_ao_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private TayAo tayAo;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    List<SanPhamChiTiet> SanPhamChiTiet;

//    @JsonIgnoreProperties(value = {"sanPham", "tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
//    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
//    private List<HinhAnh> hinhAnh = new ArrayList<>(); // Khởi tạo danh sách mặc định
}
