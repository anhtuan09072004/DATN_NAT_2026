package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VaiTroRequest {
    @NotBlank(message = "Tên màu sắc không được để trống")
    @Size(min = 2, max = 50, message = "Tên màu sắc phải từ 2 đến 50 ký tự")
    private String ten;
}
