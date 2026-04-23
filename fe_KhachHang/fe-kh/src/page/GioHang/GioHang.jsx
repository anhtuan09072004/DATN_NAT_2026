import { useEffect, useState } from "react";
import cartApi from "../../api/cartApi";
import "./GioHang.css";

export default function GioHang() {
  const [items, setItems] = useState([]);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const userStr = localStorage.getItem("user");

    if (!userStr) {
      alert("Bạn chưa đăng nhập");
      return;
    }

    let user;
    try {
      user = JSON.parse(userStr);
    } catch {
      console.error("Lỗi parse user");
      return;
    }

    if (!user?.id) {
      alert("User không hợp lệ");
      return;
    }

    loadCart(user.id);
  }, []);

  // 🔥 load giỏ hàng (FIX: res.items)
  const loadCart = (userId) => {
    cartApi
      .getMyCart(userId)
      .then((res) => {
        console.log("CART:", res);

        const mapped = res.items.map((item) => ({
          id: item.id,
          tenSanPham: item.tenSanPham,
          mau: item.mauSac,
          size: item.kichCo,
          gia: item.gia,
          anh: item.hinhAnh,
          soLuong: item.soLuong,
        }));

        setItems(mapped);
        setTotal(res.total); // 🔥 lấy từ BE
      })
      .catch((err) => console.error(err));
  };

  //  xử lý ảnh
  const getImageUrl = (img) => {
    if (!img) return "/no-image.png";
    if (img.startsWith("http")) return img;
    return `http://localhost:8080/uploads/${img}`;
  };

  // update số lượng (realtime + sync BE)
  const handleUpdate = (id, newQty) => {
    if (newQty < 0) return;

    cartApi
      .updateQuantity(id, newQty)
      .then(() => {
        // ⚡ update UI ngay
        setItems((prev) =>
          prev
            .map((i) =>
              i.id === id ? { ...i, soLuong: newQty } : i
            )
            .filter((i) => i.soLuong > 0)
        );

        // gọi lại BE để lấy total chuẩn
        const user = JSON.parse(localStorage.getItem("user"));
        loadCart(user.id);
      })
      .catch((err) => console.error(err));
  };

  // xoá sản phẩm
  const handleDelete = (id) => {
    cartApi
      .deleteItem(id)
      .then(() => {
        setItems((prev) => prev.filter((i) => i.id !== id));

        // 🔥 reload total
        const user = JSON.parse(localStorage.getItem("user"));
        loadCart(user.id);
      })
      .catch((err) => console.error(err));
  };

  return (
    <div className="cart-container">
      <h2>Giỏ hàng của bạn</h2>

      {items.length === 0 ? (
        <p>Chưa có sản phẩm</p>
      ) : (
        <>
          {items.map((item) => (
            <div className="cart-item" key={item.id}>
              {/* Ảnh */}
              <img
                src={getImageUrl(item.anh)}
                alt="product"
                className="cart-img"
              />

              <div className="cart-right">
                {/* Thông tin */}
                <div>
                  <h3>{item.tenSanPham}</h3>
                  <p className="cart-info">Màu: {item.mau}</p>
                  <p className="cart-info">Size: {item.size}</p>
                </div>

                {/* Số lượng + giá */}
                <div className="cart-bottom">
                  <div className="quantity">
                    <button
                      onClick={() =>
                        handleUpdate(item.id, item.soLuong - 1)
                      }
                    >
                      -
                    </button>

                    <span>{item.soLuong}</span>

                    <button
                      onClick={() =>
                        handleUpdate(item.id, item.soLuong + 1)
                      }
                    >
                      +
                    </button>
                  </div>

                  <span className="price">
                    {item.gia?.toLocaleString()} đ
                  </span>
                </div>

                {/* Xoá */}
                <button
                  className="delete-btn"
                  onClick={() => handleDelete(item.id)}
                >
                  Xóa
                </button>
              </div>
            </div>
          ))}

          {/* 🔥 Tổng tiền */}
          <div className="cart-total">
            <h3>
              Tổng tiền: <span>{total?.toLocaleString()} đ</span>
            </h3>

            {/* 🔥 NÚT THANH TOÁN */}
            <button
              className="checkout-btn"
              onClick={() => (window.location.href = "/checkout")}
            >
              Thanh toán
            </button>
          </div>
        </>
      )}
    </div>
  );
}