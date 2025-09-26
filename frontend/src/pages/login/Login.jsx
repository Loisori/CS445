import { Link } from "react-router-dom";

// import BG from "./assets/imgs/loginBG.png";

function Login() {
  return (
    <section className="py-[20rem]!">
      <div className="min-w-[60rem] min-h-[5rem] w-fit m-auto bg-secondary py-[3rem] px-[4rem] rounded-[2rem]">
        <form action="">
          <h2 htmlFor="" className="mb-[3rem] text-center font-bold">
            Đăng nhập
          </h2>
          <br />
          <div className="mb-[2rem]">
            <label htmlFor="Email" className="text-h3">
              Email
            </label>
            <br />
            <input
              id="Email"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              type="text"
              placeholder="your@gmail.com"
              required
            />
          </div>
          <br />
          <div className="mb-[2rem]">
            <label htmlFor="Password" className="text-h3">
              Mật Khẩu
            </label>
            <br />
            <input
            className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              id="Password"
              type="text"
              placeholder="Nhập mật khẩu"
              required
            />
          </div>
          <div>
            <button className="text-tertiary text-h4" id="js-forget-password">
              Quên mật khẩu?
            </button>
            <br />
            <button className="button--primary w-full! mb-[2rem]" id="js-login" type="submit">
              Đăng nhập
            </button>
            <Link to="/register" className="text-center">
              <p className="text-h5">
                Bạn chưa có tài khoản?
                <span className="text-tertiary!"> Đăng ký ngay</span>
              </p>
            </Link>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Login;
