package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class VoucherRequest {
    @NotBlank(message = "Mã không được để trống")
    @Size(max = 50, message = "Mã tối đa 50 ký tự")
    private String ma;

    @NotBlank(message = "Tên Voucher không được để trống")
    @Size(min = 1, max = 50, message = "Tên voucher phải từ 2 đến 50 ký tự")
    private String ten;


    @NotNull(message = "Số lượng không được null")
    @Min(value = 1, message = "Số lượng phải > 0")
    private Integer soLuong;

    @NotNull(message = "Phần trăm giảm không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phải > 0")
    @DecimalMax(value = "100.0", message = "Không được > 100%")
    private BigDecimal phanTramGiam;

    @NotNull(message = "Giá trị tối đa không được null")
    @DecimalMin(value = "0.0", message = "Phải >= 0")
    private BigDecimal giaTriToiDa;

    @NotNull(message = "Giá trị tối thiểu không được null")
    @DecimalMin(value = "0.0", message = "Phải >= 0")
    private BigDecimal giaTriToiThieu;

    @NotNull(message = "Ngày bắt đầu không được null")
    private LocalDateTime ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được null")
    private LocalDateTime ngayKetThuc;

    @NotNull(message = "Trạng thái không được null")
    private Integer trangThai;

    private Long hoaDonId;
    private String maVoucher;
}
