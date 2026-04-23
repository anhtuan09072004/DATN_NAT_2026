package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mau_sac")
public class MauSac extends PrimaryEnity {

    @Nationalized
    @Column(name = "ten")
    @NotNull(message = "Tên không được bỏ trống")
    private String ten;
}
