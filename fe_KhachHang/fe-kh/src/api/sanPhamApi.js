const BASE_URL = "http://localhost:8080/api/client/san-pham";

const sanPhamApi = {
  
  // 🔥 Lấy top 8 sản phẩm (trang chủ)
  getTop8: async () => {
    const res = await fetch(`${BASE_URL}/top8`);
    if (!res.ok) throw new Error("Lỗi khi lấy top 8 sản phẩm");
    return res.json();
  },

  // 🔥 Lấy tất cả sản phẩm
  getAll: async () => {
    const res = await fetch(BASE_URL);
    if (!res.ok) throw new Error("Lỗi khi lấy danh sách sản phẩm");
    return res.json();
  },

  // 🔥 Lấy chi tiết sản phẩm
  getById: async (id) => {
    const res = await fetch(`${BASE_URL}/${id}`);
    if (!res.ok) throw new Error("Không tìm thấy sản phẩm");
    return res.json();
  }

};

export default sanPhamApi;