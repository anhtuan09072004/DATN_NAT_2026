package com.example.Backend_2026.service;

import com.example.Backend_2026.entity.Voucher;
import com.example.Backend_2026.infrastructure.request.VoucherRequest;
import com.example.Backend_2026.infrastructure.response.VoucherResponse;

import java.util.List;

public interface VoucherService {
    VoucherResponse cretae(VoucherRequest request);
    VoucherResponse update(Long id , VoucherRequest request);
    List<VoucherResponse> getAll();
    VoucherResponse getById(Long id);
    void delete(Long id);

    Voucher findById(Long id);
}
