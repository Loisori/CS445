import { Link } from "react-router-dom";

function Ready() {
  return (
    <section>
      <div className="wrapper">
        <div className="bg-tertiary py-[4rem] rounded-[2rem]">
          <h2 className="my-[3rem] text-center text-white!">
            Sẵn sàng bắt đầu hành trình đầu tư
          </h2>
          <p className="text-center text-h3 text-white!">
            Tham gia cùng hàng nghìn nhà đầu tư và chủ dự án đã tin tưởng
            InvestPro
          </p>
          <Link to="/register">
            <button className="button--primary bg-[#B4B52D]! mx-auto mt-[2rem] block text-h6">
              Đăng ký miễn phí
            </button>
          </Link>
        </div>
      </div>
    </section>
  );
}

export default Ready;
