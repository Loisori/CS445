<<<<<<< HEAD
import { Link } from "react-router-dom";
import Logo from "../assets/imgs/logo.png";

function Header() {
  return (
    <header className="bg-primary">
      <div className="wrapper flex justify-between items-center py-[2.4rem]">
        <Link to="/">
          <div className="flex gap-[1rem]">
            <picture className="size-[5rem]">
              <img src={Logo} alt="" />
            </picture>
            <p className="my-auto text-h3 font-bold">InvestPro</p>
          </div>
        </Link>
        <div>
          <ul className="flex gap-[3.2rem] text-h4">
            <Link to="/Project">
              <li>Dự án</li>
            </Link>
            <li>Giới thiệu</li>
            <li>Liên hệ</li>
          </ul>
        </div>
        <div className="flex gap-[3.2rem] text-h6">
          <Link to="/Login">
            <button className="button--secondary">Đăng nhập</button>
          </Link>
          <Link to="/Register">
            <button className="button--primary">Đăng ký</button>
          </Link>
        </div>
      </div>
=======
import searchBar from "../components/SearchBar.jsx";

function Header() {
  return (
    <header className="wrapper pt-[2.4rem] border-b-[.1rem]">
      <div className="flex justify-between items-center">
        <picture className="" id="logo">
          {/* <img src="" alt="" /> */}
          <p className="text-[2rem] font-bold">CrowdFund</p>
        </picture>
        {searchBar()}
        <div className="flex flex-wrap gap-[2rem] text-h5" id="login">
          <button className="border-[#C9C9C9] border-[.1rem] rounded-[1rem] py-[1.1rem] px-[1.5rem]">
            For Creators
          </button>
          <button>Log in</button>
        </div>
      </div>
      <nav className="pt-[1.2rem]" id="nav">
        <ul className="flex justify-center gap-4 text-h5">
          <li className="px-[.6rem] py-[1.8rem]">
            <a href="#">Art</a>
          </li>
          <li className="px-[.6rem] py-[1.8rem]">
            <a href="">Comics</a>
          </li>
          <li className="px-[.6rem] py-[1.8rem]">
            <a href="#">Craft</a>
          </li>
        </ul>
      </nav>
>>>>>>> parent of eda1b61 (update)
    </header>
  );
}

export default Header;
