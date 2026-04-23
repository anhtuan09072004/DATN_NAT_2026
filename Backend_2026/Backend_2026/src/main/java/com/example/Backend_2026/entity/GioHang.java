package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gio_hang")
public class GioHang extends PrimaryEnity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tai_khoan_id")
    @JsonIgnoreProperties(value = {"tao_luc", "cap_nhat_luc", "tao_boi", "cap_nhat_boi", "da_xoa"})
    private TaiKhoan taiKhoan;
}
