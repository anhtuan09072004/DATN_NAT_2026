import { Link, useLocation } from "react-router-dom";
import logo from "./img/logoShop.png";

const menu = [
  { name: "Bán hàng", path: "/ban-hang" },
  { name: "Sản phẩm", path: "/san-pham" },
  { name: "Chất liệu", path: "/chat-lieu" },
  { name: "CoAo", path: "/co-ao" },
  { name: "TayAo", path: "/tay-ao" },
  { name: "Màu sắc", path: "/mau-sac" },
  { name: "Kích cỡ", path: "/kich-co" },
  { name: "Thương hiệu", path: "/thuong-hieu" },
  { name: "Xuất xứ", path: "/xuat-xu" },
  { name: "Voucher", path: "/voucher" },
  { name: "Khách hàng", path: "/khach-hang" },
  { name: "Nhân viên", path: "/nhan-vien" },
  { name: "Chức vụ", path: "/chuc-vu" },
  { name: "Hóa đơn", path: "/hoa-don" },
  { name: "Thống kê", path: "/thong-ke" },




  

];

export default function Layout({ children }) {
  const location = useLocation();

  return (
    <div className="flex h-screen bg-gray-100">
      
      {/* Sidebar */}
      <div className="w-64 bg-slate-900 text-white p-5">
         <img src={logo} alt="SHOP ÁO" className="logo-img"   className="logo-img mb-4"/>

        <ul className="space-y-2">
          {menu.map((item) => (
            <li key={item.path}>
              <Link
                to={item.path}
                className={`block px-3 py-2 rounded-lg transition ${
                  location.pathname === item.path
                    ? "bg-blue-500"
                    : "hover:bg-slate-700"
                }`}
              >
                {item.name}
              </Link>
            </li>
          ))}
        </ul>
      </div>

      {/* Main */}
      <div className="flex-1 flex flex-col">
        
        {/* Header */}
        <div className="bg-white shadow px-6 py-3 flex justify-between items-center">
          <h1 className="font-semibold text-lg">Quản lý cửa hàng</h1>
          <button className="bg-red-500 text-white px-4 py-1 rounded-lg">
            Logout
          </button>
        </div>

        {/* Content */}
        <div className="p-6 overflow-auto">
          {children}
        </div>
      </div>
    </div>
  );
}