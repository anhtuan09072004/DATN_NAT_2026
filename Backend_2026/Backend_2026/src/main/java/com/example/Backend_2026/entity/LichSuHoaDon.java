package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "lich_su_hoa_don")
public class LichSuHoaDon extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;
    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
