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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kich_co")
public class KichCo extends PrimaryEnity {
    @Nationalized
    @Column(name = "ten")
    @NotNull(message = "Tên không được bỏ trống")
    private String ten;
}
