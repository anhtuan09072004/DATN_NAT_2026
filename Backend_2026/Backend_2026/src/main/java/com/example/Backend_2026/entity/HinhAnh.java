package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name = "hinh_anh")
public class HinhAnh extends PrimaryEnity {

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    @JsonBackReference
    private SanPhamChiTiet sanPhamChiTiet;
    @Nationalized
    @Column(name = "ten")
    private String ten;


}
