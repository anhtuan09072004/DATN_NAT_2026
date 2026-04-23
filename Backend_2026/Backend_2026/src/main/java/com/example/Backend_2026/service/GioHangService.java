package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.AddToCartRequest;
import com.example.Backend_2026.infrastructure.response.CartResponse;
import com.example.Backend_2026.infrastructure.response.GioHangChiTietResponse;

import java.util.List;

public interface GioHangService {
    void addToCart(AddToCartRequest request);

    CartResponse getCart(Long taiKhoanId);

    void updateQuantity(Long ghctId, Integer soLuong);

    void deleteItem(Long ghctId);
}
