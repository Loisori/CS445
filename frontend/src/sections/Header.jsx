import Logo from "../assets/imgs/logo.png";

import { Link } from "react-router-dom";

function Header() {
  return (
    <header className="bg-primary">
      <div className="wrapper flex justify-between items-center py-[2.4rem]">
        <Link to="/">
          <div className="flex gap-[1rem]">
            <picture className="size-[5rem]"><img src={Logo} alt="" /></picture>
            <p className="my-auto">InvestPro</p>
          </div>
        </Link>
        <div>
          <ul className="flex gap-[3.2rem]">
            <li>Dự án</li>
            <li>Giới thiệu</li>
            <li>Liên hệ</li>
          </ul>
        </div>
        <div className="flex gap-[3.2rem]">
          <Link to="/login">
            <button className="button--secondary">Đăng nhập</button>
          </Link>
          <Link to="/register">
            <button className="button--primary">Đăng ký</button>
          </Link>
        </div>
      </div>
    </header>
  );
}

export default Header;
