package com.example.Backend_2026.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CartResponse {
    private List<GioHangChiTietResponse> items;
    private BigDecimal total;
}
