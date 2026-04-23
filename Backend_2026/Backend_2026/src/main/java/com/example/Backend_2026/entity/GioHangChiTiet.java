package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet extends PrimaryEnity {
    @Column(name = "so_luong")
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;
    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private SanPhamChiTiet sanPhamChiTiet;
}
