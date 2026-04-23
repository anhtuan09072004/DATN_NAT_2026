const BASE_URL = "http://localhost:8080/api/client";

const checkoutApi = {
  // ================= CHECKOUT =================
  checkout: async (data) => {
    const res = await fetch(`${BASE_URL}/checkout`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    if (!res.ok) {
      const text = await res.text();
      throw new Error(text || "Lỗi checkout");
    }

    return res.json();
  },

  // ================= APPLY VOUCHER =================
  applyVoucher: async (code, total) => {
    const res = await fetch(
      `${BASE_URL}/voucher/apply?code=${code}&total=${total}`
    );

    if (!res.ok) {
      const text = await res.text();
      throw new Error(text || "Voucher không hợp lệ");
    }

    return res.json();
  },
};

export default checkoutApi;