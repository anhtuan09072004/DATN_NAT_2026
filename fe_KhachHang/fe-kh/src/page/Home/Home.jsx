import { useEffect, useState } from "react";
import banner from "../../component/img/banner1.jpg";
import sanPhamChiTietApi from "../../api/sanPhamChiTietApi";
import noImage from "../../component/img/no-image.png";
import { useNavigate } from "react-router-dom";
import "./Home.css";

export default function Home() {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();
  
  useEffect(() => {
    sanPhamChiTietApi.getTop8()
      .then(data => setProducts(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <img src={banner} alt="Banner" className="banner" />

      <h2 className="title">Sản phẩm nổi bật</h2>

      <div className="product-list">
        {products.map(p => (
          <div className="product-card" key={p.id} onClick={() => navigate(`/san-pham/${p.id}`)}>
            
            <img
              src={
                p.hinhAnhs?.[0]?.ten || noImage
              }
              alt={p.ten}
              className="product-img"
            />

            <div className="product-info">
              <h4>{p.sanPham?.ten || p.ten}</h4>
              <p className="price">
                {p.gia ? p.gia.toLocaleString() + " đ" : "Liên hệ"}
              </p>
            </div>

          </div>
        ))}
      </div>
    </div>
  );
  
}