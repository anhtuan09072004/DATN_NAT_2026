package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "thong_bao")
public class ThongBao extends PrimaryEnity {
    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

    @Nationalized
    @Column(name = "tieu_de")
    private String tieuDe;

    @Nationalized
    @Column(name = "noi_dung")
    private String content;

    @Column(name = "loai")
    private Integer loai;


    @Nationalized
    @Column(name = "hoat_dong")
    private String hoatDong;
}
