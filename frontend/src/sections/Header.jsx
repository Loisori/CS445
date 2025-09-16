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
    </header>
  );
}

export default Header;
