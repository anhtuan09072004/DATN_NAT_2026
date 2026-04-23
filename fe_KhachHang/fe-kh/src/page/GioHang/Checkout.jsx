import { useEffect, useState } from "react";
import cartApi from "../../api/cartApi";
import checkoutApi from "../../api/checkoutApi";
import diaChiApi from "../../api/diaChiApi";
import voucherApi from "../../api/voucherApi";
import "./Checkout.css";

export default function Checkout() {
  const [items, setItems] = useState([]);
  const [total, setTotal] = useState(0);

  const [addresses, setAddresses] = useState([]);
  const [vouchers, setVouchers] = useState([]);

  const [voucher, setVoucher] = useState(null);
  const [discount, setDiscount] = useState(0);

  const [form, setForm] = useState({
    tenKhachHang: "",
    sdt: "",
    email: "",
    phuongThuc: 1,
    diaChiCuThe: "",
    xa: "",
    huyen: "",
    tinh: "",
  });

  // ================= LOAD =================
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
      alert("Bạn chưa đăng nhập");
      return;
    }

    loadCart(user.id);
    loadAddresses();
    loadVouchers();

    setForm((prev) => ({
      ...prev,
      tenKhachHang: user.ten || "",
      sdt: user.soDienThoai || "",
      email: user.email || "",
    }));
  }, []);

  const loadCart = (userId) => {
    cartApi.getMyCart(userId).then((res) => {
      const mapped = res.items.map((item) => ({
        id: item.id,
        tenSanPham: item.tenSanPham,
        gia: item.gia,
        soLuong: item.soLuong,
      }));

      setItems(mapped);
      setTotal(res.total);
    });
  };

  const loadAddresses = async () => {
    try {
      const res = await diaChiApi.getMyAddresses();
      setAddresses(res || []);
    } catch (err) {
      console.error("Lỗi load địa chỉ", err);
    }
  };

  const loadVouchers = async () => {
    try {
      const res = await voucherApi.getAll();

      const now = new Date();

      // ✅ filter voucher hợp lệ
      const valid = res.filter(
        (v) =>
          v.trangThai === 1 &&
          (!v.ngayBatDau || new Date(v.ngayBatDau) <= now) &&
          (!v.ngayKetThuc || new Date(v.ngayKetThuc) >= now)
      );

      setVouchers(valid);
    } catch (err) {
      console.error("Lỗi load voucher", err);
    }
  };

  // ================= CHANGE =================
  const handleChange = (e) => {
    const { name, value } = e.target;

    setForm({
      ...form,
      [name]: name === "phuongThuc" ? Number(value) : value,
    });
  };

  // ================= ADDRESS =================
  const handleSelectAddress = (e) => {
    const id = e.target.value;

    const addr = addresses.find((a) => a.id === Number(id));
    if (!addr) return;

    setForm((prev) => ({
      ...prev,
      diaChiCuThe: addr.diaChiCuThe || "",
      xa: addr.phuongXa || "",
      huyen: addr.quanHuyen || "",
      tinh: addr.tinhThanh || "",
    }));
  };

  // ================= VOUCHER =================
  const handleSelectVoucher = (e) => {
    const id = e.target.value;

    const v = vouchers.find((x) => x.id === Number(id));

    if (!v) {
      setVoucher(null);
      setDiscount(0);
      return;
    }

    // ❌ không đủ điều kiện
    if (total < (v.giaTriToiThieu || 0)) {
      alert(`Đơn tối thiểu ${v.giaTriToiThieu?.toLocaleString()}đ`);
      setVoucher(null);
      setDiscount(0);
      return;
    }

    setVoucher(v);

    // ✅ tính giảm %
    let discountValue = (total * v.phanTramGiam) / 100;

    // ✅ giới hạn max
    if (v.giaTriToiDa && discountValue > v.giaTriToiDa) {
      discountValue = v.giaTriToiDa;
    }

    setDiscount(discountValue);
  };

  // ================= SUBMIT =================
  const handleSubmit = async () => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!form.tenKhachHang || !form.sdt) {
      alert("Thiếu thông tin");
      return;
    }

    try {
      const payload = {
        ...form,
        customerId: user.id,
        voucherId: voucher?.id || null,
      };

      const res = await checkoutApi.checkout(payload);

      alert("Đặt hàng thành công!");

      if (form.phuongThuc === 2 && res?.paymentUrl) {
        window.location.href = res.paymentUrl;
      } else {
        window.location.href = "/orders";
      }
    } catch (err) {
      alert(err.message);
    }
  };

  const shipFee = 30000;
  const finalTotal = Math.max(0, total + shipFee - discount);

  return (
    <div className="checkout-container">
      <h2>Thanh toán</h2>

      <div className="checkout-grid">
        {/* LEFT */}
        <div className="checkout-left">
          <h3>Thông tin nhận hàng</h3>

          {/* ADDRESS */}
          <select onChange={handleSelectAddress}>
            <option value="">-- Chọn địa chỉ --</option>
            {addresses.map((addr) => (
              <option key={addr.id} value={addr.id}>
                {addr.ten} - {addr.diaChiCuThe}, {addr.quanHuyen}
              </option>
            ))}
          </select>

          {/* VOUCHER */}
          <select onChange={handleSelectVoucher}>
            <option value="">-- Chọn voucher --</option>
            {vouchers.map((v) => (
              <option key={v.id} value={v.id}>
                {v.ma} - {v.phanTramGiam}% | Min{" "}
                {v.giaTriToiThieu?.toLocaleString()}đ | Max{" "}
                {v.giaTriToiDa?.toLocaleString()}đ
              </option>
            ))}
          </select>

          <input name="tenKhachHang" value={form.tenKhachHang} onChange={handleChange} />
          <input name="sdt" value={form.sdt} onChange={handleChange} />
          <input name="email" value={form.email} onChange={handleChange} />

          <input
            name="diaChiCuThe"
            value={form.diaChiCuThe}
            onChange={handleChange}
          />

          <div className="address-row">
            <input name="xa" value={form.xa} onChange={handleChange} />
            <input name="huyen" value={form.huyen} onChange={handleChange} />
            <input name="tinh" value={form.tinh} onChange={handleChange} />
          </div>

          <select name="phuongThuc" onChange={handleChange}>
            <option value={1}>COD</option>
            <option value={2}>Chuyển khoản</option>
          </select>
        </div>

        {/* RIGHT */}
        <div className="checkout-right">
          <h3>Đơn hàng của bạn</h3>

          {items.map((item) => (
            <div className="order-item" key={item.id}>
              <span>
                {item.tenSanPham} x{item.soLuong}
              </span>
              <span>
                {(item.gia * item.soLuong).toLocaleString()}đ
              </span>
            </div>
          ))}

          <hr />

          <div className="order-row">
            <span>Tạm tính</span>
            <span>{total.toLocaleString()}đ</span>
          </div>

          <div className="order-row">
            <span>Phí ship</span>
            <span>{shipFee.toLocaleString()}đ</span>
          </div>

          {discount > 0 && (
            <div className="order-row discount">
              <span>Giảm giá</span>
              <span>-{discount.toLocaleString()}đ</span>
            </div>
          )}

          <div className="order-row total">
            <span>Thanh toán</span>
            <span>{finalTotal.toLocaleString()}đ</span>
          </div>

          <button className="order-btn" onClick={handleSubmit}>
            Đặt hàng
          </button>
        </div>
      </div>
    </div>
  );
}