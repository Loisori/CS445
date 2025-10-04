import Logo from "../assets/imgs/logo.png";


function Footer() {
  return (
    <footer className="bg-primary">
      <div className="wrapper flex justify-between py-[5rem]">
        <div>
          <div className="flex gap-[2.5rem]">
            <picture className="size-[5rem]"><img src={Logo} alt="" /></picture>
            <span className="text-h3 font-bold">InvestPro</span>
          </div>
          <p className="text-h5">
            Nền tảng quản lý dự án đầu tư hàng đầu Việt Nam
          </p>
        </div>
        <div className="flex gap-[10rem]">
          <div>
            <h5 className="font-bold">Sản phẩm</h5>
            <ul className="text-h5">
              <li>Dự án</li>
              <li>Dashboard</li>
              <li>Phân tích</li>
            </ul>
          </div>
          <div>
            <h5 className="font-bold">Hỗ trợ</h5>
            <ul className="text-h5">
              <li>Trung tâm trợ giúp</li>
              <li>Liên hệ</li>
              <li>FAQ</li>
            </ul>
          </div>
          <div>
            <h5 className="font-bold">Pháp lý</h5>
            <ul className="text-h5">
              <li>Chính sách bảo mật</li>
              <li>Điều khoản sử dụng</li>
            </ul>
          </div>
        </div>
      </div>
      <div className="wrapper">
        <p className="py-[1rem] border-t-[.1rem] text-h5 text-center">© 2025 InvestPro. Tất cả quyền được bảo lưu.</p>
      </div>
    </footer>
  );
}

export default Footer;
