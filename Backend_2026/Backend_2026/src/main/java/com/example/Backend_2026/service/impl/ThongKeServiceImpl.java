package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.infrastructure.request.ThongKeRequest;
import com.example.Backend_2026.infrastructure.response.ThongKeResponse;
import com.example.Backend_2026.infrastructure.response.TonKhoResponse;
import com.example.Backend_2026.infrastructure.response.TopSanPhamResponse;
import com.example.Backend_2026.repository.ThongKeRepository;
import com.example.Backend_2026.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThongKeServiceImpl implements ThongKeService {
    private final ThongKeRepository repo;

    private ThongKeResponse build(LocalDateTime start, LocalDateTime end) {

        BigDecimal doanhThu = repo.getDoanhThu(start, end);
        Long tongDon = repo.countDon(start, end);

        return ThongKeResponse.builder()
                .doanhThu(doanhThu)
                .tongDon(tongDon)
                .tongSanPham(0L)
                .build();
    }

    @Override
    public ThongKeResponse homNay() {
        LocalDate today = LocalDate.now();

        return build(
                today.atStartOfDay(),
                today.atTime(23, 59, 59)
        );
    }

    @Override
    public ThongKeResponse theoKhoang(ThongKeRequest request) {

        return build(
                request.getFromDate().atStartOfDay(),
                request.getToDate().atTime(23, 59, 59)
        );
    }

    @Override
    public List<TopSanPhamResponse> top5BanChay() {
        return repo.topBanChay(PageRequest.of(0, 5));
    }

    @Override
    public List<TonKhoResponse> top5SapHet() {
        return repo.topSapHet(PageRequest.of(0, 5));
    }
}
