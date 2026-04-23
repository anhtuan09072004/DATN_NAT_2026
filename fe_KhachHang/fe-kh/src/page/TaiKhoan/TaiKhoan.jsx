import { useEffect, useState } from "react";
import profileApi from "../../api/profileApi";
import diaChiApi from "../../api/diaChiApi";
import "./TaiKhoan.css";

export default function TaiKhoan() {
  const [form, setForm] = useState({
    ten: "",
    email: "",
    soDienThoai: "",
    gioiTinh: "",
    ngaySinh: "",
  });

  const emptyAddress = {
    ten: "",
    soDienThoai: "",
    tinhThanh: "",
    quanHuyen: "",
    phuongXa: "",
    diaChiCuThe: "",
  };

  const [addresses, setAddresses] = useState([]);
  const [selectedAddress, setSelectedAddress] = useState(null);
  const [loading, setLoading] = useState(true);

  // ================= LOAD DATA =================
  const loadAddresses = async () => {
    const addr = await diaChiApi.getMyAddresses();
    setAddresses(addr || []);
  };

  useEffect(() => {
    const loadData = async () => {
      try {
        const profile = await profileApi.getMyProfile();

        setForm({
          ten: profile.ten || "",
          email: profile.email || "",
          soDienThoai: profile.soDienThoai || "",
          gioiTinh: profile.gioiTinh || "",
          ngaySinh: profile.ngaySinh || "",
        });

        await loadAddresses();
      } catch (err) {
        alert("Lỗi load dữ liệu");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    loadData();
  }, []);

  // ================= PROFILE =================
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await profileApi.updateMyProfile(form);
      alert("Cập nhật thành công 🎉");
    } catch (err) {
      alert(err.message);
    }
  };

  // ================= ADDRESS =================
  const handleSelectAddress = (addr) => {
    setSelectedAddress({ ...addr });
  };

  const handleAddressChange = (e) => {
    if (!selectedAddress) return;

    setSelectedAddress({
      ...selectedAddress,
      [e.target.name]: e.target.value,
    });
  };

  const handleAddNew = () => {
    setSelectedAddress({ ...emptyAddress });
  };

  const handleSaveAddress = async () => {
    try {
      if (!selectedAddress) return;

      if (selectedAddress.id) {
        await diaChiApi.updateAddress(
          selectedAddress.id,
          selectedAddress
        );
      } else {
        await diaChiApi.createAddress(selectedAddress);
      }

      alert("Lưu địa chỉ thành công");

      await loadAddresses();
      setSelectedAddress(null);
    } catch (err) {
      alert("Lỗi lưu địa chỉ");
      console.error(err);
    }
  };

  const handleDeleteAddress = async (id) => {
    if (!window.confirm("Xóa địa chỉ này?")) return;

    try {
      await diaChiApi.deleteAddress(id);
      await loadAddresses();

      if (selectedAddress?.id === id) {
        setSelectedAddress(null);
      }
    } catch (err) {
      alert("Lỗi xóa");
    }
  };

  if (loading) return <p>Đang tải...</p>;

  return (
    <div className="profile-container">
      <h2>Tài khoản của tôi</h2>

      {/* PROFILE */}
      <form onSubmit={handleSubmit} className="profile-form">
        <input name="ten" value={form.ten} onChange={handleChange} />
        <input name="email" value={form.email} onChange={handleChange} />
        <input
          name="soDienThoai"
          value={form.soDienThoai}
          onChange={handleChange}
        />

        <select
          name="gioiTinh"
          value={form.gioiTinh}
          onChange={handleChange}
        >
          <option value="">-- Chọn --</option>
          <option value="Nam">Nam</option>
          <option value="Nữ">Nữ</option>
        </select>

        <input
          type="date"
          name="ngaySinh"
          value={form.ngaySinh || ""}
          onChange={handleChange}
        />

        <button type="submit">Lưu</button>
      </form>

      {/* ADDRESS */}
      <div className="address-container">
        {/* LIST */}
        <div className="address-list">
          <h3>Địa chỉ</h3>

          <button className="btn-add" onClick={handleAddNew}>
            + Thêm địa chỉ
          </button>

          {addresses.map((addr) => (
            <div
              key={addr.id}
              className={`address-card ${
                selectedAddress?.id === addr.id ? "active" : ""
              }`}
              onClick={() => handleSelectAddress(addr)}
            >
              <b>{addr.ten}</b>
              <p>{addr.soDienThoai}</p>
              <small>
                {addr.diaChiCuThe}, {addr.quanHuyen}, {addr.tinhThanh}
              </small>

              <button
                type="button"
                onClick={(e) => {
                  e.stopPropagation();
                  handleDeleteAddress(addr.id);
                }}
              >
                Xóa
              </button>
            </div>
          ))}
        </div>

        {/* FORM */}
        <div className="address-form">
          <h3>
            {selectedAddress
              ? selectedAddress.id
                ? "Chỉnh sửa địa chỉ"
                : "Thêm địa chỉ"
              : "Chọn địa chỉ"}
          </h3>

          {selectedAddress && (
            <>
              <input
                name="ten"
                placeholder="Tên"
                value={selectedAddress.ten}
                onChange={handleAddressChange}
              />

              <input
                name="soDienThoai"
                placeholder="SĐT"
                value={selectedAddress.soDienThoai}
                onChange={handleAddressChange}
              />

              <input
                name="tinhThanh"
                placeholder="Tỉnh"
                value={selectedAddress.tinhThanh}
                onChange={handleAddressChange}
              />

              <input
                name="quanHuyen"
                placeholder="Quận"
                value={selectedAddress.quanHuyen}
                onChange={handleAddressChange}
              />

              <input
                name="phuongXa"
                placeholder="Phường"
                value={selectedAddress.phuongXa}
                onChange={handleAddressChange}
              />

              <input
                name="diaChiCuThe"
                placeholder="Địa chỉ cụ thể"
                value={selectedAddress.diaChiCuThe}
                onChange={handleAddressChange}
              />

              <button type="button" onClick={handleSaveAddress}>
                Lưu địa chỉ
              </button>
            </>
          )}
        </div>
      </div>
    </div>
  );
}