-- =============================
-- DANH MỤC
-- =============================

INSERT INTO kieu_dang (ten) VALUES
(N'Trơn'),
(N'Oversize'),
(N'Thể thao'),
(N'Cổ tròn'),
(N'Basic');

INSERT INTO chat_lieu (ten) VALUES
(N'Cotton 100%'),
(N'Cotton 65/35'),
(N'Polyester'),
(N'Vải thun lạnh'),
(N'CVC');

INSERT INTO nha_cung_cap (ten) VALUES
(N'Local Brand A'),
(N'Local Brand B'),
(N'Unisex Shop'),
(N'Coolmate'),
(N'Yame');

INSERT INTO mau_sac (ten) VALUES
(N'Đen'),
(N'Trắng'),
(N'Xám'),
(N'Xanh'),
(N'Be');

INSERT INTO size (ten) VALUES
(N'S'),
(N'M'),
(N'L'),
(N'XL'),
(N'XXL');

-- =============================
-- SẢN PHẨM (ÁO PHÔNG)
-- =============================

INSERT INTO san_pham 
(ten, mo_ta, kieu_dang_id, chat_lieu_id, nha_cung_cap_id, trang_thai, created_at, updated_at)
VALUES
(N'Áo phông basic đen', N'Áo phông trơn dễ phối đồ', 5, 1, 1, 1, GETDATE(), GETDATE()),
(N'Áo phông oversize trắng', N'Form rộng trẻ trung', 2, 2, 2, 1, GETDATE(), GETDATE()),
(N'Áo phông thể thao', N'Thấm hút mồ hôi tốt', 3, 3, 3, 1, GETDATE(), GETDATE()),
(N'Áo phông cổ tròn xám', N'Phong cách đơn giản', 4, 1, 4, 1, GETDATE(), GETDATE()),
(N'Áo phông unisex xanh', N'Phù hợp nam nữ', 2, 5, 5, 1, GETDATE(), GETDATE());

-- =============================
-- CHI TIẾT SẢN PHẨM
-- =============================

INSERT INTO san_pham_chi_tiet 
(san_pham_id, mau_sac_id, size_id, gia, so_luong, trang_thai)
VALUES
(1, 1, 1, 150000, 50, 1),
(2, 2, 2, 180000, 40, 1),
(3, 4, 3, 200000, 30, 1),
(4, 3, 4, 170000, 35, 1),
(5, 5, 5, 190000, 25, 1);

-- =============================
-- QUERY
-- =============================

SELECT sp.ten, spct.gia, ms.ten AS mau, s.ten AS size
FROM san_pham_chi_tiet spct
JOIN san_pham sp ON sp.id = spct.san_pham_id
JOIN mau_sac ms ON ms.id = spct.mau_sac_id
JOIN size s ON s.id = spct.size_id;

-- =============================
-- TEST
-- =============================

SELECT * FROM san_pham;