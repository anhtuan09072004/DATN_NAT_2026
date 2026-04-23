package com.example.Backend_2026.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ThongKeResponse {
    private BigDecimal doanhThu;
    private Long tongDon;
    private Long tongSanPham;
}
