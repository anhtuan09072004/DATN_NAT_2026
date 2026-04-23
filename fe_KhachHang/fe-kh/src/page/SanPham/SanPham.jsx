import { useEffect, useState } from "react";
import sanPhamChiTietApi from "../../api/sanPhamChiTietApi";
// import "./SanPham.css";
import { useNavigate } from "react-router-dom";

export default function SanPham() {
  const [list, setList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const navigate = useNavigate();
  const pageSize = 8; // 🔥 mỗi trang 8 sp

  useEffect(() => {
    sanPhamChiTietApi.getAll()
      .then(data => setList(data))
      .catch(err => console.error(err));
  }, []);

  // 🔥 tính toán phân trang
  const totalPage = Math.ceil(list.length / pageSize);

  const start = (currentPage - 1) * pageSize;
  const currentData = list.slice(start, start + pageSize);

  return (
    <div className="sp-container">
      <h2 className="title">Tất cả sản phẩm</h2>

      <div className="product-list">
        {currentData.map(p => (
          <div className="product-card" key={p.id}  onClick={() => navigate(`/san-pham/${p.id}`)}>
            
            <img
              src={p.hinhAnhs?.[0]?.ten}
              alt=""
              className="product-img"
            />

            <div className="product-info">
              <h4>{p.sanPham?.ten}</h4>
              <p className="price">
                {p.gia?.toLocaleString()} đ
              </p>
            </div>

          </div>
        ))}
      </div>

      {/* 🔥 Pagination */}
      <div className="pagination">
        <button
          disabled={currentPage === 1}
          onClick={() => setCurrentPage(currentPage - 1)}
        >
          ← Trước
        </button>

        <span>Trang {currentPage} / {totalPage}</span>

        <button
          disabled={currentPage === totalPage}
          onClick={() => setCurrentPage(currentPage + 1)}
        >
          Sau →
        </button>
      </div>
    </div>
  );
}