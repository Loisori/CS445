import searchBar from "../components/SearchBar.jsx";

function Header() {
  return (
    <header>
      <div className="flex justify-between items-center">
        <picture className="" id="logo">
          <img src="" alt="" />
        </picture>
        {searchBar()}
        <div className="" id="login"></div>
      </div>
      <nav className="" id="nav">
        <ul className="flex gap-4">
          <li>
            <a href="#">Art</a>
          </li>
          <li>
            <a href="">Comics</a>
          </li>
          <li>
            <a href="#">Craft</a>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
