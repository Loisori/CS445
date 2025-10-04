import { Link } from "react-router-dom";

function Hero() {
  return (
    <>
      <section>
        <div className="wrapper text-center">
          <h1 className="mb-[3.5rem]">
            Nền tảng Quản lý Dự Án Đầu tư Chuyên nghiệp
          </h1>
          <p className="mb-[3.5rem] text-h3">
            Kết nối nhà đầu tư và chủ dự án một cách hiệu quả. Quản lý đầu tư
            thông minh với công nghệ hiện đại.
          </p>
          <div className="flex justify-center gap-[2rem] text-h6">
            <button className="button--primary">Bắt đầu ngay</button>
            <Link to="/project">
              <button className="button--secondary">Khám phá dự án</button>
            </Link>
          </div>
        </div>
      </section>
    </>
  );
}

export default Hero;
