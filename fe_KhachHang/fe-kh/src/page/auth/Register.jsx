import { useState } from "react";
import { useNavigate } from "react-router-dom";
import customerApi from "../../api/customerApi";

export default function Register() {
  const [form, setForm] = useState({
    ten: "",
    username: "",
    password: "",
    email: "",
    soDienThoai: "",
  });

  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleChange = (key, value) => {
    setForm((prev) => ({ ...prev, [key]: value }));
  };

  const validate = () => {
    if (!form.ten.trim()) return "Tên không được để trống";
    if (!form.username.trim()) return "Username không được để trống";
    if (!form.password.trim()) return "Password không được để trống";

    if (form.password.length < 6) {
      return "Password phải >= 6 ký tự";
    }

    if (form.email && !/^\S+@\S+\.\S+$/.test(form.email)) {
      return "Email không hợp lệ";
    }

    if (form.soDienThoai && !/^\d{9,11}$/.test(form.soDienThoai)) {
      return "SĐT không hợp lệ";
    }

    return null;
  };

  const handleRegister = async () => {
    const error = validate();
    if (error) {
      alert(error);
      return;
    }

    try {
      setLoading(true);

      await customerApi.create(form);

      alert("🎉 Đăng ký thành công");
      navigate("/login");
    } catch (err) {
      console.error(err);

      // ưu tiên message từ backend
      const message =
        err.response?.data || err.message || "Đăng ký thất bại";

      alert(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ width: 350, margin: "80px auto" }}>
      <h2>📝 Đăng ký khách hàng</h2>

      <input
        placeholder="Tên"
        value={form.ten}
        onChange={(e) => handleChange("ten", e.target.value)}
      />
      <br /><br />

      <input
        placeholder="Username"
        value={form.username}
        onChange={(e) => handleChange("username", e.target.value)}
      />
      <br /><br />

      <input
        type="password"
        placeholder="Password"
        value={form.password}
        onChange={(e) => handleChange("password", e.target.value)}
      />
      <br /><br />

      <input
        placeholder="Email"
        value={form.email}
        onChange={(e) => handleChange("email", e.target.value)}
      />
      <br /><br />

      <input
        placeholder="SĐT"
        value={form.soDienThoai}
        onChange={(e) => handleChange("soDienThoai", e.target.value)}
      />
      <br /><br />

      <button
        onClick={handleRegister}
        disabled={loading}
        style={{ width: "100%" }}
      >
        {loading ? "Đang xử lý..." : "Đăng ký"}
      </button>

      <br /><br />

      <button
        onClick={() => navigate("/login")}
        style={{ width: "100%", background: "#eee" }}
      >
        Quay lại đăng nhập
      </button>
    </div>
  );
}