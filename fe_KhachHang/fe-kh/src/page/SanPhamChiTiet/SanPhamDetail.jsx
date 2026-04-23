import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import sanPhamChiTietApi from "../../api/sanPhamChiTietApi";
import cartApi from "../../api/cartApi";
import "./SanPhamDetail.css";

export default function SanPhamDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [sp, setSp] = useState(null);
  const [soLuong, setSoLuong] = useState(1);
  const [loading, setLoading] = useState(false);

  const user = JSON.parse(localStorage.getItem("user"));

  // 🔥 load sản phẩm
  useEffect(() => {
    sanPhamChiTietApi
      .getById(id)
      .then((data) => setSp(data))
      .catch((err) => console.error(err));
  }, [id]);

  // ➕ thêm vào giỏ
  const handleAddToCart = async () => {
    if (!sp) return;

    if (!user || !user.id) {
      alert("Bạn cần đăng nhập");
      return;
    }

    if (soLuong < 1) {
      alert("Số lượng phải >= 1");
      return;
    }

    if (soLuong > sp.soLuong) {
      alert("Vượt quá số lượng tồn");
      return;
    }

    try {
      setLoading(true);

      await cartApi.add({
         chiTietSanPhamId: sp.id,
          soLuong: soLuong,
          taiKhoanId: user.id
      });

      alert("✅ Đã thêm vào giỏ hàng");
    } catch (err) {
      console.error(err);
      alert("❌ Lỗi thêm giỏ hàng");
    } finally {
      setLoading(false);
    }
  };

  // ⚡ mua ngay
  const handleBuyNow = async () => {
    await handleAddToCart();
    navigate("/gio-hang"); // ✅ FIX redirect
  };

  if (!sp) return <p>Loading...</p>;

  return (
    <div className="detail-container">
      {/* Ảnh */}
      <div className="detail-img">
        <img
          src={sp.hinhAnhs?.[0]?.ten || "/no-image.png"} // ✅ tránh null
          alt=""
        />
      </div>

      {/* Thông tin */}
      <div className="detail-info">
        <h2>{sp.sanPham?.ten}</h2>

        <p className="price">
          {sp.gia?.toLocaleString("vi-VN")} đ
        </p>

        <p>Kích cỡ: {sp.kichCo?.ten}</p>
        <p>Màu: {sp.mauSac?.ten}</p>

        <p>Số lượng còn: {sp.soLuong}</p>

        {/* chọn số lượng */}
        <div className="qty">
          <button
            onClick={() =>
              setSoLuong((prev) => Math.max(1, prev - 1))
            }
          >
            -
          </button>

          <span>{soLuong}</span>

          <button
            onClick={() =>
              setSoLuong((prev) =>
                Math.min(sp.soLuong, prev + 1)
              )
            }
          >
            +
          </button>
        </div>

        {/* action */}
        <div className="actions">
          <button
            className="add-cart"
            onClick={handleAddToCart}
            disabled={loading || sp.soLuong === 0}
          >
            {loading ? "Đang thêm..." : "Thêm vào giỏ"}
          </button>

          <button
            className="buy-now"
            onClick={handleBuyNow}
            disabled={sp.soLuong === 0}
          >
            Mua ngay
          </button>
        </div>
      </div>
    </div>
  );
}