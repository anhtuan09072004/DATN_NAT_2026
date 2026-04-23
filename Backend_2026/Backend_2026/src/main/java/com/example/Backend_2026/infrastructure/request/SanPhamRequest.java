package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SanPhamRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 255, message = "Tên sản phẩm tối đa 255 ký tự")
    private String ten;

    @NotBlank(message = "Mã sản phẩm không được để trống")
    @Size(max = 50, message = "Mã sản phẩm tối đa 50 ký tự")
    private String ma;

    @Size(max = 255, message = "Mô tả tối đa 255 ký tự")
    private String moTa;

    @NotNull(message = "Chất liệu không được để trống")
    private Long chatLieuId;

    @NotNull(message = "Thương hiệu không được để trống")
    private Long thuongHieuId;

    @NotNull(message = "Xuất xứ không được để trống")
    private Long xuatXuId;

    @NotNull(message = "Cổ áo không được để trống")
    private Long coAoId;

    @NotNull(message = "Tay áo không được để trống")
    private Long tayAoId;
}
