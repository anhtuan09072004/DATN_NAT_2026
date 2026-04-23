import { Link, useLocation, useNavigate } from "react-router-dom";
import "./Layout.css";
import logo from "./img/logoShop.png";

const menu = [
  { name: "Trang chủ", path: "/" },
  { name: "Sản phẩm", path: "/san-pham" },
  { name: "Tôi", path: "/tai-khoan" },
];

export default function Layout({ children }) {
  const location = useLocation();
  const navigate = useNavigate();

  const userStr = localStorage.getItem("user");

  let user = null;
  try {
    user = JSON.parse(userStr);
  } catch {
    user = null;
  }

  // 👉 LOGOUT
  const handleLogout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token"); // nếu có
    navigate("/login");
  };

  return (
    <div className="layout">
      {/* Header */}
      <header className="header">
        <div className="header-container">
          <img src={logo} alt="SHOP ÁO" className="logo-img" />

          <nav className="menu">
            {menu.map((item) => (
              <Link
                key={item.path}
                to={item.path}
                className={
                  location.pathname === item.path
                    ? "menu-item active"
                    : "menu-item"
                }
              >
                {item.name}
              </Link>
            ))}
          </nav>

          <div className="actions">
            <Link to="/gio-hang" className="btn">
              Giỏ hàng
            </Link>

            {/* 👇 LOGIC LOGIN / LOGOUT */}
            {!user ? (
              <Link to="/login" className="btn primary">
                Đăng nhập
              </Link>
            ) : (
              <>
                <span style={{ marginRight: "10px" }}>
                  Xin chào, {user.ten || user.username}
                </span>
                <button onClick={handleLogout} className="btn primary">
                  Logout
                </button>
              </>
            )}
          </div>
        </div>
      </header>

      {/* Content */}
      <main className="content">{children}</main>

      {/* Footer */}
      <footer className="footer">© 2026 SHOP ÁO</footer>
    </div>
  );
}