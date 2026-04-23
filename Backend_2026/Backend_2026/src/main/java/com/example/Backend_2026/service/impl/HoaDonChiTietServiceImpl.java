package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.HoaDon;
import com.example.Backend_2026.entity.HoaDonChiTiet;
import com.example.Backend_2026.entity.SanPhamChiTiet;
import com.example.Backend_2026.repository.HoaDonChiTietRepository;
import com.example.Backend_2026.repository.HoaDonRepository;
import com.example.Backend_2026.repository.SanPhamChiTietRepository;
import com.example.Backend_2026.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository repo;

    @Autowired
    private HoaDonRepository hoaDonRepo;

    @Autowired
    private SanPhamChiTietRepository spctRepo;

    @Override
    public HoaDonChiTiet addProduct(Long hoaDonId, Long spctId, Integer soLuong) {

        if (soLuong == null || soLuong <= 0) {
            throw new RuntimeException("Số lượng phải > 0");
        }

        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        SanPhamChiTiet spct = spctRepo.findById(spctId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        int tonKho = spct.getSoLuong();

        // 👉 check đã tồn tại chưa
        Optional<HoaDonChiTiet> existing =
                repo.findByHoaDonIdAndChiTietSanPhamId(hoaDonId, spctId);

        if (existing.isPresent()) {
            HoaDonChiTiet item = existing.get();

            int newSoLuong = item.getSoLuong() + soLuong;

            // 🚨 check tồn kho
            if (newSoLuong > tonKho) {
                throw new RuntimeException("Vượt quá tồn kho");
            }

            item.setSoLuong(newSoLuong);
            return repo.save(item);
        }

        // 🚨 check tồn kho khi thêm mới
        if (soLuong > tonKho) {
            throw new RuntimeException("Không đủ hàng trong kho");
        }

        HoaDonChiTiet item = new HoaDonChiTiet();
        item.setHoaDon(hoaDon);
        item.setChiTietSanPham(spct);
        item.setSoLuong(soLuong);
        item.setGia(spct.getGia());

        return repo.save(item);
    }

    @Override
    public void decreaseQuantity(Long id) {
        HoaDonChiTiet hdct = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        if (hdct.getSoLuong() <= 1) {
            // 👉 nếu còn 1 thì xóa luôn
            repo.delete(hdct);
        } else {
            hdct.setSoLuong(hdct.getSoLuong() - 1);
            repo.save(hdct);
        }
    }

    @Override
    public void removeItem(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<HoaDonChiTiet> getByHoaDon(Long hoaDonId) {
        return repo.findByHoaDonId(hoaDonId);
    }

    @Override
    public void increaseQuantity(Long id) {

        HoaDonChiTiet hdct = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ"));

        // 👉 check tồn kho (rất quan trọng)
        int tonKho = hdct.getChiTietSanPham().getSoLuong();

        if (hdct.getSoLuong() >= tonKho) {
            throw new RuntimeException("Số lượng vượt quá tồn kho");
        }

        // 👉 tăng số lượng
        hdct.setSoLuong(hdct.getSoLuong() + 1);

        repo.save(hdct);
    }


    @Override
    public void updateQuantity(Long id, Integer soLuong) {

        HoaDonChiTiet hdct = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        int tonKho = hdct.getChiTietSanPham().getSoLuong();

        if (soLuong > tonKho) {
            throw new RuntimeException("Vượt tồn kho");
        }

        if (soLuong <= 0) {
            repo.delete(hdct);
        } else {
            hdct.setSoLuong(soLuong);
            repo.save(hdct);
        }
    }

    @Override
    public void clearCart(Long hoaDonId) {
        repo.deleteByHoaDonId(hoaDonId);
    }
}


