package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.HinhAnhRequest;
import com.example.Backend_2026.infrastructure.response.HinhAnhResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HinhAnhService {

    HinhAnhResponse create(HinhAnhRequest request);

    List<HinhAnhResponse> getBySanPhamChiTiet(Long sanPhamChiTietId);

    HinhAnhResponse upload(MultipartFile file, Long spctId, Boolean isMain) throws IOException;

    List<HinhAnhResponse> uploadMultiple(List<MultipartFile> files, Long spctId) throws IOException;

    HinhAnhResponse setMainImage(Long imageId, Long spctId);

    void delete(Long id);

    void deleteBySanPhamChiTietId(Long spctId);
}
