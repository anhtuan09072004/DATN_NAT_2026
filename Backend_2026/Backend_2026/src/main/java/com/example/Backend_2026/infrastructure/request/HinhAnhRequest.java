package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HinhAnhRequest {
    @NotNull(message = "Thiếu ID sản phẩm chi tiết")
    private Long sanPhamChiTietId;

    @NotBlank(message = "Tên ảnh không được trống")
    private String ten;
}
