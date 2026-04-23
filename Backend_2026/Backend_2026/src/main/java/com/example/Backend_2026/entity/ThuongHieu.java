package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thuong_hieu")
public class ThuongHieu extends PrimaryEnity {
    @Nationalized
    @Column(name = "ten")
    @NotNull(message = "Tên không được bỏ trống")
    private String ten;
}
