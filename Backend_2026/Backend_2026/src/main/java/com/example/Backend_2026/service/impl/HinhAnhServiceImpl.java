package com.example.Backend_2026.service.impl;

import com.example.Backend_2026.entity.HinhAnh;
import com.example.Backend_2026.entity.SanPhamChiTiet;
import com.example.Backend_2026.infrastructure.converter.HinhAnhConverter;
import com.example.Backend_2026.infrastructure.request.HinhAnhRequest;
import com.example.Backend_2026.infrastructure.response.HinhAnhResponse;
import com.example.Backend_2026.repository.HinhAnhRepository;
import com.example.Backend_2026.repository.SanPhamChiTietRepository;
import com.example.Backend_2026.service.HinhAnhService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HinhAnhServiceImpl implements HinhAnhService {

    private final HinhAnhRepository repository;
    private final SanPhamChiTietRepository spctRepository;
    private final HinhAnhConverter converter;

    // ================= CREATE =================
    @Override
    public HinhAnhResponse create(HinhAnhRequest request) {

        SanPhamChiTiet spct = spctRepository.findById(request.getSanPhamChiTietId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));

        HinhAnh entity = converter.toEntity(request);
        entity.setSanPhamChiTiet(spct);
        entity.setDaXoa(false);

        return converter.toResponse(repository.save(entity));
    }

    // ================= GET BY SPCT =================
    @Override
    public List<HinhAnhResponse> getBySanPhamChiTiet(Long sanPhamChiTietId) {

        return repository.findBySanPhamChiTietIdAndDaXoaFalse(sanPhamChiTietId)
                .stream()
                .map(converter::toResponse)
                .toList();
    }

    // ================= UPLOAD FILE =================
    @Override
    public HinhAnhResponse upload(MultipartFile file, Long spctId, Boolean isMain) throws IOException {

        SanPhamChiTiet spct = spctRepository.findById(spctId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy SPCT"));

        // tạo folder nếu chưa có
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // đặt tên file unique
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, file.getBytes());

        HinhAnh ha = new HinhAnh();
        ha.setTen("http://localhost:8080/uploads/" + fileName);
        ha.setSanPhamChiTiet(spct);
        ha.setDaXoa(false);

        // 👉 nếu bạn có field isMain thì bật dòng này
        // ha.setIsMain(isMain);

        return converter.toResponse(repository.save(ha));
    }

    @Override
    public List<HinhAnhResponse> uploadMultiple(List<MultipartFile> files, Long spctId) throws IOException {
        SanPhamChiTiet spct = spctRepository.findById(spctId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy SPCT"));

        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        List<HinhAnh> savedImages = new java.util.ArrayList<>();

        for (MultipartFile file : files) {

            if (file == null || file.isEmpty()) continue;

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, file.getBytes());

            HinhAnh ha = new HinhAnh();
            ha.setTen("http://localhost:8080/uploads/" + fileName);
            ha.setSanPhamChiTiet(spct);
            ha.setDaXoa(false);

            savedImages.add(repository.save(ha));
        }

        return savedImages.stream()
                .map(converter::toResponse)
                .toList();
    }

    @Override
    public HinhAnhResponse setMainImage(Long imageId, Long spctId) {
        return null;
    }

    // ================= DELETE (SOFT) =================
    @Override
    public void delete(Long id) {

        HinhAnh entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hình ảnh"));

        entity.setDaXoa(true);
        repository.save(entity);
    }

    @Override
    public void deleteBySanPhamChiTietId(Long spctId) {
        List<HinhAnh> list = repository.findBySanPhamChiTietIdAndDaXoaFalse(spctId);

        for (HinhAnh img : list) {
            img.setDaXoa(true);
        }

        repository.saveAll(list);
    }
}