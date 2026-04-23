import { useEffect, useState } from "react";
import thongKeApi from "../../api/thongKeApi";
import "./ThongKe.css";

export default function ThongKe() {
  const [data, setData] = useState({});
  const [topBanChay, setTopBanChay] = useState([]);
  const [topSapHet, setTopSapHet] = useState([]);

  const [filter, setFilter] = useState({
    fromDate: "",
    toDate: "",
  });

  // ================= LOAD =================
  useEffect(() => {
    loadHomNay();
    loadTop();
  }, []);

  const loadHomNay = async () => {
    const res = await thongKeApi.homNay();
    setData(res);
  };

  const loadTheoKhoang = async () => {
    if (!filter.fromDate || !filter.toDate) {
      alert("Chọn ngày");
      return;
    }

    const res = await thongKeApi.theoKhoang(filter);
    setData(res);
  };

  const loadTop = async () => {
    const banChay = await thongKeApi.topBanChay();
    const sapHet = await thongKeApi.topSapHet();

    setTopBanChay(banChay);
    setTopSapHet(sapHet);
  };

  // ================= UI =================
  return (
    <div className="tk-container">
      <h2>Thống kê</h2>

      {/* FILTER */}
      <div className="tk-filter">
        <input
          type="date"
          onChange={(e) =>
            setFilter({ ...filter, fromDate: e.target.value })
          }
        />
        <input
          type="date"
          onChange={(e) =>
            setFilter({ ...filter, toDate: e.target.value })
          }
        />

        <button onClick={loadTheoKhoang}>Lọc</button>
        <button onClick={loadHomNay}>Hôm nay</button>
      </div>

      {/* CARD */}
      <div className="tk-cards">
        <div className="tk-card">
          <h4>Doanh thu</h4>
          <p>{(data.doanhThu || 0).toLocaleString()} đ</p>
        </div>

        <div className="tk-card">
          <h4>Tổng đơn</h4>
          <p>{data.tongDon || 0}</p>
        </div>
      </div>

      {/* TOP */}
      <div className="tk-grid">
        {/* TOP BÁN */}
        <div className="tk-box">
          <h3>Top 5 bán chạy</h3>
          {topBanChay.map((item, i) => (
            <div key={i} className="tk-item" style={{
                color: item.soLuongTon <= 5 ? "red" : "black",
                fontWeight: item.soLuongTon <= 5 ? "bold" : "normal",
            }}>
              <span>{item.tenSanPham}</span>
              <b>{item.soLuongBan}</b>
            </div>
          ))}
        </div>

        {/* TOP SẮP HẾT */}
        <div className="tk-box">
          <h3>Sắp hết hàng</h3>
          {topSapHet.map((item, i) => (
            <div key={i} className="tk-item">
              <span>{item.tenSanPham}</span>
              <b>{item.soLuongTon}</b>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}