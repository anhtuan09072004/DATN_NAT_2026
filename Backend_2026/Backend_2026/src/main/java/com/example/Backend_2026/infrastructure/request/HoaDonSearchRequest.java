package com.example.Backend_2026.infrastructure.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HoaDonSearchRequest {
    private String ma;
    private Integer trangThai;
    private Integer kieuHoaDon;
    private String sdt;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
