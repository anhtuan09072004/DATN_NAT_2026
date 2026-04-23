package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequest {
    @NotNull(message = "Chi tiết sản phẩm không được null")
    private Long chiTietSanPhamId;

    private Long taiKhoanId;

    @NotNull(message = "Số lượng không được null")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer soLuong;
}
