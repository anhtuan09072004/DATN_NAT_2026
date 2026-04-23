import { useEffect, useState } from "react";
import voucherApi from "../../../api/voucherApi";
import khachHangApi from "../../../api/customerApi";
import addressDataJson from "../../../data/address.json";

export default function Payment({ hoaDon, thanhToan }) {
  const [form, setForm] = useState({
     customerId: "",
    voucherId: "",
    phuongThuc: 1,
    tienKhachDua: "",
    kieuHoaDon: 0,
    tenKhachHang: "",
    sdt: "",
    email: "",
    tinh: "",
    huyen: "",
    xa: "",
    diaChiCuThe: "",
    ghiChu: ""
  });

  const [tongTien, setTongTien] = useState(0);
  const [tienGiam, setTienGiam] = useState(0);
  const [thanhTien, setThanhTien] = useState(0);

  const [vouchers, setVouchers] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [searchKH, setSearchKH] = useState("");

  const [provinces, setProvinces] = useState([]);
  const [districts, setDistricts] = useState([]);
  const [wards, setWards] = useState([]);

  //================= LOAD DATA =================
  useEffect(() => {
    const loadVoucher = async () => {
        try {
        const res = await voucherApi.getAll();

        const data =
          res?.data?.content ||
          res?.data ||
          res ||
          [];

        // 🔥 CHỈ LẤY voucher trạng thái = 1
        const activeVouchers = Array.isArray(data)
          ? data.filter((v) => v.trangThai === 1)
          : [];

        setVouchers(activeVouchers);

      } catch (e) {
        console.error("Lỗi load voucher:", e);
        setVouchers([]);
      }
    };

    const loadCustomers = async () => {
      try {
        const res = await khachHangApi.getAll();
        setCustomers(res.data || []); // 🔥 FIX axios
      } catch (e) {
        console.error("Lỗi khách hàng", e);
        setCustomers([]);
      }
    };

    loadVoucher();
    loadCustomers();
    setProvinces(addressDataJson);
    
  }, []);

  // ================= FILTER KH =================
  const filteredCustomers = Array.isArray(customers)
    ? customers.filter((c) =>
        c.ten?.toLowerCase().includes(searchKH.toLowerCase())
      )
    : [];

  // ================= TÍNH TIỀN =================
  useEffect(() => {
    if (!hoaDon) return;

    const tong =
      hoaDon.hoaDonChiTiets?.reduce(
        (sum, i) => sum + i.gia * i.soLuong,
        0
      ) || 0;

    let giam = 0;

    const voucher = vouchers.find(
      (v) => String(v.id) === String(form.voucherId)
    );

    if (voucher) {
      if (voucher.giaTriToiThieu && tong < voucher.giaTriToiThieu) {
        giam = 0;
      } else {
        if (voucher.phanTramGiam) {
          giam = (tong * voucher.phanTramGiam) / 100;
        }

        if (voucher.giaTriToiDa && giam > voucher.giaTriToiDa) {
          giam = voucher.giaTriToiDa;
        }
      }
    }

    setTongTien(tong);
    setTienGiam(giam);
    setThanhTien(tong - giam);
  }, [hoaDon, form.voucherId, vouchers]);

  // ================= CHANGE =================
  const handleChange = (key, value) => {
    if (key === "voucherId") {
      const voucher = vouchers.find(
        (v) => String(v.id) === String(value)
      );

      if (voucher && voucher.giaTriToiThieu && tongTien < voucher.giaTriToiThieu) {
        alert(`Đơn tối thiểu ${voucher.giaTriToiThieu.toLocaleString()} đ`);
      }
    }

    setForm((prev) => ({ ...prev, [key]: value }));
  };
  //==================Dia chi================
  const handleProvinceChange = (value) => {
  const province = provinces.find(p => p.name === value);

      setDistricts(province?.districts || []);
      setWards([]);

      setForm(prev => ({
        ...prev,
        tinh: value,
        huyen: "",
        xa: ""
      }));
    };

    const handleDistrictChange = (value) => {
      const district = districts.find(d => d.name === value);

      setWards(district?.wards || []);

      setForm(prev => ({
        ...prev,
        huyen: value,
        xa: ""
      }));
    };

    const handleWardChange = (value) => {
      setForm(prev => ({
        ...prev,
        xa: value
      }));
    };

// ================= SUBMIT =================
const handleSubmit = async () => {
  try {
    
    if (!hoaDon?.id) return alert("Không có hóa đơn");

    // 🔥 VALIDATE TỒN KHO
    const loiSanPham = hoaDon.hoaDonChiTiets?.find(
      (i) => i.soLuong > i.soLuongTon
    );

    if (loiSanPham) {
      return alert(
        `Sản phẩm "${loiSanPham.tenSanPham}" chỉ còn ${loiSanPham.soLuongTon} sản phẩm`
      );
    }

    // 🔥 validate địa chỉ
    if (form.kieuHoaDon === 1) {
      if (!form.tinh || !form.huyen || !form.xa || !form.diaChiCuThe) {
        return alert("Nhập đủ địa chỉ");
      }
    }

    if (form.phuongThuc === 1 && !form.tienKhachDua) {
      return alert("Nhập tiền khách đưa");
    }


    if (form.kieuHoaDon === 1) {
      if (!form.tinh || !form.huyen || !form.xa || !form.diaChiCuThe) {
        return alert("Nhập đủ địa chỉ");
      }
    }

    if (form.phuongThuc === 1 && !form.tienKhachDua) {
      return alert("Nhập tiền khách đưa");
    }

    // 🔥 xác định khách lẻ hay không
    const isGuest = !form.customerId;

    const data = {
      hoaDonId: hoaDon.id,

      // 🔥 fallback chuẩn
      customerId: isGuest ? 1 : Number(form.customerId),

      voucherId: form.voucherId ? Number(form.voucherId) : null,
      phuongThuc: form.phuongThuc,
      tienKhachDua: Number(form.tienKhachDua || 0),
      kieuHoaDon: form.kieuHoaDon,

      // 🔥 nếu khách lẻ → set mặc định
      tenKhachHang: isGuest ? "Khách lẻ" : form.tenKhachHang,
      sdt: isGuest ? "" : form.sdt,
      email: isGuest ? "" : form.email,

      tinh: form.tinh,
      huyen: form.huyen,
      xa: form.xa,
      diaChiCuThe: form.diaChiCuThe,
      ghiChu: form.ghiChu
    };

    console.log("🚀 DATA GỬI:", data);

    await thanhToan(data);
  } catch (e) {
    console.error(e);
    alert("Lỗi thanh toán");
  }
};
  // ================= UI =================
  return (
    <div className="payment">
      <h3>💳 Thanh toán</h3>

      {/* ===== KHÁCH HÀNG ===== */}
      <div style={{ border: "1px solid #ccc", padding: 10 }}>
        <p>👤 Khách hàng</p>

        <input
          placeholder="Tìm tên khách..."
          value={searchKH}
          onChange={(e) => setSearchKH(e.target.value)}
        />

        <select
           value={form.customerId || ""} // 🔥 mặc định hiển thị id = 1
            onChange={(e) => {
              const value = e.target.value;

              const kh = customers.find(
                (c) => String(c.id) === String(value)
              );

              if (kh) {
                setForm((prev) => ({
                  ...prev,
                  customerId: kh.id,
                  tenKhachHang: kh.ten || "",
                  sdt: kh.soDienThoai || "",
                  email: kh.email || ""
                }));
              }
            }}
          >
            
          {/* <option value="">-- Chọn khách hàng --</option> */}
          {filteredCustomers.map((c) => (
            <option key={c.id} value={c.id}>
              {c.ten} - {c.sdt}
            </option>
          ))}
        </select>

        <input
          placeholder="Tên khách"
          value={form.tenKhachHang}
          onChange={(e) => handleChange("tenKhachHang", e.target.value)}
        />
        <input
          placeholder="SĐT"
          value={form.sdt}
          onChange={(e) => handleChange("sdt", e.target.value)}
        />
        <input
          placeholder="Email"
          value={form.email}
          onChange={(e) => handleChange("email", e.target.value)}
        />
      </div>

      {/* ===== KIỂU HÓA ĐƠN ===== */}
      <select
        value={form.kieuHoaDon}
        onChange={(e) => handleChange("kieuHoaDon", Number(e.target.value))}
      >
        <option value={0}>Tại quầy</option>
        <option value={1}>Giao hàng</option>
      </select>

      {/* ===== ĐỊA CHỈ ===== */}
     {form.kieuHoaDon === 1 && (
        <>
          <select
            value={form.tinh}
            onChange={(e) => handleProvinceChange(e.target.value)}
          >
            <option value="">-- Chọn tỉnh --</option>
            {provinces.map((p) => (
              <option key={p.name} value={p.name}>
                {p.name}
              </option>
            ))}
          </select>

          <select
            value={form.huyen}
            onChange={(e) => handleDistrictChange(e.target.value)}
          >
            <option value="">-- Chọn huyện --</option>
            {districts.map((d) => (
              <option key={d.name} value={d.name}>
                {d.name}
              </option>
            ))}
          </select>

          <select
            value={form.xa}
            onChange={(e) => handleWardChange(e.target.value)}
          >
            <option value="">-- Chọn xã --</option>
            {wards.map((w) => (
              <option key={w.name} value={w.name}>
                {w.name}
              </option>
            ))}
          </select>

          <input
            placeholder="Địa chỉ cụ thể"
            value={form.diaChiCuThe}
            onChange={(e) => handleChange("diaChiCuThe", e.target.value)}
          />
        </>
      )}

      {/* VOUCHER */}
        <select
          value={form.voucherId}
          onChange={(e) => handleChange("voucherId", e.target.value)}
        >
          <option value="">-- Chọn voucher --</option>
          {vouchers.map((v) => (
            <option key={v.id} value={v.id}>
              {v.ten} - giảm {v.phanTramGiam}%
            </option>
          ))}
        </select>
      {/* ===== THANH TOÁN ===== */}
      <select onChange={(e) => handleChange("phuongThuc", Number(e.target.value))}>
        <option value={1}>Tiền mặt</option>
        <option value={2}>Chuyển khoản</option>
      </select>

      {form.phuongThuc === 1 && (
        <input
          placeholder="Tiền khách đưa"
          onChange={(e) => handleChange("tienKhachDua", e.target.value)}
        />
      )}

      {/* ===== TIỀN ===== */}
      <p>💰 Tổng: {tongTien.toLocaleString()} đ</p>
      <p>🎁 Giảm: {tienGiam.toLocaleString()} đ</p>
      <p>🧾 Cần trả: {thanhTien.toLocaleString()} đ</p>

      <button onClick={handleSubmit}>Thanh toán</button>
    </div>
  );
}