package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SanPhamChiTietResponse {
    private Long id;
    private List<HinhAnhResponse> hinhAnhs;
    private SimpleResponse sanPham;
    private SimpleResponse kichCo;
    private SimpleResponse mauSac;

    private BigDecimal gia;
    private Integer soLuong;
    private String ma;

    private Boolean daXoa;

    private LocalDateTime taoLuc;
    private LocalDateTime capNhatLuc;

    private String taoBoi;
    private String capNhatBoi;


}
