import { Link } from "react-router-dom";

function Login() {
  return (
    <section>
      Login
      <div className="bg-secondary py-[3rem] px-[4rem] test">
        <form action="">
          <label htmlFor="" className="text-[4rem]">
            Đăng nhập
          </label>
          <br />
          <div>
            <label htmlFor="Email" className="text-h3">
              Email
            </label>
            <input type="text" placeholder="your@gmail.com" required />
          </div>
          <br />
          <div>
            <label htmlFor="Password" className="text-h3">
              Password
            </label>
            <input type="text" placeholder="Nhập mật khẩu" required />
          </div>
          <div>
            <button className="" id="js-forget-password">
              Quên mật khẩu?
            </button>
            <br />
            <button className="button--primary" id="js-login" type="submit">
              Đăng nhập
            </button>
            <Link to="/register">
              <p>
                Bạn chưa có tài khoản?
                <span className="text-primary">Đăng ký ngay</span>
              </p>
            </Link>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Login;