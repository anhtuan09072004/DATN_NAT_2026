package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KichCoRequest {
    @NotBlank(message = "Tên Size sắc không được để trống")
    @Size(min = 1, max = 50, message = "Tên Size sắc phải từ 2 đến 50 ký tự")
    private String ten;
}
