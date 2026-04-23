package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class SanPhamChiTietRequest {
    @NotNull(message = "Sản phẩm không được để trống")
    private Long sanPhamId;

    @NotNull(message = "Kích cỡ không được để trống")
    private Long kichCoId;

    @NotNull(message = "Màu sắc không được để trống")
    private Long mauSacId;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải > 0")
    private BigDecimal gia;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng >= 0")
    private Integer soLuong;

    @NotBlank(message = "Mã không được để trống")
    @Size(max = 50, message = "Mã tối đa 50 ký tự")
    private String ma;

    private List<String> hinhAnh;
}
