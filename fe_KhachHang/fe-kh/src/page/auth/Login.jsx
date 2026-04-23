import { useState } from "react";
import { useNavigate } from "react-router-dom";
import authApi from "../../api/authApi";

export default function Login() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleLogin = async () => {
      try {
        const res = await authApi.login(form);
        console.log(res);

        // lưu user
        localStorage.setItem("user", JSON.stringify(res));

        // 🔥 thêm dòng này
        localStorage.setItem("userId", res.id);

        navigate("/");
      } catch (err) {
        alert(err.response?.data || err.message);
      }
    };
  return (
    <div style={{ width: "300px", margin: "100px auto" }}>
      <h2>Đăng nhập</h2>

      <input
        placeholder="Username"
        value={form.username}
        onChange={(e) =>
          setForm({ ...form, username: e.target.value })
        }
        onKeyDown={(e) => e.key === "Enter" && handleLogin()}
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        value={form.password}
        onChange={(e) =>
          setForm({ ...form, password: e.target.value })
        }
        onKeyDown={(e) => e.key === "Enter" && handleLogin()}
      />

      <br /><br />

      <button onClick={handleLogin} style={{ width: "100%" }}>
        Đăng nhập
      </button>

      <br /><br />

      {/* 👉 NÚT ĐĂNG KÝ */}
      <button
        onClick={() => navigate("/register")}
        style={{
          width: "100%",
          background: "#f0f0f0",
          border: "1px solid #ccc",
        }}
      >
        Đăng ký tài khoản mới
      </button>
    </div>
  );
}