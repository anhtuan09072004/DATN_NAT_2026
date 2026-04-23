package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VoucherResponse {
    private Long id;
    private String ma;
    private String ten;
    private Integer soLuong;
    private BigDecimal phanTramGiam;
    private BigDecimal giaTriToiDa;
    private BigDecimal giaTriToiThieu;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private Integer trangThai;

    private LocalDateTime taoLuc;
    private LocalDateTime capNhatLuc;
}
