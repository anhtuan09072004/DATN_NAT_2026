package com.example.Backend_2026.entity;

import com.example.Backend_2026.entity.Base.PrimaryEnity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_lieu")
public class ChatLieu extends PrimaryEnity {
    @Nationalized
    @Column(name = "ten")
    @NotNull(message = "Tên không được bỏ trống")
    private String ten;
}

