package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.entity.HoaDonChiTiet;
import com.example.Backend_2026.infrastructure.converter.HoaDonChiTietConverter;
import com.example.Backend_2026.infrastructure.request.HoaDonChiTietRequest;
import com.example.Backend_2026.infrastructure.response.HoaDonChiTietResponse;
import com.example.Backend_2026.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietService service;

    @Autowired
    private HoaDonChiTietConverter converter;

    // thêm sản phẩm
    @PostMapping
    public HoaDonChiTietResponse add(@RequestBody HoaDonChiTietRequest req) {
        return converter.toResponse(
                service.addProduct(req.getHoaDonId(), req.getChiTietSanPhamId(), req.getSoLuong())
        );
    }

    // giỏ hàng
    @GetMapping("/hoa-don/{hoaDonId}")
    public List<HoaDonChiTietResponse> get(@PathVariable Long hoaDonId) {
        return service.getByHoaDon(hoaDonId)
                .stream()
                .map(converter::toResponse)
                .toList();
    }

    //  xóa 1 sản phẩm
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeItem(id);
    }

    // giảm
    @PutMapping("/giam/{id}")
    public void giam(@PathVariable Long id) {
        service.decreaseQuantity(id);
    }

    //  tăng
    @PutMapping("/tang/{id}")
    public void tang(@PathVariable Long id) {
        service.increaseQuantity(id);
    }

    //  update số lượng
    @PutMapping("/{id}/so-luong")
    public void updateSoLuong(@PathVariable Long id, @RequestParam Integer soLuong) {
        service.updateQuantity(id, soLuong);
    }

     // clear cart
    @DeleteMapping("/hoa-don/{hoaDonId}")
    public void clear(@PathVariable Long hoaDonId) {
        service.clearCart(hoaDonId);
    }

}
