
export default function OrderTabs({
  hoaDons,
  activeHoaDonId,
  switchHoaDon,
  createHoaDon,
}) {
  if (!hoaDons || hoaDons.length === 0) {
    return (
      <div className="flex justify-center my-4">
        <button
          onClick={createHoaDon}
          className="bg-green-500 hover:bg-green-600 text-white px-5 py-2 rounded-xl shadow"
        >
          + Tạo hóa đơn
        </button>
      </div>
    );
  }

  return (
    <div className="flex gap-2 mb-2 items-center">
      {hoaDons.map((o, index) => (
        <button
          key={o.id}
          onClick={() => switchHoaDon(o.id)} // ✅ đúng function
          className={`px-3 py-1 rounded-lg transition ${
            o.id === activeHoaDonId
              ? "bg-blue-500 text-white shadow"
              : "bg-white border hover:bg-gray-100"
          }`}
        >
          HĐ {index + 1}
        </button>
      ))}

      {/* ADD */}
      <button
        onClick={createHoaDon}
        className="bg-green-500 hover:bg-green-600 text-white px-3 py-1 rounded-lg"
      >
        + HĐ
      </button>
    </div>
  );
}